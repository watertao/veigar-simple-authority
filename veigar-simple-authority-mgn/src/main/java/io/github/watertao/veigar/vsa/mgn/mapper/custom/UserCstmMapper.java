package io.github.watertao.veigar.vsa.mgn.mapper.custom;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("io.github.watertao.veigar.vsa.mgn.mapper.custom.UserCstmMapper")
@Mapper
public interface UserCstmMapper {

  List<Map> findRolesByUserIds(List<Integer> userIds);


}
