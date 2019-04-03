package io.github.watertao.veigar.vsa.mgn.mapper.autogen;

import io.github.watertao.veigar.vsa.mgn.model.TAuthResource;
import io.github.watertao.veigar.vsa.mgn.model.TAuthResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component("io.github.watertao.veigar.vsa.mgn.mapper.autogen.TAuthResourceMapper")
public interface TAuthResourceMapper {
    long countByExample(TAuthResourceExample example);

    int deleteByExample(TAuthResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuthResource record);

    int insertSelective(TAuthResource record);

    List<TAuthResource> selectByExample(TAuthResourceExample example);

    TAuthResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuthResource record, @Param("example") TAuthResourceExample example);

    int updateByExample(@Param("record") TAuthResource record, @Param("example") TAuthResourceExample example);

    int updateByPrimaryKeySelective(TAuthResource record);

    int updateByPrimaryKey(TAuthResource record);
}