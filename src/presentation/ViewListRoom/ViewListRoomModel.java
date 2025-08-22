package presentation.ViewListRoom;

import java.util.List;

public class ViewListRoomModel extends Publisher{
	private List<RoomViewItem> listRoomView;

    public void setListRoomView(List<RoomViewItem> listRoomView) {
        this.listRoomView = listRoomView;
        notifySubscribers();
    }
    
    public List<RoomViewItem> getListRoomView() {
        return listRoomView;
    }
}
