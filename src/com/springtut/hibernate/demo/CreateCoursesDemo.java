package com.springtut.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springtut.hibernate.demo.entity.Course;
import com.springtut.hibernate.demo.entity.Instructor;
import com.springtut.hibernate.demo.entity.InstructorDetail;


public class CreateCoursesDemo {

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
			String theDateOfBirthStr = "31/12/1998";
			Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
			
			Instructor instructor = new Instructor("Susan","Publica", "susan.publica@gmail.com");
			InstructorDetail instructorDetail = new InstructorDetail("youtube.com", "Video Games");
			
			instructor.setInstructorDetail(instructorDetail);
			
			//start transaction 
			session.beginTransaction();
			
			//save the instructor
			//note: this will ALSO save the details object because of CascadeType.ALL
			System.out.println("Saving instructor: "+ instructor);
			session.save(instructor);
						
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
