package com.brandeis.pa1.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.brandeis.pa1.entity.State;

@Repository
public class StateDAOimpl implements StateDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<State> getAll() {
		return jdbcTemplate.query("SELECT * FROM state;", new BeanPropertyRowMapper<State>(State.class));
	}

	@Override
	public int save(State state) {
		return jdbcTemplate.update("INSERT INTO state(name,abbreviation,area,population) VALUES(?,?,?,?)",new Object[] {state.getName(),state.getAbbreviation(),state.getArea(),state.getPopulation()});
	}
		
	@Override
	public State getName(String name) {
			try {
				return jdbcTemplate.queryForObject("SELECT * FROM state WHERE name = ?;", new BeanPropertyRowMapper<State>(State.class),name);
			} catch (DataAccessException e) {
				return null;
			}
	}
	
	@Override
	public State getAb(String ab) {
			try {
				System.out.print(1);
				return jdbcTemplate.queryForObject("SELECT * FROM state WHERE abbreviation = ?;", new BeanPropertyRowMapper<State>(State.class),ab);
			} catch (DataAccessException e) {
				return null;
			}
	}
}
