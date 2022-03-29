package com.brandeis.pa1.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.brandeis.pa1.entity.Report;


@Repository
public class ReportDAOimpl implements ReportDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int save(int sensor_id,Timestamp report_time,double temperature) {
		return jdbcTemplate.update("INSERT INTO report(sensor_id,report_time,temperature) VALUES(?,?,?)",sensor_id,report_time,temperature);
	}
	
	@Override
	public List<Report> getAll() {
		return jdbcTemplate.query("SELECT * FROM report;", new BeanPropertyRowMapper<Report>(Report.class));
	}
}