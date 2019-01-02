package com.mspets.microprofile.nextmicroservice;

import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotAuthorizedException;

/**
 * @author spetsiotis
 */
@RequestScoped
public class PublishBookService {
    @Inject
    // we can inject a JsonWebToken, a Principal specific to the JWT specification
    private JsonWebToken jsonWebToken;
    // we could also inject individual ClaimValue objects.
    @Inject
    private AuthorService authorService;
    @Inject
    private EntityManager entityManager;
    @Timeout(500)
    // we want to limit how long it takes to publish and if it
    // exceeds, return an exception to the caller.
    public BookId publish(PublishBook publishBook) {
        // we check the standard claim of subject
        if (!publishBook.getAuthor().equals(jsonWebToken.getSubject())) {
            // as well as a custom claim as a boolean
            boolean createAny = jsonWebToken.getClaim("create.books.for.other.authors");
            if (!createAny) {
                throw new NotAuthorizedException("Cannot create book, wrong author");
            }
        }
        Author author = authorService.findAuthor(publishBook.getAuthor());
        if (author == null) {
            throw new NotAuthorizedException("The list author is not an author");
        }
        Book book = entityManager.merge(new Book(publishBook.getIsbn(),
                                                 publishBook.getAuthor()));
        return new BookId(book.getIsbn(), book.getAuthor());
    }
}
