package persistence.ViewListRoom;

import java.sql.SQLException;
import java.util.List;

public class TestViewListRoom {
    public static void main(String[] args) {
        try {
            // Tạo DAO
            MySQLViewListRoomDAO dao = new MySQLViewListRoomDAO();

            // Lấy danh sách phòng
            List<RoomDTO> rooms = dao.getAll();

            // In dữ liệu ra console
            if (rooms.isEmpty()) {
                System.out.println("Không có dữ liệu phòng nào.");
            } else {
                for (RoomDTO room : rooms) {
                    System.out.println("----- Phòng -----");
                    System.out.println("Mã phòng: " + room.roomId);
                    System.out.println("Khối nhà: " + room.buildingBlock);
                    System.out.println("Diện tích: " + room.area);
                    System.out.println("Số bóng đèn: " + room.numOfLights);
                    System.out.println("Ngày bắt đầu sử dụng: " + room.startDate);
                    System.out.println("Loại phòng: " + room.typeName);
                    System.out.println("Có máy chiếu: " + room.hasProjector);
                    System.out.println("Số máy tính: " + room.numComputers);
                    System.out.println("Chuyên môn: " + room.specialization);
                    System.out.println("Sức chứa: " + room.capacity);
                    System.out.println("Có bồn rửa: " + room.hasSink);
                    System.out.println("-------------------------");
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
