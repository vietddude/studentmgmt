<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Course Details</title>
    <style>
        /* Add your CSS styles here */
        body {
            font-family: Arial, sans-serif;
            font-size: 16px;
        }

        h1, h2 {
            color: #333;
            margin-top: 20px;
        }

        table {
            border-collapse: collapse;
            margin-top: 20px;
            width: 100%;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        label {
            display: inline-block;
            margin-top: 20px;
        }

        select {
            display: inline-block;
            margin-left: 10px;
            padding: 5px 10px;
        }

        input[type="submit"] {
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            color: #fff;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
            padding: 10px 20px;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0062cc;
        }
        /* Style for the dropdown */
        select {
            padding: 12px 20px;
            border-radius: 4px;
            background-color: #f1f1f1;
        }
        /* Style for the input box */
        input[type=number], input[type=text] {
            padding: 12px 20px;
            margin: 8px 0;
            box-sizing: border-box;
            border-radius: 4px;
        }
        /* Style for the Delete button */
        a {
            background-color: #f44336;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
        }
        a:hover {
            background-color: #f13326;
        }
        #return {
            background-color: #22b422;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
        }
        #return:hover {
            background-color: #018804;
        }
    </style>
</head>
<body>
<h1>Course Details</h1>
<p><strong>Course ID:</strong> ${course.courseId}</p>
<p><strong>Name:</strong> ${course.name}</p>
<p><strong>Lecture:</strong> ${course.lecture}</p>
<p><strong>Year:</strong> ${course.year}</p>
<p><strong>Notes:</strong> ${course.notes}</p>
<br>
<h2>Add Student</h2>
<form action="?action=add" method="post">
    <label for="studentId">Student:</label>
    <select name="studentId" id="studentId">
        <c:forEach items="${availableStudents}" var="student">
            <option value="${student.studentId}">${student.name}</option>
        </c:forEach>
    </select>
    <label for="grade">Grade:</label>
    <input type="number" name="grade" id="grade" min="0" max="10" step="0.1" required>
    <input type="hidden" name="courseId" value="${course.courseId}">
    <input type="submit" value="Add">
</form>
<br>
<h2>Enrolled Students</h2>
<table>
    <tr>
        <th>Student ID</th>
        <th>Name</th>
        <th>Grade</th>
        <th></th>
    </tr>
    <c:forEach items="${students}" var="student" varStatus="status">
        <tr>
            <td>${student.studentId}</td>
            <td>${student.name}</td>
            <td>${grades[status.index]}</td>
            <td>
                <a href="?action=remove&courseId=${course.courseId}&studentId=${student.studentId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a id="return" href="?action=list">Back to Course List</a>
</body>

</html>