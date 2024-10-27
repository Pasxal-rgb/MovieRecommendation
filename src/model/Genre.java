package model;

public enum Genre {
    DRAMA(1),
    WESTERN(2),
    ACTION(3),
    SCIENCEFICTION(4),
    KOMÖDIE(5),
    MISTERY(6);

    private int value;

    Genre(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return switch (this) {
            case DRAMA -> "Drama";
            case WESTERN -> "Western";
            case ACTION -> "Action";
            case SCIENCEFICTION -> "Science-Fiction";
            case KOMÖDIE -> "Komödie";
            case MISTERY -> "Mistery";
            default -> super.toString();
        };
    }
}