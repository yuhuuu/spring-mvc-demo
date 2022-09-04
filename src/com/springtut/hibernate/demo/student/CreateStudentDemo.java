package com.springtut.hibernate.demo.student;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springtut.hibernate.demo.DateUtils;
import com.springtut.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		
		try {
			String theDateOfBirthStr = "31/12/1998";
			Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
			
			//use the session object to save/retrieve Java objects
			//create a student object
			System.out.println("Creating new student object");
			Student newStudent = new Student( "Yu","Yu", "yu.yu@gmail.com", theDateOfBirth);
			
			//start transaction
			session.beginTransaction();
			
			System.out.println("Saving the student...");
			//save the student
			session.save(newStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			factory.close();
		}
	}

}
