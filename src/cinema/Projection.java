package cinema;

public class Projection {
    private int projectionId;
    private int filmId;
    private int hallId;
    private String date;
    private String startTime;

    public Projection(int projectionId, int filmId, int hallId, String date, String startTime) {
        this.projectionId = projectionId;
        this.filmId = filmId;
        this.hallId = hallId;
        this.date = date;
        this.startTime = startTime;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

	@Override
	public String toString() {
		return "projectionId=" + projectionId + ", filmId=" + filmId + ", hallId=" + hallId + ", date="
				+ date + ", startTime=" + startTime + "\n";
	}
}
