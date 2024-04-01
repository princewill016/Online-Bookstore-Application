package com.dave.books.bookstore.Bookstore.BooksStoreServices;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dave.books.bookstore.Bookstore.BookStoreRepos.BookRepository;
import com.dave.books.bookstore.Bookstore.BookstoreEntities.Books;

import jakarta.transaction.Transactional;

@Service
public class BookServices {

    @Autowired
    BookRepository bookRepository;

    public BookServices(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Books> getAllBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            throw new UnsupportedOperationException("something went wrong", e);
        }

    }

    @SuppressWarnings("null")
    public Optional<Books> getBookById(Long booksId) {
        boolean exist = bookRepository.existsById(booksId);
        if (!exist) {
            throw new UnsupportedOperationException("There is no book with id " +
                    booksId);
        }
        return bookRepository.findById(booksId);
    }

    public Optional<Books> getBookByTitle(String booksTitle) {
        boolean exist = bookRepository.existsByTitle(booksTitle);
        if (!exist) {
            throw new UnsupportedOperationException("There is no book with title " +
                    booksTitle);
        }
        return bookRepository.findByTitle(booksTitle);
    }

    public Optional<Books> getBookByAuthor(String booksAuthor) {
        boolean exist = bookRepository.existsByAuthor(booksAuthor);
        if (!exist) {
            throw new UnsupportedOperationException("There is no book with Author " +
                    booksAuthor);
        }
        return bookRepository.findByAuthor(booksAuthor);
    }

    public Books addNewBook(Books books) {
        Optional<Books> bookOpt = bookRepository.findByTitle(books.getTitle());
        if (bookOpt.isPresent()) {
            throw new IllegalStateException("Book already exists");
        } else {
            return bookRepository.save(books);
        }
    }

    public List<Books> addNewBooks(List<Books> books) {
        if (books == null) {
            throw new IllegalArgumentException("books cannot be null");
        }
        return bookRepository.saveAll(books);
    }

    @SuppressWarnings("null")
    @Transactional
    public void updateBookDetail(Double price, String title, String author, String description, Long booksId) {

        Books book = bookRepository.findById(booksId)
                .orElseThrow(() -> new IllegalStateException("Book with id " + booksId + " not found"));
        if (price != null && !Objects.equals(book.getPrice(), price)) {
            book.setPrice(price);
        }
        if (title != null && !title.isEmpty() && !Objects.equals(book.getTitle(), title)) {
            book.setTitle(title);
        }
        if (author != null && !author.isEmpty() && !Objects.equals(book.getAuthor(), author)) {
            book.setAuthor(author);
        }
        if (description != null && !description.isEmpty() && !Objects.equals(book.getDescription(), description)) {
            book.setDescription(description);
        }
        bookRepository.save(book);
    }

    @SuppressWarnings("null")
    public void deleteBook(Long booksId) {
        boolean exists = bookRepository.existsById(booksId);
        if (!exists) {
            throw new IllegalStateException("There is no book with id " + booksId);
        }
        bookRepository.deleteById(booksId);
    }

}
