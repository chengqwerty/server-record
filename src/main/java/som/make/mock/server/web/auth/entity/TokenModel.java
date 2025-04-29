package som.make.mock.server.web.auth.entity;

import java.time.LocalDateTime;
import java.util.Set;

public class TokenModel {

    private String token;
    private String refreshToken;
    private String loginName;
    private UserInfo user;
    private Set<String> roles;
    private Set<String> authorities;
    private LocalDateTime issueTime;

    public TokenModel() {
    }

    public TokenModel(String token, String refreshToken, String loginName, UserInfo user, Set<String> roles, Set<String> authorities) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.loginName = loginName;
        this.user = user;
        this.roles = roles;
        this.authorities = authorities;
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public LocalDateTime getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(LocalDateTime issueTime) {
        this.issueTime = issueTime;
    }

}
