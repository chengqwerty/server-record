package som.make.mock.server.web.system.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.sql.Timestamp;

@Entity
@Table(name = "sys_server_service_relation")
public class SysServerServiceRelation {

    private String ssjId;

    private Long serverId;

    private Long serviceId;

    private Timestamp createTime;

    private Long createUser;

    private Timestamp updateTime;

    private Long updateUser;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Comment("id")
    public String getSsjId() {
        return ssjId;
    }

    public void setSsjId(String ssjId) {
        this.ssjId = ssjId;
    }

    @Column
    @Comment("服务器id")
    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    @Column
    @Comment("服务id")
    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    @Column
    @Comment("创建时间")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Column
    @Comment("创建人")
    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    @Column
    @Comment("更新时间")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Column
    @Comment("更新人")
    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
}
