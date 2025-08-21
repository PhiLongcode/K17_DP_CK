package persistence.OpenUpdateRoomForm;

import java.sql.SQLException;
import java.util.List;

public interface OpenUpdateRoomFromGateway {
  List<TypeRoomDTO> getTypeRoom() throws SQLException;
  RoomDTO getRoomDTO(String id) throws SQLException;
}
