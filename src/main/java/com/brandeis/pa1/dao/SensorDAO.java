package com.brandeis.pa1.dao;

import java.sql.Timestamp;
import java.util.List;

import com.brandeis.pa1.entity.Sensor;
import com.brandeis.pa1.entity.SensorRanking;
import com.brandeis.pa1.entity.SensorUpdate;
import com.brandeis.pa1.entity.Worker;

public interface SensorDAO {
	int save(Sensor sensor);
	
	List<Sensor> getAll();
	
	Sensor getByID(int id);
	
	Sensor getByCoordinates(double x,double y);
	
	int swithch(String a,String b);

	Worker getWorkerByName(String name);

	int update(SensorUpdate su);

	Worker getWorkerBySsn(String ssn);

	int addReport(int sensor_id, double temperature);

	List<SensorRanking> getByRank();
}
