package labbb3;
import java.util.List; 
import java.util.Date;
import java.util.Iterator; 
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageStudent {
   private static SessionFactory factory; 
   public ManageStudent(){
	   try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
   }
   
  
   /* Method to CREATE an Student in the database */
   public Integer addStudent(String fname, String lname, int year){
      Session session = factory.openSession();
      Transaction tx = null;
      Integer StudentID = null;
      try{
         tx = session.beginTransaction();
         Student Student = new Student(fname, lname, year);
         StudentID = (Integer) session.save(Student); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return StudentID;
   }
   /* Method to  READ all the Students */
   public List<Student> listStudents( ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         List<Student> students = session.createQuery("FROM Student").list(); 
         tx.commit();
         return students;         
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return null;
   }
   /* Method to UPDATE year for an Student */
   public void update(Integer StudentID,  String fname, String lname, int year ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Student Student = 
                    (Student)session.get(Student.class, StudentID); 
         Student.setFirstName(fname);
         Student.setLastName(lname);
         Student.setYear( year );
		 session.update(Student); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   /* Method to DELETE an Student from the records */
   public void deleteStudent(Integer StudentID){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Student Student = 
                   (Student)session.get(Student.class, StudentID); 
         session.delete(Student); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
}