package hcmus.viet.studentmgmtweb.model;

public class Enrollment {
    private String studentId;
    private String courseId;
    private int year;
    private double grade;

    public Enrollment(String studentId, String courseId, int year, double grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.year = year;
        this.grade = grade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
