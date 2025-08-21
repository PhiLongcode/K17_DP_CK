package dto;

public class RoomResDTO {
    private String roomId;
    private String typeId;
    private String buildingBlock;
    private String area;
    private String numLightBulbs;
    private String startDateOfOperation;
    private String hasProjector;
    private String numComputers;
    private String specialization;
    private String capacity;
    private String hasSink;

    // Default constructor
    public RoomResDTO() {
    }

    // Constructor with all parameters
    public RoomResDTO(String roomId, String typeId, String buildingBlock, String area,
                      String numLightBulbs, String startDateOfOperation, String hasProjector,
                      String numComputers, String specialization, String capacity, String hasSink) {
        this.roomId = roomId;
        this.typeId = typeId;
        this.buildingBlock = buildingBlock;
        this.area = area;
        this.numLightBulbs = numLightBulbs;
        this.startDateOfOperation = startDateOfOperation;
        this.hasProjector = hasProjector;
        this.numComputers = numComputers;
        this.specialization = specialization;
        this.capacity = capacity;
        this.hasSink = hasSink;
    }

    // Getter methods
    public String getRoomId() {
        return roomId;
    }

    // Setter methods
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getBuildingBlock() {
        return buildingBlock;
    }

    public void setBuildingBlock(String buildingBlock) {
        this.buildingBlock = buildingBlock;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNumLightBulbs() {
        return numLightBulbs;
    }

    public void setNumLightBulbs(String numLightBulbs) {
        this.numLightBulbs = numLightBulbs;
    }

    public String getStartDateOfOperation() {
        return startDateOfOperation;
    }

    public void setStartDateOfOperation(String startDateOfOperation) {
        this.startDateOfOperation = startDateOfOperation;
    }

    public String getHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(String hasProjector) {
        this.hasProjector = hasProjector;
    }

    public String getNumComputers() {
        return numComputers;
    }

    public void setNumComputers(String numComputers) {
        this.numComputers = numComputers;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getHasSink() {
        return hasSink;
    }

    public void setHasSink(String hasSink) {
        this.hasSink = hasSink;
    }
}
