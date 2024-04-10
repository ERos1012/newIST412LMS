package Model;

/**
 * The StudentCourseRecord class represents a record of a student's course enrollment and performance.
 */
public class StudentCourseRecord {
    private int studentId;
    private String courseCode;
    private String grade;
    private String semester;
    private int year;

    /**
     * Constructs a new StudentCourseRecord object with the specified student ID, course code, grade, semester, and year.
     * 
     * @param studentId The ID of the student.
     * @param courseCode The code of the course.
     * @param grade The grade received in the course.
     * @param semester The semester in which the course was taken.
     * @param year The year in which the course was taken.
     */
    public StudentCourseRecord(int studentId, String courseCode, String grade, String semester, int year) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.grade = grade;
        this.semester = semester;
        this.year = year;
    }

    /**
     * Gets the ID of the student.
     * 
     * @return The ID of the student.
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Gets the code of the course.
     * 
     * @return The code of the course.
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Gets the grade received in the course.
     * 
     * @return The grade received in the course.
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Gets the semester in which the course was taken.
     * 
     * @return The semester in which the course was taken.
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Gets the year in which the course was taken.
     * 
     * @return The year in which the course was taken.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the grade received in the course.
     * 
     * @param grade The grade received in the course.
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Sets the semester in which the course was taken.
     * 
     * @param semester The semester in which the course was taken.
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Sets the year in which the course was taken.
     * 
     * @param year The year in which the course was taken.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns a string representation of the StudentCourseRecord object.
     * 
     * @return A string representation of the object.
     */
    public String toString() {
        return "Student ID: " + studentId + ", Course Code: " + courseCode + ", Grade: " + grade + ", Semester: " + semester + ", Year: " + year;
    }
}
