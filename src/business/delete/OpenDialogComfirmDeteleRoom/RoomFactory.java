package business.delete.OpenDialogComfirmDeteleRoom;

import business.entity.ComputerRoom;
import business.entity.LaboratoryRoom;
import business.entity.LectureRoom;
import business.entity.Room;
import persistence.delete.OpenDialogComfirmDeteleRoom.RoomDTO;


public class RoomFactory {
    public static Room create(RoomDTO roomDTO) {
        String type = roomDTO.roomType.toLowerCase();

        switch (type) {
            case "computer room":
                return new ComputerRoom(
                        roomDTO.roomID,
                        roomDTO.buildingBlock,
                        roomDTO.area,
                        roomDTO.numLightBulbs,
                        roomDTO.startDateOfOperation,
                        roomDTO.numComputers,
                        roomDTO.typeId
                );

            case "laboratory room":
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

            case "lecture room":
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
