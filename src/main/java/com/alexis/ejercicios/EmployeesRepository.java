package com.alexis.ejercicios;



import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


import com.alexis.ejercicios.Jobs;



public class EmployeesRepository {

	genders gen= new genders();
	
	Jobs job = new Jobs();
	
	List<Employees> emp;
	
	Connection con =null;
	// creamos el constructor para poder realizar la conexion a la base de datos
	public EmployeesRepository() {
		
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
	
	
	// creamos la funcion para poder registrar al empleado
	public void create(Employees emp) {
		
		String sql="INSERT INTO employees values(null,?,?,?,?,?)";
		try {
			
			PreparedStatement st= con.prepareStatement(sql);
			st.setInt(1, emp.getIdgenders());
			st.setInt(2, emp.getIdjobs());
			st.setString(3, emp.getName());
			st.setString(4,emp.getLast_name());
			st.setString(5,emp.getBirthdate());
			st.executeUpdate();
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	
	// creamos la funcion para validar que no exista un empleado con nombre y apellido iguales a los que se quieren insertar
	public Employees validacion_parametros(String nombre, String Apellido) {
		
		String sql="select * from employees where name='"+nombre+"' and last_name='"+Apellido+"';";

		
		Employees j= new Employees();
		
			try {
				
				Statement st= con.createStatement();
				ResultSet rs= st.executeQuery(sql);
				if(rs.next()) {
					
					j.setIdgenders(rs.getInt(2));
					j.setIdjobs(rs.getInt(3));
					j.setName(rs.getString(4));
					j.setLast_name(rs.getString(5));
					j.setBirthdate(rs.getString(6));
				}
		
				
				
			}
			
			catch(Exception e) {
				System.out.println(e);
			}

			
			return j;
			
			
		
	}

	
	// creamos una funcion para calcular la edad apartir de la fecha que se le pase por parametro y la que se quiera calcular
	public  int calcularEdad(String fecha) {
	   
		 String dia_cadena=fecha.substring(8,10);
	     String mes_cadena=fecha.substring(5,7);
         String ano_cadena=fecha.substring(0,4);
		
		int day=Integer.parseInt(dia_cadena);
		int month=Integer.parseInt(mes_cadena);
		int year=Integer.parseInt(ano_cadena);
		
		Calendar fechaNacimiento = new GregorianCalendar(year, month, day);
	    Calendar fechaActual = Calendar.getInstance();

	    //Calcula diferencias.
	    int years = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
	    int months = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
	    int days = fechaActual.get(Calendar.DAY_OF_MONTH) - fechaNacimiento.get(Calendar.DAY_OF_MONTH);

	    if(months < 0 //Aun no es mes de cumpleaños.
	        || (months == 0 && days < 0)){//Es el mes pero no ha llegado el día.
	        years--; //Se resta 1 a la diferencia de años.
	    }
	    return years;
	}
	
	// funcion para validar que id de empleado exista
	public Employees valida_empleado(int id)
 {
		
		String sql="select * from employees where  ID='"+id+"';";

		
		Employees j= new Employees();
		
			try {
				
				Statement st= con.createStatement();
				ResultSet rs= st.executeQuery(sql);
				if(rs.next()) {
					j.setId(rs.getInt(1));
					j.setIdgenders(rs.getInt(2));
					j.setIdjobs(rs.getInt(3));
					j.setName(rs.getString(4));
					j.setLast_name(rs.getString(5));
					j.setBirthdate(rs.getString(6));
				}
		
				
				
			}
			
			catch(Exception e) {
				System.out.println(e);
			}

			
			return j;
			
			
		
	}
	
	
//funcion para obtener los empleados por puesto y su gender

	public Lista getemployessdetalle(int id){
	
	String sql="select * from genders INNER JOIN employees "
			+ "on employees.GENDER_ID=genders.ID INNER JOIN jobs "
			+ "on jobs.ID=employees.JOB_ID where Jobs.ID='"+id+"';";
	
	  Lista lista = new Lista();
		

		try {
			
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(sql);
			
			 List<Jobs> resultado = new ArrayList();
			 
			  List<Employees> resultado2 = new ArrayList();	    
			  List<genders> resultado3 = new ArrayList();
		
			  
			 
			  
			
			while(rs.next()) {
					Employees emp= new Employees();
	Jobs j= new Jobs();
	genders gen= new genders();
			emp.setId(rs.getInt(3));
			emp.setName(rs.getString(6));
			emp.setLast_name(rs.getString(7));
			emp.setBirthdate(rs.getString(8));
			j.set_name(rs.getString(10));
			j.set_salary(rs.getDouble(11));
			gen.setId(rs.getInt(1));
			gen.setName(rs.getString(2));
			 resultado.add(j);
			   lista.setJob(resultado);
			  resultado2.add(emp);	
			  
			  lista.setEmployees(resultado2);
			  resultado3.add(gen);
			  
			
			  lista.setGender(resultado3);			  
			 
			}
	
			
			
		}
		
		catch(Exception e) {
			System.out.println(e);
		}

		
		return lista;
		
		
	
}
   
}