package com.dave.books.bookstore.Bookstore.BooksStoreServices;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dave.books.bookstore.Bookstore.BookStoreRepos.BookCategoryRepository;
import com.dave.books.bookstore.Bookstore.BookstoreEntities.BookCategory;

import jakarta.transaction.Transactional;

@Service
public class BookCategoryServices {

    @Autowired
    BookCategoryRepository bookCategoryRepository;

    public BookCategoryServices(BookCategoryRepository bookCategoryRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
    }

    public List<BookCategory> getAllBookCategory() {
        try {
            return bookCategoryRepository.findAll();
        } catch (Exception e) {
            throw new UnsupportedOperationException("something went wrong", e);
        }

    }


    public Optional<BookCategory> getBookCategoryById(Long bookCategoryId) {
        boolean exist = bookCategoryRepository.existsById(bookCategoryId);
        if (!exist) {
            throw new UnsupportedOperationException("There is no book with id " +
                    bookCategoryId);
        }
        return bookCategoryRepository.findById(bookCategoryId);
    }

    public Optional<BookCategory> getBookCategoryByTitle(String bookCategoryTitle) {
        boolean exist = bookCategoryRepository.existsByTitle(bookCategoryTitle);
        if (!exist) {
            throw new UnsupportedOperationException("There is no book with title " +
                    bookCategoryTitle);
        }
        return bookCategoryRepository.findByTitle(bookCategoryTitle);
    }

    public Optional<BookCategory> getBookByCategory(String bookCategory) {
        boolean exist = bookCategoryRepository.existsByCategory(bookCategory);
        if (!exist) {
            throw new UnsupportedOperationException("There is no book with category " +
                    bookCategory);
        }
        return bookCategoryRepository.findByCategory(bookCategory);
    }

    public BookCategory addNewCategory(BookCategory bookCategory) {
        Optional<BookCategory> bookOpt = bookCategoryRepository.findByCategory(bookCategory.getCategory());
        if (bookOpt.isPresent()) {
            throw new IllegalStateException("Book category already exists");
        } else {
            return bookCategoryRepository.save(bookCategory);
        }
    }

    public List<BookCategory> addNewCategories(List<BookCategory> bookCategory) {
        if (bookCategory == null) {
            throw new IllegalArgumentException("books cannot be null");
        }
        return bookCategoryRepository.saveAll(bookCategory);
    }


    @Transactional
    public void editBookCategory(String title, String category, Long bookCategoryId) {

        BookCategory bookCategory = bookCategoryRepository.findByCategoryId(bookCategoryId)
                .orElseThrow(() -> new IllegalStateException("Book with id " + bookCategoryId + " not found"));
        if (title != null && !title.isEmpty() && !Objects.equals(bookCategory.getTitle(), title)) {
            bookCategory.setTitle(title);
        }
        if (category != null && !category.isEmpty() && !Objects.equals(bookCategory.getCategory(), category)) {
            bookCategory.setCategory(category);
        }
        bookCategoryRepository.save(bookCategory);
    }


    public void deleteCategory(Long bookCategoryId) {
        boolean exists = bookCategoryRepository.existsById(bookCategoryId);
        if (!exists) {
            throw new IllegalStateException("There is no book with id " + bookCategoryId);
        }
        bookCategoryRepository.deleteById(bookCategoryId);
    }

}
