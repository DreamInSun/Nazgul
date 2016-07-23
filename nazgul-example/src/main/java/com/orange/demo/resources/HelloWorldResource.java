package com.orange.demo.resources;

/**
 * Created by DreamInSun on 2016/6/30.
 */

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.orange.demo.NazgulConfiguration;
import com.orange.demo.entities.Saying;
import com.google.common.base.Optional;
import cyan.svc.EntityOutput;
import io.dropwizard.jersey.caching.CacheControl;
import io.dropwizard.setup.Environment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Api(value = "/hello", description = "Greeting API", position = 1)
@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
@Timed
@Metered
public class HelloWorldResource {

    /*========== Properties ==========*/
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    /*========== Constructor ==========*/
    public HelloWorldResource(NazgulConfiguration config, Environment environment) {
        this.template = config.getTemplate();
        this.defaultName = config.getDefaultName();
        this.counter = new AtomicLong();
    }

    /*========== REST API ==========*/
    @ApiOperation(value = "Greeting by Name",
            notes = "Say hello to the people",
            response = Saying.class,
            position = 0)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "No Name Provided")
    })
    @GET
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }

    /*========= Test ==========*/
    @POST
    @Path("/test/{id}")
    public EntityOutput postTest(@PathParam("id") String id, @QueryParam("value") String value) {
        Saying saying = new Saying(counter.incrementAndGet(), value);
        return new EntityOutput(0, id, saying);
    }

    @GET
    @Path("/test/{id}")
    @CacheControl(maxAge = 6, maxAgeUnit = TimeUnit.HOURS)
    public EntityOutput getTest(@PathParam("id") String id) {
        return new EntityOutput(1, "Normal Test" + id, null);
    }
}
