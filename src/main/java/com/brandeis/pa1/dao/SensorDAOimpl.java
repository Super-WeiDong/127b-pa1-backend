package com.brandeis.pa1.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.brandeis.pa1.entity.Sensor;
import com.brandeis.pa1.entity.SensorRanking;
import com.brandeis.pa1.entity.SensorUpdate;
import com.brandeis.pa1.entity.Worker;

@Repository
public class SensorDAOimpl implements SensorDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//method of Task #3
	@Override
	public int save(Sensor sensor) {
		return jdbcTemplate.update("INSERT INTO sensor(sensor_id,x,y,last_charged,maintainer,last_read,energy) VALUES(?,?,?,?,?,?,?)",new Object[] {sensor.getSensor_id(),sensor.getX(),sensor.getY(),sensor.getLast_charged(),sensor.getMaintainer(),sensor.getLast_read(),sensor.getEnergy()});
	}

	@Override
	public List<Sensor> getAll() {
		return jdbcTemplate.query("SELECT * FROM sensor;", new BeanPropertyRowMapper<Sensor>(Sensor.class));
	}

	@Override
	public Sensor getByID(int id) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM sensor WHERE sensor_id = ? ;", new BeanPropertyRowMapper<Sensor>(Sensor.class),id);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	//helper method of Task #5
	@Override
	public Sensor getByCoordinates(double x, double y) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM sensor WHERE x = ? and y = ?;", new BeanPropertyRowMapper<Sensor>(Sensor.class),x,y);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	//helper method of Task #4
	@Override
	public int swithch(String a, String b) {
		return jdbcTemplate.update("UPDATE sensor SET maintainer = CASE WHEN maintainer = ? THEN ? WHEN maintainer = ? THEN ? ELSE maintainer END;",a,b,b,a);
	}
	
	//helper method of Task #4
	@Override
	public Worker getWorkerByName(String name) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM worker WHERE name = ?;", new BeanPropertyRowMapper<Worker>(Worker.class),name);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	//helper method of Task #3
	@Override
	public Worker getWorkerBySsn(String ssn) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM worker WHERE ssn = ?;", new BeanPropertyRowMapper<Worker>(Worker.class),ssn);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	@Override
	public int update(SensorUpdate su) {
		return jdbcTemplate.update("UPDATE sensor SET energy = ?, last_charged = ? WHERE x = ? and y = ?",su.getEnergy(),su.getLast_charged(),su.getX(),su.getY());
	}
	
	//helper method of 
	@Override
	public int addReport(int sensor_id,double temperature) {
		Timestamp dateNow=new Timestamp(System.currentTimeMillis());
		return jdbcTemplate.update("INSERT INTO report(sensor_id,report_time,temperature) VALUES(?,?,?)",sensor_id,dateNow,temperature);
	}
	
	@Override
	//helper method of Task #8
	public List<SensorRanking> getByRank(){
		return jdbcTemplate.query("select * from (select sensor_id,count(*) as report_number from report group by sensor_id order by count(*) desc) as top,sensor where top.sensor_id = sensor.sensor_id;", new BeanPropertyRowMapper<SensorRanking>(SensorRanking.class));
	}
}
