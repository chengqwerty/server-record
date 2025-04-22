package som.make.mock.server.web.system.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import som.make.mock.server.web.system.entity.SysMenu;

import java.util.List;

public interface SysMenuDao extends JpaRepository<SysMenu, String> {

    List<SysMenu> findAllByDelFlagFalse();

    List<SysMenu> findAllByParentIdAndDelFlagFalse(String parentId, Sort sort);

    // 自定义删除方法
    @Modifying
    @Query("""
            UPDATE SysMenu sm SET sm.delFlag = true, sm.updateUser = :#{#sysMenu.updateUser}, sm.updateTime = :#{#sysMenu.updateTime}
            where sm.menuId = :#{#sysMenu.menuId}
            """)
    void logicalDeleteById(SysMenu sysMenu);

}
