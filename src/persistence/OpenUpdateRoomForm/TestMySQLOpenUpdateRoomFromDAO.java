package persistence.OpenUpdateRoomForm;
import java.sql.SQLException;
import java.util.List;
public class TestMySQLOpenUpdateRoomFromDAO {
	  public static void main(String[] args) {
	        try {
	            // Tạo DAO (khi new sẽ kết nối DB)
	            MySQLOpenUpdateRoomFromDAO dao = new MySQLOpenUpdateRoomFromDAO();

	            // Test lấy 1 phòng theo id
	            String testRoomId = "LT101"; // bạn nhớ thay bằng room_id có sẵn trong DB
	            RoomDTO room = dao.getRoomDTO(testRoomId);
	            if (room != null) {
	                System.out.println("=== THÔNG TIN PHÒNG ===");
	                System.out.println("Room ID: " + room.roomId);
	                System.out.println("Dãy nhà: " + room.builddingBlock);
	                System.out.println("Diện tích: " + room.area);
	                System.out.println("Số bóng đèn: " + room.numLightBulbs);
	                System.out.println("Ngày hoạt động: " + room.startDateOfOperation);
	                System.out.println("Loại phòng (typeID): " + room.typeRoom);
	                System.out.println("Có máy chiếu: " + room.hasProjector);
	                System.out.println("Số máy tính: " + room.numComputers);
	                System.out.println("Chuyên môn: " + room.specialization);
	                System.out.println("Sức chứa: " + room.capacity);
	                System.out.println("Có bồn rửa: " + room.hasSink);
	            } else {
	                System.out.println("Không tìm thấy phòng với id = " + testRoomId);
	            }

	            // Tạo lại DAO vì trong getRoomDTO đã đóng kết nối
	            dao = new MySQLOpenUpdateRoomFromDAO();

	            // Test lấy danh sách loại phòng
	            List<TypeRoomDTO> types = dao.getTypeRoom();
	            System.out.println("\n=== DANH SÁCH LOẠI PHÒNG ===");
	            for (TypeRoomDTO t : types) {
	                System.out.println(t.typeRoomId + " - " + t.nameTypeRoom + " - " + t.description);
	            }

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
