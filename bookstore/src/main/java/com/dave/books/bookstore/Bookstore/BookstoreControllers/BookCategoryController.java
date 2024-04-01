package com.dave.books.bookstore.Bookstore.BookstoreControllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dave.books.bookstore.Bookstore.BooksStoreServices.BookCategoryServices;
import com.dave.books.bookstore.Bookstore.BookstoreEntities.BookCategory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "api/v3/booksCategory")
public class BookCategoryController {
    @Autowired
    BookCategoryServices booksCategoryServices;

    public BookCategoryController(BookCategoryServices booksCategoryServices) {
        this.booksCategoryServices = booksCategoryServices;
    }

    @GetMapping()
    public List<BookCategory> getAllBookCategory() {
        return booksCategoryServices.getAllBookCategory();
    }

    @GetMapping(path = "{BookCategoryId}")
    public Optional<BookCategory> getBookCategoryById(@PathVariable("BookCategoryId") Long BookCategoryId) {
        try {
            return booksCategoryServices.getBookCategoryById(BookCategoryId);
        } catch (Exception e) {
            throw new UnsupportedOperationException("There is no book with " + BookCategoryId
                    + " id please check the id and try again", e);
        }
    }

    @GetMapping(path = "bookCategoryTitle/{BookCategoryTitle}")
    public Optional<BookCategory> getBookCategoryByTitle(@PathVariable("BookCategoryTitle") String BookCategoryTitle) {
        try {
            return booksCategoryServices.getBookCategoryByTitle(BookCategoryTitle);
        } catch (Exception e) {
            throw new UnsupportedOperationException("There is no book with " + BookCategoryTitle
                    + " as a title,  please check the spelling and try again", e);
        }
    }

    @GetMapping(path = "bookCategory/{BookCategory}")
    public Optional<BookCategory> getBookByCategory(
            @PathVariable("BookCategory") String BookCategoryCategory) {
        try {
            return booksCategoryServices.getBookByCategory(BookCategoryCategory);
        } catch (Exception e) {
            throw new UnsupportedOperationException("There is no book with category " + BookCategoryCategory
                    + " please check the spelling and try again", e);
        }
    }

    @PostMapping("/addCategory")
    public BookCategory addNewCategory(@RequestBody BookCategory bookCategory) {
        return booksCategoryServices.addNewCategory(bookCategory);
    }

    @PostMapping("/addCategories")
    public List<BookCategory> addNewCategories(@RequestBody List<BookCategory> bookCategory) {
        booksCategoryServices.addNewCategories(bookCategory);
        return bookCategory;
    }

    @PutMapping("{BookCategoryId}")
    public void editBookCategory(@PathVariable("BookCategoryId") Long BookCategoryId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String category) {
        booksCategoryServices.editBookCategory(title, category, BookCategoryId);
    }

    @DeleteMapping(path = "{BookCategoryId}")
    public void deleteBook(@PathVariable("BookCategoryId") Long BookCategoryId) {
        booksCategoryServices.deleteCategory(BookCategoryId);

    }
}
