package presentation.controller;

import java.util.List;

import business.usecase.OpenAddRoomUsecase;
import dto.RoomTypeViewDTO;
import presentation.model.OpenAddRoomModel;

public class OpenAddRoomController {
    OpenAddRoomUsecase openAddRoomUsecase;
    OpenAddRoomModel model;

    public OpenAddRoomController(OpenAddRoomUsecase openAddRoomUsecase, OpenAddRoomModel model) {
        this.openAddRoomUsecase = openAddRoomUsecase;
        this.model = model;
    }

    public void execute() {
        List<RoomTypeViewDTO> roomTypeViewDTOs = openAddRoomUsecase.execute();
        model.roomTypeViewDTOs = roomTypeViewDTOs;
        model.notifySubscribers();
    }
}
