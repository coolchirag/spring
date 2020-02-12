package com.example.jpaExample.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.jpaExample.dao.bean.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer>{

}
