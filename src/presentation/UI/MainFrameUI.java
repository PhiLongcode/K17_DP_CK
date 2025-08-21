package presentation.UI;



import presentation.View.TableViewPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MainFrameUI extends javax.swing.JFrame {
    public MainFrameUI() throws SQLException, ClassNotFoundException {
        setTitle("Quản lý phòng học");
        setSize(1500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        add(new HeaderPanel(), BorderLayout.NORTH);

        // Sidebar trái
        SidebarPanel sidebar = new SidebarPanel();
        sidebar.setPreferredSize(new Dimension(280, 0));
        add(sidebar, BorderLayout.WEST);

        // Trung tâm: Filter + Table
        JPanel center = new JPanel(new BorderLayout());
        center.add(new FilterPanel(), BorderLayout.NORTH);
        try {
            center.add(new TableViewPanel(), BorderLayout.CENTER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        add(center, BorderLayout.CENTER);

        // Status bar
       /* add(new StatusBar(), BorderLayout.SOUTH);*/
    }

}
