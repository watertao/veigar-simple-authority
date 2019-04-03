package io.github.watertao.veigar.vsa.mgn.mapper.autogen;

import io.github.watertao.veigar.vsa.mgn.model.TAuthRole;
import io.github.watertao.veigar.vsa.mgn.model.TAuthRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component("io.github.watertao.veigar.vsa.mgn.mapper.autogen.TAuthRoleMapper")
public interface TAuthRoleMapper {
    long countByExample(TAuthRoleExample example);

    int deleteByExample(TAuthRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuthRole record);

    int insertSelective(TAuthRole record);

    List<TAuthRole> selectByExample(TAuthRoleExample example);

    TAuthRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuthRole record, @Param("example") TAuthRoleExample example);

    int updateByExample(@Param("record") TAuthRole record, @Param("example") TAuthRoleExample example);

    int updateByPrimaryKeySelective(TAuthRole record);

    int updateByPrimaryKey(TAuthRole record);
}