package persistence.delete.OpenDialogComfirmDeteleRoom;

import persistence.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLOpenDialogComfirmDeteleRoomDAO implements OpenDialogComfirmDeteleRoomGateway {

    private Connection conn;

    public MySQLOpenDialogComfirmDeteleRoomDAO() throws SQLException, ClassNotFoundException {
        ConnectDB connectDB = new ConnectDB();
        this.conn = connectDB.getConnection();
    }

    @Override
    public RoomDTO getRoomDeatail(String roomId) {
        String sql = "SELECT r.room_id, t.name AS roomType, r.building_block, " +
                "r.area, r.num_of_lights, r.start_date, r.has_projector, " +
                "r.num_computers, r.specialization, r.capacity, r.has_sink " +
                "FROM rooms r " +
                "JOIN typeroom t ON r.typeID = t.id " +
                "WHERE r.room_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, roomId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToDTO(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<RoomDTO> getAllRooms() {
        List<RoomDTO> rooms = new ArrayList<>();
        String sql = "SELECT r.room_id, t.name AS roomType, r.building_block, " +
                "r.area, r.num_of_lights, r.start_date, r.has_projector, " +
                "r.num_computers, r.specialization, r.capacity, r.has_sink " +
                "FROM rooms r " +
                "JOIN typeroom t ON r.typeID = t.id";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                rooms.add(mapRowToDTO(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    private RoomDTO mapRowToDTO(ResultSet rs) throws SQLException {
        RoomDTO dto = new RoomDTO();
        dto.roomID = rs.getString("room_id");
        dto.roomType = rs.getString("roomType"); // từ JOIN typeroom.name
        dto.buildingBlock = rs.getString("building_block");
        dto.area = rs.getDouble("area");
        dto.numLightBulbs = rs.getInt("num_of_lights");
        dto.startDateOfOperation = rs.getDate("start_date");
        dto.hasProject = rs.getBoolean("has_projector");
        dto.numComputers = rs.getInt("num_computers");
        dto.specialization = rs.getString("specialization");
        dto.capacity = rs.getInt("capacity");
        dto.hasSink = rs.getBoolean("has_sink");
        return dto;
    }

}
