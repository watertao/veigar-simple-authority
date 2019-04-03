package io.github.watertao.veigar.vsa.mgn.mapper.autogen;

import io.github.watertao.veigar.vsa.mgn.model.TAuthRoleResourceRelExample;
import io.github.watertao.veigar.vsa.mgn.model.TAuthRoleResourceRelKey;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component("io.github.watertao.veigar.vsa.mgn.mapper.autogen.TAuthRoleResourceRelMapper")
public interface TAuthRoleResourceRelMapper {
    long countByExample(TAuthRoleResourceRelExample example);

    int deleteByExample(TAuthRoleResourceRelExample example);

    int deleteByPrimaryKey(TAuthRoleResourceRelKey key);

    int insert(TAuthRoleResourceRelKey record);

    int insertSelective(TAuthRoleResourceRelKey record);

    List<TAuthRoleResourceRelKey> selectByExample(TAuthRoleResourceRelExample example);

    int updateByExampleSelective(@Param("record") TAuthRoleResourceRelKey record, @Param("example") TAuthRoleResourceRelExample example);

    int updateByExample(@Param("record") TAuthRoleResourceRelKey record, @Param("example") TAuthRoleResourceRelExample example);
}