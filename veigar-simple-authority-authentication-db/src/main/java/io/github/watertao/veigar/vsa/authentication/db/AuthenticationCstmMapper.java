package io.github.watertao.veigar.vsa.authentication.db;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("AuthenticationCstmMapper")
@Mapper
public interface AuthenticationCstmMapper {
  
  Map selectUserByEmployeeNo(
    @Param("employeeNo") String employeeNo
  );

  List<Map> findActiveRolesByUserId(Integer userId);


  List<Map> findResourcesByRoleIds(List<Integer> roleIds);

  Integer updateUserPassword(
    @Param("userId") Integer userId,
    @Param("originPassword") String originPassword,
    @Param("newPassword") String newPassword
  );

}
