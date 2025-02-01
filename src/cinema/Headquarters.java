package cinema;

public class Headquarters {
    private int headquartersId;
    private String name;

    public Headquarters(int headquartersId, String name) {
        this.headquartersId = headquartersId;
        this.name = name;
    }

    public int getHeadquartersId() {
        return headquartersId;
    }

    public void setHeadquartersId(int headquartersId) {
        this.headquartersId = headquartersId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "[headquartersId=" + headquartersId + ", name=" + name + "]";
	}
    
}
