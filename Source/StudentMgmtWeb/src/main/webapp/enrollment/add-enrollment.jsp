<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Enrollment</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        form {
            max-width: 500px;
            margin: 0 auto;
        }
        input, textarea {
            padding: 5px;
            margin-bottom: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
            box-sizing: border-box;
            width: 100%;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: #fff;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #3e8e41;
        }
        select {
            padding: 5px;
            margin-bottom: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
            box-sizing: border-box;
            width: 100%;
        }
    </style>
</head>
<body>
<h1>Add Enrollment</h1>
<form method="post" action="">
    <input type="hidden" name="action" value="insert">
    <br>
    <label for="course">Course:</label>
    <select name="courseId" id="course" required>
        <option value="">-- Select Course --</option>
        <c:forEach var="year" items="${years}">
            <optgroup label="${year}">
                <c:forEach var="course" items="${yearToCoursesMap[year]}">
                    <option value="${course.courseId}">${course.name}</option>
                </c:forEach>
            </optgroup>
        </c:forEach>
    </select>

    <label for="studentId">Student:</label>
    <select id="studentId" name="studentId" required>
        <c:forEach var="student" items="${students}">
            <option value="${student.studentId}">${student.name}</option>
        </c:forEach>
    </select>

    <label for="grade">Grade:</label>
    <input type="number" id="grade" name="grade" min="0" max="10" step="0.1" required>

    <br>
    <br>
    <%-- Display error message if it is present --%>
    <c:if test="${not empty errorMessage}">
        <div style="color: red;">${errorMessage}</div>
    </c:if>

    <input type="submit" value="Add Enrollment">
</form>
<br>
<a href="?action=list">Back to Enrollment List</a>
</body>
</html>
