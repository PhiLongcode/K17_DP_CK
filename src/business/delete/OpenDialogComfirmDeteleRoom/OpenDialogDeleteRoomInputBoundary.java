package business.delete.OpenDialogComfirmDeteleRoom;

public interface OpenDialogDeleteRoomInputBoundary {
     ResRoomDetailDTO execute(String id);
    ResRoomDetailDTO convertToViewObject(ResRoomDetailDTO dto, boolean meetsStandard);
}
