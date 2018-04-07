package cyan.svc.nazgulexample.resources;

import com.alibaba.fastjson.JSON;
import com.codahale.metrics.annotation.Counted;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import com.google.common.base.Optional;
import cyan.nazgul.dropwizard.resources.DbResource;
import cyan.svc.EntityOutput;
import cyan.svc.nazgulexample.Configuration;
import cyan.svc.nazgulexample.contract.ErrCode;
import cyan.svc.nazgulexample.entities.Person;
import cyan.svc.nazgulexample.entities.QPerson;
import cyan.svc.nazgulexample.mappers.PersonMapper;
import cyan.svc.nazgulexample.views.PersonView;
import cyan.util.FileOperation;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import io.dropwizard.jersey.caching.CacheControl;
import io.dropwizard.setup.Environment;
import io.swagger.annotations.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Resource Definition Example
 * <p>
 * 演示JAX-RS注解
 * 演示Swagger注解
 * <p>
 * 演示QueryDSL的使用
 * 演示FreemarkView的使用
 * 演示MyBatis的使用
 * <p>
 *
 * @HeaderParamenter输入
 * @PathParameter输入
 * @QueryParameter输入
 * @CookieParam 输入
 * <p>
 * APPLICATION_JSON输入
 * MULTIPART_FORM_DATA输入
 * <p>
 * Context注入
 * UriInfo注入
 * <p>
 * Response类型返回
 * 200
 * 302 重定向
 * EntityOutput类型返回
 * 其他实体类型返回
 * <p>
 * 演示 @Timed
 * 演示 @Metered
 * 演示 @ExceptionMetered
 * Created by DreamInSun on 2016/7/22.
 */
@Api(value = "/person 演示资源", description = "Person Management")
@Path(value = "/person")
@Produces(MediaType.TEXT_HTML)
@Counted
@Timed
@Metered(name = "PersonResource")
public class PersonResource extends DbResource<Configuration> {

    /*========== Properties =========*/

    /*========== Constructor =========*/
    public PersonResource(Configuration config, Environment env) {
        super(config, env);
    }

    @Override
    public int initialize(Configuration configuration, Environment env) {
        super.initialize(configuration, env);
        return 0;
    }


    /*========= 01 SimpleDemo ==========*/
    @GET
    @Path("/test/{id}")
    @CacheControl(maxAge = 6, maxAgeUnit = TimeUnit.HOURS)
    public EntityOutput getTest(@PathParam("id") String id) {
        return EntityOutput.getInstance(ErrCode.SUCCESS);
    }

