package com.brandeis.pa1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brandeis.pa1.dao.CoverageDAO;
import com.brandeis.pa1.entity.Coverage;

@CrossOrigin(origins = "http://databasepa1.s3-website-us-east-1.amazonaws.com")
@RestController
public class CoverageController {
	@Autowired
	private CoverageDAO cDAO;
	
	@Transactional
	@GetMapping("/coverage")
	public List<Coverage> getCoverage(){
		return cDAO.getAll();
	}
}