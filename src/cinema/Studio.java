package cinema;

public class Studio {
    private int studioId;
    private String name;
    private int headquartersId;

    public Studio(int studioId, String name, int headquartersId) {
        this.studioId = studioId;
        this.name = name;
        this.headquartersId = headquartersId;
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeadquartersId() {
        return headquartersId;
    }

    public void setHeadquartersId(int headquartersId) {
        this.headquartersId = headquartersId;
    }

	@Override
	public String toString() {
		return "[studioId=" + studioId + ", name=" + name + ", headquartersId=" + headquartersId + "]";
	}
}
