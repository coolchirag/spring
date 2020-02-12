package com.example.jpaExample.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.jpaExample.dao.bean.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
