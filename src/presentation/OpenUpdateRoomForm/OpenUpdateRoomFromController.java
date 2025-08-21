package presentation.OpenUpdateRoomForm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.OpenUpdateRoomForm.OpenUpdateRoomFormUseCase;
import business.OpenUpdateRoomForm.ResRoomDTO;
import business.OpenUpdateRoomForm.ResTypeRoomDTO;
import presentation.OpenUpdateRoomForm.OpenUpdateRoomFromModel;

public class OpenUpdateRoomFromController {
    private  OpenUpdateRoomFormUseCase useCase;
    private  OpenUpdateRoomFromModel model;

    public OpenUpdateRoomFromController(OpenUpdateRoomFormUseCase useCase, OpenUpdateRoomFromModel model) {
        this.useCase = useCase;
        this.model = model;
    }

    // Gọi khi user muốn mở form update theo id phòng
    public void execute(String roomId) throws SQLException {
        // Gọi UseCase để lấy dữ liệu
        ResRoomDTO roomDTO = useCase.executeRoom(roomId);
        List<ResTypeRoomDTO> typeRoomDTOs = useCase.execute();
        

        // Chuyển dữ liệu từ DTO sang Item để hiển thị
        RoomItem roomItem = convertRoomDTOtoItem(roomDTO);
        List<TypeRoomItem> typeRoomItems = convertTypeRoomDTOsToItems(typeRoomDTOs);
        
        // Gắn dữ liệu vào Model
        model.setRoomItem(roomItem);
        model.setTypeRoomItemList(typeRoomItems);
    }

    private RoomItem convertRoomDTOtoItem(ResRoomDTO dto) {
        RoomItem item = new RoomItem();
        item.roomId = dto.roomId;
        item.builddingBlock = dto.builddingBlock;
        item.area = String.valueOf(dto.area);
        item.numLightBulbs = String.valueOf(dto.numLightBulbs);
        item.startDateOfOperation = dto.startDateOfOperation.toString();
        item.typeRoom = String.valueOf(dto.typeRoom);
        item.hasProjector = String.valueOf(dto.hasProjector);
        item.numComputers = String.valueOf(dto.numComputers);
        item.specialization = dto.specialization;
        item.capacity = String.valueOf(dto.capacity);
        item.hasSink = String.valueOf(dto.hasSink);
        return item;
    }

    private List<TypeRoomItem> convertTypeRoomDTOsToItems(List<ResTypeRoomDTO> dtos) {
        List<TypeRoomItem> items = new ArrayList<>();
        for (ResTypeRoomDTO dto : dtos) {
            TypeRoomItem item = new TypeRoomItem();
            item.typeRoomId = String.valueOf(dto.typeRoomId);
            item.nameTypeRoom = dto.nameTypeRoom;
            item.description = dto.description;
            items.add(item);
        }
        return items;
    }
}
