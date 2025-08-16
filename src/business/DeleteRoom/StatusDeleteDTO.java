package business.DeleteRoom;

public class StatusDeleteDTO {
    public String message;
    public Boolean success;

    public StatusDeleteDTO(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }
}
