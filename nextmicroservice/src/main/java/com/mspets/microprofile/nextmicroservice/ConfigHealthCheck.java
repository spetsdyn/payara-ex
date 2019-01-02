package com.mspets.microprofile.nextmicroservice;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Health
public class ConfigHealthCheck implements HealthCheck {


    @Override
    public HealthCheckResponse call() {

            return HealthCheckResponse.named("next-microservice")
                    .up()
                    .build();

    }
}
