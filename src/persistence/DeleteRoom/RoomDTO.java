package persistence.DeleteRoom;

import java.util.Date;

public class RoomDTO {
    public String roomID;
    public String buildingBlock;
    public Double area;
    public String roomType;
    public int numLightBulbs;
    public Date startDateOfOperation;
    public Boolean hasProject;
    public int  numComputers;
    public String specialization;
    public int capacity;
    public Boolean hasSink;
    public int typeId;

    public RoomDTO(String roomID, String buildingBlock, Double area, String roomType, int numLightBulbs, Date startDateOfOperation, Boolean hasProject, int numComputers, String specialization, int capacity, Boolean hasSink , int typeId) {
        this.roomID = roomID;
        this.buildingBlock = buildingBlock;
        this.area = area;
        this.roomType = roomType;
        this.numLightBulbs = numLightBulbs;
        this.startDateOfOperation = startDateOfOperation;
        this.hasProject = hasProject;
        this.numComputers = numComputers;
        this.specialization = specialization;
        this.capacity = capacity;
        this.hasSink = hasSink;
        this.typeId = typeId;
    }
    public RoomDTO() {}
}
