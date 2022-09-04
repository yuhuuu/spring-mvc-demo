package com.springtut.hibername.demo.student;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springtut.hibernate.demo.DateUtils;
import com.springtut.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		property name="hbm2ddl.auto">create</property
		
		//create session factory
				SessionFactory factory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();
				
				//create session
				Session session = factory.getCurrentSession();
			
				try {
					//use the session object to save/retrieve Java objects
					

					//create a student object
					System.out.println("Creating new student object");
					Student newStudent1 = new Student("Paul","Yu", "paul.yu.yu@gmail.com", DateUtils.parseDate("21/2/1982"));
					Student newStudent2 = new Student("Mary","Kwan", "mary@gmail.com", DateUtils.parseDate("1/1/1982"));
					Student newStudent3 = new Student("Bonita","Lily", "bonita@gmail.com", DateUtils.parseDate("31/5/1978"));
					
					//start transaction
					session.beginTransaction();
					
					System.out.println("Saving the student...");
					//save the student
					session.save(newStudent1);
					session.save(newStudent2);
					session.save(newStudent3);
					
					//commit the transaction
					session.getTransaction().commit();
					
					System.out.println("Done!");
				}
				catch(Exception ex) {
					ex.printStackTrace();
				
				}finally {
					factory.close();
				}
	}

}
