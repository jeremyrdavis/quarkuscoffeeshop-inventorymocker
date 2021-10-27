package io.quarkuscoffeeshop.inventorymocker.infrastructure;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.smallrye.reactive.messaging.connectors.InMemoryConnector;

import java.util.HashMap;
import java.util.Map;

public class KafkaTestResource implements QuarkusTestResourceLifecycleManager {

    @Override
    public Map<String, String> start() {
        Map<String, String> env = new HashMap<>();
        Map<String, String> inventoryIn = InMemoryConnector.switchOutgoingChannelsToInMemory("inventory-in");
        env.putAll(inventoryIn);
        return env;
    }

    @Override
    public void stop() {
        InMemoryConnector.clear();
    }
}
