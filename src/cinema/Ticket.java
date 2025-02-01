package cinema;

public class Ticket {
    private int ticketId;
    private int projectionId;
    private String purchaseDate;
    private int typeId;
    private int customerId;

    public Ticket(int ticketId, int projectionId, String purchaseDate, int typeId, int customerId) {
        this.ticketId = ticketId;
        this.projectionId = projectionId;
        this.purchaseDate = purchaseDate;
        this.typeId = typeId;
        this.customerId = customerId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int type) {
        this.typeId = type;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

	@Override
	public String toString() {
		return "ticketId=" + ticketId + ", projectionId=" + projectionId
				+ ", purchaseDate=" + purchaseDate + ", type=" + typeId + ", customerId=" + customerId+"\n";
	}
    
}
