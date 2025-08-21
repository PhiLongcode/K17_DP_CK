package business.ViewListRoom;


import business.ViewListRoom.RoomFactory;
import business.entity.Room;
import persistence.ViewListRoom.RoomDTO;
import persistence.ViewListRoom.ViewRoomGateway;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FilterViewRoomUseCase {
    private ViewRoomGateway listViewDAO;

    public FilterViewRoomUseCase(ViewRoomGateway listViewDAO) {
        super();
        this.listViewDAO = listViewDAO;
    }

    // Lấy tất cả phòng
    public List<ViewRoomDTO> execute() throws SQLException, ParseException {
        List<RoomDTO> listDTO = listViewDAO.viewRoom();
        List<Room> rooms = convertToBusinessObjects(listDTO);
        return convertToViewDTO(rooms);
    }

    // Lọc ra phòng KHÔNG đạt chuẩn
    public List<ViewRoomDTO> filterRoomsNotStandard() throws SQLException, ParseException {
        List<RoomDTO> listDTO = listViewDAO.viewRoom();
        List<Room> rooms = convertToBusinessObjects(listDTO);

        List<Room> notStandardRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (!isStandard(room)) {
                notStandardRooms.add(room);
            }
        }

        return convertToViewDTO(notStandardRooms);
    }

    // Logic chuẩn phòng (có thể sửa theo yêu cầu)
    private boolean isStandard(Room room) {
        return room.getArea() >= 50 && room.getNumLightBulbs() >= 2;
    }

    private List<Room> convertToBusinessObjects(List<RoomDTO> dtos) {
        List<Room> rooms = new ArrayList<>();
        for (RoomDTO dto : dtos) {
            Room room = RoomFactory.createRoom(dto);
            rooms.add(room);
        }
        return rooms;
    }

    private List<ViewRoomDTO> convertToViewDTO(List<Room> rooms) {
        List<ViewRoomDTO> viewList = new ArrayList<>();
        for (Room room : rooms) {
            ViewRoomDTO dto = new ViewRoomDTO();
            dto.setRoomID(room.getRoomId());
            dto.setBuildingBlock(room.getBuildingBlock());
            dto.setArea(room.getArea());
            dto.setRoomType(room.getRoomType());
            dto.setNumLightBulbs(room.getNumLightBulbs());
            dto.setStartDateOfOperation(room.getStartDateOfOperation());
            viewList.add(dto);
        }
        return viewList;
    }
}

