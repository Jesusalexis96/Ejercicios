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


import com.alexis.ejercicios.Jobs;
import com.alexis.ejercicios.JobsRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Path("horas")
public class worked_oursResource {
	EmployeesRepository empleado= new EmployeesRepository();
	gendersRepository gen= new gendersRepository(); 
	
	JobsRepository jo=new JobsRepository();
	
	worked_oursRepository hr=new worked_oursRepository();
	
	// metodo correspondiente al ejercicio 4
	//Realiza un web service que permita consultar el total de horas trabajadas de un 
	//empleado por rango de fechas.
	//Se debe validar que el empleado exista y que la fecha de inicio sea menor a la fecha de fin.

	
	@GET
	@Path("ejercicio4/{id}/{inicio}/{fin}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<worked_ours> getotalhrs( @PathParam("id") int id, @PathParam("inicio") String inicio,@PathParam("fin") String fin)
	{
		// se valida que el empleado exista 
		Employees emp=empleado.valida_empleado(id);
		// se valida que la fecha de inicio no sea mayor a la fecha fin 
		int h=hr.validaFechas(inicio, fin);
		//si se cumplen las validaciones se retorna el resultado correspondiente
		if(h==0 && emp.getId()>0)
		{
			return hr.cuenta_hrs(inicio, fin, id);	
		}
					
		else {
			return null;
		}
	}
	
	// metodo correspondiente al ejercicio 2 Realiza un web service que permita agregar horas 
	//trabajadas de un empleado 
	//Se debe validar que el empleado exista, que el total de horas 
	//trabajadas no sea mayor a 20 horas y que la fecha de trabajo sea menor o igual a la actual 
	//y no se duplique por empleado (un empleado sólo puede tener un registro de horas trabajadas por día).

	
	@POST
	@Path("ejercicio2")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public worked_ours createEmployes1(worked_ours hrs)
	{
		// se valida que el empleado exista 
Employees j= empleado.valida_empleado(hrs.getEmployee_id());

// se valida que la fecha sea menor o igual a la actual
int h=hr.calcularFecha(hrs.getWorked_date());
// se valida que no se duplique registro de hr de empleado por fecha
worked_ours val=hr.validacion_parametros(hrs.getWorked_date(), hrs.getEmployee_id()); 	


if(h==0 && hrs.getWorked_hours()<=20 && j.getId()>0 && val.getId()<1 )
{
	hr.create(hrs);
	return hrs;
}
else {
	return null;
}
		
			
	}

}


