package persistence.OpenUpdateRoomForm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLOpenUpdateRoomFromDAO implements OpenUpdateRoomFromGateway {
	
	private Connection conn;

	public MySQLOpenUpdateRoomFromDAO() throws ClassNotFoundException, SQLException {
		// tải driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		String username = "root";
		String password = "123456";
		String url = "jdbc:mysql://localhost:3306/roommagement?useSSL=false&serverTimezone=UTC";
		conn = DriverManager.getConnection(url, username, password);
		System.out.println("Connected!!!");
	}
	
	
	// Lấy thông tin 1 phòng theo room_id
	@Override
	public RoomDTO getRoomDTO(String id) throws SQLException {
	    RoomDTO dto = null;
	    Statement stmt = null;
	    ResultSet rs = null;

	    String sql = "SELECT * FROM rooms WHERE room_id = '" + id + "'";

	    stmt = conn.createStatement();
	    rs = stmt.executeQuery(sql);

	    if (rs.next()) {
	        dto = new RoomDTO();
	        dto.roomId = rs.getString("room_id");
	        dto.builddingBlock = rs.getString("building_block");
	        dto.area = rs.getDouble("area");
	        dto.numLightBulbs = rs.getInt("num_of_lights");
	        dto.startDateOfOperation = rs.getDate("start_date");
	        dto.typeRoom = rs.getInt("typeID");
	        dto.hasProjector = rs.getBoolean("has_projector");

	        // --- Xử lý cột có thể NULL ---
	        int numComp = rs.getInt("num_computers");
	        dto.numComputers = rs.wasNull() ? null : numComp;

	        dto.specialization = rs.getString("specialization");

	        int cap = rs.getInt("capacity");
	        dto.capacity = rs.wasNull() ? null : cap;

	        boolean sink = rs.getBoolean("has_sink");
	        dto.hasSink = rs.wasNull() ? null : sink;
	    }

	    rs.close();
	    stmt.close();


	    return dto;
	}




	// Lấy danh sách tất cả loại phòng
	@Override
	public List<TypeRoomDTO> getTypeRoom() throws SQLException {
	    List<TypeRoomDTO> list = new ArrayList<>();
	    Statement stmt = null;
	    ResultSet rs = null;

	    String sql = "SELECT id, name, description FROM typeroom";

	    stmt = conn.createStatement();
	    rs = stmt.executeQuery(sql);

	    while (rs.next()) {
	        TypeRoomDTO dto = new TypeRoomDTO();
	        dto.typeRoomId = rs.getInt("id");
	        dto.nameTypeRoom = rs.getString("name");
	        dto.description = rs.getString("description");

	        list.add(dto);
	    }

	    rs.close();
	    stmt.close();


	    return list;
	}

}
