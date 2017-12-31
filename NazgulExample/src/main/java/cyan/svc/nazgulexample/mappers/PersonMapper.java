package cyan.svc.nazgulexample.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import cyan.svc.nazgulexample.entities.Person;


public interface PersonMapper {
    int insert(@Param("person") Person person);

    int insertSelective(@Param("person") Person person);

    int insertList(@Param("persons") List<Person> persons);

    int update(@Param("person") Person person);

    Person findOneByPid(@Param("pid")Integer pid);

     Person findOneById(@Param("id")Integer id);


}
