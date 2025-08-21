package business.OpenUpdateRoomForm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.OpenUpdateRoomForm.OpenUpdateRoomFromGateway;
import persistence.OpenUpdateRoomForm.RoomDTO;
import persistence.OpenUpdateRoomForm.TypeRoomDTO;

public class OpenUpdateRoomFormUseCase {
	private OpenUpdateRoomFromGateway openUpdateRoomFromGateway;

	public OpenUpdateRoomFormUseCase(OpenUpdateRoomFromGateway openUpdateRoomFromGateway) {
		super();
		this.openUpdateRoomFromGateway = openUpdateRoomFromGateway;
	}
	
	 // Lấy danh sách loại phòng
    public List<ResTypeRoomDTO> execute() throws SQLException {
        List<TypeRoomDTO> typeRoomDtos = openUpdateRoomFromGateway.getTypeRoom();
        return convertToResDTO(typeRoomDtos);
    }

    // Mở phòng theo id
    public ResRoomDTO executeRoom(String id) throws SQLException {
        RoomDTO roomDTO = openUpdateRoomFromGateway.getRoomDTO(id);
        return convertToResRoomDTO(roomDTO);
    }

    // Chuyển DTO sang Business Object
    private List<TypeRoom> convertToBusinessObject(List<TypeRoomDTO> typeRoomDtos) {
        List<TypeRoom> result = new ArrayList<>();
        for (TypeRoomDTO dto : typeRoomDtos) {
            TypeRoom typeRoom = new TypeRoom(
                dto.typeRoomId,
                dto.nameTypeRoom,
                dto.description
            );
            result.add(typeRoom);
        }
        return result;
    }

    // Chuyển Business Object -> ResTypeRoomDTO
    private List<ResTypeRoomDTO> convertToResDTO(List<TypeRoomDTO> typeRoomDtos) {
        List<ResTypeRoomDTO> result = new ArrayList<>();
        for (TypeRoomDTO dto : typeRoomDtos) {
            ResTypeRoomDTO res = new ResTypeRoomDTO();
            res.typeRoomId = dto.typeRoomId;
            res.nameTypeRoom = dto.nameTypeRoom;
            res.description = dto.description;
            result.add(res);
        }
        return result;
    }

    // Chuyển RoomDTO -> ResRoomDTO
    private ResRoomDTO convertToResRoomDTO(RoomDTO roomDTO) {
        if (roomDTO == null) return null;

        ResRoomDTO res = new ResRoomDTO();
        res.roomId = roomDTO.roomId;
        res.builddingBlock = roomDTO.builddingBlock;
        res.area = roomDTO.area;
        res.numLightBulbs = roomDTO.numLightBulbs;
        res.startDateOfOperation = roomDTO.startDateOfOperation;
        res.typeRoom = roomDTO.typeRoom;
        res.hasProjector = roomDTO.hasProjector;
        res.numComputers = roomDTO.numComputers;
        res.specialization = roomDTO.specialization;
        res.capacity = roomDTO.capacity;
        res.hasSink = roomDTO.hasSink;
        return res;
    }
}
