package com.brandeis.pa1.dao;

import java.util.List;

import com.brandeis.pa1.entity.Forest;
import com.brandeis.pa1.entity.State;

public interface ForestDAO {
	int save(Forest forest);
	
	int update(Forest forest,int forest_no);
	
	int delete(int id);
	
	List<Forest> getAll();
	
	Forest getByName(String name);

	int updateCover(String name, double mbr_xmin, double mbr_xmax, double mbr_ymin, double mbr_ymax, String state);

	State searchState(String state);
}
