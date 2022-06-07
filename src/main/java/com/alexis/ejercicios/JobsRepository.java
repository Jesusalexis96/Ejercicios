package com.alexis.ejercicios;


import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class JobsRepository {

	List<Jobs> jobs;
	
	Connection con =null;
	// creamos el constructor para poder realizar la conexion a la base de datos
	public JobsRepository() {
		
		String url="jdbc:mysql://localhost:3306/mydb";
		String username="root";
		String password="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	
	}
	
	
	
	//creamos una funcion que nos devuelva los jobs por id
	
	public Jobs getJobs(int id) {
	
	String sql="select * from jobs where ID="+id;
	Jobs j= new Jobs();
	
		try {
			
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(sql);
			if(rs.next()) {
				
				j.set_id(rs.getInt(1));
				j.set_name(rs.getString(2));
				j.set_salary(rs.getDouble(3));
				
			}
	
			
			
		}
		
		catch(Exception e) {
			System.out.println(e);
		}

		
		return j;
		
	}


	// creamos una funcion para validar que exista un registro en la tabla jobs 
	//con el id que se le pase por parametro

	public Jobs validacion_job(int id) {
		
		String sql="select * from jobs where ID='"+id+"';";

		
		Jobs j= new Jobs();
		
			try {
				
				Statement st= con.createStatement();
				ResultSet rs= st.executeQuery(sql);
				if(rs.next()) {
				j.set_id(rs.getInt(9));
				j.set_name(rs.getString(10));
				j.set_salary(rs.getDouble(11));
				}
		
				
				
			}
			
			catch(Exception e) {
				System.out.println(e);
			}

			
			return j;
			
			
		
	}

	
	// creamos una funcion que calcule  cuanto se le pagó a un empleado en un rango de fechas
public List<Jobs> cuenta_salario(String inicio, String fin,int id) {
	
	String sql="select sum(jobs.salary) from employee_worked_ours INNER JOIN "
			+ "employees on employees.ID=employee_worked_ours.EMPLOYEE_ID INNER JOIN"
			+ " jobs on jobs.ID=employees.JOB_ID where "
			+ "employee_worked_ours.employee_id='"+id+"' and employee_worked_ours.worked_date "
			+ "BETWEEN '"+inicio+"' and '"+fin+"';";
	


	Jobs j=new Jobs();

	List<Jobs> salary = new ArrayList<>();
		try {
			
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(sql);
			if(rs.next()) {
				j.set_salary(rs.getDouble(1));
				salary.add(j);
			}
	
			
			
		}
		
		catch(Exception e) {
			System.out.println(e);
		}

		
		return salary;
		
		
	
}

	
}
