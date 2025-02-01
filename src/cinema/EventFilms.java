package cinema;

public class EventFilms {
    private int eventId;
    private int filmId;

    public EventFilms(int eventId, int filmId) {
        this.eventId = eventId;
        this.filmId = filmId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

	@Override
	public String toString() {
		return "[eventId=" + eventId + ", filmId=" + filmId + "]";
	}
}

