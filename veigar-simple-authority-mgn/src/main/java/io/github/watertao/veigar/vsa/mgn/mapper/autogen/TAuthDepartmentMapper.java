package io.github.watertao.veigar.vsa.mgn.mapper.autogen;

import io.github.watertao.veigar.vsa.mgn.model.TAuthDepartment;
import io.github.watertao.veigar.vsa.mgn.model.TAuthDepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component("io.github.watertao.veigar.vsa.mgn.mapper.autogen.TAuthDepartmentMapper")
public interface TAuthDepartmentMapper {
    long countByExample(TAuthDepartmentExample example);

    int deleteByExample(TAuthDepartmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuthDepartment record);

    int insertSelective(TAuthDepartment record);

    List<TAuthDepartment> selectByExample(TAuthDepartmentExample example);

    TAuthDepartment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuthDepartment record, @Param("example") TAuthDepartmentExample example);

    int updateByExample(@Param("record") TAuthDepartment record, @Param("example") TAuthDepartmentExample example);

    int updateByPrimaryKeySelective(TAuthDepartment record);

    int updateByPrimaryKey(TAuthDepartment record);
}