package presentation.SaveUpdateRoom;

import javax.swing.*;
import presentation.SaveUpdateRoom.SaveUpdateRoomModel;

public class SaveUpdateRoomUI extends JFrame implements Subscriber {
    private SaveUpdateRoomModel model;

    public SaveUpdateRoomUI(SaveUpdateRoomModel model) {
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
            JOptionPane.showMessageDialog(this, "Room saved/updated successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save/update room.");
        }
    }
}
