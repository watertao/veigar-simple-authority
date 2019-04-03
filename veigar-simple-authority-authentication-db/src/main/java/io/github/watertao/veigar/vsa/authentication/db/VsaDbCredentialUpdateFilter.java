package io.github.watertao.veigar.vsa.authentication.db;

import io.github.watertao.veigar.vsa.session.VsaAuthenticationObject;
import io.github.watertao.veigar.auth.filter.CredentialUpdateFilter;
import io.github.watertao.veigar.core.exception.BadRequestException;
import io.github.watertao.veigar.core.exception.ConflictException;
import io.github.watertao.veigar.session.spi.AuthenticationObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Request Json Sample:
 * -----------------------------------------------------------
 * {
 *     "origin_password": "111111",
 *     "new_password": "888888"
 * }
 * -----------------------------------------------------------
 *
 */
public class VsaDbCredentialUpdateFilter extends CredentialUpdateFilter {

  @Autowired
  private AuthenticationCstmMapper authenticationCstmMapper;

  @Override
  protected void updateCredential(AuthenticationObject authObj, Object requestBody) {

    CredentialUpdateRequest credentialUpdateRequest = (CredentialUpdateRequest) requestBody;
    VsaAuthenticationObject vsaAuthenticationObject = (VsaAuthenticationObject) authObj;

    if (StringUtils.isEmpty(credentialUpdateRequest.getNew_password()) ||
        StringUtils.isEmpty(credentialUpdateRequest.getOrigin_password())) {
      throw new BadRequestException();
    }

    String originPasswordSHA256Hex = DigestUtils.sha256Hex(credentialUpdateRequest.getOrigin_password());
    String newPasswordSha256Hex = DigestUtils.sha256Hex(credentialUpdateRequest.getNew_password());

    int result = authenticationCstmMapper.updateUserPassword(
      vsaAuthenticationObject.getId(),
      originPasswordSHA256Hex,
      newPasswordSha256Hex
    );

    if (result <= 0) {
      throw new ConflictException("原密码错误");
    }
  }

  @Override
  protected Class getReqBindingClass() {
    return CredentialUpdateRequest.class;
  }


  public static class CredentialUpdateRequest {
    private String origin_password;
    private String new_password;

    public String getOrigin_password() {
      return origin_password;
    }

    public void setOrigin_password(String origin_password) {
      this.origin_password = origin_password;
    }

    public String getNew_password() {
      return new_password;
    }

    public void setNew_password(String new_password) {
      this.new_password = new_password;
    }
  }

}
