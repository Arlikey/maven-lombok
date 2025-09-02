package library;

import library.models.catalogue.Library;
import library.models.editions.*;
import library.services.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {

    private Library library;
    private LibraryService libraryService;
    private Book book1;
    private Book book2;
    private Newspaper newspaper;
    private Almanac almanac;

    @BeforeEach
    void setUp() {
        library = new Library();
        libraryService = new LibraryService(library);

        book1 = new Book("Book 1", LocalDate.now(), "Author 1",
                new HashSet<>(List.of(Genre.FANTASY)), 120, "Magic and adventure");
        book2 = new Book("Book 2", LocalDate.now(), "Author 2",
                new HashSet<>(List.of(Genre.DETECTIVE)), 200, "Mystery and investigation");

        newspaper = new Newspaper("Daily News", LocalDate.now(), 42,
                new HashSet<>(List.of("Pirates occupied Carribean Sea", "John Carver exited his artist career")));

        almanac = new Almanac("Great Almanac", LocalDate.now(),
                "Collection of books", new HashSet<>(List.of(book1, book2)));

        libraryService.addPublication(book1);
        libraryService.addPublication(book2);
        libraryService.addPublication(newspaper);
        libraryService.addPublication(almanac);
    }

    @Test
    void testAddPublication() {
        Book newBook = new Book("New Book", LocalDate.now(), "Author 3",
                new HashSet<>(List.of(Genre.HORROR)), 150, "Scary story");
        boolean added = libraryService.addPublication(newBook);
        assertTrue(added);
        assertTrue(library.getPublications().contains(newBook));
    }

    @Test
    void testRemoveByTitle() {
        boolean removed = libraryService.removeByTitle("Book 1");
        assertTrue(removed);
        assertFalse(library.getPublications().contains(book1));
    }

    @Test
    void testFindBooksByAuthor() {
        List<Book> result = libraryService.findBooksByAuthor("Author 1");
        assertEquals(1, result.size());
        assertTrue(result.contains(book1));
    }

    @Test
    void testFindByTitle() {
        List<Publication> result = libraryService.findByTitle("Book");
        assertEquals(2, result.size());
        assertTrue(result.contains(book1));
        assertTrue(result.contains(book2));
    }

    @Test
    void testFindBooksByKeywords() {
        List<Book> result = libraryService.findBooksByKeywords(Set.of("magic"));
        assertEquals(1, result.size());
        assertTrue(result.contains(book1));
    }

    @Test
    void testFindBooksOrAlmanacsByPages() {
        List<Publication> result = libraryService.findBooksOrAlmanacsByPages(150, null, null);
        assertTrue(result.contains(book1));
        assertFalse(result.contains(book2));
        assertTrue(result.contains(almanac));
    }

    @Test
    void testFindNewspapersByHeader() {
        List<Newspaper> result = libraryService.findNewspapersByHeader("John Carver exited his artist career");
        assertEquals(1, result.size());
        assertTrue(result.contains(newspaper));
    }

    @Test
    void testGetFullCatalogue() {
        String catalogue = libraryService.getFullCatalogue();
        assertTrue(catalogue.contains("Book 1"));
        assertTrue(catalogue.contains("Daily News"));
        assertTrue(catalogue.contains("Great Almanac"));
    }
}
