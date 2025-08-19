package dto;

public class RoomTypeDTO {
    private String name;
    private String description;

    public RoomTypeDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public RoomTypeDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
