package library.models.editions;

public enum Genre {
    FANTASY ("Fantasy"),
    DETECTIVE ("Detective"),
    THRILLER ("Thriller"),
    POETRY ("Poetry"),
    CLASSICS ("Classics"),
    MYSTERY ("Mystery"),
    HORROR ("Horror"),
    HISTORY ("History"),
    FICTION ("Fiction");

    private String title;
    Genre(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
