<%@ page import="java.util.Iterator" %>
<%@ page import="models.Student" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  Date: 15.10.2014
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<p>List of all students:</p>
<p>
<table border='1'>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Year</th>
        <th>&nbsp;</th>
        <th>&nbsp;</th>
    </tr>
    <%
        List<Student> students = (List<Student>) request.getAttribute("students");
        Iterator i = students.iterator();
        Student student;
        while (i.hasNext()) {
            student = (Student) i.next();
            out.println("<tr>");
            out.println("<td>" + student.getId() + "</td>");
            out.println("<td>" + student.getName() + "</td>");
            out.println("<td>" + student.getYear() + "</td>");
            out.println("<td><a href=\"update?id=" + student.getId() + "\">[Update]</a></td>");
            out.println("<td><a href=\"delete?id=" + student.getId() + "\">[Delete]</a></td>");
            out.println("</tr>");
        }
    %>
</table>
</p>
<p>
<form action="add" method="post">
    Add new student:<br>
    <input type="text" name="name" placeholder="Name"><br>
    <input type="text" name="year" placeholder="Year"><br>
    <input type="submit">
</form>
</p>
</body>
</html>
