package business.entity;

import java.util.Date;

public abstract class Room {
    protected String roomId;
    protected String buildingBlock;
    protected Double  area;
    protected Integer  numLightBulbs;
    protected Date startDateOfOperation;
    protected  int typeId;
    public Room(String roomId, String buildingBlock, Double area, Integer numLightBulbs, Date startDateOfOperation , int typeId) {
        this.roomId = roomId;
        this.buildingBlock = buildingBlock;
        this.area = area;
        this.numLightBulbs = numLightBulbs;
        this.startDateOfOperation = startDateOfOperation;
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

    public Integer getNumLightBulbs() {
        return numLightBulbs;
    }

    public void setNumLightBulbs(Integer numLightBulbs) {
        this.numLightBulbs = numLightBulbs;
    }

    public Date getStartDateOfOperation() {
        return startDateOfOperation;
    }

    public void setStartDateOfOperation(Date startDateOfOperation) {
        this.startDateOfOperation = startDateOfOperation;
    }

    public  abstract  boolean meetsStandard();
    public  abstract String getRoomType();

    public boolean checkSufficientLight()
    {
        return (this.getArea() / this.getNumLightBulbs()) <= 10;
    }

}
