package persistence.delete.DeleteRoom;

import persistence.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLDeleteRoomDAO implements DeleteRoomGateway {
    private Connection conn;
    public MySQLDeleteRoomDAO() throws ClassNotFoundException, SQLException {
        ConnectDB connectDB = new ConnectDB();
        this.conn = connectDB.getConnection();
    }
    @Override
    public Boolean deleteRoom(String roomID) {
        String sql = "DELETE FROM Rooms WHERE room_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, roomID);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
