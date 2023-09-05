package som.make.mock.server.web.system.bean;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "sys_user")
public class SysUser {

    private String userId;

    private String userName;

    private String loginName;

    private String nickname;

    private String password;

    private String status;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Comment("用户id")
    @Column(length = 64)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(length = 32)
    @Comment("用户名")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(length = 32)
    @Comment("登录名")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(length = 32)
    @Comment("昵称")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(length = 64)
    @Comment("密码")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(length = 2)
    @Comment("状态")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
