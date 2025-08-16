package presentation;

import business.entity.Room;
import Observer.Publisher;

import java.util.ArrayList;
import java.util.List;

public class RoomViewModel extends Publisher {
    private final List<Room> rooms = new ArrayList<>();
    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms); // tránh bị sửa ngoài ý muốn
    }

    public void setRooms(List<Room> newRooms) {
        rooms.clear();
        if (newRooms != null) {
            rooms.addAll(newRooms);
        }
        notifySubscribers();
    }

    public void addRoom(Room room) {
        if (room != null) {
            rooms.add(room);
            notifySubscribers();
        }
    }

    public void removeRoom(String roomId) {
        if (roomId != null && !roomId.isEmpty()) {
            rooms.removeIf(r -> roomId.equals(r.getRoomId()));
            notifySubscribers();
        }
    }

}
