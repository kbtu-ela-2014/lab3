package servlets;

import models.HibernateUtil;
import models.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        Student student = new Student();

        student.setName(request.getParameter("name"));
        student.setYear(Integer.parseInt(request.getParameter("year")));

        session.save(student);
        transaction.commit();

        response.sendRedirect("main");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
