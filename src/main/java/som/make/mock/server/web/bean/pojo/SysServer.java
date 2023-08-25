package som.make.mock.server.web.bean.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.sql.Timestamp;

@Entity
@Table(name = "sys_server")
public class SysServer {

    public String serverId;

    public String serverName;

    public String serverIp;

    public String serverDescription;

    public Timestamp createTime;

    public Long createUser;

    public Timestamp updateTime;

    public Long updateUser;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Comment("服务器id")
    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    @Column(length = 64)
    @Comment("服务器名称")
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Column(length = 32)
    @Comment("服务器Ip")
    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    @Column(length = 128)
    @Comment("服务器描述")
    public String getServerDescription() {
        return serverDescription;
    }

    public void setServerDescription(String serverDescription) {
        this.serverDescription = serverDescription;
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
    @Comment("修改时间")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Column
    @Comment("修改人")
    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
}
