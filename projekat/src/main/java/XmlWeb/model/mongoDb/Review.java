package XmlWeb.model.mongoDb;

public class Review {
	
	private String userId;
	
	private String smestajId;
	
	private String rezervacijaId;
	
	private String comment;
	
	private int score;
	
	private boolean approved;

	public Review() {
		super();
	}

	public Review(String userId, String smestajId, String rezervacijaId, String comment, int score, boolean approved) {
		super();
		this.userId = userId;
		this.smestajId = smestajId;
		this.rezervacijaId = rezervacijaId;
		this.comment = comment;
		this.score = score;
		this.approved = approved;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSmestajId() {
		return smestajId;
	}

	public void setSmestajId(String smestajId) {
		this.smestajId = smestajId;
	}

	public String getRezervacijaId() {
		return rezervacijaId;
	}

	public void setRezervacijaId(String rezervacijaId) {
		this.rezervacijaId = rezervacijaId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	

}
