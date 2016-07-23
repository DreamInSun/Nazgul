package com.orange.demo.resources;

import com.orange.demo.NazgulConfiguration;
import com.orange.demo.entities.Person;
import com.orange.demo.views.PersonView;
import com.orange.demo.dao.PersonDAO;
import cyan.nazgul.dropwizard.resources.JdbiResource;
import io.dropwizard.setup.Environment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by DreamInSun on 2016/7/22.
 */
@Api(value = "/people", description = "Person Information", position = 1)
@Path("/people/{id}")
@Produces(MediaType.TEXT_HTML)
public class PersonResource extends JdbiResource {
    /*========== Properties =========*/
    private PersonDAO m_personDao = null;

    /*========== Constructor =========*/
    public PersonResource(NazgulConfiguration config, Environment env) {
        super(config, env);
        //this.registerMapper( );
        m_personDao = (PersonDAO) this.getDAO(PersonDAO.class);
    }

    @ApiOperation(value = "根据ID获取Person",
            notes = "用户ID为整数",
            response = String.class,
            position = 0)
    @GET
    public PersonView getPerson(@PathParam("id") int id) {
        //TODO
        //ResultSet set = m_personDao.find(id);
        return new PersonView(new Person(id, "XXX"));
    }
}
