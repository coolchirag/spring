package com.example.jpaExample.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpaExample.dao.bean.Company;
import com.example.jpaExample.dao.bean.CompanyRepository;

@Service
@Transactional
public class ThreadService {

	@Autowired
	private CompanyService cService;
	
	@Autowired
	CompanyRepository cmpRepo;
	
	public void test()
	{
		ExecutorService es = Executors.newFixedThreadPool(5);
		Future<Company> copyCmpTask = es.submit(new Callable<Company>() {

			@Override
			public Company call() throws Exception {
				 Company cmp = cService.copyCompany();
				return cmp;
			}
		});
		
		Company c = null;
		try {
			c = copyCmpTask.get();
		} catch (InterruptedException | ExecutionException e) {
			
			e.printStackTrace();
		}
		cmpRepo.save(c);
		System.out.println("Done");
	}
	
}
