package io.github.watertao.veigar.vsa.mgn.mapper.custom;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("io.github.watertao.veigar.vsa.mgn.mapper.custom.RoleCstmMapper")
@Mapper
public interface RoleCstmMapper {

  List<Map> findResourcesByRoleIds(List<Integer> roleIds);

}
