package persistence.ViewListRoom;

import persistence.ConnectDB;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MySQLViewRoom implements ViewRoomGateway {
    private Connection conn; //acso
// contrucstor MySQLViewRoom
    // b1 tạo biến đối tượng
    // b2
    // b3
    public MySQLViewRoom() throws ClassNotFoundException, SQLException {
         ConnectDB connectDB = null;
                connectDB = new ConnectDB();
        this.conn = connectDB.getConnection();
        // lưu vào bộ nhớ đệm của java khi mà chạy trg chình thì xóa

    }



    @Override// đanhs dấu đang ghi đè tính chất ghi đè
    public List<RoomDTO> viewRoom() {
        List<RoomDTO> list = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Statement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM rooms";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                RoomDTO room = new RoomDTO();
                room.setRoomID(rs.getString("room_id"));
                room.setBuildingBlock(rs.getString("building_block"));
                room.setArea(rs.getDouble("area"));
                room.setNumLightBulbs(rs.getInt("num_of_lights"));
                room.setStartDateOfOperation((rs.getDate("start_date")));
                room.setTypeId(rs.getInt("typeID"));
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
                if (conn != null) conn.close(); // Nếu bạn không dùng connection pool
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;


    }
}
