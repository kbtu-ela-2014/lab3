package servlets;

import models.HibernateUtil;
import models.Student;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;


public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Student> students = session.createCriteria(Student.class).list();

        request.setAttribute("students", students);
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }
}
