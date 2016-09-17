package com.orange.demo.resources;

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.orange.demo.NazgulConfiguration;
import com.orange.demo.entities.User;
import com.orange.demo.mappers.UsersMapper;
import cyan.nazgul.dropwizard.resources.MybatisResource;
import cyan.svc.EntityOutput;
import io.dropwizard.setup.Environment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by DreamInSun on 2016/7/21.
 */
@Api(value = "/user", description = "用户管理", position = 1)
@Path("/user")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Timed
@Metered
public class UserResource extends MybatisResource<NazgulConfiguration> {

    /*========== Constructor =========*/
    public UserResource(NazgulConfiguration config, Environment env) {
        super(config, env);
    }

    /*========== REST API =========*/
    @ApiOperation(value = "根据ID获取用户名",
            notes = "用户ID为整数",
            response = String.class,
            position = 0)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "No Name Provided")
    })
    @GET
    @Path("/{id}")
    public EntityOutput getUserByID(@PathParam("id") int id) {
        User user = null;
        SqlSessionFactory factory = MybatisResource.g_sessionFactory;
        try (SqlSession session = this.getSqlSessionFactory().openSession()) {
            UsersMapper users = session.getMapper(UsersMapper.class);
            user = users.findById(id);
        }
        return new EntityOutput(0, null, user);
    }

    @ApiOperation(value = "创建用户",
            notes = "用户ID为整数,",
            response = EntityOutput.class,
            position = 1)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "No Name Provided")
    })
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EntityOutput createUser(User user) {
//        m_userDao.createUser(user.id, user.name);
        return new EntityOutput(0, "ok", null);
    }
}
