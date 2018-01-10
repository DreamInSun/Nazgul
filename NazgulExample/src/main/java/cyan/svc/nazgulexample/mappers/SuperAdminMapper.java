package cyan.svc.nazgulexample.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import cyan.svc.nazgulexample.entities.admin.SuperAdmin;


public interface SuperAdminMapper {
    int insert(@Param("superAdmin") SuperAdmin superAdmin);

    int insertSelective(@Param("superAdmin") SuperAdmin superAdmin);

    int insertList(@Param("superAdmins") List<SuperAdmin> superAdmins);

    int update(@Param("superAdmin") SuperAdmin superAdmin);
}
