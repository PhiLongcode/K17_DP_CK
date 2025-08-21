package business.ViewListRoom;

import business.entity.ComputerRoom;
import business.entity.LaboratoryRoom;
import business.entity.LectureRoom;
import business.entity.Room;
import persistence.ViewListRoom.RoomDTO;

public class RoomFactory {
    public static Room createRoom(RoomDTO roomDTO) {
        String type = roomDTO.roomType.toLowerCase();

        switch (type) {
            case "computerroom":
                return new ComputerRoom(
                        roomDTO.roomID,
                        roomDTO.buildingBlock,
                        roomDTO.area,
                        roomDTO.numLightBulbs,
                        roomDTO.startDateOfOperation,
                        roomDTO.numComputers,
                        roomDTO.typeId
                );

            case "laboratoryroom":
                return new LaboratoryRoom(
                        roomDTO.roomID,
                        roomDTO.buildingBlock,
                        roomDTO.area,
                        roomDTO.numLightBulbs,
                        roomDTO.startDateOfOperation,
                        roomDTO.typeId,
                        roomDTO.specialization,
                        roomDTO.capacity,
                        roomDTO.hasSink

                );

            case "lectureroom":
                return new LectureRoom(
                        roomDTO.roomID,
                        roomDTO.buildingBlock,
                        roomDTO.area,
                        roomDTO.numLightBulbs,
                        roomDTO.startDateOfOperation,
                        roomDTO.typeId,
                        roomDTO.hasProject

                );

            default:
                throw new IllegalArgumentException("Unknown room type: " + roomDTO.roomType);
        }
    }
}
