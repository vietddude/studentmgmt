package hcmus.viet.studentmgmtweb.model;

public class Course {
    private String courseId;
    private String name;
    private String lecture;
    private int year;
    private String notes;

    public Course(String courseId, String name, String lecture, int year, String notes) {
        this.courseId = courseId;
        this.name = name;
        this.lecture = lecture;
        this.year = year;
        this.notes = notes;
    }

    public Course() {

    }

    public String getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public String getLecture() {
        return lecture;
    }

    public int getYear() {
        return year;
    }

    public String getNotes() {
        return notes;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
