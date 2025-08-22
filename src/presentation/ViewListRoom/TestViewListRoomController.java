package presentation.ViewListRoom;

import business.ViewListRoom.ViewListRoomUseCase;
import persistence.ViewListRoom.MySQLViewListRoomDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class TestViewListRoomController {
    public static void main(String[] args) {
        try {
            // Model (đóng vai UI model)
            ViewListRoomModel model = new ViewListRoomModel();

            // UseCase (dùng DAO thật)
            ViewListRoomUseCase useCase = new ViewListRoomUseCase(new MySQLViewListRoomDAO());

            // Controller
            ViewListRoomController controller = new ViewListRoomController(model, useCase);

            // Chạy use case
            controller.execute();

            // Lấy kết quả từ model
            List<RoomViewItem> items = model.getListRoomView();

            if (items.isEmpty()) {
                System.out.println("⚠️ Không có phòng nào.");
            } else {
                for (RoomViewItem item : items) {
                    System.out.println("Mã phòng: " + item.roomId);
                    System.out.println("Khối nhà: " + item.buildingBlock);
                    System.out.println("Diện tích: " + item.area);
                    System.out.println("Số bóng đèn: " + item.numOfLights);
                    System.out.println("Ngày bắt đầu: " + item.startDate);
                    System.out.println("Loại: " + item.type);
                    System.out.println("Chuẩn: " + item.standard);
                    System.out.println("--------------------------");
                }
            }

        } catch (ClassNotFoundException | SQLException | ParseException e) {
            e.printStackTrace();
        }
    }
}
