package org.acme.vertx;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.vertx.core.eventbus.Message;
import org.eclipse.microprofile.context.ManagedExecutor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class GreetingService {
    @Inject
    ManagedExecutor executor;

    @ConsumeEvent("greeting")
    public String greeting(String name) {
        return "Hello " + name;
    }

    @ConsumeEvent("greeting")
    public void consume(Message<String> msg) {
        System.out.println(msg.address());
        System.out.println(msg.body());
    }

    @ConsumeEvent("greeting")
    public Uni<String> consume2(String name) {
        return Uni.createFrom().item(() -> name.toUpperCase()).emitOn(executor);
    }

    @ConsumeEvent(value = "blocking-consumer", blocking = true)
    void consumeBlocking(String message) {
        // Something blocking
    }
}