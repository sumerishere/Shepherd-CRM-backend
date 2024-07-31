package com.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.template.repository.DataTableRepository;

@Service
public class DataTableService {
	
	@Autowired
	DataTableRepository dataTableRepository;

}
