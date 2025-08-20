package business.entity;

import java.util.Date;

public class LaboratoryRoom extends Room {
    private String specialization;
    private int capacity;
    private boolean hasSink;

    public LaboratoryRoom(String roomId, String buildingBlock, Double area, Integer numOfLights, Date startDate,
                          int typeId, String specialization, int capacity, boolean hasSink) {
        super(roomId, buildingBlock, area, numOfLights, startDate, typeId);
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isHasSink() {
        return hasSink;
    }

    public void setHasSink(boolean hasSink) {
        this.hasSink = hasSink;
    }
}
