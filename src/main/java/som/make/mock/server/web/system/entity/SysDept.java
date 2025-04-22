package som.make.mock.server.web.system.entity;

import jakarta.persistence.*;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "sys_dept")
@Where(clause = "delete_flag = 0")
public class SysDept {

    @Id
    @Column(name = "dept_id", length = 64)
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Comment("部门id")
    private String deptId;

    @Column(name = "dept_code", length = 64, unique = true, nullable = false)
    @Comment("部门编码")
    private String deptCode;

    @Column(length = 256, nullable = false)
    @Comment("部门名称")
    private String deptName;

    @Column(name = "parent_id", length = 64)
    private String parentId;

    @Column(length = 1024, unique = true, nullable = false)
    @Comment("扩展编码，从最上级编码递进。")
    private String expandCode;

    @Column(length = 1024)
    @Comment("部门详细信息，备注。")
    private String deptDescription;

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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id", referencedColumnName = "parent_id", foreignKey = @ForeignKey(name = "none", value =
            ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false)
    private Set<SysDept> children;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public String getDeptDescription() {
        return deptDescription;
    }

    public void setDeptDescription(String deptDescription) {
        this.deptDescription = deptDescription;
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

    public Set<SysDept> getChildren() {
        return children;
    }

    public void setChildren(Set<SysDept> children) {
        this.children = children;
    }
}