    @ApiOperation(value = "简单的服务演示",
            notes = "在本资源模块相对路径下的一个资源，使用GET方式获取。</br>" +
                    "资源路径使用 @Path(\"/_greeting\")注解，约定若是一个动词，使用下划线开头，若是rest则使用小写驼峰</br>" +
                    "@Produces(MediaType.APPLICATION_JSON)表示使用Json编码格式返回。</br>" +
                    "请求参数可以使用@QueryParam/@HeaderParam/@PathParam的标准JAX-RS注解的方式注入。</br>" +
                    "参数可以使用Optional<Integer>，在路径匹配不到的时候不填。也可以使用@DefaultValue添加匹配不到时的默认值。",
            response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功返回"),
            @ApiResponse(code = 400, message = "No Name Provided")
    })
    @GET
    @Path("/_greeting")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getSuperAdmin(
            @ApiParam(value = "你的名字", required = true, example = "Petter") @DefaultValue("Stranger") @QueryParam("name") Optional<String> name,
            @Context UriInfo uriInfo,
            @Context SqlSessionFactory sqlSessionFactory
    ) {
        Person person = new Person();
        person.setName(name.get());
        return person;
    }

    /*========== REST API =========*/
    @ApiOperation(value = "QueryDSL 使用样例。",
            notes = "用户ID为整数",
            response = String.class,
            position = 0)
    @GET
    @Path("/pid/{pid}")
    public PersonView getPerson(
            @ApiParam(value = "Person ID", required = true, example = "2") @PathParam("pid") String personId,
            @Context EntityManager entityManager
    ) {

        Person person1 = this.getEntityManager().find(Person.class, 2);
        this.getLogger().info(JSON.toJSONString(person1));

        QPerson qPerson = QPerson.person;
        List<String> persons = this.getQueryFactory().select(qPerson.name).from(qPerson).groupBy(qPerson.name).fetch();
        this.getLogger().info(JSON.toJSONString(persons));
        /*===== Query =====*/
        Person person = this.getQueryFactory().selectFrom(qPerson).where(qPerson.personId.eq(personId)).fetchFirst();
        return new PersonView(person);
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
            @ApiResponse(code = ErrCode.ROUTE_MAPPING_PERSISTENCE_ERROR, message = "写入数据库错误"),
    })
    @GET
    @Path("/image/{imageName}")
    @Produces("image/*")
    public Response getImage(
            @ApiParam(value = "源IP", required = true, example = "10.1.0.1") @PathParam("imageName") String imageName,
            @Context ServletContext application) {
        this.getLogger().info("getImage:\t" + imageName);
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

    /*========= File ==========*/
    @ApiOperation(value = "RSQL的查询演示样例",
            notes = "@see <a>https://github.com/jirutka/rsql-parser</br>" +
                    "@see http://www.cl.cam.ac.uk/~mgk25/iso-14977.pdf</br>" +
                    "示例：</br>" +
                    "<ul><li>id==1</li>" +
                    "<li>age>=10 and age < 20</li>" +
                    "<li>lastName=in=(Chow,King)</li></ul>",
            response = Response.class,
            position = 2)
    @ApiResponses(value = {
            @ApiResponse(code = ErrCode.SUCCESS, message = "操作成功", response = EntityOutput.class),
            @ApiResponse(code = ErrCode.ROUTE_MAPPING_SOURCE_IP_ILLEGAL, message = "源IP格式错误"),
            @ApiResponse(code = ErrCode.ROUTE_MAPPING_SOURCE_PORT_ILLEGAL, message = "源端口格式错误"),
            @ApiResponse(code = ErrCode.ROUTE_MAPPING_TARGET_HOST_ILLEGAL, message = "目标主机格式错误"),
            @ApiResponse(code = ErrCode.ROUTE_MAPPING_TARGET_PORT_ILLEGAL, message = "目标端口格式错误"),
            @ApiResponse(code = ErrCode.ROUTE_MAPPING_PERSISTENCE_ERROR, message = "写入数据库错误"),
    })
    @GET
    @Path("/entity/{clz}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> query(
            @ApiParam(value = "源IP", required = true, example = "id==1") @QueryParam("clz") String clz,
            @ApiParam(value = "RSQL查询语句,参看ISO-14977标准", required = true, example = "id==1") @QueryParam("search") String search,
            @Context ServletContext application) {
        EntityManager entityMngr = this.getEntityManager();
        // Create the JPA Visitor
        RSQLVisitor<CriteriaQuery<Person>, EntityManager> visitor = new JpaCriteriaQueryVisitor<>();
        // Parse a RSQL into a Node
        Node rootNode = new RSQLParser().parse(search);
        // Visit the node to retrieve CriteriaQuery
        CriteriaQuery<Person> query = rootNode.accept(visitor, entityMngr);

        // Do all sort of operations you want with the criteria query
        //query.orderBy("id DESC");
        // Execute and get results
        List<Person> persons = entityMngr.createQuery(query).getResultList();
        return persons;
    }

    /*========= Upload File ==========*/
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
        /*==== Save Path ====*/
        String saveDir = "D:\\upload";
        String saveFileName = fileName + fileFullName.substring(fileFullName.indexOf("."), fileFullName.length());
        /*==== Save File ====*/
        String savePath = FileOperation.saveInputSteam(inputStream, saveDir, saveFileName);
        /*==== Return ====*/
        return Response.ok().entity(savePath).build();
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

    /*========== REST API =========*/
    @ApiOperation(value = "Mybatis的调用演示",
            notes = "用户ID为整数",
            response = String.class,
            position = 0)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "No Name Provided")
    })
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntityOutput getUserByID(
            @ApiParam(value = "Person的逻辑主键，默认为ID", required = true, example = "1") @PathParam("id") Long id) {
        Person person = null;
        try (SqlSession session = this.getSqlSessionFactory().openSession()) {
            PersonMapper personMpr = session.getMapper(PersonMapper.class);
            person = personMpr.findOneById(id);
        }
        return EntityOutput.getInstance(ErrCode.SUCCESS, person);
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
    public EntityOutput createUser(
            @ApiParam(value = "person对象的JSON字符串") Person person) {
//        m_userDao.createUser(user.id, user.name);
        return EntityOutput.getInstance(ErrCode.SUCCESS, person);
    }
}
