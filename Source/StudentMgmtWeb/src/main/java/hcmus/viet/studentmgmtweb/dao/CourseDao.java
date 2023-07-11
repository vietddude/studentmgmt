package hcmus.viet.studentmgmtweb.dao;

import hcmus.viet.studentmgmtweb.helper.DatabaseHelper;
import hcmus.viet.studentmgmtweb.model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {
    public boolean insert(Course course) throws Exception {
        String sql = "insert into [dbo].course ([id], [name], [lecture], [year], [notes])"
                + " values (?,?,?,?,?)";

        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, course.getCourseId());
            preparedStatement.setString(2, course.getName());
            preparedStatement.setString(3, course.getLecture());
            preparedStatement.setInt(4, course.getYear());
            preparedStatement.setString(5, course.getNotes());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean update(Course course) throws Exception {
        String sql = "update [dbo].course set [name]=?, [lecture]=?, [year]=?, [notes]=? where [id]=?";

        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getLecture());
            preparedStatement.setInt(3, course.getYear());
            preparedStatement.setString(4, course.getNotes());
            preparedStatement.setString(5, course.getCourseId());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean delete(String courseId) throws Exception {
        String sql = "delete [dbo].course where [id] = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, courseId);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public Course findByName(String name) throws Exception {
        String sql = "select * from [dbo].course where name = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, name);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return createCourse(rs);
                }
            }
            return null;
        }
    }

    public Course findById(String id) throws Exception {
        String sql = "select * from [dbo].course where id = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return createCourse(rs);
                }
            }
            return null;
        }
    }

    public List<Course> findAll() throws Exception {
        String sql = "select * from [dbo].course";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                List<Course> list = new ArrayList<>();
                while (rs.next()) {
                    Course course = createCourse(rs);
                    list.add(course);
                }
                return list;
            }
        }
    }

    Course createCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setCourseId(rs.getString("id"));
        course.setName(rs.getString("name"));
        course.setLecture(rs.getString("lecture"));
        course.setYear(rs.getInt("year"));
        course.setNotes(rs.getString("notes"));

        return course;
    }
}
