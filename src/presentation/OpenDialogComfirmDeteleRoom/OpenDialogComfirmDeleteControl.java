package presentation.OpenDialogComfirmDeteleRoom;

import business.OpenDialogComfirmDeteleRoom.OpenDialogComfirmDeteleRoomUseCase;
import business.OpenDialogComfirmDeteleRoom.ResRoomDetailDTO;

import java.text.SimpleDateFormat;

public class OpenDialogComfirmDeleteControl {
    OpenDialogComfirmDeteleRoomUseCase openDialogComfirmDeteleRoomUseCase;
    OpenDialogComfirmDeleteModel model;
    public OpenDialogComfirmDeleteControl(OpenDialogComfirmDeteleRoomUseCase openDialogComfirmDeteleRoomUseCase) {
        this.openDialogComfirmDeteleRoomUseCase = openDialogComfirmDeteleRoomUseCase;
    }

    public void execute(String id) {

        ResRoomDetailDTO res = openDialogComfirmDeteleRoomUseCase.execute(id);
        model.viewDeatailRoomDTO = convertToViewDeatail(res);
        model.notifySubscribers();

    }
    public void setModel(OpenDialogComfirmDeleteModel model) {
        this.model = model;
    }
    private  ViewDeatailRoomDTO convertToViewDeatail(ResRoomDetailDTO res){
        ViewDeatailRoomDTO dto = new ViewDeatailRoomDTO();
        dto.roomId = res.roomID;
        dto.buildingBlock =  res.buildingBlock;
        dto.roomType = res.roomType;
        dto.area = String.valueOf(res.area);
        dto.numLightBulbs = String.valueOf(res.numLightBulbs) ;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dto.startDateOfOperation = sdf.format(res.startDateOfOperation);
        dto.meetsStandard = res.meetsStandard ? "Yes" : "No";
        return dto;
    }
}
