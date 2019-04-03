package io.github.watertao.veigar.vsa.authentication.db;

import io.github.watertao.veigar.vsa.session.VsaAuthenticationObject;
import io.github.watertao.veigar.auth.filter.AuthenticationFilter;
import io.github.watertao.veigar.core.exception.BadRequestException;
import io.github.watertao.veigar.core.exception.NotFoundException;
import io.github.watertao.veigar.core.exception.UnauthenticatedException;
import io.github.watertao.veigar.session.spi.AuthenticationObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Request Json Sample:
 * -----------------------------------------------------------
 * {
 *     "employee_no": "011367",
 *     "password": "111111"
 * }
 * -----------------------------------------------------------
 *
 */
public class VsaDbAuthenticationFilter extends AuthenticationFilter {

  @Autowired
  private AuthenticationCstmMapper authenticationCstmMapper;

  @Override
  protected AuthenticationObject authenticate(Object requestBody) throws NotFoundException {

    AuthenticationRequest authenticationRequest = (AuthenticationRequest) requestBody;

    if (StringUtils.isEmpty(authenticationRequest.getEmployee_no()) ||
        StringUtils.isEmpty(authenticationRequest.getPassword())) {
      throw new BadRequestException();
    }

    Map user = authenticationCstmMapper.selectUserByEmployeeNo(authenticationRequest.getEmployee_no());

    if (user == null) {
      throw new UnauthenticatedException("用户不存在");
    }

    if (!StringUtils.equals((String) user.get("password"), DigestUtils.sha256Hex(authenticationRequest.getPassword()))) {
      throw new UnauthenticatedException("密码无效");
    }

    if (!"1".equals(user.get("status"))) {
      throw new UnauthenticatedException("用户被禁用");
    }

    List<Map> roles = authenticationCstmMapper.findActiveRolesByUserId((Integer) user.get("id"));
    List<Integer> roleIds = new ArrayList<>();
    List<String> roleCodes = new ArrayList<>();
    roles.forEach(role -> {
      roleIds.add((Integer) role.get("id"));
      roleCodes.add((String) role.get("code"));
    });
    List<Map> resources = null;
    if (roleIds.size() > 0) {
      resources = authenticationCstmMapper.findResourcesByRoleIds(roleIds);
    } else {
      resources = Collections.EMPTY_LIST;
    }
    VsaAuthenticationObject authObj = new VsaAuthenticationObject();
    authObj.setId((Integer) user.get("id"));
    authObj.setEmployee_no((String) user.get("employee_no"));
    authObj.setName((String) user.get("name"));
    authObj.setResources(resources);
    authObj.setRoles(roles);
    authObj.setAttributes(roleCodes);
    VsaAuthenticationObject.HtRsrvAuthenticationObjectDepartment department =
      new VsaAuthenticationObject.HtRsrvAuthenticationObjectDepartment();
    department.setId((Integer) user.get("dep_id"));
    department.setCode((String) user.get("dep_code"));
    department.setName((String) user.get("dep_name"));
    department.setLevel((Integer) user.get("dep_level"));
    authObj.setDepartment(department);

    return authObj;
  }

  @Override
  protected Class getReqBindingClass() {
    return AuthenticationRequest.class;
  }


  public static class AuthenticationRequest {
    private String employee_no;
    private String password;

    public String getEmployee_no() {
      return employee_no;
    }

    public void setEmployee_no(String employee_no) {
      this.employee_no = employee_no;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }

}
