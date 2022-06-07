package com.alexis.ejercicios;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Jobs {
	//creamos los atributos de la clase jobs 
	private int id;
	private String name;
	private double salary;
	// creamos los getters y setters correspondientes
	public void set_name(String name) {
		this.name=name;
		
	}
	

	public void set_salary(double salary) {
		this.salary=salary;
	}
	
	public String get_name() {
		return this.name=name;
	}
	
	public double get_salary() {
		return this.salary;
	}
	
	public void set_id(int id) {
		this.id=id;
	}

	public int get_id() {
		return this.id;
	}
	
}
