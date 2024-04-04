package com.dave.books.bookstore.Bookstore.BookstoreEntities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BooksCategory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BookCategory_sequence")
    private Long categoryId;
    private String title;
    private String category;
    @OneToMany(mappedBy = "bookCategory", cascade = CascadeType.ALL)
    private List<Books> books;

}
