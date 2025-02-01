package cinema;

public class Hall {
    private int hallId;
    private int hallNumber;
    private int cinemaId;
    private int capacity;

    public Hall(int hallId, int hallNumber, int cinemaId, int capacity) {
        this.hallId = hallId;
        this.hallNumber = hallNumber;
        this.cinemaId = cinemaId;
        this.capacity = capacity;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

	@Override
	public String toString() {
		return "hallId=" + hallId + ", hallNumber=" + hallNumber + ", cinemaId=" + cinemaId + ", capacity="
				+ capacity;
	}
    
}
