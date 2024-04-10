package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Student class represents a student in the system.
 */
public class Student {
    private String name;
    private int id;
    private String program;
    private String email;
    private String classification;

    /**
     * Constructs a new Student object with the specified name, ID, program, and email.
     * 
     * @param name The name of the student.
     * @param id The ID of the student.
     * @param program The program in which the student is enrolled.
     * @param email The email address of the student.
     */
    public Student(String name, int id, String program, String email) {
        this.name = name;
        this.id = id;
        this.program = program;
        this.email = email;
    }

    /**
     * Adds dummy students to the Student class.
     */
    public static List<Student> addDummyStudents() {
        List<Student> dummyStudents = new ArrayList<>();

        // Create dummy students
        Student student1 = new Student("John Doe", 1, "Computer Science", "john.doe@example.com");
        Student student2 = new Student("Jane Smith", 2, "Engineering", "jane.smith@example.com");
        Student student3 = new Student("Bob Johnson", 3, "Business", "bob.johnson@example.com");

        // Add dummy students to the list
        dummyStudents.add(student1);
        dummyStudents.add(student2);
        dummyStudents.add(student3);

        return dummyStudents;
    }
    
    /**
     * Gets the name of the student.
     * 
     * @return The name of the student.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the ID of the student.
     * 
     * @return The ID of the student.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the program in which the student is enrolled.
     * 
     * @return The program of the student.
     */
    public String getProgram() {
        return program;
    }

    /**
     * Gets the email address of the student.
     * 
     * @return The email address of the student.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the classification of the student.
     * 
     * @return The classification of the student.
     */
    public String getClassification() {
        return classification;
    }

    /**
     * Sets the classification of the student.
     * 
     * @param classification The classification of the student.
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
