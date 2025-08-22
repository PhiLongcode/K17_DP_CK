package presentation.ViewListRoom;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import business.OpenUpdateRoomForm.OpenUpdateRoomFormUseCase;
import persistence.OpenUpdateRoomForm.MySQLOpenUpdateRoomFromDAO;
import presentation.OpenUpdateRoomForm.OpenUpdateRoomFromController;
import presentation.OpenUpdateRoomForm.OpenUpdateRoomFromModel;
import presentation.OpenUpdateRoomForm.OpenUpdateRoomFromView;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ViewListRoomView extends JFrame implements Subscriber {

    private JTable table;
    private DefaultTableModel model;
    private ViewListRoomModel viewModel;

    public ViewListRoomView(ViewListRoomModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addSubscriber(this);
        setTitle("Quản lý phòng học");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 600);
        setLocationRelativeTo(null);

        // Main container
        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        // ===== TOP PANEL =====
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(25, 55, 100));
        topPanel.setPreferredSize(new Dimension(0, 50));

        JLabel lblTitle = new JLabel("  Quản lý phòng học");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(lblTitle, BorderLayout.WEST);
        contentPane.add(topPanel, BorderLayout.NORTH);

        // ===== LEFT MENU =====
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(10, 1, 5, 5));
        leftPanel.setBackground(new Color(45, 65, 90));
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton btnAdd = new JButton("+ Thêm phòng");
        JButton btnAvg = new JButton("+ Tính trung bình diện tích");
        JButton btnCount = new JButton("+ Tổng số lượng phòng");

        leftPanel.add(btnAdd);
        leftPanel.add(btnAvg);
        leftPanel.add(btnCount);

        contentPane.add(leftPanel, BorderLayout.WEST);

        // ===== MAIN PANEL =====
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm và Lọc"));

        searchPanel.add(new JLabel("Từ khóa:"));
        JTextField txtKeyword = new JTextField(15);
        searchPanel.add(txtKeyword);

        JButton btnSearch = new JButton("Tìm kiếm");
        searchPanel.add(btnSearch);

        searchPanel.add(new JLabel("Dãy nhà:"));
        JComboBox<String> cboDayNha = new JComboBox<>(new String[]{"", "Dãy A", "Dãy B","Dãy C"});
        searchPanel.add(cboDayNha);

        searchPanel.add(new JLabel("Đủ tiêu chuẩn:"));
        JComboBox<String> cboStandard = new JComboBox<>(new String[]{"", "Đạt", "Không đạt"});
        searchPanel.add(cboStandard);


        JButton btnRefresh = new JButton("Làm mới");
        searchPanel.add(btnRefresh);

        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // ===== TABLE =====
        String[] columns = {
                "STT", "Mã phòng", "Dãy nhà", "Loại phòng", "Diện tích",
                "Số bóng đèn", "Ngày hoạt động", "Đủ tiêu chuẩn", "Hành động"
        };

        model = new DefaultTableModel(
                new Object[]{"STT", "Mã phòng", "Dãy nhà", "Diện tích", "Số đèn", "Ngày bắt đầu", "Loại", "Đạt chuẩn","Hành động"},
                0
                );
        table = new JTable(model);
        

        // chỉnh row height và font
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(0, 35));


        // Custom renderer + editor cho cột hành động
        TableColumn actionColumn = table.getColumnModel().getColumn(8);
        actionColumn.setCellRenderer(new ButtonRenderer());
        actionColumn.setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        contentPane.add(mainPanel, BorderLayout.CENTER);
        
    }

    // Renderer hiển thị 3 nút
    class ButtonRenderer extends JPanel implements TableCellRenderer {
        JButton btnEdit = new JButton("Edit");


        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            btnEdit.setPreferredSize(new Dimension(70, 25));
          add(btnEdit);

        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            return this;
        }
    }

    // Editor xử lý sự kiện khi bấm nút
    class ButtonEditor extends DefaultCellEditor {
        JPanel panel = new JPanel();
        JButton btnEdit = new JButton("Edit");

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            btnEdit.setPreferredSize(new Dimension(70, 25));

            panel.add(btnEdit);

         // Gán sự kiện cho nút Edit
            btnEdit.addActionListener(e -> {
                // Lấy mã phòng từ dòng hiện tại
                String maPhong = table.getValueAt(currentRow, 1).toString();
                MySQLOpenUpdateRoomFromDAO dao = null;
                try {
					dao = new MySQLOpenUpdateRoomFromDAO();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
                OpenUpdateRoomFromModel model = new OpenUpdateRoomFromModel();
                OpenUpdateRoomFormUseCase uc = new OpenUpdateRoomFormUseCase(dao);
                OpenUpdateRoomFromController controller = new OpenUpdateRoomFromController(uc, model);
                
                new OpenUpdateRoomFromView(model);
                
                try {
					controller.execute(maPhong);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            });
        }

        private int currentRow;

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            currentRow = row; // lưu lại dòng đang bấm
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
        
        
    }
    @Override
    public void update() {
        model.setRowCount(0); // clear
        int stt = 1;
        for (RoomViewItem item : viewModel.getListRoomView()) {
            model.addRow(new Object[]{
                    stt++,
                    item.roomId,
                    item.buildingBlock,
                    item.area,
                    item.numOfLights,
                    item.startDate,
                    item.type,
                    item.standard ? "Đạt chuẩn" : "Không đạt chuẩn"
            });
        }
    }
}