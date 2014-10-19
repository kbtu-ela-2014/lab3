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

public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        int id = Integer.parseInt(request.getParameter("id"));
        Student student = (Student) session.load(Student.class, id);

        Transaction transaction = session.beginTransaction();
        session.delete(student);
        transaction.commit();

        response.sendRedirect("main");
    }
}
