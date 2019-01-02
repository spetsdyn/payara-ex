package com.mspets.microprofile.nextmicroservice;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author spetsiotis
 */
@ApplicationScoped
public class BookService {
    @Inject
    // JPA is not provided out of the box, but most providers support it at
    // some level.  worst case, create your own producer for the field
    private EntityManager entityManager;
    @Inject
    // use configuration to control how much data you want to supply at
    // a given time
    @ConfigProperty(name = "max.books.per.page", defaultValue = "20")
    private int maxBooks;
    public Books getAll() {
        List<Book> bookList = entityManager
                .createQuery("select b from Book b", Book.class)
                .setMaxResults(maxBooks) // use that configuration to do a paginated look up
                .getResultList();
        return new Books(bookList);
    }
}
