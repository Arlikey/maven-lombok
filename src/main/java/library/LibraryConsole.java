package library;

import library.models.editions.*;
import library.services.LibraryService;

import java.time.LocalDate;
import java.util.*;

public class LibraryConsole {
    private final LibraryService service;
    private final Scanner scan = new Scanner(System.in);

    public LibraryConsole(LibraryService service) {
        this.service = service;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Add publication");
            System.out.println("2. Add random publication");
            System.out.println("3. Remove publication by title");
            System.out.println("4. Show full catalogue");
            System.out.println("5. Search by title");
            System.out.println("6. Search books by author");
            System.out.println("7. Search books by keywords in annotation");
            System.out.println("8. Search books/almanacs by pages/author/genre");
            System.out.println("9. Search newspapers by header");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            String choice = scan.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> {
                        if (!addPublication()){
                            System.out.println("Publication already in library.");
                        }
                    }
                    case "2" -> {
                        service.addRandomPublication();
                        System.out.println("Random publication added.");
                    }
                    case "3" -> removeByTitle();
                    case "4" -> showCatalogue();
                    case "5" -> searchByTitle();
                    case "6" -> searchByAuthor();
                    case "7" -> searchByKeywords();
                    case "8" -> searchByPagesAuthorGenre();
                    case "9" -> searchNewspaperByHeader();
                    case "0" -> {
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception ex) {
                System.out.println("Something went wrong. Try again! (" + ex.getMessage() + ")");
            }
        }
    }

    private boolean addPublication() {
        System.out.print("Choose type (1=Book, 2=Newspaper, 3=Almanac): ");
        String type = scan.nextLine().trim();
        boolean added = false;
        switch (type) {
            case "1" -> added = service.addPublication(promptBook());
            case "2" -> added = service.addPublication(promptNewspaper());
            case "3" -> added = service.addPublication(promptAlmanac());
            default -> System.out.println("Invalid type.");
        }
        return added;
    }

    private Book promptBook() {
        System.out.print("Title: ");
        String title = scan.nextLine().trim();
        System.out.print("Year of publish (YYYY-MM-DD): ");
        String dateStr = scan.nextLine().trim();
        System.out.print("Author: ");
        String author = scan.nextLine().trim();
        System.out.print("Genres (comma separated, e.g. Fantasy, Detective): ");
        String[] genreStrs = scan.nextLine().trim().split(",");
        Set<Genre> genres = new HashSet<>();
        for (String g : genreStrs) genres.add(Genre.valueOf(g.trim().toUpperCase()));
        System.out.print("Pages count: ");
        int pages = Integer.parseInt(scan.nextLine().trim());
        System.out.print("Annotation: ");
        String annotation = scan.nextLine().trim();
        return new Book(title, LocalDate.parse(dateStr), author, new HashSet<>(genres), pages, annotation);
    }

    private Newspaper promptNewspaper() {
        System.out.print("Title: ");
        String title = scan.nextLine().trim();
        System.out.print("Year of publish (YYYY-MM-DD): ");
        String dateStr = scan.nextLine().trim();
        System.out.print("Issue number: ");
        int issue = Integer.parseInt(scan.nextLine().trim());
        System.out.print("Headers (comma separated): ");
        HashSet<String> headers = new HashSet<>(Arrays.asList(scan.nextLine().trim().split(",")));
        return new Newspaper(title, LocalDate.parse(dateStr), issue, headers);
    }

    private Almanac promptAlmanac() {
        System.out.print("Title: ");
        String title = scan.nextLine().trim();
        System.out.print("Year of publish (YYYY-MM-DD): ");
        String dateStr = scan.nextLine().trim();
        System.out.print("Annotation: ");
        String annotation = scan.nextLine().trim();
        System.out.print("Number of books in almanac: ");
        int n = Integer.parseInt(scan.nextLine().trim());
        HashSet<Book> books = new HashSet<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter book #" + (i + 1));
            books.add(promptBook());
        }
        return new Almanac(title, LocalDate.parse(dateStr), annotation, books);
    }

    private void removeByTitle() {
        System.out.print("Enter title to remove: ");
        String title = scan.nextLine().trim();
        boolean removed = service.removeByTitle(title);
        System.out.println(removed ? "Removed." : "Not found.");
    }

    private void showCatalogue() {
        System.out.println(service.getFullCatalogue());
    }

    private void searchByTitle() {
        System.out.print("Enter title to search: ");
        String title = scan.nextLine().trim();
        List<Publication> found = service.findByTitle(title);
        if (found.isEmpty()) System.out.println("No results.");
        else found.forEach(p -> System.out.println(p.formatForUser()));
    }

    private void searchByAuthor() {
        System.out.print("Enter author: ");
        String author = scan.nextLine().trim();
        List<Book> found = service.findBooksByAuthor(author);
        if (found.isEmpty()) System.out.println("No results.");
        else found.forEach(b -> System.out.println(b.formatForUser()));
    }

    private void searchByKeywords() {
        System.out.print("Enter keywords (comma separated): ");
        Set<String> keywords = new HashSet<>(Arrays.asList(scan.nextLine().trim().split(",")));
        List<Book> found = service.findBooksByKeywords(keywords);
        if (found.isEmpty()) System.out.println("No results.");
        else found.forEach(b -> System.out.println(b.formatForUser()));
    }

    private void searchByPagesAuthorGenre() {
        System.out.print("Max pages: ");
        int pages = Integer.parseInt(scan.nextLine().trim());
        System.out.print("Author (empty for any): ");
        String author = scan.nextLine().trim();
        if (author.isEmpty()) author = null;
        System.out.print("Genre (empty for any): ");
        String genreStr = scan.nextLine().trim();
        Genre genre = genreStr.isEmpty() ? null : Genre.valueOf(genreStr.toUpperCase());
        List<Publication> found = service.findBooksOrAlmanacsByPages(pages, author, genre);
        if (found.isEmpty()) System.out.println("No results.");
        else found.forEach(p -> System.out.println(p.formatForUser()));
    }

    private void searchNewspaperByHeader() {
        System.out.print("Enter header text to search: ");
        String header = scan.nextLine().trim();
        List<Newspaper> found = service.findNewspapersByHeader(header);
        if (found.isEmpty()) System.out.println("No results.");
        else found.forEach(n -> System.out.println(n.formatForUser()));
    }
}
