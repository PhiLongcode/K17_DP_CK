package business.ViewListRoom;

import java.util.Date;

public class LabRoom extends Room{
	private String specialization;
    private int capacity;
    private boolean hasSink;

    public LabRoom(String roomId, String buildingBlock, double area, int numOfLights, Date startDate,
                   String typeName, String specialization, int capacity, boolean hasSink) {
        super(roomId, buildingBlock, area, numOfLights, startDate, typeName);
        this.specialization = specialization;
        this.capacity = capacity;
        this.hasSink = hasSink;
    }


	    public boolean isStandard() {
	        boolean enoughLight = (area / numOfLights) <= 10;
	        return enoughLight && hasSink;
	    }
}
