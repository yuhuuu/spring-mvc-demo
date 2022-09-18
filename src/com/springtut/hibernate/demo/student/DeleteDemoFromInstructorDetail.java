package com.springtut.hibernate.demo.student;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springtut.hibernate.demo.DateUtils;
import com.springtut.hibernate.demo.entity.Instructor;
import com.springtut.hibernate.demo.entity.InstructorDetail;


public class DeleteDemoFromInstructorDetail {

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
			//insert new user
			Instructor instructor = new Instructor("User","Delete", "delete@gmail.com");
			InstructorDetail instructorDetail = new InstructorDetail("youtube.com", "Trial Code");
			
			instructor.setInstructorDetail(instructorDetail);
			
			//start transaction 
			session.beginTransaction();
			
			//save the instructor
			//note: this will ALSO save the details object because of CascadeType.ALL
			System.out.println("Saving instructor: "+ instructor);
			session.save(instructor);
						
			//commit the transaction
			session.getTransaction().commit();
			
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<InstructorDetail> instructorDetails = session.createQuery("from InstructorDetail where hobby = 'Trial Code'").list();
			for(InstructorDetail tempInstructorDetail : instructorDetails) {
				tempInstructorDetail.getInstructor().setInstructorDetail(null);
				session.remove(tempInstructorDetail);
			}
			
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
