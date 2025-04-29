package som.make.mock.server.web.system.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sys_user")
public class SysUser {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Comment("用户id")
    @Column(length = 64)
    private String userId;
    @Column(length = 32)
    @Comment("用户名")
    private String userName;
    @Column(length = 32)
    @Comment("登录名")
    private String loginName;
    @Column(length = 32)
    @Comment("昵称")
    private String nickname;
    @Column(length = 256)
    @Comment("密码")
    private String password;
    @Column(length = 64)
    @Comment("邮箱")
    private String email;
    @Column(length = 36)
    @Comment("手机号")
    private String phoneNumber;
    @Column()
    @Comment("性别，1：男，2：女")
    private Integer gender;
    @Column(length = 128)
    @Comment("头像地址")
    private String avatar;
    @Column()
    @Comment("用户出生日期")
    private Date birthDate;
    @Column()
    @Comment("登录时间")
    private LocalDateTime lastLoginTime;
    @Column()
    @Comment("登录ip")
    private String lastLoginIp;
    @Column(length = 2)
    @Comment("状态")
    private String status;
    @Column
    @Comment("创建时间")
    private LocalDateTime createTime;
    @Column(length = 64)
    @Comment("创建人")
    private String createUser;
    @Column
    @Comment("修改时间")
    private LocalDateTime updateTime;
    @Column(length = 64)
    @Comment("修改人")
    private String updateUser;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<SysRole> sysRoleList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public List<SysRole> getSysRoleList() {
        return sysRoleList;
    }

    public void setSysRoleList(List<SysRole> sysRoleList) {
        this.sysRoleList = sysRoleList;
    }
}
