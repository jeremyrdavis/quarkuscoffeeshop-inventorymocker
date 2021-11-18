package io.quarkuscoffeeshop.inventorymocker.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/api")
public class RESTService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RESTService.class);

    @Inject
    InventoryMockerService inventoryMockerService;

    @GET
    @Path("/start")
    public Response startMocking() {

        LOGGER.debug("starting mocking");
        inventoryMockerService.beginMocking();
        LOGGER.debug("InventoryMockerService is running: {}", inventoryMockerService.isRunning());
        return Response.ok().build();
    }
}
