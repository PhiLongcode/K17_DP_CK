package business.entity;

import java.util.Date;

public class LectureRoom extends Room {
    private boolean hasProjector;

    public LectureRoom(String roomId, String buildingBlock, Double area, Integer numOfLights,
                       Date startDate, int typeId, boolean hasProjector) {
        super(roomId, buildingBlock, area, numOfLights, startDate, typeId);
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

    public boolean isHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }
}
