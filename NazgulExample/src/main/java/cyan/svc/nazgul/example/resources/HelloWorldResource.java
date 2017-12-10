package cyan.svc.nazgul.example.resources;

/**
 * An demostration reource
 * Created by DreamInSun on 2016/6/30.
 */

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import cyan.svc.nazgul.example.Configuration;
import cyan.svc.nazgul.example.contract.ErrCode;
import cyan.svc.nazgul.example.entities.Saying;
import com.google.common.base.Optional;
import cyan.nazgul.dropwizard.resources.BaseResource;
import cyan.nazgul.dropwizard.resources.IResource;
import cyan.svc.EntityOutput;
import io.dropwizard.jersey.caching.CacheControl;
import io.dropwizard.setup.Environment;
import io.swagger.annotations.*;
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
public class HelloWorldResource extends BaseResource<Configuration> implements IResource<Configuration> {
    private final static Logger g_logger = LoggerFactory.getLogger(HelloWorldResource.class);

    /*========== Properties ==========*/
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    /*========== Constructor ==========*/
    public HelloWorldResource(Configuration config, Environment environment) {
        super(config, environment);
        this.template = config.getTemplate();
        this.defaultName = config.getDefaultName();
        this.counter = new AtomicLong();
    }

    @Override
    public int initialize(Configuration configuration, Environment env) {
        super.initialize(configuration, env);
        this.getLogger().info("HelloWorldResource initializing.");
        return 0;
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
        return EntityOutput.getInstance(ErrCode.SUCCESS, saying);
    }

    @GET
    @Path("/test/{id}")
    @CacheControl(maxAge = 6, maxAgeUnit = TimeUnit.HOURS)
    public EntityOutput getTest(@PathParam("id") String id) {
        return EntityOutput.getInstance(ErrCode.SUCCESS, null);
    }


    /*========= File ==========*/
    @ApiOperation(value = "获取图片文件",
            notes = "获取/assets/images文件夹下的image文件<br/>version >= 0.2.0",
            response = Response.class,
            position = 2)
    @ApiResponses(value = {
            @ApiResponse(code = ErrCode.SUCCESS, message = "操作成功", response = EntityOutput.class),
            @ApiResponse(code = ErrCode.ROUTE_MAPPING_SOURCE_IP_ILLEGAL, message = "源IP格式错误"),
            @ApiResponse(code = ErrCode.ROUTE_MAPPING_SOURCE_PORT_ILLEGAL, message = "源端口格式错误"),
            @ApiResponse(code = ErrCode.ROUTE_MAPPING_TARGET_HOST_ILLEGAL, message = "目标主机格式错误"),
            @ApiResponse(code = ErrCode.ROUTE_MAPPING_TARGET_PORT_ILLEGAL, message = "目标端口格式错误"),
            @ApiResponse(code = ErrCode.ROUTE_MAPPING_PERSISTANCE_ERROR, message = "写入数据库错误"),
    })
    @GET
    @Path("/image/{imageName}")
    @Produces("image/*")
    public Response getImage(
            @ApiParam(value = "源IP", required = true, example = "10.1.0.1") @PathParam("imageName") String imageName,
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
