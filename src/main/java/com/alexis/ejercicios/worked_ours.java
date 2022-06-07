package com.alexis.ejercicios;

public class worked_ours {
//creamos los atributos de la clase worked_ours 
	private int id;
	private int employee_id;
	private int worked_hours;
	private String worked_date;
	private int total_worked_hrs;
	
	
	// creamos los getters y setters correspondientes
	
	public int getTotal_worked_hrs() {
		return total_worked_hrs;
	}
	public void setTotal_worked_hrs(int total_worked_hrs) {
		this.total_worked_hrs = total_worked_hrs;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public int getWorked_hours() {
		return worked_hours;
	}
	public void setWorked_hours(int worked_hours) {
		this.worked_hours = worked_hours;
	}
	public String getWorked_date() {
		return worked_date;
	}
	public void setWorked_date(String worked_date) {
		this.worked_date = worked_date;
	}
	
	
	
}
