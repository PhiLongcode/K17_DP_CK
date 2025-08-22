package business.ViewListRoom;

import persistence.ViewListRoom.MySQLViewListRoomDAO;
import persistence.ViewListRoom.RoomDTO;
import persistence.ViewListRoom.ViewListRoomGateway;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class TestViewListRoomUseCase {
    public static void main(String[] args) {
        try {
            // Tạo DAO kết nối DB
            ViewListRoomGateway gateway = new MySQLViewListRoomDAO();

            // Tạo use case
            ViewListRoomUseCase useCase = new ViewListRoomUseCase(gateway);

            // Thực thi
            List<RoomViewDTO> result = useCase.execute();

            // In ra console
            if (result.isEmpty()) {
                System.out.println("Không có phòng nào.");
            } else {
                for (RoomViewDTO room : result) {
                    System.out.println("Mã phòng: " + room.roomId);
                    System.out.println("Khối nhà: " + room.buildingBlock);
                    System.out.println("Diện tích: " + room.area);
                    System.out.println("Số bóng đèn: " + room.numOfLights);
                    System.out.println("Ngày bắt đầu: " + room.startDate);
                    System.out.println("Loại: " + room.type);
                    System.out.println("Chuẩn: " + room.standard);
                    System.out.println("----------------------");
                }
            }

        } catch (ClassNotFoundException | SQLException | ParseException e) {
            e.printStackTrace();
        }
    }
}
