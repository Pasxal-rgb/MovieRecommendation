package model;

public class Movie {

    private int id;
    private String title;

    public Movie(int id, String name) {
        this.id = id;
        this.title = name;
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
