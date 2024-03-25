package com.dave.books.bookstore.Bookstore;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    boolean existsByTitle(String booksEntityTitle);

    Optional<BookEntity> findByTitle(String booksEntityTitle);

    boolean existsByAuthor(String booksEntityAuthor);

}
