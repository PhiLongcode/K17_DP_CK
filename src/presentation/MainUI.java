package presentation;

import business.OpenDialogComfirmDeteleRoom.OpenDialogComfirmDeteleRoomUseCase;
import persistence.OpenDialogComfirmDeteleRoom.MySQLOpenDialogComfirmDeteleRoomDAO;
import persistence.OpenDialogComfirmDeteleRoom.OpenDialogComfirmDeteleRoomGateway;
import persistence.OpenDialogComfirmDeteleRoom.RoomDTO;
import presentation.OpenDialogComfirmDeteleRoom.OpenDialogComfirmDeleteControl;
import presentation.OpenDialogComfirmDeteleRoom.OpenDialogComfirmDeleteModel;
import presentation.OpenDialogComfirmDeteleRoom.OpenDialogConfirmDeleteRoomUI;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainUI extends JFrame {

    private JTable table;
    private RoomTableModel tableModel;
    private final JTextField searchTextField;
    private final JButton addButton;
    public MainUI() {
        setTitle("Quản lý phòng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 400);
        setLocationRelativeTo(null);
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        searchTextField = new JTextField();
        addButton = new JButton("Thêm");
        tableModel = new RoomTableModel();
        table = new JTable(tableModel);

        // Tạo cột Action có nút Xóa
        TableColumn actionColumn = table.getColumnModel().getColumn(7);
        actionColumn.setCellRenderer(new ButtonRenderer());
        actionColumn.setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new BorderLayout(5, 5));
        buttonPanel.add(addButton, BorderLayout.WEST);
        topPanel.add(searchTextField, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
        // Load dữ liệu mẫu
        loadSampleData();
    }

private void loadSampleData() {
    MySQLOpenDialogComfirmDeteleRoomDAO dao = null;
    try {
        dao = new MySQLOpenDialogComfirmDeteleRoomDAO();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
    for (RoomDTO dto : dao.getAllRooms()) { // lấy tất cả phòng từ DAO
        Room room = new Room(
                dto.roomID,
                dto.roomType,
                dto.buildingBlock,
                dto.area,
                dto.numLightBulbs,
                dto.startDateOfOperation.toString(),
                dto.hasProject
        );
        tableModel.addRoom(room);
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainUI().setVisible(true);
        });
    }
}

// ===== Model phòng =====
class Room {
    String roomId;
    String roomType;
    String buildingBlock;
    double area;
    int numLightBulbs;
    String startDateOfOperation;
    boolean meetsStandard;

    public Room(String roomId, String roomType, String buildingBlock, double area, int numLightBulbs, String startDateOfOperation, boolean meetsStandard) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.buildingBlock = buildingBlock;
        this.area = area;
        this.numLightBulbs = numLightBulbs;
        this.startDateOfOperation = startDateOfOperation;
        this.meetsStandard = meetsStandard;
    }
}

// ===== TableModel =====
class RoomTableModel extends AbstractTableModel {
    private String[] columnNames = {"ID", "Loại phòng", "Tòa", "Diện tích", "Số bóng đèn", "Ngày hoạt động", "Đạt chuẩn", "Action"};
    private ArrayList<Room> data = new ArrayList<>();

    public void addRoom(Room room) {
        data.add(room);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void removeRoom(int rowIndex) {
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Room room = data.get(rowIndex);
        switch (columnIndex) {
            case 0: return room.roomId;
            case 1: return room.roomType;
            case 2: return room.buildingBlock;
            case 3: return room.area;
            case 4: return room.numLightBulbs;
            case 5: return room.startDateOfOperation;
            case 6: return room.meetsStandard ? "Có" : "Không";
            case 7: return "Xóa";
        }
        return null;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 7;
    }
}

// ===== Renderer cho nút =====
class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

// ===== Editor cho nút =====
class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean clicked;
    private JTable table;
    private JFrame parent;

    private OpenDialogComfirmDeleteControl control;
    private  OpenDialogComfirmDeleteModel model;
    private OpenDialogConfirmDeleteRoomUI ui;
    private OpenDialogComfirmDeteleRoomUseCase useCase;

    private OpenDialogComfirmDeteleRoomGateway gateway;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);

        button.addActionListener(e -> {
            fireEditingStopped();
            int row = table.getSelectedRow();
            if (row >= 0) {
                String roomId = table.getValueAt(row, 0).toString();

                // Khởi tạo model trước
                OpenDialogComfirmDeleteModel model = new OpenDialogComfirmDeleteModel();

                // Khởi tạo gateway và usecase
                OpenDialogComfirmDeteleRoomGateway gateway = null;
                try {
                    gateway = new MySQLOpenDialogComfirmDeteleRoomDAO();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                OpenDialogComfirmDeteleRoomUseCase useCase = new OpenDialogComfirmDeteleRoomUseCase(gateway);

                // Khởi tạo control và liên kết model
                OpenDialogComfirmDeleteControl control = new OpenDialogComfirmDeleteControl(useCase);
                control.setModel(model);

                // Khởi tạo UI và set model
                OpenDialogConfirmDeleteRoomUI ui = new OpenDialogConfirmDeleteRoomUI(parent);
                ui.setViewModel(model);

                // Thực thi lấy dữ liệu phòng
                control.execute(roomId);

                // Hiển thị UI
                ui.setVisible(true);
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        this.table = table;
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        clicked = true;
        return button;
    }

    public Object getCellEditorValue() {
        clicked = false;
        return label;
    }

    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }
}
