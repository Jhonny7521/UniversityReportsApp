package com.tismart.Reto2.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.tismart.Reto2.models.ReportDTO;

import net.sf.jasperreports.engine.JRException;

public interface IReportService {

	ReportDTO getReport(Map<String, Object> params, String reportName) throws JRException, IOException, SQLException;
}
