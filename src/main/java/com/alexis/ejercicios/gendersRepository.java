package com.alexis.ejercicios;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alexis.ejercicios.Jobs;

public class gendersRepository {

	
	Connection con =null;
	// creamos el constructor para poder realizar la conexion a la base de datos
	public gendersRepository() {
		
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
		

		// se valida que exista un registro en la tabla genders apartir del id que se le pase 
		public genders validacion_idgender(int id) {
			
			String sql="select * from genders where ID='"+id+"';";

			
			genders g= new genders();
			
				try {
					
					Statement st= con.createStatement();
					ResultSet rs= st.executeQuery(sql);
					if(rs.next()) {
					g.setId(rs.getInt(1));
					g.setName(rs.getString(2));
					}
			
					
					
				}
				
				catch(Exception e) {
					System.out.println(e);
				}

				
				return g;
				
				
			
		}

	
	
}