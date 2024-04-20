package Model;

/**
 * The Course class represents a course in the system.
 */
public class Course {
    private String name;
    private int id;
    private String program;
    private String instructor;
    private boolean isActive; // New attribute to indicate if the course is active

    // Dummy list of courses
    /**
     * Constructs a new Course object with the specified name, ID, program, instructor, and isActive status.
     *
     * @param name The name of the course.
     * @param id The ID of the course.
     * @param program The program to which the course belongs.
     * @param instructor The instructor of the course.
     * @param isActive The status of the course (active or inactive).
     */
    public Course( int id, String name, String program, String instructor, boolean isActive) {
        this.id = id;
        this.name = name;
        this.program = program;
        this.instructor = instructor;
        this.isActive = isActive;
    }

    public Course() {

    }

    // Getters and setters for the attributes
    /**
     * Gets the name of the course.
     *
     * @return The name of the course.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the course.
     *
     * @param name The name of the course to set.
     */
    public void setName(String name) {
        this.name = name;
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
     * Sets the ID of the course.
     *
     * @param id The ID of the course to set.
     */
    public void setId(int id) {
        this.id = id;
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
     * Sets the program to which the course belongs.
     *
     * @param program The program of the course to set.
     */
    public void setProgram(String program) {
        this.program = program;
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
     * Sets the instructor of the course.
     *
     * @param instructor The instructor of the course to set.
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     * Checks if the course is active.
     *
     * @return True if the course is active, false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the active status of the course.
     *
     * @param active The active status of the course to set.
     */
    public void setActive(boolean active) {
        isActive = active;
    }

}
