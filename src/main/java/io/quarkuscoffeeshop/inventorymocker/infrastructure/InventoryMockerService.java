package io.quarkuscoffeeshop.inventorymocker.infrastructure;

import io.quarkus.runtime.Startup;
import io.quarkuscoffeeshop.inventorymocker.domain.Item;
import io.quarkuscoffeeshop.inventorymocker.domain.RestockItemCommand;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class InventoryMockerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryMockerService.class);

    private boolean running;

    CustomerVolume customerVolume = CustomerVolume.SLOW;

    Item[] items = Item.values();

    Random random = new Random();

    @Inject
    @Channel("inventory-in")
    Emitter<RestockItemCommand> inventoryEmitter;

    public boolean isRunning() {

        return running;
    }

    public CompletionStage<Void> beginMocking() {
        LOGGER.debug("starting mocker");
        this.running = true;
        return CompletableFuture.runAsync(mockInventoryCommands);
    }


    Runnable mockInventoryCommands = () -> {

        while (running) {
            if (!running) LOGGER.info("CustomerMocker now stopping");
            try {
                Thread.sleep(customerVolume.getDelay() * 1000);
                Item item = randomItem();
                RestockItemCommand restockItemCommand = new RestockItemCommand(item, 20);
                inventoryEmitter.send(restockItemCommand);
                LOGGER.debug("RestockItemCommand sent: {}", restockItemCommand);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Item randomItem() {
        return items[random.nextInt(items.length)];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InventoryMockerService{");
        sb.append("running=").append(running);
        sb.append(", customerVolume=").append(customerVolume);
        sb.append(", items=").append(Arrays.toString(items));
        sb.append(", random=").append(random);
        sb.append(", inventoryEmitter=").append(inventoryEmitter);
        sb.append(", mockInventoryCommands=").append(mockInventoryCommands);
        sb.append('}');
        return sb.toString();
    }

    public void stop() {
        this.running = false;
    }
}