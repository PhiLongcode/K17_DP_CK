package presentation;

import business.DeleteRoomUseCase;

public class DeleteRoomController {
    private final DeleteRoomUseCase useCase;

    public DeleteRoomController(DeleteRoomUseCase useCase) {
        this.useCase = useCase;
    }

    public boolean deleteRoom(String roomId) {
        return useCase.execute(roomId);
    }
}
