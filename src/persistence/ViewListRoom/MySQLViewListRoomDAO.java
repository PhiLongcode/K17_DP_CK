package persistence.ViewListRoom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLViewListRoomDAO implements ViewListRoomGateway{
	 private Connection conn;

	    public MySQLViewListRoomDAO() throws ClassNotFoundException, SQLException {
			// tải driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			String username = "root";
			String password = "123456";
			String url = "jdbc:mysql://localhost:3306/roommagement?useSSL=false&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected!!!");
	    }

	    @Override
	    public List<RoomDTO> getAll() {
	        List<RoomDTO> list = new ArrayList<>();

	        String sql = "SELECT r.room_id, r.building_block, r.area, r.num_of_lights, r.start_date, " +
	                     "t.name AS room_type, r.has_projector, r.num_computers, r.specialization, " +
	                     "r.capacity, r.has_sink " +
	                     "FROM rooms r JOIN typeroom t ON r.typeID = t.id";

	        try (PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                RoomDTO room = new RoomDTO();

	                room.roomId = rs.getString("room_id");
	                room.buildingBlock = rs.getString("building_block");
	                room.area = rs.getDouble("area");
	                room.numOfLights = rs.getInt("num_of_lights");
	                room.startDate = rs.getDate("start_date");
	                room.typeName = rs.getString("room_type");
	                room.hasProjector = rs.getObject("has_projector") != null ? rs.getBoolean("has_projector") : null;
	                room.numComputers = rs.getObject("num_computers") != null ? rs.getInt("num_computers") : null;
	                room.specialization = rs.getString("specialization");
	                room.capacity = rs.getObject("capacity") != null ? rs.getInt("capacity") : null;
	                room.hasSink = rs.getObject("has_sink") != null ? rs.getBoolean("has_sink") : null;

	                list.add(room);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
}
