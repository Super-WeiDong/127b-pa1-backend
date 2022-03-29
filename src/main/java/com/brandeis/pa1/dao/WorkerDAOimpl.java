package com.brandeis.pa1.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.brandeis.pa1.entity.State;
import com.brandeis.pa1.entity.TopK;
import com.brandeis.pa1.entity.Worker;

@Repository
public class WorkerDAOimpl implements WorkerDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	//Task #2 Add worker
	@Override
	public int save(Worker worker) {
		return jdbcTemplate.update("INSERT INTO worker(ssn,name,rank,employing_state) VALUES(?,?,?,?)",new Object[] {worker.getSsn(),worker.getName(),worker.getRank(),worker.getEmploying_state()});
	}
	//Task #2 helper method
	@Override
	public List<Worker> getAll() {
		return jdbcTemplate.query("SELECT * FROM worker;", new BeanPropertyRowMapper<Worker>(Worker.class));
	}
	
	@Override
	public Worker getBySsn(String ssn) {
			try {
				return jdbcTemplate.queryForObject("SELECT * FROM worker WHERE ssn = ?;", new BeanPropertyRowMapper<Worker>(Worker.class),ssn);
			} catch (DataAccessException e) {
				return null;
			}
	}
	
	@Override
	public Worker getByName(String name) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM worker WHERE name = ?;", new BeanPropertyRowMapper<Worker>(Worker.class),name);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	@Override
	public List<TopK> getTopK(int k){
		return jdbcTemplate.query("select ssn,name,rank,employing_state,number_of_sensor from (SELECT maintainer,count(*) as number_of_sensor FROM sensor where energy <=2 group by maintainer order by number_of_sensor desc limit ?) as top,worker where top.maintainer = worker.ssn order by number_of_sensor desc;", new BeanPropertyRowMapper<TopK>(TopK.class),k);
	}
	
	@Override
	public State searchState(String state) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM state WHERE abbreviation = ?;", new BeanPropertyRowMapper<State>(State.class),state);
		} catch (DataAccessException e) {
			return null;
		}
	}
}
