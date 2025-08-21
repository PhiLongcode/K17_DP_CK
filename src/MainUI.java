
import presentation.OpenUpdateRoomForm.OpenUpdateRoomFromController;
import presentation.OpenUpdateRoomForm.OpenUpdateRoomFromModel;
import presentation.OpenUpdateRoomForm.OpenUpdateRoomFromUI;

import javax.swing.*;

import business.OpenUpdateRoomForm.OpenUpdateRoomFormUseCase;
import persistence.OpenUpdateRoomForm.MySQLOpenUpdateRoomFromDAO;
import persistence.OpenUpdateRoomForm.OpenUpdateRoomFromGateway;

import java.awt.*;
import java.sql.SQLException;

public class MainUI extends JFrame {
	
	
    public MainUI() {
        setTitle("Quản lý phòng - MainUI");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnEdit = new JButton("Sửa phòng");

        // Gắn sự kiện click
        btnEdit.addActionListener(e -> {
            // fix cứng id phòng
            String fixedRoomId = "TN101";
            MySQLOpenUpdateRoomFromDAO dao = null;
			try {
				dao = new MySQLOpenUpdateRoomFromDAO();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            OpenUpdateRoomFromModel model = new OpenUpdateRoomFromModel();
            OpenUpdateRoomFormUseCase uc = new OpenUpdateRoomFormUseCase(dao);
            OpenUpdateRoomFromController controller = new OpenUpdateRoomFromController(uc, model);


            new OpenUpdateRoomFromUI(model);
            
            try {
				controller.execute(fixedRoomId);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        });

        setLayout(new FlowLayout());
        add(btnEdit);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainUI::new);
    }
}
