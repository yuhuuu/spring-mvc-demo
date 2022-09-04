package com.springtut.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=hb_student_tracker;user=hbstudent;password=hbstudent;encrypt=false;";  
		
		try {
		System.out.println("Connecting to Database");
		
		Connection myCon = DriverManager.getConnection(jdbcUrl);
		
		System.out.println("Connection successful!");
		
		myCon.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
