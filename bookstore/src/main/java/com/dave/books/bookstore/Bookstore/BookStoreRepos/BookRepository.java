package com.dave.books.bookstore.Bookstore.BookStoreRepos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dave.books.bookstore.Bookstore.BookstoreEntities.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    boolean existsByTitle(String booksEntityTitle);
    Optional<BookEntity> findByTitle(String booksEntityTitle);
    boolean existsByAuthor(String booksEntityAuthor);
    Optional<BookEntity> findByAuthor(String booksEntityAuthor);
    @SuppressWarnings("null")
    Optional<BookEntity> findById(Long bookEntityId);

}
