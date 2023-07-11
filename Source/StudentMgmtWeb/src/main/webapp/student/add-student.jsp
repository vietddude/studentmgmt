<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Edit Student</title>
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
<h1>Add Student</h1>
<form action="" method="post">
  <input type="hidden" name="action" value="insert">
  <label for="id">ID:</label>
  <input type="text" id="id" name="id" value="${student.studentId}">
  <br>
  <label for="name">Name:</label>
  <input type="text" id="name" name="name" value="${student.name}">
  <br>
  <label for="grade">Grade:</label>
  <input type="number" id="grade" step="0.1" name="grade" value="${student.grade}">
  <br>
  <label for="birthday">Birthday:</label>
  <input type="date" id="birthday" name="birthday" value="${student.birthday}">
  <br>
  <label for="address">Address:</label>
  <input type="text" id="address" name="address" value="${student.address}">
  <br>
  <label for="notes">Notes:</label>
  <textarea id="notes" name="notes">${student.notes}</textarea>
  <br>
  <input type="submit" value="Save">
</form>
<a href="?action=list">Back to Student List</a>
</body>
</html>
