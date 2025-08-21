package presentation.View;

import business.ViewListRoom.ViewRoomDTO;
import business.ViewListRoom.ViewRoomUseCase;

import javax.swing.text.html.ListView;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ViewRoomController {
    private ViewRoomModel viewRoomModel;
    private ViewRoomUseCase viewRoomUseCase;

    public ViewRoomController(ViewRoomUseCase viewRoomUseCase, ViewRoomModel viewRoomModel) {
        this.viewRoomUseCase = viewRoomUseCase;
        this.viewRoomModel = viewRoomModel;
    }
    public void execute() throws SQLException, ParseException {

        List<ViewRoomDTO> list=viewRoomUseCase.execute();// gửi thông điệp
        List<ListViewObject> listPresent = convertToViewObject(list);//
        // chuc nang cua controller la gi? trong mvc/
        // 1. nhan yeu cau tu User
        // 2. thong bao cho model / vi du : t co du lieu danh sach phong roi nef model
        // model do lay du lieu nay gui thong diep cho V trinh dien di.
        // Model: cap nhat trang thai moi
        // model cos nhiem vu nhan yeu cau tu controller vaf phan hoi cho V
        // View : tu dong
        // Tu dong cap nhap du lieu khi co su thay doi.
        // Muon co tu dong
        viewRoomModel.setListViewObjectList(listPresent);

    }

    public List<ListViewObject>  convertToViewObject (List<ViewRoomDTO> listRoomDTO)
    {
        List<ListViewObject> list = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        int stt = 1;
        for (ViewRoomDTO dto: listRoomDTO) {
            ListViewObject item = new ListViewObject();
            item.stt = String.valueOf(stt++);
            item.roomID = dto.roomID;
            item.buildingBlock = dto.buildingBlock;
            item.roomType = dto.roomType;
            if (dto.meetsStandard != null && dto.meetsStandard) {
                item.meetsStandard = "Đạt";
            } else {
                item.meetsStandard = "Không đạt";
            }
            item.startDateOfOperation = fmt.format(dto.startDateOfOperation);
            item.area = String.valueOf(dto.area);
            item.numLightBulbs = String.valueOf(dto.numLightBulbs);
            list.add(item);
        }

        return list;
    }

}


