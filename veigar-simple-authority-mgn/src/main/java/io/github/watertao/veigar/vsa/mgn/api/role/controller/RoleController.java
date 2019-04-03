package io.github.watertao.veigar.vsa.mgn.api.role.controller;

import com.github.pagehelper.PageInfo;
import io.github.watertao.veigar.vsa.mgn.api.role.payload.RoleCreateReq;
import io.github.watertao.veigar.vsa.mgn.api.role.payload.RoleRes;
import io.github.watertao.veigar.vsa.mgn.api.role.payload.RoleUpdateReq;
import io.github.watertao.veigar.vsa.mgn.service.role.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/auth/roles")
public class RoleController {

  @Autowired
  private RoleService roleService;


  @GetMapping("/{roleId}")
  public RoleRes getRole(
    @PathVariable(value = "roleId", required = true) Integer roleId
  ) {

    RoleResult roleResult = roleService.selectRoleById(roleId);

    return makeRoleResponse(roleResult);

  }


  @GetMapping
  public List<RoleRes> findRoles(
    @RequestParam(value = "code", required = false) String code,
    @RequestParam(value = "name", required = false) String name,
    @RequestParam(value = "status", required = false) String status,
    @RequestParam(value = "level", required = false) Integer level,
    @RequestParam(value = "last_cursor", required = false) Integer lastCursor,
    @RequestParam(value = "count", required = false) Integer count,
    @RequestParam(value = "infinite_count",required = false) boolean infiniteCount,
    HttpServletResponse response
  ) {

    RoleFilterParam roleFilterParam = new RoleFilterParam();
    roleFilterParam.setCode(code);
    roleFilterParam.setName(name);
    roleFilterParam.setLevel(level);
    roleFilterParam.setStatus(status);
    roleFilterParam.setCount(count);
    roleFilterParam.setLastCursor(lastCursor);
    roleFilterParam.setInfiniteCount(infiniteCount);
    PageInfo<RoleResult> pageInfo = roleService.findRoles(roleFilterParam);

    response.setHeader("X-Total-Count", String.valueOf(pageInfo.getTotal()));

    List<RoleRes> roleResList = new ArrayList<>();
    pageInfo.getList().forEach(roleResult -> roleResList.add(makeRoleResponse(roleResult)));

    return roleResList;

  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RoleRes createRole(
    @Valid @RequestBody RoleCreateReq roleCreateReq
  ) {

    RoleCreateParam roleCreateParam = new RoleCreateParam();
    roleCreateParam.setCode(roleCreateReq.getCode());
    roleCreateParam.setName(roleCreateReq.getName());
    roleCreateParam.setLevel(roleCreateReq.getLevel());
    roleCreateParam.setRemark(roleCreateReq.getRemark());
    roleCreateParam.setResourceIds(roleCreateReq.getResource_ids());

    RoleResult roleResult = roleService.createRole(roleCreateParam);

    return makeRoleResponse(roleResult);

  }


  @PutMapping("/{roleId}")
  public RoleRes updateRole(
    @PathVariable(value = "roleId", required = true) Integer roleId,
    @Valid @RequestBody RoleUpdateReq roleUpdateReq
  ) {

    RoleUpdateParam roleUpdateParam = new RoleUpdateParam();
    roleUpdateParam.setId(roleId);
    roleUpdateParam.setCode(roleUpdateReq.getCode());
    roleUpdateParam.setStatus(roleUpdateReq.getStatus());
    roleUpdateParam.setLevel(roleUpdateReq.getLevel());
    roleUpdateParam.setName(roleUpdateReq.getName());
    roleUpdateParam.setRemark(roleUpdateReq.getRemark());
    roleUpdateParam.setResourceIds(roleUpdateReq.getResource_ids());

    RoleResult roleResult = roleService.updateRole(roleUpdateParam);

    return makeRoleResponse(roleResult);

  }


  @DeleteMapping("/{roleId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteRole(
    @PathVariable(value = "roleId", required = true) Integer roleId
  ) {

    roleService.deleteRole(roleId);

  }


  private RoleRes makeRoleResponse(RoleResult roleResult) {

    RoleRes roleRes = new RoleRes();
    roleRes.setId(roleResult.getId());
    roleRes.setCode(roleResult.getCode());
    roleRes.setName(roleResult.getName());
    roleRes.setLevel(roleResult.getLevel());
    roleRes.setRemark(roleResult.getRemark());
    roleRes.setStatus(roleResult.getStatus());

    if (roleResult.getResources() != null && roleResult.getResources().size() > 0) {

      List<RoleRes.RoleResourceRes> roleResourceResList = new ArrayList<>();
      roleResult.getResources().forEach(resource -> {
        RoleRes.RoleResourceRes roleResourceRes = new RoleRes.RoleResourceRes();
        roleResourceRes.setId(resource.getId());
        roleResourceRes.setName(resource.getName());
        roleResourceRes.setUriPattern(resource.getUriPattern());
        roleResourceRes.setVerb(resource.getVerb());
        roleResourceRes.setRemark(resource.getRemark());
        roleResourceResList.add(roleResourceRes);
      });
      roleRes.setResources(roleResourceResList);

    } else {
      roleRes.setResources(Collections.EMPTY_LIST);
    }


    return roleRes;

  }

}
