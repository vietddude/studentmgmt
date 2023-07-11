package hcmus.viet.studentmgmtweb.controller;

import hcmus.viet.studentmgmtweb.dao.CourseDao;
import hcmus.viet.studentmgmtweb.dao.EnrollmentDao;
import hcmus.viet.studentmgmtweb.dao.StudentDao;
import hcmus.viet.studentmgmtweb.model.Course;
import hcmus.viet.studentmgmtweb.model.Enrollment;
import hcmus.viet.studentmgmtweb.model.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDao studentDao;
    private EnrollmentDao enrollmentDao;
    private CourseDao courseDao;

    public void init() {
        studentDao = new StudentDao();
        enrollmentDao = new EnrollmentDao();
        courseDao = new CourseDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "insert":
                try {
                    insertStudent(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                try {
                    updateStudent(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    deleteStudent(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "detail":
                try {
                    detailStudent(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                try {
                    listStudents(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Student> students = studentDao.findAll();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/student/student-list.jsp").forward(request, response);
    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            request.getRequestDispatcher("/student/add-student.jsp").forward(request, response);
        } else if (request.getMethod().equalsIgnoreCase("POST")) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            double grade = Double.parseDouble(request.getParameter("grade"));
            LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));
            String address = request.getParameter("address");
            String notes = request.getParameter("notes");

            Student newStudent = new Student(id, name, grade, birthday, address, notes);
            studentDao.insert(newStudent);

            response.sendRedirect("student?action=list");
        }
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            String id = request.getParameter("id");
            Student student = studentDao.findById(id);

            request.setAttribute("student", student);
            request.getRequestDispatcher("/student/edit-student.jsp").forward(request, response);
        } else if (request.getMethod().equalsIgnoreCase("POST")) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            double grade = Double.parseDouble(request.getParameter("grade"));
            LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));
            String address = request.getParameter("address");
            String notes = request.getParameter("notes");

            Student studentToUpdate = new Student(id, name, grade, birthday, address, notes);
            studentDao.update(studentToUpdate);

            response.sendRedirect("student?action=list");
        }
    }



    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");

        enrollmentDao.deleteByStudentId(id);
        studentDao.delete(id);
        response.sendRedirect("student?action=list");
    }

    private void detailStudent(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String studentId = req.getParameter("id");

        Student student = studentDao.findById(studentId);
        List<Enrollment> enrollmentList = enrollmentDao.findByStudent(studentId);
        List<Course> courses = courseDao.findAll();

        Integer selectedYear;
        if (req.getParameter("year") != null) {
            selectedYear = Integer.parseInt(req.getParameter("year"));
        } else {
            selectedYear = null;
        }
        List<Course> studentCourses = courses.stream()
                .filter(course -> enrollmentList.stream().anyMatch(enrollment ->
                        enrollment.getCourseId().equals(course.getCourseId())))
                .filter(course -> selectedYear == null || course.getYear() == selectedYear)
                .collect(Collectors.toList());

        Map<String, Double> grades = new HashMap<>();
        for (Course course : studentCourses) {
            enrollmentList.stream()
                    .filter(e -> e.getCourseId().equals(course.getCourseId()))
                    .findFirst().ifPresent(enrollment -> grades.put(course.getName(), enrollment.getGrade()));
        }

        Set<Integer> years = courses.stream()
                .map(Course::getYear)
                .collect(Collectors.toSet());

        req.setAttribute("student", student);
        req.setAttribute("years", years);
        req.setAttribute("studentCourses", studentCourses);
        req.setAttribute("grades", grades);
        req.getRequestDispatcher("/student/detail-student.jsp").forward(req, res);
    }
}
