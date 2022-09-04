package com.springtut.hibername.demo.student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springtut.hibernate.demo.DateUtils;
import com.springtut.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
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
					Student newStudent = new Student("Mario","Bros", "mario@nitendo.com", DateUtils.parseDate("31/7/1998"));
					
					//start transaction
					session.beginTransaction();
					
					System.out.println("Saving the student...");
					System.out.println(newStudent);
					
					//save the student
					session.save(newStudent);
					
					//commit the transaction
					session.getTransaction().commit();
					
					
					//now get a new session 
					session = factory.getCurrentSession();
					session.beginTransaction();
					
					//retrieve student based on the id: primary key
					System.out.println("Getting student with id: " + newStudent.getId());
					Student myStudent = session.get(Student.class, newStudent.getId());
					
					System.out.println("Get complete: " + myStudent);
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
