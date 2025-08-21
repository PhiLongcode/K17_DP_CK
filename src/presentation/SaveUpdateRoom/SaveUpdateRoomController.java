package presentation.SaveUpdateRoom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import business.SaveUpdateRoom.ResRoomDTO;
import business.SaveUpdateRoom.SaveUpdateRoomUseCase;
import presentation.OpenUpdateRoomForm.RoomItem;

public class SaveUpdateRoomController {
    private SaveUpdateRoomModel model;
    private SaveUpdateRoomUseCase useCase;

    public SaveUpdateRoomController(SaveUpdateRoomModel model, SaveUpdateRoomUseCase useCase) {
        this.model = model;
        this.useCase = useCase;
    }

    public void execute(RoomItem roomItem) {
        // Convert từ RoomItem (UI) sang ResRoomDTO (Business)
        ResRoomDTO dto = convertBusinessRoom(roomItem);

        // Gọi UseCase để lưu
        boolean result = useCase.executeRoom(dto);

        // Gửi kết quả về Model
        model.setStatus(result);
    }

    private ResRoomDTO convertBusinessRoom(RoomItem roomItem) {
        ResRoomDTO dto = new ResRoomDTO();

        dto.roomId = roomItem.roomId;
        dto.builddingBlock = roomItem.builddingBlock;

        // Dùng safe-parse
        dto.area = safeParseDouble(roomItem.area, 0.0);
        dto.numLightBulbs = safeParseInt(roomItem.numLightBulbs, 0);
        dto.typeRoom = safeParseInt(roomItem.typeRoom, 0);
        dto.numComputers = safeParseInt(roomItem.numComputers, 0);
        dto.capacity = safeParseInt(roomItem.capacity, 0);

        // Date cần validate
        if (roomItem.startDateOfOperation != null && !roomItem.startDateOfOperation.trim().isEmpty()) {
            dto.startDateOfOperation = java.sql.Date.valueOf(roomItem.startDateOfOperation);
        } else {
            dto.startDateOfOperation = null; // hoặc new Date(System.currentTimeMillis())
        }

        dto.hasProjector = Boolean.valueOf(roomItem.hasProjector);
        dto.hasSink = Boolean.valueOf(roomItem.hasSink);

        dto.specialization = roomItem.specialization;

        return dto;
    }

    // ===== Helper methods =====
    private int safeParseInt(String value, int defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        return Integer.parseInt(value.trim());
    }

    private double safeParseDouble(String value, double defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        return Double.parseDouble(value.trim());
    }
}
