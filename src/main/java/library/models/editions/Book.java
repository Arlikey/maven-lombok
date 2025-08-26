package library.models.editions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.HashSet;

@Data
@EqualsAndHashCode(callSuper = true)
public class Book extends Publication {
    private String author;
    private HashSet<Genre> genres;
    private int pagesCount;
    private String annotation;

    public Book(String title, LocalDate yearOfPublish, String author,
                HashSet<Genre> genres, int pagesCount, String annotation) {
        super(title, yearOfPublish);
        this.author = author;
        this.genres = genres;
        this.pagesCount = pagesCount;
        this.annotation = annotation;
    }

    @Override
    public String formatForUser() {
        return "Book:\n" + super.formatForUser() +
                "Author: " + author +
                "\n\tGenres: " + genres +
                "\n\tPages: " + pagesCount +
                "\n\tAnnotation: " + annotation;
    }

    @Override
    public String formatForList() {
        return "Book:\n\t\t\t" + super.formatForList() +
                " | Author: " + author +
                " | Genres: " + genres +
                " | Pages: " + pagesCount +
                " | Annotation: " + annotation + "\n\t\t";
    }
}
