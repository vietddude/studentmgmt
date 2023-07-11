package hcmus.viet.studentmgmtweb.dao;

import hcmus.viet.studentmgmtweb.helper.DatabaseHelper;
import hcmus.viet.studentmgmtweb.model.Enrollment;
import hcmus.viet.studentmgmtweb.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDao {
    public boolean insert(Enrollment enrollment) throws Exception {
        String sql = "insert into [dbo].enrollment ([student_id], [course_id], [year], [grade])"
                + " values (?,?,?,?)";

        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, enrollment.getStudentId());
            preparedStatement.setString(2, enrollment.getCourseId());
            preparedStatement.setInt(3, enrollment.getYear());
            preparedStatement.setDouble(4, enrollment.getGrade());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean update(Enrollment enrollment) throws Exception {
        String sql = "update [dbo].enrollment set [grade]=? where [student_id]=? and [course_id]=? and [year]=?";

        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setDouble(1, enrollment.getGrade());
            preparedStatement.setString(2, enrollment.getStudentId());
            preparedStatement.setString(3, enrollment.getCourseId());
            preparedStatement.setInt(4, enrollment.getYear());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean delete(String courseId, String studentId, int year) throws Exception {
        String sql = "delete [dbo].enrollment where [course_id] = ? and [student_id] = ? and [year] = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, courseId);
            preparedStatement.setString(2, studentId);
            preparedStatement.setInt(3, year);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean deleteByStudentId(String studentId) throws Exception {
        String sql = "delete [dbo].enrollment where [student_id] = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, studentId);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean deleteByCourseId(String courseId) throws Exception {
        String sql = "delete [dbo].enrollment where [course_id] = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, courseId);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public Enrollment find(String studentId, String courseId, int year) throws Exception {
        String sql = "select * from enrollment where [student_id] = ? and [course_id] = ? and [year] = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, courseId);
            preparedStatement.setInt(3, year);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (!rs.next())
                    return null;
                return createEnrollment(rs);
            }
        }
    }


    public List<Enrollment> findByStudent(String studentId) throws Exception {
        String sql = "select * from enrollment where [student_id] = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, studentId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                List<Enrollment> list = new ArrayList<>();
                while (rs.next()) {
                    Enrollment course = createEnrollment(rs);
                    list.add(course);
                }
                return list;
            }
        }
    }

    public List<Enrollment> findByCourse(String courseId) throws Exception {
        String sql = "select * from enrollment where [course_id] = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, courseId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                List<Enrollment> list = new ArrayList<>();
                while (rs.next()) {
                    Enrollment course = createEnrollment(rs);
                    list.add(course);
                }
                return list;
            }
        }
    }

    public List<Enrollment> findAll() throws Exception {
        String sql = "select * from [dbo].enrollment";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                List<Enrollment> list = new ArrayList<>();
                while (rs.next()) {
                    Enrollment course = createEnrollment(rs);
                    list.add(course);
                }
                return list;
            }
        }
    }

    Enrollment createEnrollment(ResultSet rs) throws SQLException {
        return new Enrollment(rs.getString("student_id"),
                rs.getString("course_id"),
                rs.getInt("year"),
                rs.getDouble("grade"));
    }
}

