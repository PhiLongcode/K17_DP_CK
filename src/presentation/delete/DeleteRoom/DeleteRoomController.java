package presentation.delete.DeleteRoom;

import business.delete.DeleteRoom.DeleteRoomUsecase;
import business.delete.DeleteRoom.StatusDeleteDTO;


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
