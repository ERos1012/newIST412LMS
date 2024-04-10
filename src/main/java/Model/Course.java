package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Course class represents a course in the system.
 */
public class Course {
    private String name;
    private int id;
    private String program;
    private String instructor;

    // Dummy list of courses
    private static List<Course> dummyCourses = new ArrayList<>();

    static {
        dummyCourses.add(new Course("Math", 101, "Engineering", "Dr. Smith"));
        dummyCourses.add(new Course("Physics", 102, "Science", "Prof. Johnson"));
        dummyCourses.add(new Course("Literature", 103, "Arts", "Dr. Brown"));
    }

    /**
     * Constructs a new Course object with the specified name, ID, program, and instructor.
     * 
     * @param name The name of the course.
     * @param id The ID of the course.
     * @param program The program to which the course belongs.
     * @param instructor The instructor of the course.
     */
    public Course(String name, int id, String program, String instructor) {
        this.name = name;
        this.id = id;
        this.program = program;
        this.instructor = instructor;
    }
    
    /**
     * Gets the name of the course.
     * 
     * @return The name of the course.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the ID of the course.
     * 
     * @return The ID of the course.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the program to which the course belongs.
     * 
     * @return The program of the course.
     */
    public String getProgram() {
        return program;
    }

    /**
     * Gets the instructor of the course.
     * 
     * @return The instructor of the course.
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * Gets a list of dummy courses.
     * 
     * @return List of dummy courses.
     */
    public static List<Course> getDummyCourses() {
        return dummyCourses;
    }
}
