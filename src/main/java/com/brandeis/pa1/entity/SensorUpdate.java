package com.brandeis.pa1.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorUpdate {
	private double x;
	private double y;
	private Timestamp last_charged;
	private double energy;
	private double temperature;
	
	public SensorUpdate() {
		
	}

	public SensorUpdate(double x, double y, Timestamp last_charged, double energy, double temperature) {
		this.x = x;
		this.y = y;
		this.last_charged = last_charged;
		this.energy = energy;
		this.temperature = temperature;
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
	public double getEnergy() {
		return energy;
	}
	public void setEnergy(double energy) {
		this.energy = energy;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
}
