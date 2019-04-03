package io.github.watertao.veigar.vsa.mgn.mapper.autogen;

import io.github.watertao.veigar.vsa.mgn.model.TAuthUserRoleRelExample;
import io.github.watertao.veigar.vsa.mgn.model.TAuthUserRoleRelKey;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component("io.github.watertao.veigar.vsa.mgn.mapper.autogen.TAuthUserRoleRelMapper")
public interface TAuthUserRoleRelMapper {
    long countByExample(TAuthUserRoleRelExample example);

    int deleteByExample(TAuthUserRoleRelExample example);

    int deleteByPrimaryKey(TAuthUserRoleRelKey key);

    int insert(TAuthUserRoleRelKey record);

    int insertSelective(TAuthUserRoleRelKey record);

    List<TAuthUserRoleRelKey> selectByExample(TAuthUserRoleRelExample example);

    int updateByExampleSelective(@Param("record") TAuthUserRoleRelKey record, @Param("example") TAuthUserRoleRelExample example);

    int updateByExample(@Param("record") TAuthUserRoleRelKey record, @Param("example") TAuthUserRoleRelExample example);
}