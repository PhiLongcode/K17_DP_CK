package dto;

public class RoomTypeResDTO {
    private String id;
    private String name;
    private String description;

    public RoomTypeResDTO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public RoomTypeResDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription(String description) {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
