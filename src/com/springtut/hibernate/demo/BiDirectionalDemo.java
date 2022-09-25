package com.springtut.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springtut.hibernate.demo.entity.Instructor;
import com.springtut.hibernate.demo.entity.InstructorDetail;

public class BiDirectionalDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			int id = 2;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
			if (instructorDetail != null) {
				
				Instructor instructor = instructorDetail.getInstructor();
				
				System.out.println("Instructor: " + instructor);
				System.out.println("Instructor Detail: " + instructorDetail);
			}
			else System.out.println("ID is not found.");
			
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		}finally {
			session.close();
			factory.close();
		}

	}

}
