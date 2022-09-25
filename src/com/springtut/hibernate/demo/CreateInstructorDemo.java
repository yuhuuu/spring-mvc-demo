package com.springtut.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springtut.hibernate.demo.entity.Course;
import com.springtut.hibernate.demo.entity.Instructor;
import com.springtut.hibernate.demo.entity.InstructorDetail;


public class CreateInstructorDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		
		try {
			
			//start transaction 
			session.beginTransaction();
			
			//get instructor from db
			int theConstructorId = 1;
			Instructor instructor = session.get(Instructor.class, theConstructorId);
			
			System.out.println("Instructor :" + instructor);
			
			//get course for the instructor
			System.out.println("Courses : " + instructor.getCourses());
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}

}
