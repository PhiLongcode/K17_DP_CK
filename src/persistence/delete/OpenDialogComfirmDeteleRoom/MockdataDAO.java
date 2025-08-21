package persistence.delete.OpenDialogComfirmDeteleRoom;

import java.util.LinkedHashMap;
import java.util.Map;

public class MockdataDAO implements OpenDialogComfirmDeteleRoomGateway {
    @Override
    public RoomDTO getRoomDeatail(String idRoom) {
        return store.get(idRoom);
    }

    private final Map<String, RoomDTO> store = new LinkedHashMap<>();



    /*// Thêm dữ liệu mẫu
    private void seed() {
        store.put("R-001", new RoomDTO(
                "R-001",
                "A",
                150.0,
                "LECTURE_HALL",
                12,
                new Date(2024 - 1900, 0, 10), // tháng bắt đầu từ 0
                true,
                0,
                "General",
                120,
                false
        ));

        store.put("R-002", new RoomDTO(
                "R-002",
                "B",
                80.0,
                "COMPUTER_LAB",
                8,
                new Date(2024 - 1900, 2, 5),
                true,
                40,
                "IT",
                40,
                false
        ));

        store.put("R-003", new RoomDTO(
                "R-003",
                "C",
                60.0,
                "LABORATORY",
                6,
                new Date(2022 - 1900, 5, 15),
                false,
                10,
                "Chemistry",
                20,
                true
        ));
    }

    // Lấy toàn bộ danh sách phòng
    public Map<String, RoomDTO> findAll() {
        return new LinkedHashMap<>(store);
    }


       public void deleteById(String id) {
        store.remove(id);
    }*/
}
