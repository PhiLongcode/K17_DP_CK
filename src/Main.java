import business.usecase.OpenAddRoomUsecase;
import dto.RoomTypeResDTO;
import persistence.dao.OpenAddRoomTypeDAO;
import persistence.gateway.OpenAddRoomGateway;
import presentation.controller.OpenAddRoomController;
import presentation.model.OpenAddRoomModel;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        OpenAddRoomGateway openAddRoomGateway = new OpenAddRoomTypeDAO();
        OpenAddRoomModel openAddRoomModel = new OpenAddRoomModel();
        OpenAddRoomUsecase openAddRoomUsecase = new OpenAddRoomUsecase(openAddRoomGateway);
        OpenAddRoomController openAddRoomController = new OpenAddRoomController(openAddRoomUsecase, openAddRoomModel);
        openAddRoomController.execute();
        for (RoomTypeResDTO roomTypeResDTO : openAddRoomModel.roomTypeResDTO) {
            System.out.println(roomTypeResDTO.getName());
        }
    }
}