package presentation.OpenDialogComfirmDeteleRoom;
import Observer.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OpenDialogConfirmDeleteRoomUI extends JDialog implements Subscriber {

    private JLabel lblRoomId;
    private JLabel lblBuildingBlock;
    private JLabel lblArea;
    private JLabel lblRoomType;
    private JLabel lblNumLightBulbs;
    private JLabel lblStartDateOfOperation;
    private JLabel lblMeetsStandard;

    private JButton btnDelete;
    private JButton btnCancel;

    public OpenDialogConfirmDeleteRoomUI(JFrame parent) {
        super(parent, "Xác nhận xóa phòng", true);
        initUI();
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
        lblNumLightBulbs.setText(room.numLightBulbs));
        lblStartDateOfOperation.setText(room.startDateOfOperation);
        lblMeetsStandard.setText(room.meetsStandard );
    }

    public void onDelete(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    public void onCancel(ActionListener listener) {
        btnCancel.addActionListener(listener);
    }

    @Override
    public void update() {

    }
}
