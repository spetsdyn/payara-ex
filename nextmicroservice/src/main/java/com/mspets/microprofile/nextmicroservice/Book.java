package com.mspets.microprofile.nextmicroservice;

import javax.persistence.Entity;

/**
 * @author spetsiotis
 */
@Entity
public class Book {
    private String isbn;
    private String author;

    public Book(String isbn, String author) {

    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }
}
