<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Edit Enrollment</title>
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
  </style>
</head>
<body>
<h1>Edit Enrollment</h1>
<form method="post" action="">
  <br>
  <input type="hidden" name="studentId" value="${enrollment.studentId}">
  <input type="hidden" name="courseId" value="${enrollment.courseId}">
  <input type="hidden" name="year" value="${enrollment.year}">
  <label for="grade">Grade:</label>
  <input type="number" id="grade" name="grade" min="0" max="10" step="0.1" value="${enrollment.grade}" required>
  <br>
  <br>
  <%-- Display error message if it is present --%>
  <c:if test="${not empty errorMessage}">
  <div style="color: red;">${errorMessage}</div>
  </c:if>

  <input type="submit" value="Update Enrollment">
</form>
<br>
<a href="?action=list">Back to Enrollment List</a>
</body>
</html>