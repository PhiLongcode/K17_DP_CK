import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainUI extends JFrame {
    // --- Khai báo các thành phần UI ---
    // Header
    private JButton logoutButton;
    // Filter
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox<String> buildingFilter;
    private JComboBox<String> statusFilter;
    private JComboBox<String> lightFilter;
    private JButton refreshButton;
    // Details Form
    private JTextField idField, buildingBlockField, areaField, numLightBulbsField, startDatePicker, lightField, statusField;
    private JButton addButton, updateButton, clearButton;
    // Table
    private JTable roomTable;
    private DefaultTableModel tableModel;
    // Status Bar
    private JLabel toastLabel;

    public MainUI() {
        // --- Cấu hình cửa sổ chính (JFrame) ---
        setTitle("Quản lý phòng học - Giao diện Swing");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        // --- Khởi tạo và sắp xếp các thành phần ---
        initComponents();
    }

    public static void main(String[] args) {
        // Chạy UI trên Event Dispatch Thread (EDT) - là một good practice của Swing
        SwingUtilities.invokeLater(() -> {
            try {
                // Sử dụng Look and Feel của hệ điều hành để giao diện đẹp hơn
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MainUI().setVisible(true);
        });
    }

    private void initComponents() {
        // Sử dụng BorderLayout cho Frame chính
        setLayout(new BorderLayout());
        // 1. Tạo Header Panel
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
        // 2. Tạo Filter Panel
        JPanel filterPanel = createFilterPanel();
        // 3. Tạo Main Panel (chứa cả Form và Table)
        JSplitPane mainPanel = createMainPanel();
        // Gom Filter và Main Panel vào một panel trung tâm
        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.add(filterPanel, BorderLayout.NORTH);
        centerContainer.add(mainPanel, BorderLayout.CENTER);
        add(centerContainer, BorderLayout.CENTER);
        // 4. Tạo Status Bar
        JPanel statusBar = createStatusBar();
        add(statusBar, BorderLayout.SOUTH);
        // TODO: Thêm dữ liệu mẫu và các trình xử lý sự kiện (event listeners) ở đây
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(44, 62, 80)); // #2c3e50
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        JLabel titleLabel = new JLabel("Quản lý phòng học");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        logoutButton = new JButton("🚪 Đăng Xuất");
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(new Color(231, 76, 60)); // #e74c3c
        logoutButton.setFont(new Font("Arial", Font.BOLD, 12));
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.setFocusPainted(false);
        panel.add(titleLabel, BorderLayout.WEST);
        panel.add(logoutButton, BorderLayout.EAST);
        return panel;
    }

    private JPanel createFilterPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(15, 15, 15, 15),
                BorderFactory.createTitledBorder("Tìm kiếm và Lọc")
        ));
        searchField = new JTextField(20);
        searchButton = new JButton("Tìm kiếm");
        buildingFilter = new JComboBox<>(new String[]{"Dãy A", "Dãy B", "Dãy C"});
        statusFilter = new JComboBox<>(new String[]{"Đạt", "Không đạt"});
        lightFilter = new JComboBox<>(new String[]{"Đủ", "Thiếu"});
        refreshButton = new JButton("Làm mới");
        panel.add(createFilterGroup("Từ khóa:", searchField));
        panel.add(createFilterGroup("Tìm kiếm:", searchButton));
        panel.add(createFilterGroup("Dãy nhà:", buildingFilter));
        panel.add(createFilterGroup("Đủ tiêu chuẩn:", statusFilter));
        panel.add(createFilterGroup("Đủ ánh sáng:", lightFilter));
        panel.add(createFilterGroup("Làm mới:", refreshButton));
        return panel;
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

    private JSplitPane createMainPanel() {
        // Panel bên trái chứa form chi tiết
        JPanel detailsPanel = createDetailsPanel();
        // Panel bên phải chứa bảng
        JPanel tablePanel = createTablePanel();
        // Tạo SplitPane để chia hai panel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, detailsPanel, tablePanel);
        splitPane.setDividerLocation(400); // Kích thước ban đầu cho panel bên trái
        splitPane.setBorder(new EmptyBorder(10, 15, 10, 15));
        return splitPane;
    }

    private JPanel createDetailsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Thông tin phòng học"),
                new EmptyBorder(10, 10, 10, 10)
        ));
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // --- Thêm các trường vào form bằng GridBagLayout ---
        // Hàng 0: Mã phòng
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Mã phòng:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        idField = new JTextField();
        idField.setEnabled(false);
        panel.add(idField, gbc);
        // Hàng 1: Dãy nhà
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("Dãy nhà:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        buildingBlockField = new JTextField();
        panel.add(buildingBlockField, gbc);
        // Hàng 2: Diện tích
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Diện tích (m²):"), gbc);
        gbc.gridx = 1;
        areaField = new JTextField();
        panel.add(areaField, gbc);
        // Hàng 3: Số bóng đèn
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Số bóng đèn:"), gbc);
        gbc.gridx = 1;
        numLightBulbsField = new JTextField();
        panel.add(numLightBulbsField, gbc);
        // Hàng 4: Ngày hoạt động
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Ngày hoạt động:"), gbc);
        gbc.gridx = 1;
        startDatePicker = new JTextField();
        panel.add(startDatePicker, gbc);
        // Hàng 5: Ánh sáng và Tiêu chuẩn
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Ánh sáng:"), gbc);
        gbc.gridx = 1;
        lightField = new JTextField();
        panel.add(lightField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Đủ tiêu chuẩn:"), gbc);
        gbc.gridx = 1;
        statusField = new JTextField();
        panel.add(statusField, gbc);
        // Hàng 6: Các nút bấm
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        addButton = new JButton("Thêm");
        updateButton = new JButton("Cập nhật");
        clearButton = new JButton("Xóa form");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 5, 5, 5);
        panel.add(buttonPanel, gbc);
        // Dummy component để đẩy mọi thứ lên trên
        gbc.gridy = 8;
        gbc.weighty = 1.0;
        panel.add(new JLabel(), gbc);
        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Danh sách phòng học"),
                new EmptyBorder(10, 10, 10, 10)
        ));
        panel.setBackground(Color.WHITE);
        // Tạo các cột cho bảng
        String[] columnNames = {"Mã phòng", "Dãy nhà", "Loại phòng", "Diện tích", "Số bóng đèn", "Ngày hoạt động", "Đủ ánh sáng", "Đủ tiêu chuẩn", "Thao tác"};
        tableModel = new DefaultTableModel(columnNames, 0);
        roomTable = new JTable(tableModel);
        roomTable.setFillsViewportHeight(true); // Đảm bảo bảng lấp đầy chiều cao
        roomTable.setRowHeight(25);
        roomTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(roomTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createStatusBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toastLabel = new JLabel("Sẵn sàng.");
        toastLabel.setBorder(new EmptyBorder(5, 15, 5, 15));
        panel.add(toastLabel);
        return panel;
    }
}