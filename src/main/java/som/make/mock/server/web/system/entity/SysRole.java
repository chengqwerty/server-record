package som.make.mock.server.web.system.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sys_role")
public class SysRole {

    @Id
    @Column(length = 64)
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Comment("主键")
    private String roleId;
    @Column(length = 32)
    @Comment("角色编码")
    private String roleCode;
    @Column(length = 64)
    @Comment("角色名称")
    private String roleName;
    @Column(length = 256)
    @Comment("角色描述")
    private String roleDescription;
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_role_perm", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "perm_id")})
    public List<SysPerm> sysPermList;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
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

    public List<SysPerm> getSysPermList() {
        return sysPermList;
    }

    public void setSysPermList(List<SysPerm> sysPermList) {
        this.sysPermList = sysPermList;
    }
}
