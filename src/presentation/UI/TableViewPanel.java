package presentation.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;


public class TableViewPanel extends JPanel {
    public JTable roomTable;
    public DefaultTableModel tableModel;

    public TableViewPanel() {
        setLayout(new BorderLayout(0, 10));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Danh sách phòng học"),
                new EmptyBorder(10, 10, 10, 10)
        ));
        setBackground(Color.WHITE);

        String[] columnNames = {"STT","Mã phòng", "Dãy nhà", "Loại phòng", "Diện tích",
                "Số bóng đèn", "Ngày hoạt động", "Đủ tiêu chuẩn", "Hành động"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Chỉ cho cột cuối được edit (để chứa button)
                return column == 7;
            }
        };

        roomTable = new JTable(tableModel);
        roomTable.setFillsViewportHeight(true);
        roomTable.setRowHeight(30);
        roomTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        // Renderer & Editor cho cột hành động
        TableColumn actionColumn = roomTable.getColumn("Hành động");
        actionColumn.setCellRenderer(new ActionCellRenderer());
        actionColumn.setCellEditor(new ActionCellEditor());

// Cố định độ rộng cột "Hành động"
        int actionColWidth = 250;
        actionColumn.setMinWidth(actionColWidth);
        actionColumn.setMaxWidth(actionColWidth);
        actionColumn.setPreferredWidth(actionColWidth);

        JScrollPane scrollPane = new JScrollPane(roomTable);
        add(scrollPane, BorderLayout.CENTER);

        // Dữ liệu demo
        tableModel.addRow(new Object[]{"1","P101", "A", "Phòng học", "50", "10", "2023-01-01", "Đạt", ""});
        tableModel.addRow(new Object[]{"2","P202", "B", "Phòng lab", "70", "15", "2023-03-10", "Không đạt", ""});
    }

    // Renderer (hiển thị)
    static class ActionCellRenderer extends JPanel implements TableCellRenderer {
        private final JButton editButton = new JButton("Edit");
        private final JButton deleteButton = new JButton("Delete");
        private final JButton viewButton = new JButton("View");

        public ActionCellRenderer() {
            setLayout(new GridLayout(1, 3, 5, 0)); // 1 hàng 3 cột, khoảng cách 5px
            setOpaque(true);

            // Đồng bộ style button (cho nhỏ gọn hơn)
            Dimension btnSize = new Dimension(60, 25);
            editButton.setPreferredSize(btnSize);
            deleteButton.setPreferredSize(btnSize);
            viewButton.setPreferredSize(btnSize);

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

    // Editor (cho phép bấm)
    static class ActionCellEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel panel;
        private final JButton editButton = new JButton("Edit");
        private final JButton deleteButton = new JButton("Delete");
        private final JButton viewButton = new JButton("View");

        public ActionCellEditor() {
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            panel.add(editButton);
            panel.add(deleteButton);
            panel.add(viewButton);

            // Gán sự kiện mẫu
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
