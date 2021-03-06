package com.brandeis.pa1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
	private String ssn;
	private String name;
	private int rank;
	private String employing_state;
	public String getSsn() {
		return ssn;
	}
	

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getEmploying_state() {
		return employing_state;
	}
	public void setEmploying_state(String employing_state) {
		this.employing_state = employing_state;
	}
	
	
}
