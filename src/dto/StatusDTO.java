package dto;

import common.StatusCode;

public class StatusDTO {
    public String message;
    public StatusCode status;

    public StatusDTO() {
    }

    public StatusDTO(StatusCode status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StatusCode getStatus() {
        return status;
    }

    public void setStatus(StatusCode status) {
        this.status = status;
    }
}
