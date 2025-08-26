package library.services;

import library.models.catalogue.Library;
import library.models.editions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class LibraryService {
    private Library library;

    public LibraryService(Library library) {
        this.library = library;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public boolean addPublication(Publication p) {
        return library.getPublications().add(p);
    }

    public void addRandomPublication() {
        Random rand = new Random();
        int type = rand.nextInt(3);
        switch (type) {
            case 0 -> addPublication(generateRandomBook());
            case 1 -> addPublication(generateRandomNewspaper());
            case 2 -> addPublication(generateRandomAlmanac());
        }
    }

    public String getFullCatalogue() {
        StringBuilder fullInfo = new StringBuilder();
        for (var publication : library.getPublications()) {
            fullInfo.append("\n").append(publication.formatForUser());
        }
        return fullInfo.toString();
    }

    public boolean removeByTitle(String title) {
        return library.getPublications().removeIf(p -> p.getTitle().equalsIgnoreCase(title));
    }

    public List<Book> findBooksByAuthor(String author) {
        return library.getPublications().stream()
                .filter(p -> p instanceof Book)
                .map(p -> (Book) p)
                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public List<Publication> findByTitle(String title) {
        return library.getPublications().stream()
                .filter(p -> p.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByKeywords(Set<String> keywords) {
        return library.getPublications().stream()
                .filter(p -> p instanceof Book)
                .map(p -> (Book) p)
                .filter(b -> keywords.stream().allMatch(k -> b.getAnnotation().toLowerCase().contains(k.toLowerCase())))
                .collect(Collectors.toList());
    }

    public List<Publication> findBooksOrAlmanacsByPages(int maxPages, String author, Genre genre) {
        return library.getPublications().stream()
                .filter(p -> p instanceof Book b && b.getPagesCount() <= maxPages &&
                        (author == null || b.getAuthor().equalsIgnoreCase(author)) &&
                        (genre == null || b.getGenres().contains(genre))
                        || p instanceof Almanac)
                .collect(Collectors.toList());
    }

    public List<Newspaper> findNewspapersByHeader(String header) {
        return library.getPublications().stream()
                .filter(p -> p instanceof Newspaper)
                .map(p -> (Newspaper) p)
                .filter(n -> n.getHeaders().stream()
                        .anyMatch(h -> h.toLowerCase().contains(header.toLowerCase())))
                .collect(Collectors.toList());
    }

    private Book generateRandomBook() {
        Set<Genre> genres = new HashSet<>();
        genres.add(Genre.values()[new Random().nextInt(Genre.values().length)]);
        return new Book("Book" + new Random().nextInt(100), LocalDate.now(),
                "Author" + new Random().nextInt(10), new HashSet<>(genres), 50 + new Random().nextInt(300),
                "Some random annotation");
    }

    private Newspaper generateRandomNewspaper() {
        Set<String> headers = new HashSet<>();
        headers.add("Header" + new Random().nextInt(10));
        return new Newspaper("Newspaper" + new Random().nextInt(100), LocalDate.now(), new Random().nextInt(50), new HashSet<>(headers));
    }

    private Almanac generateRandomAlmanac() {
        Set<Book> books = new HashSet<>();
        books.add(generateRandomBook());
        books.add(generateRandomBook());
        return new Almanac("Almanac" + new Random().nextInt(50), LocalDate.now(),
                "Some annotation", new HashSet<>(books));
    }
}
