<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Course</title>
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
<h1>Edit Course</h1>
<form action="" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${course.courseId}">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${course.name}">
    <br>
    <label for="lecture">Lecture:</label>
    <input type="text" id="lecture" name="lecture" value="${course.lecture}">
    <br>
    <label for="year">Year:</label>
    <input type="number" id="year" name="year" value="${course.year}">
    <br>
    <label for="notes">Notes:</label>
    <textarea id="notes" name="notes">${course.notes}</textarea>
    <br>
    <input type="submit" value="Save">
</form>
<a href="?action=list">Back to Course List</a>
</body>
</html>