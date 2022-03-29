package com.brandeis.pa1.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.brandeis.pa1.entity.Forest;
import com.brandeis.pa1.entity.State;

@Repository
public class ForestDAOimpl implements ForestDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//#Task 1 add forest
	@Override
	public int save(Forest forest) {
		int x;
		try {
			x = jdbcTemplate.update("INSERT INTO forest(name,area,acid_level,mbr_xmin,mbr_xmax,mbr_ymin,mbr_ymax,state) VALUES(?,?,?,?,?,?,?,?)",new Object[] {forest.getName(),forest.getArea(),forest.getAcid_level(),forest.getMbr_xmin(),forest.getMbr_xmax(),forest.getMbr_ymin(),forest.getMbr_ymax(),forest.getState()});
			Forest f = getByName(forest.getName());
			jdbcTemplate.update("INSERT INTO coverage(forest_no,state,percentage,area) VALUES(?,?,?,?)",f.getForest_no(),f.getState(),100,f.getArea());
		} catch (DataAccessException e) {
			e.printStackTrace();
			return -1;
		}
		return x;
	}
	
	//helper method of Task #5
	@Override
	public List<Forest> getAll() {
		return jdbcTemplate.query("SELECT * FROM forest;", new BeanPropertyRowMapper<Forest>(Forest.class));
	}
	
	//helper method of Task #5 and Task #1
	@Override
	public Forest getByName(String name) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM forest WHERE name = ?;", new BeanPropertyRowMapper<Forest>(Forest.class),name);
		} catch (DataAccessException e) {
			return null;
		}
	}

	@Override
	public int update(Forest forest, int forest_no) {
		return jdbcTemplate.update("UPDATE forest SET name = ?, area = ?, acid_level = ?, mbr_xmin = ?, mbr_xmax = ?, mbr_ymin = ?, mbr_ymax = ?, state = ?  WHERE forest_no=?",new Object[] {forest.getName(),forest.getArea(),forest.getAcid_level(),forest.getMbr_xmin(),forest.getMbr_xmax(),forest.getMbr_ymin(),forest.getMbr_ymax(),forest.getState(), forest_no});
	}
	// Task #6 method
	@Override
	public int updateCover(String name, double mbr_xmin,double mbr_xmax,double mbr_ymin,double mbr_ymax,String state) {
		double area = (mbr_xmax - mbr_xmin) * (mbr_ymax - mbr_ymin);
		int f_no = getByName(name).getForest_no();
		jdbcTemplate.update("UPDATE coverage SET state = ?, area = ? WHERE forest_no=?",state,area,f_no);
		return jdbcTemplate.update("UPDATE forest SET area = ?, mbr_xmin = ?, mbr_xmax = ?, mbr_ymin = ?, mbr_ymax = ?, state = ?  WHERE name=?",area,mbr_xmin,mbr_xmax,mbr_ymin,mbr_ymax,state,name);
	}
	
	@Override
	public int delete(int forest_no) {
		return jdbcTemplate.update("DELETE FROM forest where forest_no=?",forest_no);
	}
	
	public State searchState(String state) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM state WHERE abbreviation = ?;", new BeanPropertyRowMapper<State>(State.class),state);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
}
