package persistence.ViewListRoom;

import persistence.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLViewRoom implements ViewRoomGateway {
    private Connection conn;

    public MySQLViewRoom() throws ClassNotFoundException, SQLException {
        ConnectDB connectDB = new ConnectDB();
        this.conn = connectDB.getConnection();
    }

    @Override
    public List<RoomDTO> viewRoom() {
        List<RoomDTO> list = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM rooms INNER JOIN typeroom ON rooms.typeId = typeroom.id";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                RoomDTO room = new RoomDTO();
                room.setRoomID(rs.getString("room_id"));
                room.setBuildingBlock(rs.getString("building_block"));
                room.setArea(rs.getDouble("area"));
                room.setRoomType(rs.getString("name")); // cột name từ bảng typeroom
                room.setNumLightBulbs(rs.getInt("num_of_lights"));
                room.setStartDateOfOperation(rs.getDate("start_date"));
                room.setTypeId(rs.getInt("typeId"));
                room.setHasProject(rs.getBoolean("has_projector"));
                room.setNumComputers(rs.getInt("num_computers"));
                room.setSpecialization(rs.getString("specialization"));
                room.setCapacity(rs.getInt("capacity"));
                room.setHasSink(rs.getBoolean("has_sink"));

                list.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    // Hàm test
    public static void main(String[] args) {
        try {
            MySQLViewRoom repo = new MySQLViewRoom();
            List<RoomDTO> rooms = repo.viewRoom();

            if (rooms.isEmpty()) {
                System.out.println("Không có dữ liệu phòng nào trong DB.");
            } else {
                for (RoomDTO room : rooms) {
                    System.out.println(room);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
