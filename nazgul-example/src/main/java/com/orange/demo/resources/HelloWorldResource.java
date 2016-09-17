package com.orange.demo.resources;

/**
 * Created by DreamInSun on 2016/6/30.
 */

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.orange.demo.NazgulConfiguration;
import com.orange.demo.entities.Saying;
import com.google.common.base.Optional;
import cyan.nazgul.dropwizard.resources.BaseResource;
import cyan.svc.EntityOutput;
import io.dropwizard.jersey.caching.CacheControl;
import io.dropwizard.setup.Environment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Api(value = "/hello", description = "Greeting API", position = 1)
@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
@Timed
@Metered
public class HelloWorldResource extends BaseResource<NazgulConfiguration> {
    private final static Logger g_logger = LoggerFactory.getLogger(HelloWorldResource.class);

    /*========== Properties ==========*/
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    /*========== Constructor ==========*/
    public HelloWorldResource(NazgulConfiguration config, Environment environment) {
        super(config, environment);
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


    /*========= File ==========*/
    @ApiOperation(
            value = "获取图片文件",
            notes = "获取/assets/images文件夹下的image文件")
    @GET
    @Path("/image/{imageName}")
    @Produces("image/*")
    public Response getImage(@PathParam("imageName") String imageName,
                             @Context ServletContext application) {
        g_logger.info("getImage:\t" + imageName);
        InputStream fileStream = this.getResourceAsStream("assets/images/" + imageName);
        File file = this.getResource("assets/images/" + imageName);

        /*===== Exception Response ======*/
        if (null == fileStream) {
            throw new WebApplicationException(404);
        }
        /*===== Response ======*/
        String contentType = new MimetypesFileTypeMap().getContentType(imageName);
        return Response.ok(file, contentType).header("", "").build();
    }
}
