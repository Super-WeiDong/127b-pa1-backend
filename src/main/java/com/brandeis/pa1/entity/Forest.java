package com.brandeis.pa1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forest {
	private int forest_no;
	private String name;
	private double area;
	private double acid_level;
	private double mbr_xmin;
	private double mbr_xmax;
	private double mbr_ymin;
	private double mbr_ymax;
	private String state;
	
	public int getForest_no() {
		return forest_no;
	}
	public void setForest_no(int forest_no) {
		this.forest_no = forest_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public double getAcid_level() {
		return acid_level;
	}
	public void setAcid_level(double acid_level) {
		this.acid_level = acid_level;
	}
	public double getMbr_xmin() {
		return mbr_xmin;
	}
	public void setMbr_xmin(double mbr_xmin) {
		this.mbr_xmin = mbr_xmin;
	}
	public double getMbr_xmax() {
		return mbr_xmax;
	}
	public void setMbr_xmax(double mbr_xmax) {
		this.mbr_xmax = mbr_xmax;
	}
	public double getMbr_ymin() {
		return mbr_ymin;
	}
	public void setMbr_ymin(double mbr_ymin) {
		this.mbr_ymin = mbr_ymin;
	}
	public double getMbr_ymax() {
		return mbr_ymax;
	}
	public void setMbr_ymax(double mbr_ymax) {
		this.mbr_ymax = mbr_ymax;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}