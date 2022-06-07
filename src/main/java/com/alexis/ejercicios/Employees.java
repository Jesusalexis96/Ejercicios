package com.alexis.ejercicios;


import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employees")
public class Employees {
//creamos todos los atributos de la clase employees
private int id;
private String name;
private String last_name;
private String birthdate;
private int idjobs;
private int idgenders;

// creamos los getters y setters correspondientes
public int getIdjobs() {
	return idjobs;
}
public void setIdjobs(int idjobs) {
	this.idjobs = idjobs;
}
public int getIdgenders() {
	return idgenders;
}
public void setIdgenders(int idgenders) {
	this.idgenders = idgenders;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getLast_name() {
	return last_name;
}
public void setLast_name(String last_name) {
	this.last_name = last_name;
}
public String getBirthdate() {
	return birthdate;
}
public void setBirthdate(String birthdate) {
	this.birthdate = birthdate;
}


	
	
}
