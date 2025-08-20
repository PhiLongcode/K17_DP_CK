package business.usecase;

import business.TypeRoomFactory;
import business.entity.TypeRoom;
import dto.RoomTypeDTO;
import dto.RoomTypeViewDTO;
import persistence.gateway.OpenAddRoomGateway;

import java.util.ArrayList;
import java.util.List;

public class OpenAddRoomUsecase {
    private final OpenAddRoomGateway openAddRoomGateway;

    public OpenAddRoomUsecase(OpenAddRoomGateway openAddRoomGateway) {
        this.openAddRoomGateway = openAddRoomGateway;
    }

    public List<RoomTypeViewDTO> execute() {
        List<RoomTypeDTO> list = openAddRoomGateway.GetAllRoomTypes();
        List<TypeRoom> typeRoom = convertToBusinessObjects(list);
        return convertToViews(typeRoom);
    }

    private List<TypeRoom> convertToBusinessObjects(List<RoomTypeDTO> RoomTypeDTO) {
        List<TypeRoom> list = new ArrayList<>();
        for (RoomTypeDTO roomTypeViewDTO : RoomTypeDTO) {
            TypeRoom roomtype = TypeRoomFactory.createTypeRoom(roomTypeViewDTO);
            list.add(roomtype);
        }
        return list;
    }

    private List<RoomTypeViewDTO> convertToViews(List<TypeRoom> TypeRoom) {
        List<RoomTypeViewDTO> list = new ArrayList<>();
        for (TypeRoom room : TypeRoom) {
            RoomTypeViewDTO roomTypeViewDTO = new RoomTypeViewDTO();
            roomTypeViewDTO.setId(room.getId());
            roomTypeViewDTO.setName(room.getName());
            roomTypeViewDTO.setDescription(room.getDescription());
            list.add(roomTypeViewDTO);
        }
        return list;
    }
}
