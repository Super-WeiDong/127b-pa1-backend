package com.brandeis.pa1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brandeis.pa1.dao.SensorDAO;
import com.brandeis.pa1.entity.Sensor;
import com.brandeis.pa1.entity.SensorRanking;
import com.brandeis.pa1.entity.SensorUpdate;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SensorController {
	
	@Autowired
	private SensorDAO sDAO;
	
	@Transactional
	@GetMapping("/sensor")
	public List<Sensor> getSensor(){
		return sDAO.getAll();
	}
	
	//Task #3 Add Sensor
	@Transactional
	@PostMapping("/sensor")
	public String createSensor(@RequestBody Sensor sensor) {
		String error = ""; 
		if(sDAO.getByID(sensor.getSensor_id())!=null) {
			error += "sensor_id " + sensor.getSensor_id() + " is already exists\n";
		}
		if(sDAO.getByCoordinates(sensor.getX(), sensor.getY())!=null) {
			error += "sensor with coordinates x: "+sensor.getX()+" y: "+sensor.getY() + " is already exists\n";
		}
		if(sDAO.getWorkerBySsn(sensor.getMaintainer())==null) {
			error += "there is no such a maintainer with ssn: " + sensor.getMaintainer() + "\n";
		}
		if(sDAO.getByID(sensor.getSensor_id())==null && sDAO.getByCoordinates(sensor.getX(), sensor.getY())==null && sDAO.getWorkerBySsn(sensor.getMaintainer())!=null) {
			sDAO.save(sensor);
			return "sensor "+ sensor.getSensor_id()+" with coordinates x: "+sensor.getX()+" y: "+sensor.getY() + " is successfully added to database!";
		}else {
			return error;
		}
	}
	
	//Task #4 Switch Workers Duties
	@Transactional
	@GetMapping("/sensor/switch")
	public String updateForest(String a, String b) {
		// a and b do not exist, print error message
		if(sDAO.getWorkerByName(a)==null&&sDAO.getWorkerByName(b)==null) {
			return a + " and " + b + " do not exist";
		}else if(sDAO.getWorkerByName(a)==null) {
			// a does not exist, print error message
			return a + " does not exist";
		}else if(sDAO.getWorkerByName(b)==null) {
			// b does not exist, print error message
			return b + " does not exist";
		}else if(sDAO.getWorkerByName(a).getEmploying_state()!=sDAO.getWorkerByName(b).getEmploying_state()) {
			return "can not swithch! "+ a + " and " + b + " is not in the same state"; 
		}else {
			String aState = sDAO.getWorkerByName(a).getEmploying_state();
			String bState = sDAO.getWorkerByName(b).getEmploying_state();
			if(aState.equals(bState)) {
				// a and b are in the same state,switch duties
				String aSsn = sDAO.getWorkerByName(a).getSsn();
				String bSsn = sDAO.getWorkerByName(b).getSsn();
				sDAO.swithch(aSsn, bSsn);
				return "Successfully switch duties of "+ a + " and " + b;
			}else {
				// a and b are not in the same state, print error message
				return "Can not switch! Because " + a + " and " + b + " are not in the same state";
			}
		}
	}
	
	//Task #5 Update Sensor Status
	@Transactional
	@PostMapping("/sensor/update")
	public String updateSensor(@RequestBody SensorUpdate su) {
		//coordinate not found
		if(sDAO.getByCoordinates(su.getX(), su.getY())==null) {
			return "There is no sensor in coordinate "+ "(" +su.getX() +" , "+ su.getY() +")";
		}else {
			sDAO.update(su);
			//detect emergency
			if(su.getTemperature()>100) {
				sDAO.addReport(sDAO.getByCoordinates(su.getX(), su.getY()).getSensor_id(), su.getTemperature());
				return "Successfully update status of sensor in coordinate "+ "(" +su.getX() +" , "+ su.getY()+"). " + "An emergency is triggered";
			}else {
				sDAO.addReport(sDAO.getByCoordinates(su.getX(), su.getY()).getSensor_id(), su.getTemperature());
				return "Successfully update status of sensor in coordinate "+ "(" +su.getX()+" , "+ su.getY()+"). " + "No emergency";
			}
		}
	}
	
	//Task #8 
	@Transactional
	@GetMapping("/sensor/ranking")
	public List<SensorRanking> getSensorRanking(){
		return sDAO.getByRank();
	}
}
