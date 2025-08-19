package persistence.gateway;

import dto.RoomTypeDTO;

import java.util.List;

public interface OpenAddRoomGateway {
    List<RoomTypeDTO> GetAllRoomTypes();
}
