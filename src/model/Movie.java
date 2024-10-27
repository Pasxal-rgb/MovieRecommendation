package model;

public class Movie {

    private int id;
    private String title;
    private Genre genre;

    public Movie(int id, String name, Genre genre) {
        this.id = id;
        this.title = name;
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString(){
        return "Movie-ID : + " + getId() + "  |  " + "Titel: " + getTitle();
    }
}
