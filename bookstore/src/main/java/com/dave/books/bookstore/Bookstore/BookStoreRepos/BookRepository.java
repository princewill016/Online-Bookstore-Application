package com.dave.books.bookstore.Bookstore.BookStoreRepos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dave.books.bookstore.Bookstore.BookstoreEntities.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {
    boolean existsByTitle(String booksTitle);

    Optional<Books> findByTitle(String booksTitle);

    boolean existsByAuthor(String booksAuthor);

    Optional<Books> findByAuthor(String booksAuthor);

    @SuppressWarnings("null")
    Optional<Books> findById(Long booksId);

}
