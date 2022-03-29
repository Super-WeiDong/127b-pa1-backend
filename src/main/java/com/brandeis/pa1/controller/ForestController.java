package com.brandeis.pa1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brandeis.pa1.dao.ForestDAO;
import com.brandeis.pa1.entity.Forest;

@CrossOrigin(origins = "http://databasepa1.s3-website-us-east-1.amazonaws.com")
@RestController
public class ForestController {
	
	@Autowired
	private ForestDAO fDAO;
	
	
	//Task #1 Add Forest
	@Transactional
	@PostMapping("/forest")
	public String saveForest(@RequestBody Forest forest) {
		//check state
		if(fDAO.searchState(forest.getState())==null) {
			return "The state " + forest.getState() + " does not exist. you can go to state table to add this state.";
		}
		if(getForestgetByName(forest.getName())==null){
			//if name of this forest not exists, insert to database
			int i = fDAO.save(forest);
			if(i==-1) {
				return "invalid data type";
			}
			return forest.getName() +" is successfully added to the database.";
		}else {
			//if it exists, print error message
			return forest.getName() + " already exists.";
		}
	};
	
	@Transactional
	@GetMapping("/forest")
	public List<Forest> getForest(){
		return fDAO.getAll();
	}
	
	@Transactional
	@GetMapping("/forest/name/{name}")
	public Forest getForestgetByName(@PathVariable String name){
		try {
			return fDAO.getByName(name);
		} catch (Exception e) {
			return null;
		}
	}
	
	//Task #6 Update Forest Covered Area
	@Transactional
	@GetMapping("/forest/update")
	public String updateCover(String name, double mbr_xmin,double mbr_xmax,double mbr_ymin,double mbr_ymax,String state){
		//check whether name is valid
		if(fDAO.getByName(name)==null) {
			return name + " does not exist.";
		}
		//check state
		if(fDAO.searchState(state)==null) {
			return "The state " + state + " does not exist. you can go to state table to add this state.";
		}
		fDAO.updateCover(name, mbr_xmin, mbr_xmax, mbr_ymin, mbr_ymax, state);
		return "Successfully update cover area.";
	}
	
	
	@Transactional
	@PutMapping("/forest/{forest_no}")
	public String updateForest(@RequestBody Forest forest,@PathVariable int forest_no) {
		return fDAO.update(forest, forest_no)+" rows updated to the database.";
	}
	
	@Transactional
	@DeleteMapping("/forest/{forest_no}")
	public String deleteForestById(@PathVariable int forest_no) {
		return fDAO.delete(forest_no) + " rows deleted from the database.";
	}
}
