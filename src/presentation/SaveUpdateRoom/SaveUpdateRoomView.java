package presentation.SaveUpdateRoom;

import javax.swing.*;

import presentation.OpenUpdateRoomForm.RoomUpdateCallback;
import presentation.SaveUpdateRoom.SaveUpdateRoomModel;

public class SaveUpdateRoomView extends JFrame implements Subscriber {
    private SaveUpdateRoomModel model;
    private RoomUpdateCallback callback;
    private JFrame parentForm;

    public SaveUpdateRoomView(SaveUpdateRoomModel model,RoomUpdateCallback callback,JFrame parentForm) {
        this.model = model;
        this.callback = callback;
        this.parentForm = parentForm;
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
            if (callback != null) {
                callback.onRoomUpdated();
            }
            
            if (parentForm != null) {
                parentForm.dispose(); 
            }
            dispose(); 
        } else {
            JOptionPane.showMessageDialog(this, "Thông tin phòng được cập nhật thất bại.");
        }
    }
}
