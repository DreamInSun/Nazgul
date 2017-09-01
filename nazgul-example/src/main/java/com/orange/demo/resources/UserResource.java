package com.orange.demo.resources;

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.orange.demo.NazgulConfiguration;
import com.orange.demo.entities.User;
import com.orange.demo.mappers.UsersMapper;
import cyan.nazgul.dropwizard.resources.MybatisResource;
import cyan.svc.EntityOutput;
import cyan.util.FileOperation;
import io.dropwizard.setup.Environment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

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


    @ApiOperation(value = "表单提交用户数据")
    @POST
    @Path("/add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed("SUPER_ADMIN")
    public Response addUser(
            @FormDataParam("name") String name,
            @FormDataParam("age") int age) {

        return Response.status(200)
                .entity("addUser is called, name : " + name + ", age : " + age)
                .build();

    }

    //    /*========= Upload File ==========*/
    @ApiOperation(value = "上传头像")
    @POST
    @Path("/upload/portrait/{fileName}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed("SUPER_ADMIN")
    public Response uploadPortrait(
            @PathParam("fileName") String fileName,
            @FormDataParam("file") InputStream inputStream,
            @FormDataParam("file") FormDataContentDisposition fileDisposition
    ) {

        /*==== Parse Input =====*/
        String fileFullName = fileDisposition.getFileName();
        /*==== Determin ====*/
        String saveDir = "D:\\upload";
        String saveFileName = fileName + fileFullName.substring(fileFullName.indexOf("."), fileFullName.length());
        /*==== Save File ====*/
        String savePath = FileOperation.saveInputSteam(inputStream, saveDir, saveFileName);
        /*==== Return ====*/
        return Response.ok().entity(savePath).build();
    }
}
