import business.DeleteRoomUseCase;
import persistence.MySQLDeleteRoomDAO;
import presentation.DeleteRoomController;

import presentation.DeleteRoomUI;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        MySQLDeleteRoomDAO dao = new MySQLDeleteRoomDAO();
      DeleteRoomUseCase deleteRoomUseCase = new DeleteRoomUseCase(dao);
        DeleteRoomController controller = new DeleteRoomController(deleteRoomUseCase);

        // Chạy UI
        SwingUtilities.invokeLater(() -> new DeleteRoomUI( controller));
    }

}