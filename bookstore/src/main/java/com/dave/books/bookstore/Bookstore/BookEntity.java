package com.dave.books.bookstore.Bookstore;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BooksStored")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Book_sequence")
    private Long id;
    private String title;
    private String author;
    private String description;
    private double price;
    private int quantity;
    private int rating;
    private Date publishedDate;

    public BookEntity(String title, String author, String description, double price, int quantity, int rating,
            Date publishedDate) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.rating = rating;
        this.publishedDate = publishedDate;
    }

}
