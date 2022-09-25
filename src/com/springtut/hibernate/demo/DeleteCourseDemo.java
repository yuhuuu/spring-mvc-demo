package com.springtut.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springtut.hibernate.demo.entity.Course;
import com.springtut.hibernate.demo.entity.Instructor;
import com.springtut.hibernate.demo.entity.InstructorDetail;


public class DeleteCourseDemo {

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
			int id = 2;
			Course course = session.get(Course.class, id);
			
			System.out.println("Deleting course:" + course);
			session.delete(course);
			
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
