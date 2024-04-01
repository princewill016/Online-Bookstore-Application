package com.dave.books.bookstore.Bookstore.BookstoreEntities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BooksStored")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Book_sequence")
    private Long id;
    private String title;
    private String author;
    private String description;
    private Double price;
    private int quantity;
    private int rating;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date publishedDate;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private BookCategory bookCategory;

}
