package som.make.mock.server.web.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "sys_perm")
public class SysPerm {

    @Id
    @Column(name = "perm_id")
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Comment("权限主键")
    private String permId;
    @Column(length = 64, unique = true)
    @Comment("权限编码")
    private String permCode;
    @Column(length = 128)
    @Comment("权限名称")
    private String permName;
    @Column(length = 512)
    @Comment("权限描述")
    private String permDescription;
    @Column(length = 512)
    @Comment("权限类型")
    private Integer permType;
    @Column(length = 512)
    @Comment("权限类型")
    private String parentId;
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

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }

    public String getPermCode() {
        return permCode;
    }

    public void setPermCode(String permCode) {
        this.permCode = permCode;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
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
}
