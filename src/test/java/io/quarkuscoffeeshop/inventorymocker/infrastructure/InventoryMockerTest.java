package io.quarkuscoffeeshop.inventorymocker.infrastructure;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkuscoffeeshop.inventorymocker.domain.RestockItemCommand;
import io.smallrye.reactive.messaging.connectors.InMemoryConnector;
import io.smallrye.reactive.messaging.connectors.InMemorySink;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Any;
import javax.inject.Inject;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest @QuarkusTestResource(KafkaTestResource.class) @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InventoryMockerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryMockerTest.class);

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

        LOGGER.info("injected: {}", inventoryMockerService.toString());
        results = connector.sink(SOURCE);
    }

    @Test @Order(1)
    public void testStartStop() {

        LOGGER.info("testStartStop");
        inventoryMockerService.beginMocking();
        assertTrue(inventoryMockerService.isRunning());
    }

    @Test @Order(2)
    public void testStartingMocker() {

        LOGGER.info("starting InventoryMockerSerice");
        inventoryMockerService.beginMocking();
        assertTrue(inventoryMockerService.isRunning());
    }

    @Test
    @Order(3)
    public void testRestockItemCommandItem() {

        LOGGER.info("testRestockItemCommandItem");

        assertTrue(inventoryMockerService.isRunning());

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            assertNull(e);
        }

        assertTrue(results.received().size() >= 1);
    }

    @Test
    @Order(4)
    public void testStopping() {

        LOGGER.info("testStopping");
        inventoryMockerService.stop();
        assertFalse(inventoryMockerService.isRunning());
    }

}
