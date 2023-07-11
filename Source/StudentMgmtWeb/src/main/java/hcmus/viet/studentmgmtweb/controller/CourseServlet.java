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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourseDao courseDao;
    private EnrollmentDao enrollmentDao;
    private StudentDao studentDao;

    public void init() {
        courseDao = new CourseDao();
        enrollmentDao = new EnrollmentDao();
        studentDao = new StudentDao();
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
                    insertCourse(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                try {
                    updateCourse(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    deleteCourse(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "detail":
                try {
                    detailCourse(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "remove":
                try {
                    removeStudent(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "add":
                try {
                    addStudent(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                try {
                    listCourses(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }
    }

    private void listCourses(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Course> courses = courseDao.findAll();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/course/course-list.jsp").forward(request, response);
    }

    private void insertCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            request.getRequestDispatcher("/course/add-course.jsp").forward(request, response);

        } else if (request.getMethod().equalsIgnoreCase("POST")) {
            String courseId = request.getParameter("id");
            String name = request.getParameter("name");
            String lecture = request.getParameter("lecture");
            int year = Integer.parseInt(request.getParameter("year"));
            String notes = request.getParameter("notes");

            Course newCourse = new Course(courseId, name, lecture, year, notes);
            courseDao.insert(newCourse);

            response.sendRedirect("course?action=list");
        }
    }

    private void updateCourse(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            String courseId = request.getParameter("id");
            Course course = courseDao.findById(courseId);

            request.setAttribute("course", course);
            request.getRequestDispatcher("/course/edit-course.jsp").forward(request, response);
        } else if (request.getMethod().equalsIgnoreCase("POST")) {
            String courseId = request.getParameter("id");
            String name = request.getParameter("name");
            String lecture = request.getParameter("lecture");
            int year = Integer.parseInt(request.getParameter("year"));
            String notes = request.getParameter("notes");

            Course course = new Course(courseId, name, lecture, year, notes);
            courseDao.update(course);

            response.sendRedirect("course?action=list");
        }
    }

    protected void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String courseId = request.getParameter("id");

        enrollmentDao.deleteByCourseId(courseId);
        courseDao.delete(courseId);
        response.sendRedirect("course?action=list");
    }

    protected void detailCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String courseId = request.getParameter("id");
        Course course = courseDao.findById(courseId);

        List<Student> enrolledStudents = new ArrayList<>();
        List<Double> grades = new ArrayList<>();

        enrollmentDao.findByCourse(courseId).forEach(enrollment -> {
            try {
                enrolledStudents.add(studentDao.findById(enrollment.getStudentId()));
                grades.add(enrollment.getGrade());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        List<Student> availableStudents = studentDao.findAll();
        availableStudents.removeIf(student -> enrolledStudents.stream()
                .map(Student::getStudentId)
                .anyMatch(id -> id.equals(student.getStudentId())));

        request.setAttribute("course", course);
        request.setAttribute("students", enrolledStudents);
        request.setAttribute("grades", grades);
        request.setAttribute("availableStudents", availableStudents);
        request.getRequestDispatcher("/course/detail-course.jsp").forward(request, response);
    }

    protected void removeStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String courseId = request.getParameter("courseId");
        String studentId = request.getParameter("studentId");
        int year = courseDao.findById(courseId).getYear();

        if (enrollmentDao.delete(courseId, studentId, year))
            response.sendRedirect("course?action=detail&id=" + courseId);
    }


    protected void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String studentId = request.getParameter("studentId");
        String courseId = request.getParameter("courseId");
        double grade = Double.parseDouble(request.getParameter("grade"));

        int year = courseDao.findById(courseId).getYear();
        Enrollment enrollment = new Enrollment(studentId, courseId, year, grade);
        enrollmentDao.insert(enrollment);

        response.sendRedirect("course?action=detail&id=" + courseId);
    }

}