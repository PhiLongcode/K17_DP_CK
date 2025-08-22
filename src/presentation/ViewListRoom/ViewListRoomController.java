package presentation.ViewListRoom;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import business.ViewListRoom.RoomViewDTO;
import business.ViewListRoom.ViewListRoomUseCase;

public class ViewListRoomController {
	private ViewListRoomModel model;
    private ViewListRoomUseCase useCase;

    public ViewListRoomController(ViewListRoomModel model, ViewListRoomUseCase useCase) {
        this.model = model;
        this.useCase = useCase;
    }

    public void execute() throws SQLException, ParseException {
        List<RoomViewDTO> dtos = useCase.execute();
        List<RoomViewItem> items = convertToPresenter(dtos);
        model.setListRoomView(items);
    }

    private List<RoomViewItem> convertToPresenter(List<RoomViewDTO> dtos) {
        return dtos.stream().map(dto -> {
            RoomViewItem item = new RoomViewItem();
            item.roomId = dto.roomId;
            item.buildingBlock = dto.buildingBlock;
            item.area = String.valueOf(dto.area) ;
            item.numOfLights = String.valueOf(dto.numOfLights) ;
            item.startDate = String.valueOf(dto.startDate) ;
            item.type = dto.type;
            item.standard = dto.standard ;
            return item;
        }).toList();
    }
}
