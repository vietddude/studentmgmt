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

@WebServlet("/enrollment")
public class EnrollmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EnrollmentDao enrollmentDao;
    private CourseDao courseDao;
    private StudentDao studentDao;

    public void init() {
        enrollmentDao = new EnrollmentDao();
        courseDao = new CourseDao();
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
                    insertEnrollment(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                try {
                    updateEnrollment(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    deleteEnrollment(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                try {
                    listEnrollments(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }
    }

    private void listEnrollments(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Enrollment> enrollments = enrollmentDao.findAll();
        request.setAttribute("enrollments", enrollments);
        request.getRequestDispatcher("/enrollment/enrollment-list.jsp").forward(request, response);
    }

    private void insertEnrollment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            List<Course> courses = courseDao.findAll();
            Set<Integer> years = courses.stream()
                    .map(Course::getYear)
                    .collect(Collectors.toSet());
            List<Student> students = studentDao.findAll();

            // Create a map of year to list of courses for that year
            Map<Integer, List<Course>> yearToCoursesMap = new HashMap<>();
            for (int year : years) {
                List<Course> coursesForYear = courses.stream()
                        .filter(c -> c.getYear() == year)
                        .collect(Collectors.toList());
                yearToCoursesMap.put(year, coursesForYear);
            }

            request.setAttribute("years", years);
            request.setAttribute("yearToCoursesMap", yearToCoursesMap);
            request.setAttribute("students", students);

            request.getRequestDispatcher("/enrollment/add-enrollment.jsp").forward(request, response);

        } else if (request.getMethod().equalsIgnoreCase("POST")) {
            String studentId = request.getParameter("studentId");
            String courseId = request.getParameter("courseId");
            int year_input = courseDao.findById(courseId).getYear();
            double grade = Double.parseDouble(request.getParameter("grade"));

            Enrollment existingEnrollment = enrollmentDao.find(studentId, courseId, year_input);
            if (existingEnrollment != null) {
                request.setAttribute("errorMessage", "Enrollment already exists");

                List<Course> courses = courseDao.findAll();
                Set<Integer> years = courses.stream()
                        .map(Course::getYear)
                        .collect(Collectors.toSet());
                List<Student> students = studentDao.findAll();

                // Create a map of year to list of courses for that year
                Map<Integer, List<Course>> yearToCoursesMap = new HashMap<>();
                for (int year : years) {
                    List<Course> coursesForYear = courses.stream()
                            .filter(c -> c.getYear() == year)
                            .collect(Collectors.toList());
                    yearToCoursesMap.put(year, coursesForYear);
                }

                request.setAttribute("years", years);
                request.setAttribute("yearToCoursesMap", yearToCoursesMap);
                request.setAttribute("students", students);

                request.getRequestDispatcher("/enrollment/add-enrollment.jsp").forward(request, response);
            } else {
                Enrollment newEnrollment = new Enrollment(studentId, courseId, year_input, grade);
                enrollmentDao.insert(newEnrollment);

                response.sendRedirect("enrollment?action=list");
            }
        }
    }

    protected void updateEnrollment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            String studentId = request.getParameter("studentId");
            String courseId = request.getParameter("courseId");
            int year = Integer.parseInt(request.getParameter("year"));
            Enrollment enrollment = enrollmentDao.find(studentId, courseId, year);

            request.setAttribute("enrollment", enrollment);
            request.getRequestDispatcher("/enrollment/edit-enrollment.jsp").forward(request, response);
        } else if (request.getMethod().equalsIgnoreCase("POST")) {
            String studentId = request.getParameter("studentId");
            String courseId = request.getParameter("courseId");
            int year = Integer.parseInt(request.getParameter("year"));
            double grade = Double.parseDouble(request.getParameter("grade"));
            Enrollment enrollmentToUpdate = new Enrollment(studentId, courseId, year, grade);
            enrollmentDao.update(enrollmentToUpdate);

            response.sendRedirect("enrollment?action=list");
        }
    }



    protected void deleteEnrollment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String studentId = request.getParameter("studentId");
        String courseId = request.getParameter("courseId");
        int year = Integer.parseInt(request.getParameter("year"));

        enrollmentDao.delete(courseId, studentId, year);
        response.sendRedirect("enrollment?action=list");
    }

}
