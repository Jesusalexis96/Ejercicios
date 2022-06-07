package com.alexis.ejercicios;

import java.util.List;

public class Lista {
	// creamos los atributos de la clase lista 
	private List<Jobs> Job;
	private List<Employees> Employees;
	private List<genders> gender;
	// creamos los getters y setters correspondientes
	public Lista() {
		
	}
	public List<Jobs> getJob() {
		return Job;
	}
	public void setJob(List<Jobs> job) {
		Job = job;
	}
	public List<Employees> getEmployees() {
		return Employees;
	}
	public void setEmployees(List<Employees> employees) {
		Employees = employees;
	}
	public List<genders> getGender() {
		return gender;
	}
	public void setGender(List<genders> gender) {
		this.gender = gender;
	}
	
	
	
	
}
