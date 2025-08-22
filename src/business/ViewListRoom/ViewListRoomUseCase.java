package business.ViewListRoom;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import persistence.ViewListRoom.RoomDTO;
import persistence.ViewListRoom.ViewListRoomGateway;

public class ViewListRoomUseCase {
    private ViewListRoomGateway gateway;

    public ViewListRoomUseCase(ViewListRoomGateway gateway) {
        super();
        this.gateway = gateway;
    }

    public List<RoomViewDTO> execute() throws SQLException, ParseException {
        List<RoomDTO> listDTO = null;
        List<Room> rooms = null;

        // Lấy tất cả room DTO từ DB
        listDTO = gateway.getAll();

        // Convert RoomDTO -> Room (business objects)
        rooms = convertToBusinessObjects(listDTO);

        // Convert Room -> RoomViewDTO (cho UI)
        return this.convertToViewDTO(rooms);
    }

    private List<Room> convertToBusinessObjects(List<RoomDTO> dtos) {
        List<Room> rooms = new ArrayList<>();
        for (RoomDTO dto : dtos) {
            Room room = RoomFactory.createRoom(dto);
            if (room != null) {
                rooms.add(room);
            }
        }
        return rooms;
    }

    private List<RoomViewDTO> convertToViewDTO(List<Room> rooms) {
        List<RoomViewDTO> itemList = new ArrayList<>();
        for (Room room : rooms) {
            RoomViewDTO dto = new RoomViewDTO();
            dto.roomId = room.getRoomId();
            dto.buildingBlock = room.getBuildingBlock();
            dto.area = room.getArea();
            dto.numOfLights = room.getNumOfLights();
            dto.startDate = room.getStartDate();
            dto.type = room.getTypeName();

            // Tính toán thêm
            dto.standard = room.isStandard();

            itemList.add(dto);
        }
        return itemList;
    }
}
