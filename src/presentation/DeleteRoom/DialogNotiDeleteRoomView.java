package presentation.DeleteRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogNotiDeleteRoom extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel messageLabel; // Nhãn để hiển thị thông báo

    // Constructor
    public DialogNotiDeleteRoom(Frame parent, String message) {
        super(parent, "Delete Room Notification", true);
        setupUI();
        initializeComponents(message);
        addActionListeners();
        setLocationRelativeTo(parent); // Căn giữa màn hình
        setSize(300, 150); // Kích thước cửa sổ
    }

    // Thiết lập giao diện
    private void setupUI() {
        contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        messageLabel = new JLabel();
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");
        buttonPanel.add(buttonOK);
        buttonPanel.add(buttonCancel);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }

    // Khởi tạo các thành phần với thông báo
    private void initializeComponents(String message) {
        messageLabel.setText(message);
    }

    // Thêm sự kiện cho các nút
    private void addActionListeners() {
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng dialog khi nhấn OK
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng dialog khi nhấn Cancel
            }
        });
    }

    // Phương thức main để test
 /*   public static void main(String[] args) {
        JFrame frame = new JFrame();
        DialogNotiDeleteRoom dialog = new DialogNotiDeleteRoom(frame, "Room deleted successfully");
        dialog.setVisible(true);
    }*/
}