package Model;

import java.util.List;
import java.util.Iterator;

import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class ManageStudent {
	private static SessionFactory factory;	
	ManageStudent() {
		initialize();
	}
	public static void initialize() {
		if (factory == null) {
			try {
				Configuration config = new Configuration();
				config.configure();
				ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
				factory = config.buildSessionFactory(serviceRegistry);
			} catch (Throwable ex) {
				System.err.println("Failed to create sessionFactory object." + ex);
				throw new ExceptionInInitializerError(ex);
			}
		}
	}	
	
	public static Integer addStudent(String fname, String lname, String faculty, int yof) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer studentID = null;
		try {
			tx = session.beginTransaction();
			Student student = new Student(fname, lname, faculty, yof);
			studentID = (Integer) session.save(student);
			tx.commit();
		} 
		catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} 
		finally { 
			session.close();
		}		
		return studentID;
	}
	
	@SuppressWarnings("unchecked")
	public static String listStudents() {
		Session session = factory.openSession();		
		Transaction tx = null;
		StringBuilder html = new StringBuilder();
		html.append("<table border = \"1\" align=\"center\"><tr><td> ID </td>" + 
						"<td> First nasme </td>" + 
						"<td> Last name </td>" +
						"<td> Faculty </td>" + 
						"<td> Year of study </td>");
		try {
			tx = session.beginTransaction();
			List<Student> students = session.createQuery("FROM Student").list();
			for (Iterator<Student> iterator = students.iterator(); iterator.hasNext();) {
				Student student = iterator.next();
				html.append("<tr><td>" + student.getId() + "</td>" +
								"<td>" + student.getFirstName() + "</td>" +
								"<td>" + student.getLastName() + "</td>" + 
								"<td>" + student.getFaculty() + "</td>" + 
								"<td>" + student.getYearOfStudy() + "</td></tr>");				
			}			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		html.append("</table>");
		return html.toString();
	}

	public static void updateStudent(Integer studentID, String faculty) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Student student = (Student) session.get(Student.class, studentID);
			student.setFaculty(faculty);
			session.update(student);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void deleteStudent(Integer studentID) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Student student= (Student) session.get(Student.class, studentID);
			session.delete(student);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
