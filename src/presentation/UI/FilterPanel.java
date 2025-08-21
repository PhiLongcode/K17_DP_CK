package presentation.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FilterPanel extends JPanel {
    public JTextField searchField;
    public JButton searchButton;
    public JComboBox<String> buildingFilter;
    public JComboBox<String> statusFilter;
    public JComboBox<String> lightFilter;
    public JButton refreshButton;

    public FilterPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(15, 15, 15, 15),
                BorderFactory.createTitledBorder("Tìm kiếm và Lọc")
        ));

        // Khởi tạo
        searchField = new JTextField(20);
        searchButton = new JButton("Tìm kiếm");
        buildingFilter = new JComboBox<>(new String[]{"Dãy A", "Dãy B", "Dãy C"});
        statusFilter = new JComboBox<>(new String[]{"Đạt", "Không đạt"});
        lightFilter = new JComboBox<>(new String[]{"Đủ", "Thiếu"});
        refreshButton = new JButton("Làm mới");

        // Thêm vào panel
        add(createFilterGroup("Từ khóa:", searchField));
        add(createFilterGroup("Tìm kiếm:", searchButton));
        add(createFilterGroup("Dãy nhà:", buildingFilter));
        add(createFilterGroup("Đủ tiêu chuẩn:", statusFilter));
        add(createFilterGroup("Đủ ánh sáng:", lightFilter));
        add(createFilterGroup("Làm mới:", refreshButton));
    }

    private JPanel createFilterGroup(String label, JComponent component) {
        JPanel group = new JPanel();
        group.setOpaque(false);
        group.setLayout(new BoxLayout(group, BoxLayout.Y_AXIS));

        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Arial", Font.BOLD, 12));
        jLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        component.setAlignmentX(Component.LEFT_ALIGNMENT);

        group.add(jLabel);
        group.add(Box.createRigidArea(new Dimension(0, 5)));
        group.add(component);

        return group;
    }
}
