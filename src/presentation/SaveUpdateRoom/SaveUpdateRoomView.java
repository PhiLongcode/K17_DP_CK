package presentation.SaveUpdateRoom;

import javax.swing.*;
import presentation.SaveUpdateRoom.SaveUpdateRoomModel;

public class SaveUpdateRoomView extends JFrame implements Subscriber {
    private SaveUpdateRoomModel model;

    public SaveUpdateRoomView(SaveUpdateRoomModel model) {
        this.model = model;
        model.addSubscriber(this);
        initUI();
    }

    private void initUI() {
        setTitle("Save/Update Room");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void update() {
        boolean status = model.getStatus();
        if (status) {
            JOptionPane.showMessageDialog(this, "Thông tin phòng được cập nhật thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Thông tin phòng được cập nhật thất bại.");
        }
    }
}
