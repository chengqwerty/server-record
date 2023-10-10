package som.make.mock.server.web.auth.entity;

import java.time.LocalDateTime;

public class TokenModel {

    private String token;
    private String refreshToken;
    private LocalDateTime issueTime;

    public TokenModel(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
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

    public LocalDateTime getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(LocalDateTime issueTime) {
        this.issueTime = issueTime;
    }
}
