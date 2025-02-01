package cinema;

public class SpecialEvent {
    private int eventId;
    private String name;
    private String date;
    private String specialPrices;

    public SpecialEvent(int eventId, String name, String date, String specialPrices) {
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.specialPrices = specialPrices;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSpecialPrices() {
        return specialPrices;
    }

    public void setSpecialPrices(String specialPrices) {
        this.specialPrices = specialPrices;
    }

	@Override
	public String toString() {
		return "[eventId=" + eventId + ", name=" + name + ", date=" + date + ", specialPrices="
				+ specialPrices + "]";
	}
}
