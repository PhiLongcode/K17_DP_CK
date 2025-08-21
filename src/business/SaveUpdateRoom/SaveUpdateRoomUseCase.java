package business.SaveUpdateRoom;

import persistence.SaveUpdateRoom.RoomDTO;
import persistence.SaveUpdateRoom.SaveUpdateRoomGateway;

public class SaveUpdateRoomUseCase {
    private final SaveUpdateRoomGateway saveUpdateRoomDAO;

    public SaveUpdateRoomUseCase(SaveUpdateRoomGateway saveUpdateRoomDAO) {
        this.saveUpdateRoomDAO = saveUpdateRoomDAO;
    }

    /**
     * Thực thi lưu/ cập nhật phòng.
     */
    public boolean executeRoom(ResRoomDTO resRoomDto) {
        RoomDTO roomDTO = convertToRoomDTO(resRoomDto);
        return saveUpdateRoomDAO.updateRoom(roomDTO);
    }

    /**
     * Chuyển đổi ResRoomDTO (từ UI) -> RoomDTO (cho Persistence).
     */
    public RoomDTO convertToRoomDTO(ResRoomDTO resRoomDto) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.roomId = resRoomDto.roomId;
        roomDTO.builddingBlock = resRoomDto.builddingBlock;
        roomDTO.area = resRoomDto.area;
        roomDTO.numLightBulbs = resRoomDto.numLightBulbs;
        roomDTO.startDateOfOperation = resRoomDto.startDateOfOperation;
        roomDTO.typeRoom = resRoomDto.typeRoom;
        roomDTO.hasProjector = resRoomDto.hasProjector;
        roomDTO.numComputers = resRoomDto.numComputers;
        roomDTO.specialization = resRoomDto.specialization;
        roomDTO.capacity = resRoomDto.capacity;
        roomDTO.hasSink = resRoomDto.hasSink;

        return roomDTO;
    }
}
