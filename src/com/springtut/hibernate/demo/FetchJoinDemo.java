package com.springtut.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springtut.hibernate.demo.entity.Course;
import com.springtut.hibernate.demo.entity.Instructor;
import com.springtut.hibernate.demo.entity.InstructorDetail;


public class FetchJoinDemo {

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
			
			//get the instructor from db
			int theId = 1;
			Instructor instructor = session.get(Instructor.class, theId);
			
			System.out.println("SpringTut: Instructor: " + instructor);
						
			//commit the transaction
			session.getTransaction().commit();
			
			session.close();
			
			//get cources for the instructor
			System.out.println("SpringTut: Cources: "+instructor.getCourses());
			
			
			System.out.println("Done!");
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}

}
