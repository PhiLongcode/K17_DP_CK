package business.ViewListRoom;

import java.util.Date;

public class ViewRoomDTO {
    public String roomID;
    public String buildingBlock;
    public Double area;
    public String roomType;
    public int numLightBulbs;


    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setBuildingBlock(String buildingBlock) {
        this.buildingBlock = buildingBlock;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setNumLightBulbs(int numLightBulbs) {
        this.numLightBulbs = numLightBulbs;
    }

    public void setStartDateOfOperation(Date startDateOfOperation) {
        this.startDateOfOperation = startDateOfOperation;
    }

    public void setMeetsStandard(Boolean meetsStandard) {
        this.meetsStandard = meetsStandard;
    }

    public Date startDateOfOperation;


    public Boolean meetsStandard;
}
