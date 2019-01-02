package com.mspets.microprofile.nextmicroservice;

import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author spetsiotis
 */
@ApplicationScoped
public class Resources {
    // use @SuppressWarnings to tell IDE to ignore warnings about field not being referenced directly
    @SuppressWarnings("unused")
    @Produces
    @PersistenceContext
    private EntityManager em;

    // @Produces
    // public Logger produceLog(InjectionPoint injectionPoint) {
    //   return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    // }

    @Produces
    private JsonWebToken jsonWebToken;

}
