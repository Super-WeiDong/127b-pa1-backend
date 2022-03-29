package com.brandeis.pa1.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sensor {
	private int sensor_id;
	private double x;
	private double y;
	private Timestamp last_charged;
	private String maintainer;
	private Timestamp last_read;
	private double energy;
	
	public int getSensor_id() {
		return sensor_id;
	}
	public void setSensor_id(int sensor_id) {
		this.sensor_id = sensor_id;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public Timestamp getLast_charged() {
		return last_charged;
	}
	public void setLast_charged(Timestamp last_charged) {
		this.last_charged = last_charged;
	}
	public String getMaintainer() {
		return maintainer;
	}
	public void setMaintainer(String maintainer) {
		this.maintainer = maintainer;
	}
	public Timestamp getLast_read() {
		return last_read;
	}
	public void setLast_read(Timestamp last_read) {
		this.last_read = last_read;
	}
	public double getEnergy() {
		return energy;
	}
	public void setEnergy(double energy) {
		this.energy = energy;
	}
}
