package business;

import business.entity.TypeRoom;
import dto.RoomTypeDTO;

public class TypeRoomFactory {
    public static TypeRoom createTypeRoom(RoomTypeDTO roomTypeDTO) {
        TypeRoom typeRoom = new TypeRoom();
        typeRoom.setId(roomTypeDTO.getId());
        typeRoom.setName(roomTypeDTO.getName());
        typeRoom.setDescription(roomTypeDTO.getDescription());
        return typeRoom;
    }

}
