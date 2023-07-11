<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Details</title>
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
        #return {
            background-color: #22b422;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin-top: 20px;
        }
        #return:hover {
            background-color: #018804;
        }
    </style>
</head>
<body>
<h1>Student Details</h1>
<form>
    <input type="hidden" name="id" value="${student.studentId}"/>
    <label for="year">Select Year:</label>
    <select name="year" id="year" onchange="selectYear()">
        <option value="">All Years</option>
        <c:forEach var="year" items="${years}">
            <option value="${year}" <c:if test="${year eq param.year}">selected</c:if>>${year}</option>
        </c:forEach>
    </select>
</form>
<hr/>
<p><strong>Student ID:</strong> ${student.studentId}</p>
<p><strong>Student Name:</strong> ${student.name}</p>

<script>
    function selectYear() {
        var year = document.getElementById("year").value;
        if (year === "") {
            window.location.href = "student?action=detail&id=${student.studentId}";
        } else {
            window.location.href = "student?action=detail&id=${student.studentId}&year=" + year;
        }
    }
</script>
<c:choose>
    <c:when test="${empty param.year}">
        <h2>All Courses Taken</h2>
        <table>
            <thead>
            <tr>
                <th>Course Name</th>
                <th>Grade</th>
                <th>Year</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${studentCourses}">
                <tr>
                    <td>${course.name}</td>
                    <td>${grades[course.name]}</td>
                    <td>${course.year}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <h2>Courses Taken in ${param.year}</h2>
        <table>
            <thead>
            <tr>
                <th>Course Name</th>
                <th>Grade</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${studentCourses}">
                <c:if test="${course.year eq param.year}">
                    <tr>
                        <td>${course.name}</td>
                        <td>${grades[course.name]}</td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
<a id="return" href="?action=list">Back to Student List</a>
</body>
</html>
