package com.brandeis.pa1.dao;

import java.util.List;

import com.brandeis.pa1.entity.State;
import com.brandeis.pa1.entity.TopK;
import com.brandeis.pa1.entity.Worker;

public interface WorkerDAO {
	int save(Worker worker);
	
	List<Worker> getAll();
	
	Worker getBySsn(String ssn);

	Worker getByName(String name);

	List<TopK> getTopK(int k);
	
	State searchState(String state);
}
