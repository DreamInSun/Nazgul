package cyan.svc.nazgulexample.resources;

import com.codahale.metrics.annotation.Counted;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import cyan.nazgul.dropwizard.auth.jwt.JwtUser;
import cyan.nazgul.dropwizard.resources.DbResource;
import cyan.svc.nazgulexample.Configuration;
import io.dropwizard.auth.Auth;
import io.dropwizard.setup.Environment;
import io.swagger.annotations.*;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.Map;

/**
 * 测试类
 * 使用 JWT 让你的 RESTful API 更安全
 */
@SwaggerDefinition(
        securityDefinition = @SecurityDefinition(
                apiKeyAuthDefintions = {
                        @ApiKeyAuthDefinition(key = "JWT", description = "Authorization: Bearer <apiKey>", name = "Authorization", in = io.swagger.annotations.ApiKeyAuthDefinition.ApiKeyLocation.HEADER)})
)
@Api(value = "/jwt 演示资源", description = "Person Management")
@Path(value = "/jwt")
@Produces(MediaType.APPLICATION_JSON)
@Counted
@Timed
@Metered(name = "SecuredResource")
public class SecuredResource extends DbResource<Configuration> {

    /*========== Properties ==========*/
    private final byte[] m_tokenSecret;

    /*========== Constructor ==========*/
    public SecuredResource(Configuration config, Environment env) {
        super(config, env);
        this.m_tokenSecret = config.getAuthConfig().convertJwtTokenSecret();
    }

    /*========== API ==========*/
    @ApiOperation(value = "生成过期的Token")
    @GET
    @Path("/generateExpiredToken")
    public Map<String, String> generateExpiredToken() {
        final JwtClaims claims = new JwtClaims();
        claims.setExpirationTimeMinutesInTheFuture(-20);
        claims.setSubject("good-guy");

        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        jws.setKey(new HmacKey(m_tokenSecret));
        try {
            return Collections.singletonMap("token", jws.getCompactSerialization());
        } catch (JoseException e) {
            throw Throwables.propagate(e);
        }
    }

    /*==========  ==========*/
    @ApiOperation(value = "生成有效的Token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功返回"),
    })
    @GET
    @Path("/generateValidToken")
    public Map<String, String> generateValidToken() {
        final JwtClaims claims = new JwtClaims();
        claims.setSubject("good-guy");
        claims.setExpirationTimeMinutesInTheFuture(30);

        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        jws.setKey(new HmacKey(m_tokenSecret));

        try {
            return Collections.singletonMap("token", jws.getCompactSerialization());
        } catch (JoseException e) {
            throw Throwables.propagate(e);
        }
    }

    /*==========  ==========*/
    @ApiOperation(value = "校验Token",
            notes = "",
            response = Map.class,
            authorizations = {
                    @Authorization(value = "JWT"),
            })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功返回"),
    })
    @GET
    @Path("/checkToken")
    public Map<String, Object> checkToken(
            @ApiParam(hidden = true) @Auth JwtUser user
    ) {
        Map<String, Object> retMap = ImmutableMap.<String, Object>of("username", user.getName(), "id", (user).getId());
        return retMap;
    }


}