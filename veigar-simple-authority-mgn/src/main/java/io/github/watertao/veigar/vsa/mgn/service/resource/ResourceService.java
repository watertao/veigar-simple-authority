package io.github.watertao.veigar.vsa.mgn.service.resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.github.watertao.veigar.vsa.mgn.mapper.autogen.TAuthResourceMapper;
import io.github.watertao.veigar.vsa.mgn.model.TAuthResource;
import io.github.watertao.veigar.vsa.mgn.model.TAuthResourceExample;
import io.github.watertao.veigar.vsa.mgn.service.AbstractFilterParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceService {

  private static final Logger logger = LoggerFactory.getLogger(ResourceService.class);

  @Autowired
  private TAuthResourceMapper resourceMapper;


  public PageInfo<TAuthResource> findResources(ResourceFilterParam filterParam) {

    TAuthResourceExample resourceExample = new TAuthResourceExample();
    if(filterParam.makeOrderClause() != null) {
      resourceExample.setOrderByClause(filterParam.makeOrderClause());
    }

    if (!filterParam.isInfiniteCount()) {
      PageHelper.offsetPage(filterParam.getLastCursor(), filterParam.getCount());
    }

    List<TAuthResource> resources = resourceMapper.selectByExample(resourceExample);
    return new PageInfo<>(resources);

  }


  public static class ResourceFilterParam extends AbstractFilterParam {

    @Override
    protected String getRealSortField(String sortFieldInQueryParam) {
      if ("verb".equals(sortFieldInQueryParam)) {
        return "verb";
      } else if ("uri_pattern".equals(sortFieldInQueryParam)) {
        return "uri_pattern";
      }
      return null;
    }

  }

}
