package persistence.SaveUpdateRoom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.DriverManager;

public class MySQLSaveUpdateRoomDAO implements SaveUpdateRoomGateway {

    private Connection conn;

    public MySQLSaveUpdateRoomDAO() throws ClassNotFoundException, SQLException {
		// tải driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		String username = "root";
		String password = "123456";
		String url = "jdbc:mysql://localhost:3306/roommagement?useSSL=false&serverTimezone=UTC";
		conn = DriverManager.getConnection(url, username, password);
		System.out.println("Connected!!!");
	}

    @Override
    public boolean updateRoom(RoomDTO roomDto) {
        String sql = "UPDATE rooms SET building_block = ?, area = ?, num_of_lights = ?, "
                   + "start_date = ?, typeID = ?, has_projector = ?, "
                   + "num_computers = ?, specialization = ?, capacity = ?, has_sink = ? "
                   + "WHERE room_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, roomDto.builddingBlock);
            stmt.setDouble(2, roomDto.area);
            stmt.setInt(3, roomDto.numLightBulbs);
            stmt.setDate(4, new Date(roomDto.startDateOfOperation.getTime()));
            stmt.setInt(5, roomDto.typeRoom);
            stmt.setBoolean(6, roomDto.hasProjector);
            stmt.setInt(7, roomDto.numComputers);
            stmt.setString(8, roomDto.specialization);
            stmt.setInt(9, roomDto.capacity);
            stmt.setBoolean(10, roomDto.hasSink);
            stmt.setString(11, roomDto.roomId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
