package io.github.watertao.veigar.vsa.mgn.api.resource.controller;

import com.github.pagehelper.PageInfo;
import io.github.watertao.veigar.vsa.mgn.api.resource.payload.ResourceRes;
import io.github.watertao.veigar.vsa.mgn.model.TAuthResource;
import io.github.watertao.veigar.vsa.mgn.service.resource.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth/resources")
public class ResourceController {

  private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

  @Autowired
  private ResourceService resourceService;


  @GetMapping()
  List<ResourceRes> findResources(
    @RequestParam(value = "last_cursor", required = false) Integer lastCursor,
    @RequestParam(value = "count", required = false) Integer count,
    @RequestParam(value = "infinite_count", required = false) boolean infiniteCount,
    @RequestParam(value = "sort_field", required = false) String sortField,
    @RequestParam(value = "sort_order", required = false) String sortOrder,
    HttpServletResponse response
  ) {

    ResourceService.ResourceFilterParam filterParam = new ResourceService.ResourceFilterParam();
    filterParam.setLastCursor(lastCursor);
    filterParam.setCount(count);
    filterParam.setInfiniteCount(infiniteCount);
    filterParam.setSortField(sortField);
    filterParam.setSortOrder(sortOrder);

    PageInfo<TAuthResource> resourcesPageInfo = resourceService.findResources(filterParam);

    List<ResourceRes> resources = new ArrayList<>();
    resourcesPageInfo.getList().forEach(item -> {
      ResourceRes resourceRes = new ResourceRes();
      resourceRes.setId(item.getId());
      resourceRes.setName(item.getName());
      resourceRes.setRemark(item.getRemark());
      resourceRes.setUri_pattern(item.getUriPattern());
      resourceRes.setVerb(item.getVerb());
      resources.add(resourceRes);
    });

    response.setHeader("X-Total-Count", String.valueOf(resourcesPageInfo.getTotal()));

    return resources;

  }


}
