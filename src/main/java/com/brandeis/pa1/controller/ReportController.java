package com.brandeis.pa1.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brandeis.pa1.dao.ReportDAO;
import com.brandeis.pa1.entity.Report;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ReportController {
	@Autowired
	private ReportDAO rDAO;
	
	@Transactional
	@GetMapping("/report")
	public List<Report> getReport(){
		return rDAO.getAll();
	}
}

