package com.springtut.hibernate.demo.employee;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springtut.hibernate.demo.entity.Employee;
import com.springtut.hibernate.demo.entity.Student;




public class EmployeeDemo {
	
		public static void main(String[] args) {

			//create factory
			SessionFactory factory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Employee.class)
					.buildSessionFactory();			

			//create session
			Session session = factory.getCurrentSession();
			List<Employee> employees = null;
			
			try {
			//create new employee
			Employee newEmployee1 = new Employee("Mika","Suwandi", "P&G");
			Employee newEmployee2 = new Employee("Cici","Asep", "Unilever");

			session.beginTransaction();
			
			//save the employee
			System.out.println("Saving employees...");
			session.save(newEmployee1);
			session.save(newEmployee2);
			
			//commit
			session.getTransaction().commit();
			

			//GET Employee
			int id = newEmployee1.getId();
			System.out.println("Get employee by id = " + id);
			session = factory.getCurrentSession();
			session.beginTransaction();
			Employee employee = session.get(Employee.class, id);			
			session.getTransaction().commit();			
			System.out.println(employee);
			
			//Update Employee
			System.out.println("Update employee name to John");
			session = factory.getCurrentSession();
			session.beginTransaction();
			employee = session.get(Employee.class, id);
			employee.setFirstName("John");
			session.getTransaction().commit();
			System.out.println(employee);
		
			//Delete Employee
			System.out.println("Delete employee name John");
			session = factory.getCurrentSession();
			session.beginTransaction();
			employees = session.createQuery("from Employee where firstName = 'John'").list();
			for(Employee e : employees) {
				session.delete(e);
			}
			session.getTransaction().commit();
			
			System.out.println("List of employees");
			session = factory.getCurrentSession();
			session.beginTransaction();
			employees = session.createQuery("from Employee").list();
			for(Employee e : employees) {
				System.out.println(e);
			}
			
			session.getTransaction().commit();

			
			System.out.println("Done");
			
			
			}
			finally {
				session.close();
				factory.close();
			}
		}	
}
