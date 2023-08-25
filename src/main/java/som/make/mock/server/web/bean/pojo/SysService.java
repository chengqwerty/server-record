package som.make.mock.server.web.bean.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.sql.Timestamp;

@Entity
@Table(name = "sys_service")
public class SysService {

    private String serviceId;

    private String serviceName;

    private String servicePath;

    private String serviceDescription;

    private Timestamp createTime;

    private Long createUser;

    private Timestamp updateTime;

    private Long updateUser;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Comment("服务id")
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Column(length = 64)
    @Comment("服务名称")
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Column(length = 128)
    @Comment("服务器目录")
    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    @Column(length = 256)
    @Comment("服务描述")
    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
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
