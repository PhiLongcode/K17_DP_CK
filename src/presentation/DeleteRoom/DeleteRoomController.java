package presentation.DeleteRoom;

import business.DeleteRoom.DeleteRoomUsecase;
import business.DeleteRoom.StatusDeleteDTO;
import presentation.DeleteRoom.DeleteRoomModel;

public class DeleteRoomController {
    private final DeleteRoomUsecase deleteRoomUseCase;
    private final DeleteRoomModel viewModel;

    public DeleteRoomController(DeleteRoomUsecase deleteRoomUseCase, DeleteRoomModel viewModel) {
        this.deleteRoomUseCase = deleteRoomUseCase;
        this.viewModel = viewModel;
    }

    public void deleteRoom(String roomId) {
        StatusDeleteDTO result = deleteRoomUseCase.execute(roomId);
        viewModel.setMessage(result.message);
    }
}
