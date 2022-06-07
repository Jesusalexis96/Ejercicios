package com.alexis.ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class worked_oursRepository {

	
	Connection con =null;
	
	// creamos el constructor que nos devuelve la conexion
	public worked_oursRepository() {
		
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
	
	// funcion que valida si una fecha que se le pase por parametro es mayor a la fecha actual
	public  int calcularFecha(String fecha) {

        int resultado=0;

    Date fechaactual = new Date(System.currentTimeMillis());
    String fechaInicio = fecha; //fecha de ejemplo
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    Date fechaInicioDate = null;
	try {
		fechaInicioDate = date.parse(fechaInicio);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  //String a date

    //comprueba si es que inicio esta después que fecha actual
    if(fechaInicioDate.after(fechaactual)){
       resultado=1;
    }

        
        return resultado;
	}
	
// funcion para crear registro de horas trabajadas de un empleado
	public void create(worked_ours hr) {
		
		String sql="INSERT INTO employee_worked_ours  values(null,?,?,?)";
		try {
			
			PreparedStatement st= con.prepareStatement(sql);
			st.setInt(1,hr.getEmployee_id());
			st.setInt(2,hr.getWorked_hours());
			st.setString(3,hr.getWorked_date());
			st.executeUpdate();
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	
	// funcion que valida el registro de un empleado en una fecha
public worked_ours validacion_parametros(String date, int id) {
	
		String sql ="select * from employee_worked_ours where "
				+ "EMPLOYEE_ID='"+id+"' and WORKED_DATE='"+date+"';";
		

		
		worked_ours w= new worked_ours();
		
			try {
				
				Statement st= con.createStatement();
				ResultSet rs= st.executeQuery(sql);
				if(rs.next()) {
					w.setId(rs.getInt(1));
					w.setEmployee_id(rs.getInt(2));
					w.setWorked_hours(rs.getInt(3));
					w.setWorked_date(rs.getString(4));
					
				}
		
				
				
			}
			
			catch(Exception e) {
				System.out.println(e);
			}

			
			return w;
			
			
		
	}


//funcion que permita consultar el total de horas trabajadas de un empleado por rango de fechas

public List<worked_ours> cuenta_hrs(String inicio, String fin,int id) {
	
	String sql="select sum(worked_hours) from employee_worked_ours where "
			+ "employee_id='"+id+"' and worked_date BETWEEN '"+inicio+"' and '"+fin+"';";


	
	worked_ours th= new worked_ours();
	List<worked_ours> hrs = new ArrayList<>();
		try {
			
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(sql);
			if(rs.next()) {
				th.setTotal_worked_hrs(rs.getInt(1));
				hrs.add(th);
			}
	
			
			
		}
		
		catch(Exception e) {
			System.out.println(e);
		}

		
		return hrs;
		
		
	
}

// funcion que valida que una fecha no sea mayor que otra fecha , las dos fechas se asignan por parametros 
public  int validaFechas(String fecha,String fecha2) {

    int resultado=0;

Date fechaactual = new Date(System.currentTimeMillis());
String fechaInicio = fecha; //fecha de ejemplo
SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
Date fechaInicioDate = null;
Date fechaFinDate=null;
try {
	fechaInicioDate = date.parse(fechaInicio);
	fechaFinDate = date.parse(fecha2);
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}  //String a date

//comprueba si es que inicio esta después que fecha actual
if(fechaInicioDate.after(fechaFinDate)){
   resultado=1;
}

    
    return resultado;
}
	
}
