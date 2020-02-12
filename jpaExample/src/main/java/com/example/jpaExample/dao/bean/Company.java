package com.example.jpaExample.dao.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "company")
public class Company implements Cloneable {

	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "increment")
	private Integer id;

	@Column(name = "name")
	private String companyName;

	@Column(name = "city")
	private String city;

	/* @OneToMany(mappedBy = "compnayToEmpMap" , cascade = CascadeType.ALL ) */
	@OneToMany  (cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id")
	private List<Employee> employeeList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {

		/*
		 * employeeList.forEach(emp -> { emp.setCompnayToEmpMap(this); });
		 */

		this.employeeList = employeeList;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", city=" + city + ", employeeList="
				+ employeeList + "]";
	}

	@Override
	public Company clone() throws CloneNotSupportedException {

		return (Company) super.clone();
	}

}
