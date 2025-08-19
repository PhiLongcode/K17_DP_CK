package business.ViewListRoom;

import business.ViewListRoom.RoomFactory;
import business.entity.Room;
import persistence.ViewListRoom.RoomDTO;
import persistence.ViewListRoom.ViewRoomGateway;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ViewRoomUseCase {
    private ViewRoomGateway listViewDAO; // thuộc tính
    // constructor
    public ViewRoomUseCase( ViewRoomGateway listViewDAO) {
        super();
        this.listViewDAO = listViewDAO;
    }
    // hành động
    public List<ViewRoomDTO> execute() throws SQLException, ParseException {
        List<RoomDTO> listDTO = listViewDAO.viewRoom() ;

        // Convert RoomDTO => Room (business object)
        List<Room> rooms = convertToBusinessObjects(listDTO);

        // Convert Room => ViewRoomDTO
        return convertToViewDTO(rooms);
    }

    private List<Room> convertToBusinessObjects(List<RoomDTO> dtos) {
        List<Room> rooms = new ArrayList<>();
        for (RoomDTO dto : dtos) {
            Room room = RoomFactory.createRoom(dto); // Giả sử bạn có RoomFactory giống StudentFactory
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
