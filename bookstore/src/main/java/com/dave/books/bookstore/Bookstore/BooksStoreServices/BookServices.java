package com.dave.books.bookstore.Bookstore.BooksStoreServices;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dave.books.bookstore.Bookstore.BookStoreRepos.BookRepository;
import com.dave.books.bookstore.Bookstore.BookstoreEntities.BookEntity;

import jakarta.transaction.Transactional;

@Service
public class BookServices {

    @Autowired
    BookRepository bookRepository;

    public BookServices(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> getAllBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            throw new UnsupportedOperationException("something went wrong", e);
        }

    }

    @SuppressWarnings("null")
    public Optional<BookEntity> getBookById(Long booksEntityId) {
        boolean exist = bookRepository.existsById(booksEntityId);
        if (!exist) {
            throw new UnsupportedOperationException("There is no book with id " +
                    booksEntityId);
        }
        return bookRepository.findById(booksEntityId);
    }

    public Optional<BookEntity> getBookByTitle(String booksEntityTitle) {
        boolean exist = bookRepository.existsByTitle(booksEntityTitle);
        if (!exist) {
            throw new UnsupportedOperationException("There is no book with title " +
                    booksEntityTitle);
        }
        return bookRepository.findByTitle(booksEntityTitle);
    }

    public Optional<BookEntity> getBookByAuthor(String booksEntityAuthor) {
        boolean exist = bookRepository.existsByAuthor(booksEntityAuthor);
        if (!exist) {
            throw new UnsupportedOperationException("There is no book with Author " +
                    booksEntityAuthor);
        }
        return bookRepository.findByAuthor(booksEntityAuthor);
    }

    public BookEntity addNewBook(BookEntity bookEntity) {
        Optional<BookEntity> bookOpt = bookRepository.findByTitle(bookEntity.getTitle());
        if (bookOpt.isPresent()) {
            throw new IllegalStateException("Book already exists");
        } else {
            return bookRepository.save(bookEntity);
        }
    }

    public List<BookEntity> addNewBooks(List<BookEntity> bookEntity) {
        if (bookEntity == null) {
            throw new IllegalArgumentException("bookEntity cannot be null");
        }
        return bookRepository.saveAll(bookEntity);
    }

    @Transactional
    public void updateBookDetail(Double price, String title, String author, String description, Long booksEntityId) {

        BookEntity book = bookRepository.findById(booksEntityId)
                .orElseThrow(() -> new IllegalStateException("Book with id " + booksEntityId + " not found"));
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
        // bookRepository.save(book);
    }

    @SuppressWarnings("null")
    public void deleteBook(Long booksEntityId) {
        boolean exists = bookRepository.existsById(booksEntityId);
        if (!exists) {
            throw new IllegalStateException("There is no book with id " + booksEntityId);
        }
        bookRepository.deleteById(booksEntityId);
    }

}