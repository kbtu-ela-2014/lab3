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
   
   public static void main(String[] args) {
      //ManageStudent ME = new ManageStudent();

      /* Add few Student records in database */
     // Integer empID1 = ME.addStudent("Zara", "Ali", 1,"IT");
      //Integer empID2 = ME.addStudent("Daisy", "Das", 3,"Economic");
      //Integer empID3 = ME.addStudent("John", "Paul", 4,"Oil and Gas");

      /* List down all the Students */
      //ME.listStudents();

      /* Update Student's records */
     // ME.updateStudent(empID1, 2);

      /* Delete an Student from the database */
     // ME.deleteStudent(empID2);

      /* List down new list of the Students */
     // ME.listStudents();
   }
   
   /* Method to CREATE an Student in the database */
   public Integer addStudent(String fname, String lname, int year, String faculty){
      Session session = factory.openSession();
      Transaction tx = null;
      Integer StudentID = null;
      try{
         tx = session.beginTransaction();
         Student Student = new Student(fname, lname, year, faculty);
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
   public String listStudents( ){
      Session session = factory.openSession();
      Transaction tx = null;
      String res="";
      try{
         tx = session.beginTransaction();
         List Students = session.createQuery("FROM Student").list(); 
         for (Iterator iterator = 
                           Students.iterator(); iterator.hasNext();){
            Student Student = (Student) iterator.next(); 
            res+=Student.getId()+"&"+Student.getFirstName()+"&"+Student.getLastName()+"&"+Student.getFaculty()+"&"+Student.getyear()+"!";
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return res;
   }
   /* Method to UPDATE year for an Student */
   public void updateYear(Integer StudentID,  String fname, String lname, String faculty, int year ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Student Student = 
                    (Student)session.get(Student.class, StudentID); 
         Student.setFirstName(fname);
         Student.setLastName(lname);
         Student.setFaculty(faculty);
         Student.setyear( year );
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