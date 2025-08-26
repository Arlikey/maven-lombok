package library.models.editions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.HashSet;

@Data
@EqualsAndHashCode(callSuper = true)
public class Newspaper extends Publication {
    private int issue;
    private HashSet<String> headers;

    public Newspaper(String title, LocalDate yearOfPublish,
                     int issue, HashSet<String> headers) {
        super(title, yearOfPublish);
        this.issue = issue;
        this.headers = headers;
    }

    @Override
    public String formatForUser() {
        return "Newspaper:\n" + super.formatForUser() +
                "Issue: " + issue + "\n\tHeaders:\n\t\t" + headers;
    }

    @Override
    public String formatForList() {
        return "Newspaper:" + super.formatForList() +
                " | Issue: " + issue + " | Headers: " + headers;
    }
}
