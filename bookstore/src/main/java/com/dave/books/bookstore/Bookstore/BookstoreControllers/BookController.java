package com.dave.books.bookstore.Bookstore.BookstoreControllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dave.books.bookstore.Bookstore.BooksStoreServices.BookServices;
import com.dave.books.bookstore.Bookstore.BookstoreEntities.Books;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "api/v2/books")
public class BookController {
    @Autowired
    BookServices booksServices;

    public BookController(BookServices booksServices) {
        this.booksServices = booksServices;
    }

    @GetMapping()
    public List<Books> getAllBooks() {
        return booksServices.getAllBooks();
    }

    @GetMapping(path = "{BooksId}")
    public Optional<Books> getBookById(@PathVariable("BooksId") Long BooksId) {
        try {
            return booksServices.getBookById(BooksId);
        } catch (Exception e) {
            throw new UnsupportedOperationException("There is no book with " + BooksId
                    + " id please check the id and try again", e);
        }
    }

    @GetMapping(path = "bookTitle/{BooksTitle}")
    public Optional<Books> getBookByTitle(@PathVariable("BooksTitle") String BooksTitle) {
        try {
            return booksServices.getBookByTitle(BooksTitle);
        } catch (Exception e) {
            throw new UnsupportedOperationException("There is no book with " + BooksTitle
                    + " as a title,  please check the spelling and try again", e);
        }
    }

    @GetMapping(path = "bookAuthor/{BooksAuthor}")
    public Optional<Books> getBookByAuthor(@PathVariable("BooksAuthor") String BooksAuthor) {
        try {
            return booksServices.getBookByAuthor(BooksAuthor);
        } catch (Exception e) {
            throw new UnsupportedOperationException("There is no book with author " + BooksAuthor
                    + " please check the spelling and try again", e);
        }
    }

    @PostMapping("/newBook")
    public Books addNewBook(@RequestBody Books books) {
        return booksServices.addNewBook(books);
    }

    @PostMapping("/newBooks")
    public List<Books> addNewBooks(@RequestBody List<Books> books) {
        booksServices.addNewBooks(books);
        return books;
    }

    @PutMapping("{BooksId}")
    public void editBookDetail(@PathVariable("BooksId") Long BooksId,
            @RequestParam(required = false) Double price,

            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String description) {
        booksServices.updateBookDetail(price, title, author, description, BooksId);
    }

    @DeleteMapping(path = "{BooksId}")
    public void deleteBook(@PathVariable("BooksId") Long BooksId) {
        booksServices.deleteBook(BooksId);

    }
}
