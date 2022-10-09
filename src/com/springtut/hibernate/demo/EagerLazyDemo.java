package com.springtut.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.springtut.hibernate.demo.entity.Course;
import com.springtut.hibernate.demo.entity.Instructor;
import com.springtut.hibernate.demo.entity.InstructorDetail;


public class EagerLazyDemo {

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
			
			
			//option 2: Hibernate query with HQL
			
			//get the instructor from db
			int theId = 1;
			Query<Instructor> query = session.createQuery("select i from Instructor i "
															+ "JOIN FETCH i.courses "
															+ "where i.id =: theInstructorId", Instructor.class);
			//set parameter on query
			query.setParameter("theInstructorId", theId);
			Instructor instructor = query.getSingleResult();
			System.out.println("SpringTut: Instructor: " + instructor);
			System.out.println("SpringTut: Courses: " + instructor.getCourses() );			
			
			//commit the transaction
			session.getTransaction().commit();
			
			session.close();
			
					
			System.out.println("Done!");
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}

}
