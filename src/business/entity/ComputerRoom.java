package business.entity;

import java.util.Date;

public class ComputerRoom extends Room {
    private int numComputers;

    public ComputerRoom(String roomId, String buildingBlock, Double area, Integer numOfLights, Date startDate,
                        int typeId, int numComputers) {
        super(roomId, buildingBlock, area, numOfLights, startDate, typeId);
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

    public int getNumComputers() {
        return numComputers;
    }

    public void setNumComputers(int numComputers) {
        this.numComputers = numComputers;
    }
}
