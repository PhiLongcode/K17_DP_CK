package presentation.OpenUpdateRoomForm;

import java.util.List;

public class OpenUpdateRoomFromModel extends Publisher {
    private List<TypeRoomItem> typeRoomItemList;
    private RoomItem roomItem;

    public OpenUpdateRoomFromModel() {
        this.typeRoomItemList = new java.util.ArrayList<>();
        this.roomItem = roomItem;
        

    }

    public RoomItem getRoomItem() {
        return roomItem;
    }

    public void setRoomItem(RoomItem roomItem) {
        this.roomItem = roomItem;
        notifySubscribers();
    }

    public void setTypeRoomItemList(List<TypeRoomItem> typeRoomItemList) {
        this.typeRoomItemList = typeRoomItemList;
        notifySubscribers();
    }


	public List<TypeRoomItem> getTypeRoomItemList() {	
        return this.typeRoomItemList;
    }
}
