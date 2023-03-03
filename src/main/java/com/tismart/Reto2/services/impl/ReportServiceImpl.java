package com.tismart.Reto2.services.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tismart.Reto2.commons.JasperReportManager;
import com.tismart.Reto2.enums.TypeReportEnum;
import com.tismart.Reto2.models.ReportDTO;
import com.tismart.Reto2.services.IReportService;

import net.sf.jasperreports.engine.JRException;

@Service
public class ReportServiceImpl implements IReportService{
	
	@Autowired
	private JasperReportManager reportManager;
	
	@Autowired
	private DataSource dataSource;

	@Override
	public ReportDTO getReport(Map<String, Object> params, String reportName) throws JRException, IOException, SQLException {
		String fileName = reportName;
		ReportDTO dto = new ReportDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TypeReportEnum.EXCEL.name()) ? ".xlsx" : ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), null,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}

}

