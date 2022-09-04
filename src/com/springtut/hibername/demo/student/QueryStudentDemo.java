package com.springtut.hibername.demo.student;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springtut.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		//create session factory
				SessionFactory factory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();
				
				//create session
				Session session = factory.getCurrentSession();
			
				try {
					
					//now get a new session 
					session = factory.getCurrentSession();
					session.beginTransaction();
					
					//retrieve all students
					System.out.println("List all students");
					List<Student> myStudents = session.createQuery("from Student").list() ;
					dsiplayStudents(myStudents);
					
					//retrieve a student which last name is Kwan
					System.out.println("List of student whose lastName is Kwan");
					myStudents = session.createQuery("from Student where lastName='Kwan'").list() ;
					dsiplayStudents(myStudents);
					
					System.out.println("Student whose firstName is Bonita or lastName is Kwan");
					//retrieve a student which last name is Kwan or firstname is Bonita
					myStudents = session.createQuery("from Student where lastName='Kwan' OR firstName='Bonita'").list() ;
					dsiplayStudents(myStudents);
					
					//retrieve a student which email like daffy 
					System.out.println("Student where email like 'daffy%'");
					myStudents = session.createQuery("from Student where email like 'daffy%'").list() ;
					dsiplayStudents(myStudents);
					
					//commit the transaction
					session.getTransaction().commit();
					
					System.out.println("Done!");
				}finally {
					session.close();
					factory.close();
				}

	}

	private static void dsiplayStudents(List<Student> myStudents) {
		for(Student s : myStudents ) {
			System.out.println(s);
		}
	}

}


