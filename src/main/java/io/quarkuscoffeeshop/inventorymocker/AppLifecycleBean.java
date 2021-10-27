package io.quarkuscoffeeshop.inventorymocker;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkuscoffeeshop.inventorymocker.infrastructure.InventoryMockerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class AppLifecycleBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppLifecycleBean.class);

    @Inject
    InventoryMockerService inventoryMockerService;

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("The application is starting...");
        inventoryMockerService.start();
        LOGGER.debug("InventoryMockerService: {}", inventoryMockerService.isRunning());
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application is stopping...");
    }}
