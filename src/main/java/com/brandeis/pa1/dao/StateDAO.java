package com.brandeis.pa1.dao;
import java.util.List;

import com.brandeis.pa1.entity.State;

public interface StateDAO {
	List<State> getAll();
	
	int save(State state);

	State getName(String name);

	State getAb(String ab);
}
