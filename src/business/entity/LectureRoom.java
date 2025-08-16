package business.entity;

import java.util.Date;

public class LectureRoom extends Room {
    private boolean hasProjector;

    public LectureRoom(String roomId, String buildingBlock, Double area, Integer numLightBulbs, Date startDateOfOperation, int typeId, boolean hasProjector) {
        super(roomId, buildingBlock, area, numLightBulbs, startDateOfOperation, typeId);
        this.hasProjector = hasProjector;
    }

    @Override
    public boolean meetsStandard() {
        return checkSufficientLight() && hasProjector;
    }

    @Override
    public String getRoomType() {
        return "LectureRoom";
    }
}
