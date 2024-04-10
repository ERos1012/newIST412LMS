package Model;


public class Assignment {
    private String name;
    private String description;
    private String dueDate;
    private int id;

    public Assignment(int id, String name, String description, String dueDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
    }

    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getId() {
        return id;
    }
}