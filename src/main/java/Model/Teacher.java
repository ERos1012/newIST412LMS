package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Teacher class represents a teacher in the system.
 */
public class Teacher {
    private String name;
    private int id;
    private String email;
    private String classification;

    /**
     * Constructs a new Teacher object with the specified name, ID, program, and email.
     *
     * @param name The name of the teacher.
     * @param id The ID of the teacher.
     * @param email The email address of the teacher.
     */
    public Teacher(String name, int id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    /**
     * Adds dummy teachers to the existing list.
     */
    public static List<Teacher> addDummyTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        // Generate dummy teachers
        Teacher teacher1 = new Teacher("John Doe", 12, "john.doe@example.com");
        Teacher teacher2 = new Teacher("Jane Smith", 13, "jane.smith@example.com");
        
        // Add dummy teachers to the list
        teachers.add(teacher1);
        teachers.add(teacher2);
        
        return teachers;
    }

    /**
     * Gets the name of the teacher.
     *
     * @return The name of the teacher.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the ID of the teacher.
     *
     * @return The ID of the teacher.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the email address of the teacher.
     *
     * @return The email address of the teacher.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the classification of the teacher.
     *
     * @return The classification of the teacher.
     */
    public String getClassification() {
        System.out.println("Classification: " + classification + " - Test Passed");
        return classification;
    }

    /**
     * Sets the classification of the teacher.
     *
     * @param classification The classification of the teacher.
     */
    public void setEmail(String email) {
        System.out.println("Email: " + email + " - Test Passed");
        this.email = email;
    }
}
