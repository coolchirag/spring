package com.example.jpaExample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpaExample.dao.bean.Company;
import com.example.jpaExample.dao.bean.Employee;
import com.example.jpaExample.dao.repository.CompanyRepository;

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
			// cmp.setId(100+i);
			cmps.add(cmp);
		}
		cmpRepo.save(cmps);
		return "true";
	}

	public void copyCompany1() {
		/*
		 * Company cmp = cmpRepo.findOne(1); System.out.println(cmp.getCompanyName());
		 */
		Company cloned = new Company();
		cloned.setCity("abc" + 1);
		cloned.setCompanyName("abc" + 1);
		List<Employee> emps = new ArrayList<Employee>();
		for (int i = 0; i < 2; i++) {
			Employee clonedEmp = new Employee();
			clonedEmp.setEmployeeName("Emp" + i);
			clonedEmp.setSalary(10000);
			clonedEmp.setCmpName("abc" + 1);
			clonedEmp.setCompnayToEmpMap(cloned);
			emps.add(clonedEmp);
		}
		cloned.setEmployeeList(emps);
		cmpRepo.save(cloned);
		System.out.println("DOne");

	}

	public void copyCompany2() {
		Company cmp = cmpRepo.findOne(1);

		try {

			// Company cloned = cmp.clone();
			Company cloned = new Company();
			cloned.setId(null);
			cloned.setCity(cmp.getCity() + "test");
			cloned.setCompanyName(cmp.getCompanyName());

			List<Employee> orgEmployee = cmp.getEmployeeList();

//			List<Employee> emps = orgEmployee.stream().map(emp -> {
//				Employee clonedEmp = null;
//				try {
//					clonedEmp = emp.clone();
//					clonedEmp.setId(null);
//					clonedEmp.setCompnayToEmpMap(cloned);
//				} catch (CloneNotSupportedException e) {
//					e.printStackTrace();
//				}
//				return clonedEmp;
//			}).collect(Collectors.toList());

			/*
			 * List<Employee> emps = orgEmployee.stream().map(emp -> { Employee clonedEmp =
			 * new Employee(); clonedEmp.setEmployeeName(emp.getEmployeeName());
			 * clonedEmp.setSalary(emp.getSalary()); clonedEmp.setCmpName(emp.getCmpName());
			 * //clonedEmp.setCompanyId(emp.getCompanyId());
			 * clonedEmp.setCompnayToEmpMap(cloned);
			 * 
			 * return clonedEmp; }).collect(Collectors.toList());
			 */

			List<Employee> emps = new ArrayList<Employee>();
			for (Employee emp : orgEmployee) {
				Employee clonedEmp = new Employee();
				clonedEmp.setEmployeeName(emp.getEmployeeName());
				clonedEmp.setSalary(emp.getSalary());
				clonedEmp.setCmpName(emp.getCmpName());
				emps.add(clonedEmp);
			}

			// List<Employee> emps = new ArrayList<Employee>(orgEmployee);
			cloned.setEmployeeList(emps);
			cmpRepo.save(cloned);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");
	}

	public void copyCompanyThread() {
		ExecutorService es = Executors.newFixedThreadPool(5);
		Future<Company> copyCmpTask = es.submit(new Callable<Company>() {

			@Override
			public Company call() throws Exception {
				Company cmp = cmpRepo.findOne(1);

				try {

					Company cloned = cmp.clone();

					List<Employee> orgEmployee = cmp.getEmployeeList();
					List<Employee> emps = new ArrayList<Employee>(orgEmployee);
					/*
					 * List<Employee> emps = orgEmployee.stream().map(emp -> { Employee clonedEmp =
					 * null; try { clonedEmp = emp.clone(); clonedEmp.setId(null);
					 * //clonedEmp.setCompnayToEmpMap(cloned); } catch (CloneNotSupportedException
					 * e) { e.printStackTrace(); } return clonedEmp;
					 * }).collect(Collectors.toList());
					 */
					cloned.setEmployeeList(emps);
					return cloned;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
		try {
			Company copiedCompany = copyCmpTask.get();
			cmpRepo.save(copiedCompany);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Done");
	}

	public Company copyCompany() {

		Company cmp = cmpRepo.findOne(1);

		try {

			Company cloned = cmp.clone();
			cloned.setId(null);
			List<Employee> orgEmployee = cmp.getEmployeeList();
			List<Employee> emps = new ArrayList<Employee>(orgEmployee);
			/*
			 * List<Employee> emps = orgEmployee.stream().map(emp -> { Employee clonedEmp =
			 * null; try { clonedEmp = emp.clone(); clonedEmp.setId(null);
			 * //clonedEmp.setCompnayToEmpMap(cloned); } catch (CloneNotSupportedException
			 * e) { e.printStackTrace(); } return clonedEmp;
			 * }).collect(Collectors.toList());
			 */
			cloned.setEmployeeList(emps);
			return cloned;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");
		return null;
		
	}

}
