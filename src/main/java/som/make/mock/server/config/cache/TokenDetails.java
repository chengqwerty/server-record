package som.make.mock.server.config.cache;

import som.make.mock.server.core.security.Authentication;
import som.make.mock.server.web.system.entity.SysRole;
import som.make.mock.server.web.system.entity.SysUser;

import java.time.LocalDateTime;


public class TokenDetails {

    private String token;
    private String refreshToken;
    // token类型，0：普通。
    private Integer type;
    private Authentication<SysUser, SysRole> authentication;
    private LocalDateTime issueTime;

    public TokenDetails(String token, String refreshToken, Integer type, Authentication<SysUser, SysRole> authentication) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.type = type;
        this.authentication = authentication;
        this.issueTime = LocalDateTime.now();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Authentication<SysUser, SysRole> getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication<SysUser, SysRole> authentication) {
        this.authentication = authentication;
    }

    public LocalDateTime getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(LocalDateTime issueTime) {
        this.issueTime = issueTime;
    }
}
