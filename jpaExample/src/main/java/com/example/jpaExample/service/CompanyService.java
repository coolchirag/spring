package com.example.jpaExample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpaExample.dao.bean.Company;
import com.example.jpaExample.dao.bean.CompanyRepository;

@Service
@Transactional
public class CompanyService {

	@Autowired
	CompanyRepository cmpRepo;
	
	public String insertCompany() {
		List<Company> cmps = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Company cmp = new Company();
			cmp.setCity("city" + i);
			cmp.setCompanyName("name" + i);
			//cmp.setId(100+i);
			cmps.add(cmp);
		}
		cmpRepo.save(cmps);
		return "true";
	}
}
