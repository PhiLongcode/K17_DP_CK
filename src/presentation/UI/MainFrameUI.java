package presentation.UI;



import javax.swing.*;
import java.awt.*;

public class MainFrameUI extends javax.swing.JFrame {
    public MainFrameUI() {
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
        center.add(new TableViewPanel(), BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);

        // Status bar
       /* add(new StatusBar(), BorderLayout.SOUTH);*/
    }

}
