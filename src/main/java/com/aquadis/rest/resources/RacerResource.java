package com.aquadis.rest.resources;

import com.aquadis.models.Racer;
import com.aquadis.service.RepositoryService;
import com.aquadis.service.impl.RepositoryServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Lorenzo
 */
@Path("/racers")
public class RacerResource {
    private RepositoryService service;

    public RacerResource() {
        service = RepositoryServiceImpl.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Racer> getAllRacers(){
        return service.getallRacers();
    }
}
