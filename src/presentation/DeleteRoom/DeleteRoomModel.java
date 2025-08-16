package presentation.DeleteRoom;

import Observer.Publisher;

public class DeleteRoomModel extends Publisher {
    private String message;

    public void setMessage(String message) {
        this.message = message;
        notifySubscribers();
    }

    public String getMessage() {
        return message;
    }
}
