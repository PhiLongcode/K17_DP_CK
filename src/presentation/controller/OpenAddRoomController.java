package presentation.controller;

import business.usecase.OpenAddRoomUsecase;
import dto.RoomTypeResDTO;
import dto.RoomTypeViewDTO;
import presentation.model.OpenAddRoomModel;

import java.util.ArrayList;
import java.util.List;

public class OpenAddRoomController {
    OpenAddRoomUsecase openAddRoomUsecase;
    OpenAddRoomModel model;

    public OpenAddRoomController(OpenAddRoomUsecase openAddRoomUsecase, OpenAddRoomModel model) {
        this.openAddRoomUsecase = openAddRoomUsecase;
        this.model = model;
    }

    public void execute() {
        List<RoomTypeViewDTO> roomTypeViewDTO = openAddRoomUsecase.execute();
        model.roomTypeResDTO = convertToString(roomTypeViewDTO);
        model.notifySubscribers();
    }

    private List<RoomTypeResDTO> convertToString(List<RoomTypeViewDTO> roomTypeViewDTO) {
        List<RoomTypeResDTO> roomTypeResDTO = new ArrayList<>();
        int id = 1;
        for (RoomTypeViewDTO roomTypeViewDTO1 : roomTypeViewDTO) {
            RoomTypeResDTO roomTypeResDTO1 = new RoomTypeResDTO();
            roomTypeResDTO1.setId(String.valueOf(id++));
            roomTypeResDTO1.setName(roomTypeViewDTO1.getName());
            roomTypeResDTO1.getDescription(roomTypeViewDTO1.getDescription());
        }
        return roomTypeResDTO;
    }
}
