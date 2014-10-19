package student;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;



class Test {


	public static void main(String[] args) {

		Student_Info st = new Student_Info();
		Student_Info st2 = new Student_Info();
		Student_Info st3 = new Student_Info();
		st.setStudentName("Chris");
		st.setStudentLastName("Martin");
		st2.setStudentName("Adam");
		st2.setStudentLastName("Levine");
		st3.setStudentName("Gwen");
		st3.setStudentLastName("Stefani");

		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(st);
		session.save(st2);
		session.save(st3);
		st.setStudentName("Keit");
		session.update(st);
		
		session.delete(st2);

		session.getTransaction().commit();
		session.close();

	}



}
