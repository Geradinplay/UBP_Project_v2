package cinema;

public class Film {
    private int filmId;
    private String title;
    private String genre;
    private int duration;
    private String director;
    private int studioId;

    public Film(int filmId, String title, String genre, int duration, String director, int studioId) {
        this.filmId = filmId;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.director = director;
        this.studioId = studioId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

	@Override
	public String toString() {
		return "filmId=" + filmId + ", title=" + title + ", genre=" + genre + ", duration=" + duration
				+ ", director=" + director + ", studioId=" + studioId + "\n";
	}
}
