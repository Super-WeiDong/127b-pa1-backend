package com.brandeis.pa1.entity;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
	private int sensor_id;
	private Timestamp report_time;
	private double temperature;
	
	
	public Report() {
	}


	public Report(int sensor_id, Timestamp report_time, double temperature) {
		this.sensor_id = sensor_id;
		this.report_time = report_time;
		this.temperature = temperature;
	}


	public int getSensor_id() {
		return sensor_id;
	}


	public void setSensor_id(int sensor_id) {
		this.sensor_id = sensor_id;
	}


	public Timestamp getReport_time() {
		return report_time;
	}


	public void setReport_time(Timestamp report_time) {
		this.report_time = report_time;
	}


	public double getTemperature() {
		return temperature;
	}


	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
}
