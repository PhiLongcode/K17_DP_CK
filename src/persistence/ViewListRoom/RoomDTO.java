package persistence.ViewListRoom;

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

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setBuildingBlock(String buildingBlock) {
        this.buildingBlock = buildingBlock;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setNumLightBulbs(int numLightBulbs) {
        this.numLightBulbs = numLightBulbs;
    }

    public void setStartDateOfOperation(Date startDateOfOperation) {
        this.startDateOfOperation = startDateOfOperation;
    }

    public void setHasProject(Boolean hasProject) {
        this.hasProject = hasProject;
    }

    public void setNumComputers(int numComputers) {
        this.numComputers = numComputers;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setHasSink(Boolean hasSink) {
        this.hasSink = hasSink;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

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

    public int getTypeId() {
        return typeId;
    }

    public RoomDTO() {}
    @Override
    public String toString() {
        return "RoomDTO{" +
                "roomID='" + roomID + '\'' +
                ", buildingBlock='" + buildingBlock + '\'' +
                ", area=" + area +
                ", roomType='" + roomType + '\'' +
                ", numLightBulbs=" + numLightBulbs +
                ", startDate=" + startDateOfOperation +
                ", typeId=" + typeId +
                ", hasProjector=" + hasProject +
                ", numComputers=" + numComputers +
                ", specialization='" + specialization + '\'' +
                ", capacity=" + capacity +
                ", hasSink=" + hasSink +
                '}';
    }
}
