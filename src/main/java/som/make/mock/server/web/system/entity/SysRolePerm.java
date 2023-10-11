package som.make.mock.server.web.system.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "sys_role_perm", uniqueConstraints = @UniqueConstraint(columnNames = {"role_id", "perm_id"}))
public class SysRolePerm {

    @Id
    @Column(length = 64)
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Comment("主键")
    private String rolePermId;
    @Column(name = "role_id", length = 64)
    @Comment("roleId")
    private String roleId;
    @Column(name = "perm_id", length = 64)
    @Comment("permId")
    private String permId;
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


}
