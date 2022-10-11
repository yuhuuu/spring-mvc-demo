package com.springtut.hibernate.demo;

import java.util.Date;
import java.util.List;

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
			//get the instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("SpringTut : Instructor: " + tempInstructor);
			
			session.getTransaction().commit();
			
			//close the session
			session.close();
			
			System.out.println("SpringTut : The session is now closed \n");
			
			//this happens somewhere else / later in the program. 
			//you need to get a new session
			
			System.out.println("SpringTut : Opening a NEW session");
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			Query<Course> query = session.createQuery("select c from Course c "
					+ "where "
					+ "c.instructor.id =:theInstructorId",Course.class);
			
			query.setParameter("theInstructorId", theId);
			
			List<Course> tempCourses = query.getResultList();
			
			System.out.println("tempCourses: " + tempCourses);
			
			//now assign to instructor object in memory
			tempInstructor.setCourses(tempCourses);
			
			System.out.println("SpringTut : Courses: "+ tempInstructor.getCourses());
			
			session.getTransaction().commit();
			
			System.out.println("SpringTut: Done!");
			
//			//option 2: Hibernate query with HQL
//			
//			
//			Query<Instructor> query = session.createQuery("select i from Instructor i "
//															+ "JOIN FETCH i.courses "
//															+ "where i.id =: theInstructorId", Instructor.class);
//			//set parameter on query
//			query.setParameter("theInstructorId", theId);
//			Instructor instructor = query.getSingleResult();
//			System.out.println("SpringTut: Instructor: " + instructor);
//			System.out.println("SpringTut: Courses: " + instructor.getCourses() );			
//			
//			//commit the transaction
//			session.getTransaction().commit();
//			
//			session.close();
//			
//					
//			System.out.println("Done!");
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}

}
