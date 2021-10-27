package io.quarkuscoffeeshop.inventorymocker.infrastructure;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkuscoffeeshop.inventorymocker.domain.RestockItemCommand;
import io.smallrye.reactive.messaging.connectors.InMemoryConnector;
import io.smallrye.reactive.messaging.connectors.InMemorySink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest @QuarkusTestResource(KafkaTestResource.class)
public class InventoryMockerTest {

    @Inject
    InventoryMockerService inventoryMockerService;

    String SOURCE = "inventory-in";

    String SINK = "inventory-out";

    @Inject
    @Any
    InMemoryConnector connector;

    InMemorySink<RestockItemCommand> results;

    @BeforeEach
    public void setUp() {

        results = connector.sink(SOURCE);
    }

    @Test @Order(1)
    public void testStartStop() {

        assertFalse(inventoryMockerService.isRunning());
        inventoryMockerService.start();
        assertTrue(inventoryMockerService.isRunning());
    }

    @Test
    @Order(2)
    public void testRestockItemCommandItem() {

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            assertNull(e);
        }

        assertEquals(1, results.received().size());
        assertEquals(1, results.received().size());
    }

}
