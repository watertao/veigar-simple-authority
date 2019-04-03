package io.github.watertao.veigar.vsa.session;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("io.github.watertao.veigar.vsa.session.SecurityCstmMapper")
@Mapper
public interface SecurityCstmMapper {

  List<Map> findActiveRolesByResourceId(
    @Param("resourceId") Integer resourceId
  );

  List<Map> findFullResources();

}
