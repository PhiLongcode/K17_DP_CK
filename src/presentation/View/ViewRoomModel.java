package presentation.View;

import Observer.Publisher;
import business.ViewListRoom.ViewRoomDTO;

import java.util.ArrayList;
import java.util.List;

public class  ViewRoomModel extends Publisher {//implements dungf cho interface
   // fiedl
    public List<ListViewObject> listViewObjectList= new ArrayList<>();
// hanh dong
    public List<ListViewObject> getListViewObject() {
        return listViewObjectList;
    }
    public void setListViewObjectList(List<ListViewObject> list) {
        this.listViewObjectList = list;
        notifySubscribers();  // Gọi khi dữ liệu thay đổi
    }
}

