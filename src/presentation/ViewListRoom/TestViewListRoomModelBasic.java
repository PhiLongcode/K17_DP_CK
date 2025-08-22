package presentation.ViewListRoom;

import java.util.Arrays;

public class TestViewListRoomModelBasic {
    public static void main(String[] args) {
        // Tạo model
        ViewListRoomModel model = new ViewListRoomModel();

        // Tạo RoomViewItem giả
        RoomViewItem item1 = new RoomViewItem();
        item1.roomId = "R101";
        item1.buildingBlock = "A";
        item1.area = "45.5";
        item1.numOfLights = "10";
        item1.startDate = "2025-01-01";
        item1.type = "Lý thuyết";
        item1.standard = true;

        RoomViewItem item2 = new RoomViewItem();
        item2.roomId = "R202";
        item2.buildingBlock = "B";
        item2.area = "30.0";
        item2.numOfLights = "8";
        item2.startDate = "2025-02-01";
        item2.type = "Máy tính";
        item2.standard = false;

        // Set list vào model
        model.setListRoomView(Arrays.asList(item1, item2));

        // In ra dữ liệu trong model
        for (RoomViewItem item : model.getListRoomView()) {
            System.out.println("Mã phòng: " + item.roomId + ", Loại: " + item.type);
        }
    }
}
