package presentation.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HeaderPanel extends JPanel {
    public JButton logoutButton;

    public HeaderPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(44, 62, 80)); // #2c3e50
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Quản lý phòng học");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);



        add(titleLabel, BorderLayout.WEST);
      /*  add(logoutButton, BorderLayout.EAST);*/
    }
}
