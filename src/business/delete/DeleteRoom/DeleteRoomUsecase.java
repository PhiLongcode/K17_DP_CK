package business.delete.DeleteRoom;

import persistence.delete.DeleteRoom.DeleteRoomGateway;

public class DeleteRoomUsecase {
    private final DeleteRoomGateway deleteRoomGateway;

    public DeleteRoomUsecase(DeleteRoomGateway deleteRoomGateway) {
        this.deleteRoomGateway = deleteRoomGateway;
    }

    public StatusDeleteDTO execute(String roomId) {
        boolean deleted = deleteRoomGateway.deleteRoom(roomId);
        if (deleted) {
            return new StatusDeleteDTO("Xóa phòng thành công", true);
        } else {
            return new StatusDeleteDTO("Không tìm thấy phòng để xóa", false);
        }
    }
}
