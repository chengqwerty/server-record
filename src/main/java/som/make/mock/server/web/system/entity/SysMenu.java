package som.make.mock.server.web.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "sys_menu")
public class SysMenu {

    private String menuId;
    private String menuCode;
    private String menuName;
    private String menuDescription;
    private Integer menuType;
    private String menuLink;
    private String menuIcon;
    private String parentId;
    private Integer menuVisible;
    public LocalDateTime createTime;
    public String createUser;
    public LocalDateTime updateTime;
    public String updateUser;

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Comment("菜单id")
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Column(length = 64, unique = true)
    @Comment("菜单编码")
    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    @Column(length = 64)
    @Comment("菜单名称")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Column(length = 256)
    @Comment("菜单描述")
    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    @Column()
    @Comment("菜单类型")
    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    @Column(length = 256)
    @Comment("菜单地址")
    public String getMenuLink() {
        return menuLink;
    }

    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink;
    }

    @Column(length = 64)
    @Comment("菜单图标")
    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    @Column(length = 64)
    @Comment("parentId")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Column(length = 64)
    @Comment("是否可见")
    public Integer getMenuVisible() {
        return menuVisible;
    }

    public void setMenuVisible(Integer menuVisible) {
        this.menuVisible = menuVisible;
    }

    @Column()
    @Comment("创建时间")
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Column(length = 64)
    @Comment("创建人")
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Column()
    @Comment("修改时间")
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Column(length = 64)
    @Comment("修改人")
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
