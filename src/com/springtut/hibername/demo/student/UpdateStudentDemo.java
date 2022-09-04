

package com.springtut.hibername.demo.student;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springtut.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

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
					System.out.println("Get a studemt whose StudentId = 6");
					int  studentId= 6;
					Student myStudent = session.get(Student.class, studentId);
					System.out.println("Before update : " + myStudent);
					
					myStudent.setFirstName("Bona");
					myStudent.setLastName("Boni");
					myStudent.setEmail("bona@msn.com");
					
					
					//commit the transaction
					session.getTransaction().commit();
					
					session = factory.getCurrentSession();
					session.beginTransaction();
					List<Student> students = session.createQuery("from Student where id = "+ studentId).list();
					dsiplayStudents(students);
				
					students = session.createQuery("from Student").list();
					dsiplayStudents(students);
					
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