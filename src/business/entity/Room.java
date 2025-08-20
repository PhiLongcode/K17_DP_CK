package business.entity;

import java.util.Date;

public abstract class Room {
    protected String roomId;
    protected String buildingBlock;
    protected Double area;
    protected Integer numOfLights;
    protected Date startDate;
    protected int typeId;

    public Room(String roomId, String buildingBlock, Double area, Integer numOfLights, Date startDate, int typeId) {
        this.roomId = roomId;
        this.buildingBlock = buildingBlock;
        this.area = area;
        this.numOfLights = numOfLights;
        this.startDate = startDate;
        this.typeId = typeId;
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

    public abstract boolean meetsStandard();

    public abstract String getRoomType();

    public boolean checkSufficientLight() {
        return (this.getArea() / this.getNumOfLights()) <= 10;
    }

}
