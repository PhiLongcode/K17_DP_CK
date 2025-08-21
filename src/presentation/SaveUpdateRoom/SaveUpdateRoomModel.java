package presentation.SaveUpdateRoom;

public class SaveUpdateRoomModel extends Publisher {
    private boolean status;

    public void setStatus(boolean status) {
        this.status = status;
        notifySubscribers();
    }

    public boolean getStatus() {
        return status;
    }
}

