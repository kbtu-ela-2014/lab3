package StudentsList;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	 private static final SessionFactory sessionFactory;
	 
	    static {
	        try {
	            //creates the session factory from hibernate.cfg.xml
	            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	        } catch (ExceptionInInitializerError ex) {
	            System.err.println("Initial SessionFactory creation failed: " + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }
	    
	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
}
