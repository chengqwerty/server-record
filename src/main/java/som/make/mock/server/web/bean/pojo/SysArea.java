package som.make.mock.server.web.bean.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sys_area")
@GenericGenerator(
        name = "jpa-uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
        parameters = {@Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy")}
)
public class SysArea {

    private String areaId;

    private String areaCode;

    private String areaParentCode;

    private String expandCode;

    private String areaName;

    private String areaDescription;

    public LocalDateTime createTime;

    public Long createUser;

    public LocalDateTime updateTime;

    public Long updateUser;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Comment("区域id")
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Column(length = 64, unique = true)
    @Comment("区域编码")
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @Column(length = 64)
    @Comment("父级区域编码")
    public String getAreaParentCode() {
        return areaParentCode;
    }

    public void setAreaParentCode(String areaParentCode) {
        this.areaParentCode = areaParentCode;
    }

    @Column(length = 1024, unique = true)
    @Comment("扩展编码，从最上级编码递进。")
    public String getExpandCode() {
        return expandCode;
    }

    public void setExpandCode(String expandCode) {
        this.expandCode = expandCode;
    }

    @Column(length = 256)
    @Comment("区域名称")
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Column(length = 1024)
    @Comment("区域详细信息，备注。")
    public String getAreaDescription() {
        return areaDescription;
    }

    public void setAreaDescription(String areaDescription) {
        this.areaDescription = areaDescription;
    }

    @Column
    @Comment("创建时间")
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
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
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
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
