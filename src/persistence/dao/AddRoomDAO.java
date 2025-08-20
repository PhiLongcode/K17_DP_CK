package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.RoomDTO;
import persistence.ConnectDB;
import persistence.gateway.AddRoomGateway;

public class AddRoomDAO implements AddRoomGateway {
    ConnectDB connectDB = new ConnectDB();
    Connection connection = connectDB.getConnection();

    public AddRoomDAO() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void addRoom(RoomDTO room) {
        String sql = "INSERT INTO rooms (room_id, building_block, area, num_of_lights, start_date, typeID, has_projector, num_computers, specialization, capacity, has_sink) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, room.getRoomId());
            statement.setString(2, room.getBuildingBlock());
            if (room.getArea() != null)
                statement.setDouble(3, room.getArea());
            else
                statement.setNull(3, java.sql.Types.DOUBLE);
            if (room.getNumOfLights() != null)
                statement.setInt(4, room.getNumOfLights());
            else
                statement.setNull(4, java.sql.Types.INTEGER);
            if (room.getStartDate() != null)
                statement.setDate(5, room.getStartDate());
            else
                statement.setNull(5, java.sql.Types.DATE);
            if (room.getTypeId() != null)
                statement.setInt(6, room.getTypeId());
            else
                statement.setNull(6, java.sql.Types.INTEGER);
            if (room.getHasProjector() != null)
                statement.setBoolean(7, room.getHasProjector());
            else
                statement.setNull(7, java.sql.Types.BOOLEAN);
            if (room.getNumComputers() != null)
                statement.setInt(8, room.getNumComputers());
            else
                statement.setNull(8, java.sql.Types.INTEGER);
            statement.setString(9, room.getSpecialization());
            if (room.getCapacity() != null)
                statement.setInt(10, room.getCapacity());
            else
                statement.setNull(10, java.sql.Types.INTEGER);
            if (room.getHasSink() != null)
                statement.setBoolean(11, room.getHasSink());
            else
                statement.setNull(11, java.sql.Types.BOOLEAN);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
