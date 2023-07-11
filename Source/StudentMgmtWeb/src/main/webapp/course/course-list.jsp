<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Course List</title>
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

    .search-box {
      border: none;
      background-color: #f2f2f2;
      padding: 10px;
      border-radius: 5px;
      font-size: 16px;
      width: 300px;
      margin-bottom: 20px;
    }

  </style>
  <script>
    function sortTable(n) {
      var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
      table = document.getElementsByTagName("table")[0];
      switching = true;
      // Set the sorting direction to ascending:
      dir = "asc";
      /* Make a loop that will continue until
      no switching has been done: */
      while (switching) {
        // Start by saying: no switching is done:
        switching = false;
        rows = table.getElementsByTagName("tr");
        /* Loop through all table rows (except the
        first, which contains table headers): */
        for (i = 1; i < (rows.length - 1); i++) {
          // Start by saying there should be no switching:
          shouldSwitch = false;
          /* Get the two elements you want to compare,
          one from current row and one from the next: */
          x = rows[i].getElementsByTagName("td")[n];
          y = rows[i + 1].getElementsByTagName("td")[n];
          /* Check if the two rows should switch place,
          based on the direction, asc or desc: */
          if (dir == "asc") {
            if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
              // If so, mark as a switch and break the loop:
              shouldSwitch = true;
              break;
            }
          } else if (dir == "desc") {
            if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
              // If so, mark as a switch and break the loop:
              shouldSwitch = true;
              break;
            }
          }
        }
        if (shouldSwitch) {
          /* If a switch has been marked, make the switch
          and mark that a switch has been done: */
          rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
          switching = true;
          // Each time a switch is done, increase this count by 1:
          switchcount++;
        } else {
          /* If no switching has been done AND the direction is "asc",
          set the direction to "desc" and run the while loop again. */
          if (switchcount == 0 && dir == "asc") {
            dir = "desc";
            switching = true;
          }
        }
      }
      // Toggle the sorting direction when column header is clicked again:
      if (dir == "asc") {
        dir = "desc";
      } else {
        dir = "asc";
      }
    }
  </script>
</head>
<body>
<h1>Course List</h1>
<div>
  <label for="search-box">Search:</label>
  <input type="text" id="search-box" class="search-box" onkeyup="filterTable()" placeholder="Type to search...">
</div>
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th><a href="#" onclick="sortTable(1)">Name</a></th>
    <th>Lecture</th>
    <th>Year</th>
    <th>Notes</th>
    <th></th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${courses}" var="course">
    <tr>
      <td>${course.courseId}</td>
      <td>${course.name}</td>
      <td>${course.lecture}</td>
      <td>${course.year}</td>
      <td>${course.notes}</td>
      <td>
        <a href="?action=update&id=${course.courseId}">Update</a>
        <a href="?action=delete&id=${course.courseId}">Delete</a>
        <a href="?action=detail&id=${course.courseId}">Detail</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<br>
<a href="index.jsp">Back to Home</a>
<a href="?action=insert">Add New Course</a>

<script>
  function filterTable() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("search-box");
    filter = input.value.toUpperCase();
    table = document.getElementsByTagName("table")[0];
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
      td = tr[i].getElementsByTagName("td")[1];
      if (td) {
        txtValue = td.textContent || td.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
          tr[i].style.display = "";
        } else {
          tr[i].style.display = "none";
        }
      }
    }
  }
</script>
</body>
</html>
