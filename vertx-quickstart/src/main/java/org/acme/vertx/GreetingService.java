package org.acme.vertx;

import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    @ConsumeEvent
    public String consume(String name) {
        return name.toUpperCase();
    }
}