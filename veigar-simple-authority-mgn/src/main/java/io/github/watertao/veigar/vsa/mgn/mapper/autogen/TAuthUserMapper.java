package io.github.watertao.veigar.vsa.mgn.mapper.autogen;

import io.github.watertao.veigar.vsa.mgn.model.TAuthUser;
import io.github.watertao.veigar.vsa.mgn.model.TAuthUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component("io.github.watertao.veigar.vsa.mgn.mapper.autogen.TAuthUserMapper")
public interface TAuthUserMapper {
    long countByExample(TAuthUserExample example);

    int deleteByExample(TAuthUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuthUser record);

    int insertSelective(TAuthUser record);

    List<TAuthUser> selectByExample(TAuthUserExample example);

    TAuthUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuthUser record, @Param("example") TAuthUserExample example);

    int updateByExample(@Param("record") TAuthUser record, @Param("example") TAuthUserExample example);

    int updateByPrimaryKeySelective(TAuthUser record);

    int updateByPrimaryKey(TAuthUser record);
}