package som.make.mock.server.web.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import som.make.mock.server.web.system.entity.SysMenu;

import java.util.List;

public interface SysMenuDao extends JpaRepository<SysMenu, String> {

    List<SysMenu> findAllByParentId(String parentId);

}
