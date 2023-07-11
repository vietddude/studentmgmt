package hcmus.viet.studentmgmtweb.dao;


import hcmus.viet.studentmgmtweb.helper.DatabaseHelper;
import hcmus.viet.studentmgmtweb.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    public boolean insert(Student student) throws Exception {
        String sql = "insert into student ([id], [name], [grade], [birthday], [address], [notes])"
                + " values (?,?,?,?,?,?)";

        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, student.getStudentId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setDouble(3, student.getGrade());
            preparedStatement.setDate(4, java.sql.Date.valueOf(student.getBirthday()));
            preparedStatement.setString(5, student.getAddress());
            preparedStatement.setString(6, student.getNotes());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean update(Student student) throws Exception {
        String sql = "update [dbo].student set [name]=?, [grade]=?, [birthday]=?, [address]=?, [notes]=? where [id]=?";

        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setDouble(2, student.getGrade());
            preparedStatement.setDate(3, java.sql.Date.valueOf(student.getBirthday()));
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getNotes());
            preparedStatement.setString(6, student.getStudentId());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean delete(String studentId) throws Exception {
        String sql = "delete [dbo].student where id = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, studentId);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public Student findById(String id) throws Exception {
        String sql = "select * from [dbo].student where id = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return createStudent(rs);
                }
            }
            return null;
        }
    }

    public Student findByName(String name) throws Exception {
        String sql = "select * from [dbo].student where name like ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, name);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return createStudent(rs);
                }
            }
            return null;
        }
    }

    public List<Student> findAll() throws Exception {
        String sql = "select * from student";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sql)
        ) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                List<Student> list = new ArrayList<>();
                while (rs.next()) {
                    Student student = createStudent(rs);
                    list.add(student);
                }
                return list;
            }
        }
    }

    Student createStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setStudentId(rs.getString("id"));
        student.setName(rs.getString("name"));
        student.setGrade(rs.getDouble("grade"));
        student.setBirthday(rs.getDate("birthday").toLocalDate());
        student.setAddress(rs.getString("address"));
        student.setNotes(rs.getString("notes"));

        return student;
    }
}
