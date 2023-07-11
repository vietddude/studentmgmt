<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Enrollment List</title>
  <style>
    table {
      border-collapse: collapse;
      width: 100%;
    }
    th, td {
      text-align: left;
      padding: 8px;
    }

    th {
      background-color: #008CBA;
      color: white;
    }

    a {
      background-color: #008CBA;
      color: white;
      padding: 6px 16px;
      border-radius: 4px;
      text-decoration: none;
    }

    a:hover {
      background-color: #005580;
    }
  </style>
</head>
<body>
<h1>Enrollment List</h1>
<table>
  <thead>
  <tr>
    <th>Student ID</th>
    <th>Course ID</th>
    <th>Year</th>
    <th>Grade</th>
    <th></th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${enrollments}" var="enrollment">
    <tr>
      <td>${enrollment.studentId}</td>
      <td>${enrollment.courseId}</td>
      <td>${enrollment.year}</td>
      <td>${enrollment.grade}</td>
      <td>
        <a href="?action=update&studentId=${enrollment.studentId}&courseId=${enrollment.courseId}&year=${enrollment.year}">Update</a>
        <a href="?action=delete&studentId=${enrollment.studentId}&courseId=${enrollment.courseId}&year=${enrollment.year}">Delete</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<br>
<a href="index.jsp">Back to Home</a>
<a href="?action=insert">Add New Enrollment</a>
</body>
</html>