package persistence.DeleteRoom;

import persistence.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteRoom implements DeleteRoomGateway {
    private Connection conn;
    public DeleteRoom() throws ClassNotFoundException, SQLException {
        ConnectDB connectDB = new ConnectDB();
        this.conn = connectDB.getConnection();
    }
    @Override
    public Boolean deleteRoom(String roomID) {
        String sql = "DELETE FROM Rooms WHERE roomID = ?";
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
