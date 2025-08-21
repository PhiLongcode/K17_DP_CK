package presentation.UI;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {
    public JButton addButton, avgAreaButton, totalRoomButton;

    public SidebarPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(52, 73, 94)); // xám đậm hơn cho sang

        addButton = createMenuButton("+ Thêm phòng");
        avgAreaButton = createMenuButton("+ Tính trung bình " +"diện tích");
        totalRoomButton = createMenuButton("+ Tổng số lượng " +"phòng");

        add(Box.createVerticalStrut(20));
        add(addButton);
        add(Box.createVerticalStrut(10));
        add(avgAreaButton);
        add(Box.createVerticalStrut(10));
        add(totalRoomButton);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(
                250, 40));
        button.setBackground(new Color(41, 128, 185));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }
}
