import javax.swing.*;
import business.ViewListRoom.ViewListRoomUseCase;
import persistence.ViewListRoom.MySQLViewListRoomDAO;
import presentation.ViewListRoom.*;

public class AppRoomManage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Tạo Model
                ViewListRoomModel model = new ViewListRoomModel();

                // Tạo UseCase (gắn với DAO lấy dữ liệu từ DB)
                ViewListRoomUseCase useCase = new ViewListRoomUseCase(new MySQLViewListRoomDAO());

                // Tạo Controller
                ViewListRoomController controller = new ViewListRoomController(model, useCase);

                // Tạo View và subscribe với model
                ViewListRoomView view = new ViewListRoomView(model);
                view.setVisible(true);

                // Gọi controller để load dữ liệu ban đầu
                controller.execute();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
