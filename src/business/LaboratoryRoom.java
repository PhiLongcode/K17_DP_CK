package business;

import java.util.Date;

public class LaboratoryRoom extends Room {
    private String specialization;
    private int capacity;
    private boolean hasSink;

    public LaboratoryRoom(String roomId, String buildingBlock, Double area, Integer numLightBulbs, Date startDateOfOperation, String specialization, int capacity, boolean hasSink) {
        super(roomId, buildingBlock, area, numLightBulbs, startDateOfOperation);
        this.specialization = specialization;
        this.capacity = capacity;
        this.hasSink = hasSink;
    }

    @Override
    public boolean meetsStandard() {
        return checkSufficientLight() && hasSink;
    }

    @Override
    public String getRoomType() {
        return "LaboratoryRoom";
    }
}
