package com.brandeis.pa1.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.brandeis.pa1.entity.Coverage;


@Repository
public class CoverageDAOimpl implements CoverageDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Coverage> getAll() {
		return jdbcTemplate.query("SELECT * FROM coverage;", new BeanPropertyRowMapper<Coverage>(Coverage.class));
	}
}
