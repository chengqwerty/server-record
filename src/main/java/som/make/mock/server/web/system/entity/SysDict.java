package som.make.mock.server.web.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Table(name = "sys_dict")
@SQLDelete(table = "sys_dict", sql = "update sys_dict set delete_flag = 1 where delete_flag = ? ")
@Where(clause = "delete_flag = 0")
public class SysDict {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Comment("菜单id")
    private String dictId;

    @Column(length = 64, unique = true)
    @Comment("字典编码")
    @Length(min = 4)
    private String dictCode;

    @Column(length = 64, unique = true)
    @Comment("字典名称")
    private String dictName;

    @Column(length = 256)
    @Comment("字典描述")
    private String dictDescription;

    @Column(name = "delete_flag", nullable = false)
    @Comment("删除标记")
    @ColumnDefault("0")
    private Integer deleteFlag;

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

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictDescription() {
        return dictDescription;
    }

    public void setDictDescription(String dictDescription) {
        this.dictDescription = dictDescription;
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
}
