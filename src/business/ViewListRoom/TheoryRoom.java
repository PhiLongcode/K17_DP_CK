package business.ViewListRoom;

import java.util.Date;

public class TheoryRoom extends Room{
	 private boolean hasProjector;

	    public TheoryRoom(String roomId, String buildingBlock, double area, int numOfLights, Date startDate,
	                      String typeName, boolean hasProjector) {
	        super(roomId, buildingBlock, area, numOfLights, startDate, typeName);
	        this.hasProjector = hasProjector;
	    }

    public boolean isStandard() {
        boolean enoughLight = (area / numOfLights) <= 10;
        return enoughLight && hasProjector;
    }
}
