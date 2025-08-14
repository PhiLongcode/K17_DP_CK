package persistence;

import presentation.DeleteRoomGateway;

import java.sql.*;


public class MySQLDeleteRoomDAO implements DeleteRoomGateway {
    private final Connection conn;

    public MySQLDeleteRoomDAO() throws ClassNotFoundException, SQLException {
        ConnectDB connectDB = new ConnectDB();
        this.conn = connectDB.getConnection();
    }

    @Override
    public boolean deleteRoom(String roomId) {
        String sql = "DELETE FROM rooms WHERE room_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, roomId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa phòng: " + e.getMessage());
            return false;
        }
    }



}
