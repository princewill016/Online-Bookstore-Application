package com.dave.books.bookstore.Bookstore.BookStoreRepos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dave.books.bookstore.Bookstore.BookstoreEntities.BookCategory;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

    boolean existsByTitle(String bookCategoryTitle);

    Optional<BookCategory> findByTitle(String bookCategoryTitle);

    boolean existsByCategory(String bookCategory);

    Optional<BookCategory> findByCategory(String bookCategory); 

    Optional<BookCategory> findByCategoryId(Long bookCategoryId);

}
