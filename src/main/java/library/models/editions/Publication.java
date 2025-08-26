package library.models.editions;

import library.interfaces.IFormattable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Publication implements IFormattable {
    private String title;
    private LocalDate yearOfPublish;

    @Override
    public String formatForUser() {
        return "\tTitle: " + title +
                "\n\tYear of Publish: " +
                yearOfPublish.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) +
                "\n\t";
    }

    @Override
    public String formatForList() {
        return "Title: " + title +
                " | Year of Publish: " +
                yearOfPublish.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}
