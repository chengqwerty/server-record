package som.make.mock.server.web.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.Length;
import som.make.mock.server.common.validation.AddGroup;

import java.time.LocalDateTime;

@Entity
@Table(name = "sys_menu")
public class SysMenu {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Comment("菜单id")
    private String menuId;
    @Column(length = 64, unique = true)
    @Comment("菜单编码")
    @NotNull(groups = {AddGroup.class})
    @Length(min = 4)
    private String menuCode;
    @Column(length = 64)
    @Comment("菜单名称")
    @NotNull(groups = {AddGroup.class})
    private String menuName;
    @Column(length = 256)
    @Comment("菜单描述")
    private String menuDescription;
    @Column()
    @Comment("菜单类型，0：菜单，1：目录")
    @NotNull(groups = {AddGroup.class})
    private Integer menuType;
    @Column(length = 256)
    @Comment("菜单地址")
    private String menuLink;
    @Column(length = 64)
    @Comment("菜单图标")
    private String menuIcon;
    @Column(length = 64)
    @Comment("parentId")
    @NotNull(groups = {AddGroup.class})
    private String parentId;
    @Column()
    @Comment("是否可见")
    @NotNull(groups = {AddGroup.class})
    private Integer menuVisible;
    @Column()
    @Comment("创建时间")
    public LocalDateTime createTime;
    @Column(length = 64)
    @Comment("创建人")
    public String createUser;
    @Column()
    @Comment("修改时间")
    public LocalDateTime updateTime;
    @Column(length = 64)
    @Comment("修改人")
    public String updateUser;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public String getMenuLink() {
        return menuLink;
    }

    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getMenuVisible() {
        return menuVisible;
    }

    public void setMenuVisible(Integer menuVisible) {
        this.menuVisible = menuVisible;
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
