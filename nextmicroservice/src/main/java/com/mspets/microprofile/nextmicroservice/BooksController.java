package com.mspets.microprofile.nextmicroservice;

import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author spetsiotis
 */

@Path("/api/books") // just a basic JAX-RS resource
@Counted // track the number of times this endpoint is invoked
public class BooksController {
    @Inject //use CDI to inject a service
    private BookService bookService;
    @GET
    @RolesAllowed("read-books")
    // uses common annotations to declare a role required
    public Books findAll() {
        return bookService.getAll();
    }
}
