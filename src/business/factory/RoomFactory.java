package business.factory;

import business.entity.ComputerRoom;
import business.entity.LaboratoryRoom;
import business.entity.LectureRoom;
import business.entity.Room;
import dto.RoomDTO;

public class RoomFactory {
    public static Room createRoom(RoomDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("RoomDTO must not be null");
        }

        if (dto.getNumComputers() != null) {
            return new ComputerRoom(
                    dto.getRoomId(),
                    dto.getBuildingBlock(),
                    dto.getArea(),
                    dto.getNumOfLights(),
                    dto.getStartDate(),
                    dto.getTypeId() == null ? 0 : dto.getTypeId(),
                    dto.getNumComputers());
        }

        if (dto.getSpecialization() != null || dto.getCapacity() != null || dto.getHasSink() != null) {
            return new LaboratoryRoom(
                    dto.getRoomId(),
                    dto.getBuildingBlock(),
                    dto.getArea(),
                    dto.getNumOfLights(),
                    dto.getStartDate(),
                    dto.getTypeId() == null ? 0 : dto.getTypeId(),
                    dto.getSpecialization(),
                    dto.getCapacity() == null ? 0 : dto.getCapacity(),
                    dto.getHasSink() != null && dto.getHasSink());
        }

        return new LectureRoom(
                dto.getRoomId(),
                dto.getBuildingBlock(),
                dto.getArea(),
                dto.getNumOfLights(),
                dto.getStartDate(),
                dto.getTypeId() == null ? 0 : dto.getTypeId(),
                dto.getHasProjector() != null && dto.getHasProjector());
    }
}
