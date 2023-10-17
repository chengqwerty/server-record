package som.make.mock.server.web.system.entity;

import jakarta.persistence.*;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.*;
import som.make.mock.server.common.validation.AddGroup;
import som.make.mock.server.common.validation.DeleteGroup;
import som.make.mock.server.common.validation.UpdateGroup;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "sys_area")
@SQLDelete(table = "sys_area", sql = "update sys_area set delete_flag = 1 where area_id = ? ")
@Where(clause = "delete_flag = 0")
public class SysArea {

    @Id
    @Column(name = "area_id", length = 64)
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Comment("区域id")
    @NotNull(groups = {UpdateGroup.class, DeleteGroup.class})
    private String areaId;

    @Column(length = 64, unique = true, nullable = false)
    @Comment("区域编码")
    private String areaCode;

    @Column(name = "parent_id", length = 64, nullable = false)
    @NotNull(groups = {AddGroup.class})
    private String parentId;

    @Column(length = 1024, unique = true, nullable = false)
    @Comment("扩展编码，从最上级编码递进。")
    private String expandCode;

    @Column(length = 256, nullable = false)
    @Comment("区域名称")
    private String areaName;

    @Column(length = 1024)
    @Comment("区域详细信息，备注。")
    private String areaDescription;

    @Column(name = "delete_flag", nullable = false)
    @Comment("删除标记")
    @ColumnDefault("0")
    private Integer deleteFlag = 0;

    @Column()
    @Comment("创建时间")
    private LocalDateTime createTime;

    @Column(length = 64)
    @Comment("创建人")
    private String creteUser;

    @Column()
    @Comment("修改时间")
    private LocalDateTime updateTime;

    @Column(length = 64)
    @Comment("修改人")
    private String updateUser;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id", referencedColumnName = "parent_id", foreignKey = @ForeignKey(name = "none", value =
            ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false)
    private Set<SysArea> children;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getExpandCode() {
        return expandCode;
    }

    public void setExpandCode(String expandCode) {
        this.expandCode = expandCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaDescription() {
        return areaDescription;
    }

    public void setAreaDescription(String areaDescription) {
        this.areaDescription = areaDescription;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreteUser() {
        return creteUser;
    }

    public void setCreteUser(String creteUser) {
        this.creteUser = creteUser;
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

    public Set<SysArea> getChildren() {
        return children;
    }

    public void setChildren(Set<SysArea> children) {
        this.children = children;
    }
}
