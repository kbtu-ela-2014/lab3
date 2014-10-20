package StudentsList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.management.Query;
import javax.swing.JOptionPane;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class ListManage {

	 public static int getNumOfStudents() throws SQLException {
		    Session session = null;
		    int cnt=0;
		    try {
		      session = HibernateUtil.getSessionFactory().getCurrentSession();
		      session.beginTransaction();
		      Query query = (Query) session.createQuery(
		          " select count(id) "
		              + " from students "
		      );
		          
		      String str = query.toString();
		   
		      for(int i=0; i<str.length(); i++){
		    	  cnt+=str.toCharArray()[i]-48;
		      }
		      session.getTransaction().commit();

		    } finally {
		      if (session != null && session.isOpen()) {
		        session.close();
		      }
		    }
		    return cnt;
		  }
	
	 public static String viewOfList() throws IOException{
		 List<Student> allStudents = null;
		 String response = new String();
         
			try {
				allStudents = getAllStudents();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
     
			for(int i=0; i<allStudents.size(); i++ ){
					response += TransformData.viewStudent(allStudents.get(i));   	
			}
			return response;

	 }
	 
	 @SuppressWarnings("unchecked")
	public static List<Student> getAllStudents() throws SQLException {
		    Session session = null; 
		   List allStudents = new ArrayList<Student>();
		   try {
		      session = HibernateUtil.getSessionFactory().openSession();
		      String sql = "SELECT * FROM Students";
		      SQLQuery query = session.createSQLQuery(sql);
		      query.addEntity(Student.class);
		      //query.setParameter("user_id", 3);
		      allStudents = query.list();
		    } finally {
		      if (session != null && session.isOpen()) {
		        session.close();
		      }
		    }
		    return allStudents;
		  }
	 
	public static void addStudent(Student student){
		Session session = null;
		try {
		      session = HibernateUtil.getSessionFactory().openSession();
		     
		      session.save(student);
		     
		    } catch (Exception e) {
		      JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
		    } finally {
		      if (session != null && session.isOpen()) {

		        session.close();
		      }
		    }
		
	}
	
public static void deleteStudent(Student student){
	Session session = null;
	System.out.println("hello ="+student.getId());
	try {
	      session = HibernateUtil.getSessionFactory().openSession();
	      //student = (Student) session.get(Student.class, student.getId());
	      session.beginTransaction();
	      Student st = (Student)getStudentById(student.getId());
	      session.delete( st);     
	      session.getTransaction().commit();
	    } catch (Exception e) {
	      JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);
	    } finally {
	      if (session != null && session.isOpen()) {

	        session.close();
	      }
	    }
		
	
	}

public static void editStudent(Student student, String lastName, String firstName){
	Session session = null;
	try {
	   	  
	      session = HibernateUtil.getSessionFactory().openSession();
	      Student st = (Student)getStudentById(student.getId());
	      student.setFirstName(firstName);
	      student.setLastName(lastName); 
	      session.beginTransaction();
	      
	      session.update(st);   
	      session.getTransaction().commit();
	    } catch (Exception e) {
	      JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
	    } finally {
	      if (session != null && session.isOpen()) {

	        session.close();
	      }
	    }
	
}
public static Student getStudentById(int id) throws SQLException {
    Session session = null;
    Student student = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      student = (Student) session.load(Student.class, id);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
    return student;
  }
}
