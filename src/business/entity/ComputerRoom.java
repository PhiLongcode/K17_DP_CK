package business.entity;

import java.util.Date;

public class ComputerRoom extends Room {
    private int numComputers;

    public ComputerRoom(String roomId, String buildingBlock, Double area, Integer numLightBulbs, Date startDateOfOperation, int typeId, int numComputers) {
        super(roomId, buildingBlock, area, numLightBulbs, startDateOfOperation, typeId);
        this.numComputers = numComputers;
    }

    @Override
    public boolean meetsStandard() {
        return checkSufficientLight() && (area / numComputers >= 1.5);
    }

    @Override
    public String getRoomType() {
        return "ComputerRoom";
    }

}
