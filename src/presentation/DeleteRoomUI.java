package presentation;

import Observer.Subscriber;

import javax.swing.*;
import java.awt.*;

public class DeleteRoomUI extends JFrame implements Subscriber{
    private final DeleteRoomController controller;

    public DeleteRoomUI(DeleteRoomController controller) {
        this.controller = controller;

        setTitle("Test Xóa Phòng");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel nhập mã phòng
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Mã phòng:");
        JTextField txtRoomId = new JTextField(15);
        inputPanel.add(label);
        inputPanel.add(txtRoomId);

        // Nút Xóa
        JButton btnDelete = new JButton("Xóa");
        btnDelete.setForeground(Color.RED);
        btnDelete.addActionListener(e -> {
            String roomId = txtRoomId.getText().trim();
            if (roomId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã phòng");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc muốn xóa phòng " + roomId + "?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = controller.deleteRoom(roomId);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy hoặc xóa thất bại");
                }
            }
        });

        add(inputPanel, BorderLayout.CENTER);
        add(btnDelete, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void update() {

    }
}
