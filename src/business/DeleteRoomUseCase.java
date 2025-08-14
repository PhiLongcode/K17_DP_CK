package business;

import presentation.DeleteRoomGateway;

public class DeleteRoomUseCase {
    private DeleteRoomGateway gateway;

    public DeleteRoomUseCase(DeleteRoomGateway gateway) {
        this.gateway = gateway;
    }

    public boolean execute(String roomId) {
       return gateway.deleteRoom(roomId);
    }
}
