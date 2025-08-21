package business.usecase;

import java.util.ArrayList;
import java.util.List;

import dto.RoomTypeDTO;
import dto.RoomTypeViewDTO;
import persistence.gateway.OpenAddRoomGateway;

public class OpenAddRoomUsecase {
    private final OpenAddRoomGateway openAddRoomGateway;

    public OpenAddRoomUsecase(OpenAddRoomGateway openAddRoomGateway) {
        this.openAddRoomGateway = openAddRoomGateway;
    }

    public List<RoomTypeViewDTO> execute() {
        List<RoomTypeDTO> roomTypeDTOs = openAddRoomGateway.GetAllRoomTypes();
        return convertToViewDTOs(roomTypeDTOs);
    }

    private List<RoomTypeViewDTO> convertToViewDTOs(List<RoomTypeDTO> roomTypeDTOs) {
        List<RoomTypeViewDTO> viewDTOs = new ArrayList<>();
        for (RoomTypeDTO dto : roomTypeDTOs) {
            RoomTypeViewDTO viewDTO = new RoomTypeViewDTO();
            viewDTO.setId(String.valueOf(dto.getId()));
            viewDTO.setName(dto.getName());
            viewDTO.setDescription(dto.getDescription());
            viewDTOs.add(viewDTO);
        }
        return viewDTOs;
    }
}
