package com.tismart.Reto2.commons;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.tismart.Reto2.enums.TypeReportEnum;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Component
public class JasperReportManager {

	private static final String REPORT_FOLDER = "reports";

	private static final String JASPER = ".jasper";

	public ByteArrayOutputStream export(String fileName, String tipoReporte, Map<String, Object> params,
			Connection con) throws JRException, IOException {

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ClassPathResource resource = new ClassPathResource(REPORT_FOLDER + File.separator + fileName + JASPER);
		
		String initDateString = (String) params.get("initDate");
		String endDateString = (String) params.get("endDate");
		
		if (initDateString != null && endDateString != null) {
			DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate initDate = LocalDate.parse(initDateString, inputFormatter);
			LocalDate endDate = LocalDate.parse(endDateString, inputFormatter);
			String formattedInitDate = initDate.format(outputFormatter);
			String formattedEndDate = endDate.format(outputFormatter);
			
			params.put("initDate", formattedInitDate);
			params.put("endDate", formattedEndDate);			
		}
		
		InputStream inputStream = resource.getInputStream();
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, params, con);
		if (tipoReporte.equalsIgnoreCase(TypeReportEnum.EXCEL.toString())) {
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(stream));
			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			configuration.setDetectCellType(true);
			configuration.setCollapseRowSpan(true);
			exporter.setConfiguration(configuration);
			exporter.exportReport();
		} else {
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		}

		return stream;
	}
	
	

}

