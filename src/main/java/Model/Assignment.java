package Model;


public class Assignment {
    private String name;
    private String description;
    private String dueDate;
    private int id;
    private boolean isActive;

    private int courseId;

    public Assignment(int id, String name, String description, String dueDate, boolean isActive, int courseId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.isActive = isActive;
        this.courseId = courseId;
    }

    public Assignment() {

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

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setDueDate(String dueDate){
        this.dueDate = dueDate;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getCourseId()
    {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}