package business.usecase;

import common.StatusCode;
import dto.RoomDTO;
import dto.StatusDTO;
import persistence.gateway.AddRoomGateway;

public class AddRoomUsecase {
    private final AddRoomGateway addRoomGateway;

    public AddRoomUsecase(AddRoomGateway addRoomGateway) {
        this.addRoomGateway = addRoomGateway;
    }

    public StatusDTO exucute(RoomDTO roomDTO) {
        StatusDTO statusDTO = new StatusDTO();
        try {
            addRoomGateway.addRoom(roomDTO);
            statusDTO.setMessage("Room added successfully");
            statusDTO.setStatus(StatusCode.SUCCESS);
        } catch (Exception e) {
            statusDTO.setMessage(e.getMessage());
            statusDTO.setStatus(StatusCode.FAILURE);
        }
        return statusDTO;
    }
}
