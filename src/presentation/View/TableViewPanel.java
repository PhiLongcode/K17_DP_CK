package presentation.View;

import Observer.Subscriber;
import business.ViewListRoom.ViewRoomUseCase;
import persistence.ViewListRoom.MySQLViewRoom;
import persistence.ViewListRoom.ViewRoomGateway;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class TableViewPanel extends JPanel implements Subscriber {
    private JTable roomTable;
    private DefaultTableModel tableModel;
    private ViewRoomModel viewRoomModel;

    public TableViewPanel() throws Exception {
        setLayout(new BorderLayout(0, 10));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Danh sách phòng học"),
                new EmptyBorder(10, 10, 10, 10)
        ));
        setBackground(Color.WHITE);

        String[] columnNames = {"STT", "Mã phòng", "Dãy nhà", "Loại phòng", "Diện tích",
                "Số bóng đèn", "Ngày hoạt động", "Đủ tiêu chuẩn", "Hành động"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8; // chỉ cột cuối
            }
        };

        roomTable = new JTable(tableModel);
        roomTable.setRowHeight(30);
        roomTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        // Renderer & Editor cho cột hành động
        TableColumn actionColumn = roomTable.getColumn("Hành động");
        actionColumn.setCellRenderer(new ActionCellRenderer());
        actionColumn.setCellEditor(new ActionCellEditor());
        actionColumn.setPreferredWidth(250);

        JScrollPane scrollPane = new JScrollPane(roomTable);
        add(scrollPane, BorderLayout.CENTER);

        // Gateway -> MySQL
        MySQLViewRoom gateway = new MySQLViewRoom();

        // UC
        ViewRoomUseCase useCase = new ViewRoomUseCase(gateway);

        // Model
        this.viewRoomModel = new ViewRoomModel();
        this.viewRoomModel.addSubscriber(this);

        // Controller
        ViewRoomController controller = new ViewRoomController(useCase, this.viewRoomModel);

        // Gọi UC để load dữ liệu (model sẽ notify → update() tự chạy)
        controller.execute();
    }

    @Override
    public void update() {
        tableModel.setRowCount(0);

        List<ListViewObject> rooms = viewRoomModel.getListViewObject();

        System.out.println("=== DEBUG: rooms list ===");
        if (rooms != null) {
            for (ListViewObject r : rooms) {
                System.out.println(r);
            }
        } else {
            System.out.println("rooms = null");
        }
        System.out.println("==========================");
        if (rooms == null || rooms.isEmpty()) {

            Object[] emptyRow = new Object[tableModel.getColumnCount()];
            emptyRow[0] = "Chưa có phòng nào"; // ô đầu tiên ghi thông báo
            tableModel.addRow(emptyRow);

            // merge cell giả lập = set renderer cho row này
            roomTable.setDefaultRenderer(Object.class, (table, value, isSelected, hasFocus, row, column) -> {
                JLabel label = new JLabel();
                if (row == 0 && "Chưa có phòng nào".equals(table.getValueAt(row, 0))) {
                    label.setText("Chưa có phòng nào");
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setFont(new Font("Arial", Font.ITALIC, 14));
                    label.setForeground(Color.GRAY);
                } else {
                    label.setText(value != null ? value.toString() : "");
                }
                return label;
            });

        } else {
            for (ListViewObject item : rooms) {
                Object[] row = {
                        item.stt,
                        item.roomID,
                        item.buildingBlock,
                        item.roomType,
                        item.area,
                       item.numLightBulbs,
                        item.startDateOfOperation,
                        item.meetsStandard,

                        null
                };
                tableModel.addRow(row);
            }
            // reset renderer về mặc định sau khi có dữ liệu
            roomTable.setDefaultRenderer(Object.class, roomTable.getDefaultRenderer(Object.class));
        }
    }

    // Renderer
    static class ActionCellRenderer extends JPanel implements TableCellRenderer {
        private final JButton editButton = new JButton("Edit");
        private final JButton deleteButton = new JButton("Delete");
        private final JButton viewButton = new JButton("View");

        public ActionCellRenderer() {
            setLayout(new GridLayout(1, 3, 5, 0));
            add(editButton);
            add(deleteButton);
            add(viewButton);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            return this;
        }
    }

    // Editor
    static class ActionCellEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        private final JButton editButton = new JButton("Edit");
        private final JButton deleteButton = new JButton("Delete");
        private final JButton viewButton = new JButton("View");

        public ActionCellEditor() {
            panel.add(editButton);
            panel.add(deleteButton);
            panel.add(viewButton);

            editButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(null, "Sửa hàng");
                fireEditingStopped();
            });
            deleteButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(null, "Xóa hàng");
                fireEditingStopped();
            });
            viewButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(null, "Xem chi tiết");
                fireEditingStopped();
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }
    }


}
