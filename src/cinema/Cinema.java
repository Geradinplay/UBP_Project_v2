package cinema;

public class Cinema {
    private int cinemaId;
    private String name;
    private String address;
    private int capacity;

    public Cinema(int cinemaId, String name, String address, int capacity) {
        this.cinemaId = cinemaId;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

	@Override
	public String toString() {
		return "[cinemaId=" + cinemaId + ", name=" + name + ", address=" + address + ", capacity=" + capacity
				+ "]";
	}
    
}

