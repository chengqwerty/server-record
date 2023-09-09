package som.make.mock.server.web.system.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sys_role_perm")
public class SysRolePerm {

    private String rolePermId;
    private String roleId;
    private String permId;
    private LocalDateTime createTime;
    private String createUser;
    private LocalDateTime updateTime;
    private String updateUser;

    private List<SysPerm> sysPerms;

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Comment("主键")
    public String getRolePermId() {
        return rolePermId;
    }

    public void setRolePermId(String rolePermId) {
        this.rolePermId = rolePermId;
    }

    @Column(length = 64)
    @Comment("roleId")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(length = 64)
    @Comment("permId")
    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }

    @Column
    @Comment("创建时间")
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Column(length = 64)
    @Comment("创建人")
    public String getCreateUser() {
        return createUser;
    }

    @Column
    @Comment("修改时间")
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Column(length = 64)
    @Comment("修改人")
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "permId", referencedColumnName = "permId")
    public List<SysPerm> getSysPerms() {
        return sysPerms;
    }

    public void setSysPerms(List<SysPerm> sysPerms) {
        this.sysPerms = sysPerms;
    }
}
