package com.brandeis.pa1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brandeis.pa1.dao.StateDAO;
import com.brandeis.pa1.entity.State;

@CrossOrigin(origins = "http://databasepa1.s3-website-us-east-1.amazonaws.com")
@RestController
public class StateController {
	@Autowired
	private StateDAO sDAO;
	
	@Transactional
	@GetMapping("/state")
	public List<State> getState(){
		return sDAO.getAll();
	}
	@Transactional
	@PostMapping("/state")
	public String createState(@RequestBody State state) {
			if(sDAO.getAb(state.getAbbreviation())!=null) {
				return "State " + state.getAbbreviation() + " already exists";
			}
			if(sDAO.getName(state.getName())!=null) {
				return "State " + state.getName() + " already exists";
			}
			try {
				sDAO.save(state);
			} catch (Exception e) {
				e.printStackTrace();
				return "invalid input data!";
			}
			return state.getAbbreviation() + " is successfully added to database!";
	}
}
