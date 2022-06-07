package com.alexis.ejercicios;


import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alexis.ejercicios.Jobs;
import com.alexis.ejercicios.JobsRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Path("empleados")
public class EmployeesResource {
	EmployeesRepository empleado= new EmployeesRepository();
	gendersRepository gen= new gendersRepository(); 
	JobsRepository jo=new JobsRepository(); 

	
	
	//metodo correspondiente al ejercicio 3 Realiza un web service que permita consultar
	//los empleados por puesto.
	//Se debe validar que el puesto exista.

	
	@GET
	@Path("ejercicio3/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerLista(@PathParam("id") int id) throws Exception {
	
		//almacenamos en la variable lista de tipo lista el metodo que nos devuelve una lista y le pasamos el id del parametro
		Lista lista= empleado.getemployessdetalle(id);
		
		
	// returnamos el resultado
	    return Response.status(200).entity(lista).build();
	  
	}
	
	
	// metodo correspondiente al ejercicio 1 Realiza un web service que permita agregar un nuevo empleado.
	//Se debe validar que el nombre y apellido del empleado no existan,
	//que el empleado sea mayor de edad y que el género y puesto existan en sus tablas correspondientes.


	@POST
	@Path("ejercicio1")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Employees createEmployes1(Employees emp)
	{
		//se valida que nombre y apellido no existan
Employees j= empleado.validacion_parametros(emp.getName(),emp.getLast_name());
	// se valida que exista el registro de jobs con idjobs que se le pase 
Jobs job=jo.getJobs(emp.getIdjobs());
// se valida que exista registro en la tabla gender con el idgenders que se le pase
genders ge=gen.validacion_idgender(emp.getIdgenders());
// se calcula la edad del empleado a resitrar a partir de la fecha de nacimiento
int edad=empleado.calcularEdad(emp.getBirthdate());


//realizamos todas las validaciones correspondientes y en caso de que todo se cumpla se inserta el registor
		if(j.getLast_name()==null && j.getName()==null && job.get_id()!=0 && ge.getId()!=0 && edad>=18)
		{
			empleado.create(emp);		
			return emp;
		}
		
		// en caso de que no se cumpla retornamos:
		else {
			return null;
		}
		
		
	}


}
