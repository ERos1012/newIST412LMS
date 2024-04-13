package Model;


public class Assignment {
    private String name;
    private String description;
    private String dueDate;
    private int id;

    public Assignment(){
        this.name = "";
        this.description = "";
        this.dueDate = "";
        this.id = 0;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}