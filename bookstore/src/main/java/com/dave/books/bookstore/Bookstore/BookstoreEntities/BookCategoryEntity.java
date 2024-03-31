package com.dave.books.bookstore.Bookstore.BookstoreEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BooksCategory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Book_sequence")
    private Long id;
    private String title;
    private String category;

    public BookCategoryEntity(String title, String category) {
        this.title = title;
        this.category = category;
    }

}
