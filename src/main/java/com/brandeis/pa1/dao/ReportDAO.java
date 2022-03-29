package com.brandeis.pa1.dao;

import java.sql.Timestamp;
import java.util.List;

import com.brandeis.pa1.entity.Report;

public interface ReportDAO {
	int save(int sensor_id,Timestamp report_time,double temperature);

	List<Report> getAll();
}
