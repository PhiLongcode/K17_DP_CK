package business.ViewListRoom;

import java.util.Date;

public abstract class Room {
	protected String roomId;
    protected String buildingBlock;
    protected double area;
    protected int numOfLights;
    protected Date startDate;
    protected String typeName;

    public Room(String roomId, String buildingBlock, double area, int numOfLights, Date startDate, String typeName) {
        this.roomId = roomId;
        this.buildingBlock = buildingBlock;
        this.area = area;
        this.numOfLights = numOfLights;
        this.startDate = startDate;
        this.typeName = typeName;
    }

    public abstract boolean isStandard();
    
    public String getRoomId() { return roomId; }
    
    public String getTypeName() { return typeName; }

	public String getBuildingBlock() {
		return buildingBlock;
	}

	public double getArea() {
		return area;
	}

	public int getNumOfLights() {
		return numOfLights;
	}

	public Date getStartDate() {
		return startDate;
	}


}
