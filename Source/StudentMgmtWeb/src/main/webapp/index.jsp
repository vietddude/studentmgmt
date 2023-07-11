<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Student Management Web</title>
  <style>
    @import url('https://fonts.googleapis.com/css?family=Open+Sans');

    * {
      box-sizing: border-box;
    }

    body {
      background-color: #F9F9F9;
      font-family: 'Open Sans', sans-serif;
      margin: 0;
      padding: 0;
    }

    .container {
      max-width: 800px;
      margin: 0 auto;
      padding: 40px;
      background-color: #FFFFFF;
      box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.1);
      border-radius: 5px;
    }

    h1, h2, h3 {
      color: #333333;
      margin-top: 0;
    }

    h1 {
      font-size: 36px;
      font-weight: bold;
      text-align: center;
    }

    h3 {
      font-size: 16px;
      font-weight: bold;
      text-align: center;
    }

    h2 {
      font-size: 24px;
      font-weight: bold;
      margin-top: 40px;
    }

    ul {
      list-style: none;
      margin: 0;
      padding: 0;
    }

    li {
      margin-bottom: 20px;
      border-bottom: 1px solid #DDDDDD;
      padding-bottom: 20px;
    }

    a {
      color: #0077CC;
      text-decoration: none;
    }

    a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Welcome to Student Management Web</h1>
  <h3>20120401 - Nguyễn Đức Việt</h3>
  <h2>Manage Students</h2>
  <ul>
    <li><a href="student?action=list">List All Students</a></li>
    <li><a href="student?action=insert">Add New Student</a></li>
  </ul>

  <h2>Manage Courses</h2>
  <ul>
    <li><a href="course?action=list">List All Courses</a></li>
    <li><a href="course?action=insert">Add New Course</a></li>
  </ul>

  <h2>Manage Enrollment</h2>
  <ul>
    <li><a href="enrollment?action=list">List All Enrollments</a></li>
    <li><a href="enrollment?action=insert">Add New Enrollment</a></li>
  </ul>
</div>
</body>
</html>
