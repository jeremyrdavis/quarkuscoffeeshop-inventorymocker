package io.quarkuscoffeeshop.inventorymocker;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkuscoffeeshop.inventorymocker.infrastructure.InventoryMockerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@QuarkusMain
public class InventoryMockerMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryMockerMain.class);

    @Inject
    InventoryMockerService inventoryMockerService;

    public static void main(String ... args) {
        LOGGER.debug("Running main method");
        Quarkus.run(args);
    }
}
