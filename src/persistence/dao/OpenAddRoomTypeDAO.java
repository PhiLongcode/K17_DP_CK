package persistence.dao;

import dto.RoomTypeDTO;
import persistence.ConnectDB;
import persistence.gateway.OpenAddRoomGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OpenAddRoomTypeDAO implements OpenAddRoomGateway {
    ConnectDB connectDB = new ConnectDB();
    Connection connection = connectDB.getConnection();

    public OpenAddRoomTypeDAO() throws SQLException, ClassNotFoundException {
    }

    @Override
    public List<RoomTypeDTO> GetAllRoomTypes() {
        List<RoomTypeDTO> list = new ArrayList<>();
        String sql = "select * from typeroom";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
                roomTypeDTO.setId(resultSet.getInt("id"));
                roomTypeDTO.setName(resultSet.getString("name"));
                roomTypeDTO.setDescription(resultSet.getString("description"));
                list.add(roomTypeDTO);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
