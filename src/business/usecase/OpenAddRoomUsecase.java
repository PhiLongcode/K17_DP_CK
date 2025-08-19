package business.usecase;

import dto.RoomTypeDTO;
import persistence.gateway.OpenAddRoomGateway;

import java.util.ArrayList;
import java.util.List;

public class OpenAddRoomUsecase {
    private final OpenAddRoomGateway openAddRoomGateway;

    public OpenAddRoomUsecase(OpenAddRoomGateway openAddRoomGateway) {
        this.openAddRoomGateway = openAddRoomGateway;
    }

    public void execute() {
        List<RoomTypeDTO> list = new ArrayList<>();
        list = openAddRoomGateway.GetAllRoomTypes();
    }
}



