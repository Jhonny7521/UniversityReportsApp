package com.tismart.Reto2.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tismart.Reto2.enums.TypeReportEnum;
import com.tismart.Reto2.models.ReportDTO;
import com.tismart.Reto2.services.IReportService;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private IReportService reportService;	
	
	@GetMapping("/download/report1")
	public ResponseEntity<Resource> downloadReport1(@RequestParam Map<String, Object> params)
			throws JRException, IOException, SQLException {
		ReportDTO dto = reportService.getReport(params, "reporte1");

		InputStreamResource streamResource = new InputStreamResource(dto.getStream());
		MediaType mediaType = null;
		if (params.get("tipo").toString().equalsIgnoreCase(TypeReportEnum.EXCEL.name())) {
			mediaType = MediaType.APPLICATION_OCTET_STREAM;
		} else {
			mediaType = MediaType.APPLICATION_PDF;
		} 

		return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
				.contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
	}
	
	@GetMapping("/download/report2")
	public ResponseEntity<Resource> downloadReport2(@RequestParam Map<String, Object> params)
			throws JRException, IOException, SQLException {
		ReportDTO dto = reportService.getReport(params, "reporte2");

		InputStreamResource streamResource = new InputStreamResource(dto.getStream());
		MediaType mediaType = null;
		if (params.get("tipo").toString().equalsIgnoreCase(TypeReportEnum.EXCEL.name())) {
			mediaType = MediaType.APPLICATION_OCTET_STREAM;
		} else {
			mediaType = MediaType.APPLICATION_PDF;
		} 

		return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
				.contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
	}
	
}
