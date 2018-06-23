package xml.agent.dto;

public class RezStatusUpdateDTO {

    private String status;

    private Long id;

    public RezStatusUpdateDTO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
