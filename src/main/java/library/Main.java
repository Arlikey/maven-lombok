package library;

import library.models.catalogue.Library;
import library.models.editions.Almanac;
import library.models.editions.Book;
import library.models.editions.Genre;
import library.models.editions.Newspaper;
import library.services.LibraryService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LibraryService ls = new LibraryService(new Library());
        for (int i = 0; i < 5; i++) {
            ls.addRandomPublication();
        }
        LibraryConsole libraryConsole = new LibraryConsole(ls);

        libraryConsole.start();
    }
}
