package com.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.template.service.DataTableService;

@RestController
@CrossOrigin("*")
public class DataTableController {
	
	@Autowired
	DataTableService dataTableService;
	

}
