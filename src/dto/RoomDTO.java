package dto;

import java.sql.Date;

public class RoomDTO {
    public String roomId;
    public String buildingBlock;
    public Double area;
    public Integer numOfLights;
    public Date startDate;
    public Integer typeId;
    public Boolean hasProjector;
    public Integer numComputers;
    public String specialization;
    public Integer capacity;
    public Boolean hasSink;

    public RoomDTO(String roomId,
            String buildingBlock,
            Double area,
            Integer numOfLights,
            Date startDate,
            Integer typeId,
            Boolean hasProjector,
            Integer numComputers,
            String specialization,
            Integer capacity,
            Boolean hasSink) {
        this.roomId = roomId;
        this.buildingBlock = buildingBlock;
        this.area = area;
        this.numOfLights = numOfLights;
        this.startDate = startDate;
        this.typeId = typeId;
        this.hasProjector = hasProjector;
        this.numComputers = numComputers;
        this.specialization = specialization;
        this.capacity = capacity;
        this.hasSink = hasSink;
    }

    public RoomDTO() {
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getBuildingBlock() {
        return buildingBlock;
    }

    public void setBuildingBlock(String buildingBlock) {
        this.buildingBlock = buildingBlock;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getNumOfLights() {
        return numOfLights;
    }

    public void setNumOfLights(Integer numOfLights) {
        this.numOfLights = numOfLights;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Boolean getHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(Boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public Integer getNumComputers() {
        return numComputers;
    }

    public void setNumComputers(Integer numComputers) {
        this.numComputers = numComputers;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getHasSink() {
        return hasSink;
    }

    public void setHasSink(Boolean hasSink) {
        this.hasSink = hasSink;
    }
}
