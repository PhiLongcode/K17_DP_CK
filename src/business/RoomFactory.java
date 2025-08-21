package business;

import business.entity.ComputerRoom;
import business.entity.LaboratoryRoom;
import business.entity.LectureRoom;
import business.entity.Room;
import persistence.RoomDTO;

public class RoomFactory {
    public static Room createRoom(RoomDTO roomDTO) {
        int type = roomDTO.typeId;

        switch (type) {
            case 1:
                return new ComputerRoom(
                        roomDTO.roomID,
                        roomDTO.buildingBlock,
                        roomDTO.area,
                        roomDTO.numLightBulbs,
                        roomDTO.startDateOfOperation,
                        roomDTO.numComputers,
                        roomDTO.typeId
                );

            case 2:
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

            case 3:
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
