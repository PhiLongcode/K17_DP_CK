package presentation.DeleteRoom;

import Observer.Subscriber;

import javax.swing.*;
import java.awt.*;

public class DialogNotiDeleteRoomView extends JDialog implements Subscriber {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel messageLabel;
    private final DeleteRoomModel viewModel;

    // Constructor
    public DialogNotiDeleteRoomView(Frame parent, DeleteRoomModel viewModel) {
        super(parent, "Delete Room Notification", true);
        this.viewModel = viewModel;

        setupUI();
        addActionListeners();

        // Đăng ký lắng nghe thay đổi từ ViewModel
        this.viewModel.addSubscriber(this);

        setLocationRelativeTo(parent);
        setSize(300, 150);
    }

    private void setupUI() {
        contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        messageLabel = new JLabel("", JLabel.CENTER);
        contentPane.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");
        buttonPanel.add(buttonOK);
        buttonPanel.add(buttonCancel);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }

    private void addActionListeners() {
        buttonOK.addActionListener(e -> dispose());
        buttonCancel.addActionListener(e -> dispose());
    }

    @Override
    public void update() {
        messageLabel.setText(viewModel.getMessage());
    }
}
