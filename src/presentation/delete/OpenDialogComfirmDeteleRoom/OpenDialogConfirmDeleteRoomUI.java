package presentation.delete.OpenDialogComfirmDeteleRoom;
import Observer.Subscriber;
import business.delete.DeleteRoom.DeleteRoomUsecase;
import persistence.delete.DeleteRoom.DeleteRoomGateway;
import persistence.delete.DeleteRoom.MySQLDeleteRoomDAO;
import presentation.delete.DeleteRoom.DeleteRoomController;
import presentation.delete.DeleteRoom.DeleteRoomModel;
import presentation.delete.DeleteRoom.DialogNotiDeleteRoomView;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class OpenDialogConfirmDeleteRoomUI extends JDialog implements Subscriber {
    private JFrame parent;
    private JLabel lblRoomId;
    private JLabel lblBuildingBlock;
    private JLabel lblArea;
    private JLabel lblRoomType;
    private JLabel lblNumLightBulbs;
    private JLabel lblStartDateOfOperation;
    private JLabel lblMeetsStandard;

    private JButton btnDelete;
    private JButton btnCancel;

    private  OpenDialogComfirmDeleteModel viewModel;
    public OpenDialogConfirmDeleteRoomUI(JFrame parent) {
        super(parent, "Xác nhận xóa phòng", true);

        initUI();
        bindEvents();

    }
    public void setViewModel(OpenDialogComfirmDeleteModel viewModel) {
        this.viewModel = viewModel;

        //đăng ký subscriber với publisher
        viewModel.addSubscriber(this);
    }
    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        // Panel thông tin phòng
        JPanel infoPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        lblRoomId = new JLabel();
        lblBuildingBlock = new JLabel();
        lblArea = new JLabel();
        lblRoomType = new JLabel();
        lblNumLightBulbs = new JLabel();
        lblStartDateOfOperation = new JLabel();
        lblMeetsStandard = new JLabel();

        infoPanel.add(new JLabel("Mã phòng:"));
        infoPanel.add(lblRoomId);
        infoPanel.add(new JLabel("Dãy nhà:"));
        infoPanel.add(lblBuildingBlock);
        infoPanel.add(new JLabel("Diện tích:"));
        infoPanel.add(lblArea);
        infoPanel.add(new JLabel("Loại phòng:"));
        infoPanel.add(lblRoomType);
        infoPanel.add(new JLabel("Số bóng đèn:"));
        infoPanel.add(lblNumLightBulbs);
        infoPanel.add(new JLabel("Ngày bắt đầu hoạt động:"));
        infoPanel.add(lblStartDateOfOperation);
        infoPanel.add(new JLabel("Đạt tiêu chuẩn:"));
        infoPanel.add(lblMeetsStandard);

        add(infoPanel, BorderLayout.CENTER);

        // Panel nút bấm
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnDelete = new JButton("Xóa");
        btnCancel = new JButton("Hủy");

        buttonPanel.add(btnDelete);
        buttonPanel.add(btnCancel);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    public void setRoomDetails(ViewDeatailRoomDTO room) {
        lblRoomId.setText(room.roomId);
        lblBuildingBlock.setText(room.buildingBlock);
        lblArea.setText(room.area);
        lblRoomType.setText(room.roomType);
        lblNumLightBulbs.setText(room.numLightBulbs);
        lblStartDateOfOperation.setText(room.startDateOfOperation);
        lblMeetsStandard.setText(room.meetsStandard );
    }



    private void bindEvents() {
        // Sự kiện nút Xóa

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomId = lblRoomId.getText();
                // Khởi tạo model trước
                DeleteRoomModel model = new DeleteRoomModel();
                // gate
                DeleteRoomGateway gateway;
                try {
                    gateway = new MySQLDeleteRoomDAO();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                DeleteRoomUsecase uc = new DeleteRoomUsecase(gateway);
                DeleteRoomController controller = new DeleteRoomController(uc, model);

                DialogNotiDeleteRoomView view = new DialogNotiDeleteRoomView(parent,model );

               controller.deleteRoom(roomId);// Gọi use case
                view.setVisible(true);

                dispose(); // Đóng dialog sau khi xóa
            }
        });

        // Sự kiện nút Hủy
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng dialog
            }
        });
    }

    @Override
    public void update() {
    this.setRoomDetails(viewModel.viewDeatailRoomDTO);
    }
}
