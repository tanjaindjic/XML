package xml.agent.dto;

public class PorukaDTO {

    private String text;

    private Long posiljalacId;

    private Long primalacId;

    public PorukaDTO() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getPosiljalacId() {
        return posiljalacId;
    }

    public void setPosiljalacId(Long posiljalacId) {
        this.posiljalacId = posiljalacId;
    }

    public Long getPrimalacId() {
        return primalacId;
    }

    public void setPrimalacId(Long primalacId) {
        this.primalacId = primalacId;
    }
}
