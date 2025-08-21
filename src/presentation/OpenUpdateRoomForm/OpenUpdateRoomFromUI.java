package presentation.OpenUpdateRoomForm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import business.SaveUpdateRoom.SaveUpdateRoomUseCase;
import persistence.SaveUpdateRoom.MySQLSaveUpdateRoomDAO;
import presentation.SaveUpdateRoom.SaveUpdateRoomController;
import presentation.SaveUpdateRoom.SaveUpdateRoomModel;
import presentation.SaveUpdateRoom.SaveUpdateRoomUI;
import presentation.OpenUpdateRoomForm.RoomItem;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.text.SimpleDateFormat;

public class OpenUpdateRoomFromUI extends JFrame implements Subscriber {
    private OpenUpdateRoomFromModel model;

    // Common fields
    private JTextField txtRoomId, txtBuilding, txtArea, txtNumOfLights;
    private JComboBox<String> cboTypeRoom;
    private JSpinner spnStartDate;

    // Dynamic panel
    private JPanel dynamicPanel;
    private JCheckBox chkProjector, chkSink;
    private JTextField txtNumComputers, txtSpecialization, txtCapacity;

    // Nút lưu và hủy
    private JButton btnSave, btnCancel;

    public OpenUpdateRoomFromUI(OpenUpdateRoomFromModel model) {
        this.model = model;
        this.model.addSubscriber(this);

        setTitle("Cập nhật thông tin phòng");
        setSize(550, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel container = new JPanel(new BorderLayout(10,10));
        container.setBorder(new EmptyBorder(15,15,15,15));
        add(container);

        // ===== Panel thông tin cơ bản =====
        JPanel basicPanel = new JPanel(new GridBagLayout());
        basicPanel.setBorder(new TitledBorder("Thông tin cơ bản"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtRoomId = new JTextField();
        txtBuilding = new JTextField();
        txtArea = new JTextField();
        txtNumOfLights = new JTextField();
        cboTypeRoom = new JComboBox<>();

        spnStartDate = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnStartDate, "dd/MM/yyyy");
        spnStartDate.setEditor(dateEditor);

        addField(basicPanel, gbc, 0, "Mã phòng:", txtRoomId);
        addField(basicPanel, gbc, 1, "Tòa nhà:", txtBuilding);
        addField(basicPanel, gbc, 2, "Diện tích:", txtArea);
        addField(basicPanel, gbc, 3, "Số bóng đèn:", txtNumOfLights);
        addField(basicPanel, gbc, 4, "Ngày bắt đầu:", spnStartDate);
        addField(basicPanel, gbc, 5, "Loại phòng:", cboTypeRoom);

        container.add(basicPanel, BorderLayout.NORTH);

        // ===== Panel thông tin động =====
        dynamicPanel = new JPanel(new GridBagLayout());
        dynamicPanel.setBorder(new TitledBorder("Thông tin bổ sung"));
        container.add(dynamicPanel, BorderLayout.CENTER);

        chkProjector = new JCheckBox("Có máy chiếu");
        chkSink = new JCheckBox("Có bồn rửa");
        txtNumComputers = new JTextField();
        txtSpecialization = new JTextField();
        txtCapacity = new JTextField();

        cboTypeRoom.addActionListener(e -> refreshDynamicFields());

        // ===== Panel nút =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,15,10));
        btnSave = new JButton("💾 Lưu");
        btnCancel = new JButton("❌ Hủy");
        btnSave.setPreferredSize(new Dimension(100,35));
        btnCancel.setPreferredSize(new Dimension(100,35));
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        container.add(buttonPanel, BorderLayout.SOUTH);
        
     // Hành động cho nút 
        btnCancel.addActionListener(e -> dispose()); 
        btnSave.addActionListener(e -> onSave()); // xử lý lưu

        setVisible(true);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lbl, gbc);

        gbc.gridx = 1; gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.WEST;
        field.setPreferredSize(new Dimension(200,25));
        panel.add(field, gbc);
    }
    
 // UI gọi khi nhấn Lưu
    private void onSave() {
        // ===== Validation cơ bản =====
        if (txtRoomId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã phòng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (txtBuilding.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tòa nhà!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (txtArea.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập diện tích!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (txtNumOfLights.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số bóng đèn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (cboTypeRoom.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại phòng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ===== Gom dữ liệu chung =====
        RoomItem room = new RoomItem();
        room.roomId = txtRoomId.getText().trim();
        room.builddingBlock = txtBuilding.getText().trim();
        room.area = txtArea.getText().trim();
        room.numLightBulbs = txtNumOfLights.getText().trim();

        // Ngày bắt đầu hoạt động
        java.util.Date startDate = (java.util.Date) spnStartDate.getValue();
        room.startDateOfOperation = new SimpleDateFormat("yyyy-MM-dd").format(startDate);

        // Loại phòng
        int selectedIndex = cboTypeRoom.getSelectedIndex();
        TypeRoomItem selectedType = model.getTypeRoomItemList().get(selectedIndex);
        room.typeRoom = selectedType.typeRoomId;

        // ===== Gom dữ liệu động theo loại phòng =====
        String typeName = selectedType.nameTypeRoom.trim().toLowerCase();
        switch (typeName) {
            case "phòng học lý thuyết":
                room.hasProjector = chkProjector.isSelected() ? "true" : "false";
                break;
            case "phòng máy tính":
                if (txtNumComputers.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng máy tính!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                room.numComputers = txtNumComputers.getText().trim();
                break;
            case "phòng thí nghiệm":
                if (txtSpecialization.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập chuyên ngành!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (txtCapacity.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập sức chứa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                room.specialization = txtSpecialization.getText().trim();
                room.capacity = txtCapacity.getText().trim();
                room.hasSink = chkSink.isSelected() ? "true" : "false";
                break;
            default:
                break;
        }

        MySQLSaveUpdateRoomDAO dao = null;
        
        try {
			dao = new MySQLSaveUpdateRoomDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        SaveUpdateRoomModel model = new SaveUpdateRoomModel();
        SaveUpdateRoomUseCase uc = new SaveUpdateRoomUseCase(dao);
        // ===== Gọi Controller =====
        SaveUpdateRoomController controller = new SaveUpdateRoomController(model, uc);
        
        new SaveUpdateRoomUI(model);
        
        controller.execute(room);
    }


    @Override
    public void update() {
        // Populate combobox loại phòng
        cboTypeRoom.removeAllItems();
        List<TypeRoomItem> typeRooms = model.getTypeRoomItemList();
        for (TypeRoomItem item : typeRooms) {
            cboTypeRoom.addItem(item.nameTypeRoom);
        }

        // Lấy phòng từ model
        RoomItem room = model.getRoomItem();
        if (room != null && typeRooms != null) {
            // Set combobox typeRoom
            for (int i = 0; i < typeRooms.size(); i++) {
                if (typeRooms.get(i).typeRoomId.equals(room.typeRoom)) {
                    cboTypeRoom.setSelectedIndex(i);
                    break;
                }
            }

            // Gán dữ liệu cơ bản
            txtRoomId.setText(room.roomId != null ? room.roomId : "");
            txtBuilding.setText(room.builddingBlock != null ? room.builddingBlock : "");
            txtArea.setText(room.area != null ? room.area : "");
            txtNumOfLights.setText(room.numLightBulbs != null ? room.numLightBulbs : "");

            // Ngày bắt đầu
            if (room.startDateOfOperation != null && !room.startDateOfOperation.isEmpty()) {
                try {
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd")
                                            .parse(room.startDateOfOperation);
                    spnStartDate.setValue(date);
                } catch (Exception e) {
                    spnStartDate.setValue(new java.util.Date());
                }
            } else {
                spnStartDate.setValue(new java.util.Date());
            }
        }

        // Refresh các field động và nạp dữ liệu đặc thù
        refreshDynamicFields();

        if (room != null) {
            String typeName = "";
            if (cboTypeRoom.getSelectedIndex() >= 0 && typeRooms != null) {
                typeName = typeRooms.get(cboTypeRoom.getSelectedIndex()).nameTypeRoom.trim().toLowerCase();
            }

            switch (typeName) {
                case "phòng học lý thuyết":
                    chkProjector.setSelected("true".equalsIgnoreCase(room.hasProjector));
                    break;
                case "phòng máy tính":
                    txtNumComputers.setText(
                        room.numComputers != null && !"null".equalsIgnoreCase(room.numComputers)
                            ? room.numComputers : "0"
                    );
                    break;
                case "phòng thí nghiệm":
                    txtSpecialization.setText(room.specialization != null ? room.specialization : "");
                    txtCapacity.setText(room.capacity != null ? room.capacity : "");
                    chkSink.setSelected("true".equalsIgnoreCase(room.hasSink));
                    break;
            }
        }
    }


    private void refreshDynamicFields() {
        dynamicPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int selectedIndex = cboTypeRoom.getSelectedIndex();
        List<TypeRoomItem> typeRooms = model.getTypeRoomItemList();
        if (selectedIndex < 0 || typeRooms == null || selectedIndex >= typeRooms.size()) {
            dynamicPanel.revalidate(); dynamicPanel.repaint();
            return;
        }

        String typeName = typeRooms.get(selectedIndex).nameTypeRoom.toLowerCase().trim();
        switch (typeName) {
            case "phòng học lý thuyết":
                addField(dynamicPanel, gbc, 0, "Máy chiếu:", chkProjector);
                break;
            case "phòng máy tính":
                addField(dynamicPanel, gbc, 0, "Số lượng máy tính:", txtNumComputers);
                break;
            case "phòng thí nghiệm":
                addField(dynamicPanel, gbc, 0, "Chuyên ngành:", txtSpecialization);
                addField(dynamicPanel, gbc, 1, "Sức chứa:", txtCapacity);
                addField(dynamicPanel, gbc, 2, "Bồn rửa:", chkSink);
                break;
        }
        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }
}
