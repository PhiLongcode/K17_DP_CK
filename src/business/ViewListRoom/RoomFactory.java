package business.ViewListRoom;

import persistence.ViewListRoom.RoomDTO;

public class RoomFactory {
	 public static Room createRoom(RoomDTO dto) {
		 
		 System.out.println("Factory nhận typeName = " + dto.typeName);
		 
	        switch (dto.typeName) {
	            case "Phòng học lý thuyết":
	                return new TheoryRoom(dto.roomId, dto.buildingBlock, dto.area, dto.numOfLights,
	                        dto.startDate, dto.typeName, dto.hasProjector != null && dto.hasProjector);
	            case "Phòng máy tính":
	                return new ComputerRoom(dto.roomId, dto.buildingBlock, dto.area, dto.numOfLights,
	                        dto.startDate, dto.typeName, dto.numComputers != null ? dto.numComputers : 0);
	            case "Phòng thí nghiệm":
	                return new LabRoom(dto.roomId, dto.buildingBlock, dto.area, dto.numOfLights,
	                        dto.startDate, dto.typeName,
	                        dto.specialization, dto.capacity != null ? dto.capacity : 0,
	                        dto.hasSink != null && dto.hasSink);
	            default:
	                return null;
	        }
	    }
}
