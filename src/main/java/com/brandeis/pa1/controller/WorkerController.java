package com.brandeis.pa1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brandeis.pa1.dao.WorkerDAO;
import com.brandeis.pa1.entity.TopK;
import com.brandeis.pa1.entity.Worker;

@CrossOrigin(origins = "http://databasepa1.s3-website-us-east-1.amazonaws.com")
@RestController
public class WorkerController {
	
	@Autowired
	private WorkerDAO wDAO;
	
	@Transactional
	@GetMapping("/worker")
	public List<Worker> getWorker(){
		return wDAO.getAll();
	}
	
	//Task #2 Add worker
	@Transactional
	@PostMapping("/worker")
	public String createWorker(@RequestBody Worker worker) {
		//check state
		if(wDAO.searchState(worker.getEmploying_state())==null) {
			return "The state " + worker.getEmploying_state() + " does not exist. you can go to state table to add this state.";
		}
		//check ssn
		if(wDAO.getBySsn(worker.getSsn())==null && wDAO.getByName(worker.getName())==null) {
			wDAO.save(worker);
			return worker.getName() + " is successfully added to database!";
		}else if(wDAO.getBySsn(worker.getSsn())!=null && wDAO.getByName(worker.getName())!=null){
			return worker.getName() +" with ssn: " + worker.getSsn() + " is already exists";
		}else if(wDAO.getBySsn(worker.getSsn())!=null) {
			return "Ssn number: " + worker.getSsn() + " is already exists";
		}else {
			return "Name: " + worker.getName() + " is already exists";
		}
	}
	
	@Transactional
	@GetMapping("/worker/name/{name}")
	public Worker getByName(@PathVariable String name){
		return wDAO.getByName(name);
	}
	
	@Transactional
	@GetMapping("/worker/ssn/{ssn}")
	public Worker getBySsn(@PathVariable String ssn){
		return wDAO.getByName(ssn);
	}
	
	//Task #7 topK Worker
	@Transactional
	@GetMapping("/worker/topk")
	public List<TopK> getBySsn(int k){
		return wDAO.getTopK(k);
	}
}
