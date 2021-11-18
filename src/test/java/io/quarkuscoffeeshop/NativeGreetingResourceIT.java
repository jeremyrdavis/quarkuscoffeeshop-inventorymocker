package io.quarkuscoffeeshop;

import io.quarkus.test.junit.NativeImageTest;
import io.quarkuscoffeeshop.inventorymocker.infrastructure.StartMockerTest;

@NativeImageTest
public class NativeGreetingResourceIT extends StartMockerTest {

    // Execute the same tests but in native mode.
}