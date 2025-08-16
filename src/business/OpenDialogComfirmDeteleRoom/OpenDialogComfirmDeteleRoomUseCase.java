package business.OpenDialogComfirmDeteleRoom;

import business.entity.Room;
import persistence.OpenDialogComfirmDeteleRoom.OpenDialogComfirmDeteleRoomGateway;
import persistence.OpenDialogComfirmDeteleRoom.RoomDTO;


public class OpenDialogComfirmDeteleRoomUseCase {
    private final OpenDialogComfirmDeteleRoomGateway gateway;



    public OpenDialogComfirmDeteleRoomUseCase(OpenDialogComfirmDeteleRoomGateway gateway) {
        this.gateway = gateway;
    }
    public ResRoomDetailDTO execute(String id) {
        RoomDTO dto = gateway.getRoomDeatail(id);
        Room room = convertToBussiness(dto);// tạo đúng loại phòng
        return convertToResDTO(dto, room.meetsStandard());
    }

    private ResRoomDetailDTO convertToResDTO(RoomDTO dto, boolean meetsStandard) {
        ResRoomDetailDTO res = new ResRoomDetailDTO();
        res.roomID = dto.roomID;
        res.buildingBlock = dto.buildingBlock;
        res.area = dto.area;
        res.roomType = dto.roomType;
        res.numLightBulbs = dto.numLightBulbs;
        res.startDateOfOperation = dto.startDateOfOperation;
        res.meetsStandard = meetsStandard;
        return res;
    }



    private Room convertToBussiness(RoomDTO roomDTO) {
        Room room = RoomFactory.create(roomDTO);
        return room;
    }
}
