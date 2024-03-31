package com.dave.books.bookstore.Bookstore.BookstoreControllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dave.books.bookstore.Bookstore.BooksStoreServices.BookServices;
import com.dave.books.bookstore.Bookstore.BookstoreEntities.BookEntity;

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
    public List<BookEntity> getAllBooks() {
        return booksServices.getAllBooks();
    }

    @GetMapping(path = "{BooksEntityId}")
    public Optional<BookEntity> getBookById(@PathVariable("BooksEntityId") Long BooksEntityId) {
        try {
            return booksServices.getBookById(BooksEntityId);
        } catch (Exception e) {
            throw new UnsupportedOperationException("There is no book with " + BooksEntityId
                    + " id please check the id and try again", e);
        }
    }

    @GetMapping(path = "bookTitle/{BooksEntityTitle}")
    public Optional<BookEntity> getBookByTitle(@PathVariable("BooksEntityTitle") String BooksEntityTitle) {
        try {
            return booksServices.getBookByTitle(BooksEntityTitle);
        } catch (Exception e) {
            throw new UnsupportedOperationException("There is no book with " + BooksEntityTitle
                    + " as a title,  please check the spelling and try again", e);
        }
    }

    @GetMapping(path = "bookAuthor/{BooksEntityAuthor}")
    public Optional<BookEntity> getBookByAuthor(@PathVariable("BooksEntityAuthor") String BooksEntityAuthor) {
        try {
            return booksServices.getBookByAuthor(BooksEntityAuthor);
        } catch (Exception e) {
            throw new UnsupportedOperationException("There is no book with author " + BooksEntityAuthor
                    + " please check the spelling and try again", e);
        }
    }

    @PostMapping("/addBook")
    public BookEntity addNewBook(@RequestBody BookEntity bookEntity) {
        return booksServices.addNewBook(bookEntity);
    }

    @PostMapping("/addBooks")
    public List<BookEntity> addNewBooks(@RequestBody List<BookEntity> bookEntity) {
        booksServices.addNewBooks(bookEntity);
        return bookEntity;
    }

    @PutMapping("{BooksEntityId}")
    public void editBookDetail(@PathVariable("BooksEntityId") Long BooksEntityId,
            @RequestParam(required = false) Double price,

            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String description) {
        booksServices.updateBookDetail(price, title, author, description, BooksEntityId);
    }

    @DeleteMapping(path = "{BooksEntityId}")
    public void deleteBook(@PathVariable("BooksEntityId") Long BooksEntityId) {
        booksServices.deleteBook(BooksEntityId);

    }
}
