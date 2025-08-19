package business.entity;

public class TypeRoom {
    private int id;
    private String name;
    private String description;

    // Default constructor
    public TypeRoom() {
    }

    // Constructor with all parameters
    public TypeRoom(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
