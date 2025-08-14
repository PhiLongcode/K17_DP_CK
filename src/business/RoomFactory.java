package business;

import persistence.RoomDTO;

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
                        roomDTO.numComputers
                );

            case "laboratoryroom":
                return new LaboratoryRoom(
                        roomDTO.roomID,
                        roomDTO.buildingBlock,
                        roomDTO.area,
                        roomDTO.numLightBulbs,
                        roomDTO.startDateOfOperation,
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
                        roomDTO.hasProject
                );

            default:
                throw new IllegalArgumentException("Unknown room type: " + roomDTO.roomType);
        }
    }
}
