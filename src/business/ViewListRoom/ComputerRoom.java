package business.ViewListRoom;

import java.util.Date;

public class ComputerRoom extends Room{
	 private int numComputers;

	    public ComputerRoom(String roomId, String buildingBlock, double area, int numOfLights, Date startDate,
	                        String typeName, int numComputers) {
	        super(roomId, buildingBlock, area, numOfLights, startDate, typeName);
	        this.numComputers = numComputers;
	    }
	    public boolean isStandard() {
	        boolean enoughLight = (area / numOfLights) <= 10;
	        boolean enoughComputers = (area / numComputers) >= 1.5;
	        return enoughLight && enoughComputers;
	    }
}
