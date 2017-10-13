package cyan.svc.nazgul.example.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * JDBI demonstration: DAO
 * Created by DreamInSun on 2016/7/21.
 */
public interface UserDAO {

    @SqlUpdate("CREATE TABLE user ( id INT PRIMARY KEY, name VARCHAR(100) ) IF NOT EXISTS")
    void createSomethingTable();

    @SqlUpdate("INSERT INTO user (id, name) VALUES (:id, :name)")
    void createUser(@Bind("id") int id, @Bind("name") String name);

    @SqlQuery("SELECT name FROM user WHERE id = :id")
    String findNameById(@Bind("id") int id);


}

