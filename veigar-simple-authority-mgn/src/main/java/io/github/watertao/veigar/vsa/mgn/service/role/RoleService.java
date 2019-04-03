package io.github.watertao.veigar.vsa.mgn.service.role;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.github.watertao.veigar.vsa.mgn.mapper.autogen.TAuthResourceMapper;
import io.github.watertao.veigar.vsa.mgn.mapper.autogen.TAuthRoleMapper;
import io.github.watertao.veigar.vsa.mgn.mapper.autogen.TAuthRoleResourceRelMapper;
import io.github.watertao.veigar.vsa.mgn.mapper.autogen.TAuthUserRoleRelMapper;
import io.github.watertao.veigar.vsa.mgn.mapper.custom.RoleCstmMapper;
import io.github.watertao.veigar.vsa.mgn.model.*;
import io.github.watertao.veigar.core.exception.BadRequestException;
import io.github.watertao.veigar.core.exception.ConflictException;
import io.github.watertao.veigar.core.exception.NotFoundException;
import io.github.watertao.veigar.core.message.LocaleMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class RoleService {

    @Autowired
    private TAuthRoleMapper roleMapper;

    @Autowired
    private TAuthResourceMapper resourceMapper;

    @Autowired
    private TAuthRoleResourceRelMapper roleResourceRelMapper;

    @Autowired
    private TAuthUserRoleRelMapper userRoleRelMapper;

    @Autowired
    private RoleCstmMapper roleCstmMapper;

    @Autowired
    private LocaleMessage localeMessage;


    @Transactional
    public RoleResult createRole(RoleCreateParam roleCreateParam) {

        if (roleCreateParam.getLevel() < 0 || roleCreateParam.getLevel() > 2) {
            throw new BadRequestException("role level should be 0/1/2");
        }

        List<TAuthResource> resources = null;
        if (roleCreateParam.getResourceIds() != null && roleCreateParam.getResourceIds().size() > 0) {
            roleCreateParam.setResourceIds(roleCreateParam.getResourceIds().stream().distinct().collect(toList()));
            TAuthResourceExample resourceExample = new TAuthResourceExample();
            resourceExample.createCriteria().andIdIn(roleCreateParam.getResourceIds());
            resources = resourceMapper.selectByExample(resourceExample);
            if (resources.size() != roleCreateParam.getResourceIds().size()) {
                List<Integer> unexistResourceIds = new ArrayList<>();
                List<TAuthResource> finalResources = resources;
                roleCreateParam.getResourceIds().forEach(id -> {
                    for (TAuthResource resource : finalResources) {
                        if (resource.getId() == id) {
                            return;
                        }
                    }
                    unexistResourceIds.add(id);
                });
                throw new NotFoundException(localeMessage.bm(
                        "message.authority",
                        "resource.notExist",
                        new Object[]{StringUtils.join(unexistResourceIds)}
                ));
            }
        }


        // insert role
        TAuthRole role = new TAuthRole();
        role.setCode(roleCreateParam.getCode());
        role.setName(roleCreateParam.getName());
        role.setStatus("1");
        role.setLevel(roleCreateParam.getLevel());
        role.setRemark(roleCreateParam.getRemark());
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());

        try {
            roleMapper.insertSelective(role);
        } catch (DuplicateKeyException e) {
            throw new ConflictException(localeMessage.bm(
                    "message.authority",
                    "role.code.duplicate",
                    new Object[]{role.getCode()}
            ));
        }

        //  insert role-resource relationship
        if (resources != null && resources.size() > 0) {
            resources.forEach(resource -> {
                TAuthRoleResourceRelKey roleResourceRelKey = new TAuthRoleResourceRelKey();
                roleResourceRelKey.setRoleId(role.getId());
                roleResourceRelKey.setResourceId(resource.getId());
                roleResourceRelMapper.insert(roleResourceRelKey);
            });
        }

        // construct result
        RoleResult roleResult = makeRoleResult(role, resources);

        return roleResult;

    }


    @Transactional
    public RoleResult updateRole(RoleUpdateParam roleUpdateParam) {
        // check role existence
        TAuthRole role = roleMapper.selectByPrimaryKey(roleUpdateParam.getId());
        if (role == null) {
            throw new NotFoundException(localeMessage.bm(
                    "message.authority",
                    "role.notExist",
                    new Object[]{roleUpdateParam.getId()}
            ));
        }


        // check whether resource ids existence
        List<TAuthResource> resources = null;
        if (roleUpdateParam.getResourceIds() != null && roleUpdateParam.getResourceIds().size() > 0) {
            roleUpdateParam.setResourceIds(roleUpdateParam.getResourceIds().stream().distinct().collect(toList()));
            TAuthResourceExample resourceExample = new TAuthResourceExample();
            resourceExample.createCriteria().andIdIn(roleUpdateParam.getResourceIds());
            resources = resourceMapper.selectByExample(resourceExample);
            if (resources.size() != roleUpdateParam.getResourceIds().size()) {
                List<Integer> unexistResourceIds = new ArrayList<>();
                List<TAuthResource> finalResources = resources;
                roleUpdateParam.getResourceIds().forEach(id -> {
                    for (TAuthResource resource : finalResources) {
                        if (resource.getId() == id) {
                            return;
                        }
                    }
                    unexistResourceIds.add(id);
                });
                throw new NotFoundException(localeMessage.bm(
                        "message.authority",
                        "resource.notExist",
                        new Object[]{StringUtils.join(unexistResourceIds)}
                ));
            }
        }

        // update role
        role.setCode(roleUpdateParam.getCode());
        role.setName(roleUpdateParam.getName());
        role.setLevel(roleUpdateParam.getLevel());
        role.setStatus(roleUpdateParam.getStatus());
        role.setRemark(roleUpdateParam.getRemark());
        role.setUpdateTime(new Date());
        try {
            roleMapper.updateByPrimaryKey(role);
        } catch (DuplicateKeyException e) {
            throw new ConflictException(localeMessage.bm(
                    "message.authority",
                    "role.code.duplicate",
                    new Object[]{role.getCode()}
            ));
        }

        // update role-resource relationship
        TAuthRoleResourceRelExample roleResourceRelExample = new TAuthRoleResourceRelExample();
        roleResourceRelExample.createCriteria().andRoleIdEqualTo(role.getId());
        roleResourceRelMapper.deleteByExample(roleResourceRelExample);
        if (resources != null && resources.size() > 0) {
            resources.forEach(resource -> {
                TAuthRoleResourceRelKey roleResourceRelKey = new TAuthRoleResourceRelKey();
                roleResourceRelKey.setResourceId(resource.getId());
                roleResourceRelKey.setRoleId(role.getId());
                ;
                roleResourceRelMapper.insert(roleResourceRelKey);
            });
        }

        // construct result
        RoleResult roleResult = makeRoleResult(role, resources);

        return roleResult;
    }


    @Transactional
    public void deleteRole(Integer id) {

        // check role existence
        TAuthRole role = roleMapper.selectByPrimaryKey(id);
        if (role == null) {
            throw new NotFoundException(localeMessage.bm(
                    "message.authority",
                    "role.notExist",
                    new Object[]{id}
            ));
        }

        roleMapper.deleteByPrimaryKey(id);

        TAuthRoleResourceRelExample roleResourceRelExample = new TAuthRoleResourceRelExample();
        roleResourceRelExample.createCriteria().andRoleIdEqualTo(id);
        roleResourceRelMapper.deleteByExample(roleResourceRelExample);

        TAuthUserRoleRelExample userRoleRelExample = new TAuthUserRoleRelExample();
        userRoleRelExample.createCriteria().andRoleIdEqualTo(id);
        userRoleRelMapper.deleteByExample(userRoleRelExample);

    }


    public RoleResult selectRoleById(Integer id) {

        TAuthRole role = roleMapper.selectByPrimaryKey(id);
        if (role == null) {
            throw new NotFoundException(localeMessage.bm(
                    "message.authority",
                    "role.notExist",
                    new Object[]{id}
            ));
        }

        List<TAuthResource> resources = null;
        TAuthRoleResourceRelExample roleResourceRelExample = new TAuthRoleResourceRelExample();
        roleResourceRelExample.createCriteria().andRoleIdEqualTo(id);
        List<TAuthRoleResourceRelKey> roleResourceRels = roleResourceRelMapper.selectByExample(roleResourceRelExample);
        if (roleResourceRels.size() > 0) {
            List<Integer> resourceIds = new ArrayList<>();
            roleResourceRels.forEach(item -> {
                resourceIds.add(item.getResourceId());
            });
            TAuthResourceExample resourceExample = new TAuthResourceExample();
            resourceExample.createCriteria().andIdIn(resourceIds);
            resources = resourceMapper.selectByExample(resourceExample);
        }

        return makeRoleResult(role, resources);

    }


    public PageInfo<RoleResult> findRoles(RoleFilterParam roleFilterParam) {

        TAuthRoleExample roleExample = new TAuthRoleExample();
        TAuthRoleExample.Criteria criteria = roleExample.createCriteria();
        if (!StringUtils.isEmpty(roleFilterParam.getCode())) {
            criteria.andCodeLike("%" + roleFilterParam.getCode() + "%");
        }
        if (!StringUtils.isEmpty(roleFilterParam.getName())) {
            criteria.andNameLike("%" + roleFilterParam.getName() + "%");
        }
        if (!StringUtils.isEmpty(roleFilterParam.getStatus())) {
            criteria.andStatusEqualTo(roleFilterParam.getStatus());
        }
        if (roleFilterParam.getLevel() != null) {
            criteria.andLevelEqualTo(roleFilterParam.getLevel());
        }

        if (!roleFilterParam.isInfiniteCount()) {
            PageHelper.offsetPage(roleFilterParam.getLastCursor(), roleFilterParam.getCount());
        }

        List<TAuthRole> roles = roleMapper.selectByExample(roleExample);
        if (roles.size() == 0) {
            return new PageInfo<>(Collections.EMPTY_LIST);
        }

        // retrieve resources
        Map<Integer, List<TAuthResource>> roleResourceMap = new HashMap<>();
        List<Integer> roleIds = new ArrayList<>();
        roles.forEach(role -> {
            roleIds.add(role.getId());
        });
        List<Map> roleResources = roleCstmMapper.findResourcesByRoleIds(roleIds);
        roleResources.forEach(item -> {
            Integer roleId = (Integer) item.get("role_id");
            Integer resourceId = (Integer) item.get("resource_id");
            String verb = (String) item.get("verb");
            String uriPattern = (String) item.get("uri_pattern");
            String name = (String) item.get("name");
            String remark = (String) item.get("remark");
            if (roleResourceMap.get(roleId) == null) {
                roleResourceMap.put(roleId, new ArrayList<>());
            }
            TAuthResource resource = new TAuthResource();
            resource.setId(resourceId);
            resource.setVerb(verb);
            resource.setUriPattern(uriPattern);
            resource.setName(name);
            resource.setRemark(remark);
            roleResourceMap.get(roleId).add(resource);
        });

        // construct results
        List<RoleResult> roleResults = new ArrayList<>();
        roles.forEach(role -> {
            roleResults.add(makeRoleResult(role, roleResourceMap.get(role.getId())));
        });
//    System.out.println(new PageInfo<>(roleResults).getTotal()+":::");
        PageInfo pageInfo = new PageInfo(roles);
        pageInfo.setList(roleResults);
        return pageInfo;
    }


    private RoleResult makeRoleResult(TAuthRole role, List<TAuthResource> resources) {
        RoleResult roleResult = new RoleResult();
        roleResult.setId(role.getId());
        roleResult.setCode(role.getCode());
        roleResult.setLevel(role.getLevel());
        roleResult.setName(role.getName());
        roleResult.setRemark(role.getRemark());
        roleResult.setStatus(role.getStatus());

        if (resources != null && resources.size() > 0) {
            List<RoleResult.RoleResourceResult> resourceResults = new ArrayList<>();
            resources.forEach(resource -> {
                RoleResult.RoleResourceResult resourceResult = new RoleResult.RoleResourceResult();
                resourceResult.setId(resource.getId());
                resourceResult.setName(resource.getName());
                resourceResult.setRemark(resource.getRemark());
                resourceResult.setUriPattern(resource.getUriPattern());
                resourceResult.setVerb(resource.getVerb());
                resourceResults.add(resourceResult);
            });
            roleResult.setResources(resourceResults);
        }

        return roleResult;

    }


    private void checkReservedRoleCode(String code) {
        if ("ROLE_SUPERVISOR".equals(code)) {
            throw new BadRequestException("role code should not be ROLE_SUPERVISOR");
        }
    }


}
