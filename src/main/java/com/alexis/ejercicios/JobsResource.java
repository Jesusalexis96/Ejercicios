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

import java.util.Arrays;
import java.util.List;

@Path("jobs")
public class JobsResource {

	//creamos un objeto de nuestro repositorio para poder llamar al metodo getjobs
	EmployeesRepository empleado= new EmployeesRepository();
	worked_oursRepository hr=new worked_oursRepository();
	JobsRepository repo = new JobsRepository();
	
	
	// metodo correspondiente al ejercicio 5 Realiza un web service que permita consultar cuanto 
	//se le pagó a un empleado en un rango de fechas.
	//Se debe validar que el empleado exista y que la fecha de inicio sea menor a la fecha de fin.

	
	
	@GET
	@Path("ejercicio5/{id}/{inicio}/{fin}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Jobs> getsalario( @PathParam("id") int id, @PathParam("inicio") String inicio,@PathParam("fin") String fin)
	{
		// se valida que el empleado exista
		Employees emp=empleado.valida_empleado(id);
		// se valida que la fecha de inicio sea menor a la fecha fin
		int h=hr.validaFechas(inicio, fin);
		
		//si se cumplen las validaciones devolvemos el resultado correspondiente
		if(h==0 && emp.getId()>0)
		{
			return repo.cuenta_salario(inicio, fin, id);
		}
					
		return null;
	}
	
	



}
