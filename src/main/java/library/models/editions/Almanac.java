package library.models.editions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.HashSet;

@Data
@EqualsAndHashCode(callSuper = true)
public class Almanac extends Publication {
    private String annotation;
    private HashSet<Book> books;

    public Almanac(String title, LocalDate yearOfPublish,
                   String annotation, HashSet<Book> books) {
        super(title, yearOfPublish);
        this.annotation = annotation;
        this.books = books;
    }

    @Override
    public String formatForUser() {
        StringBuilder s = new StringBuilder("Almanac:\n" + super.formatForUser() +
                "Annotation: " + annotation + "\n\tBooks:\n\t\t");
        for (var book : books) {
            s.append(book.formatForList());
        }
        return s.toString();
    }

    @Override
    public String formatForList() {
        return "Almanac: " + super.formatForList();
    }
}
