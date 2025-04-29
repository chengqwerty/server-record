package som.make.mock.server.web.system.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import som.make.mock.server.web.system.entity.SysMenu;

import java.util.List;

public interface SysMenuRepository extends JpaRepository<SysMenu, String> {

    List<SysMenu> findAllBy();

    List<SysMenu> findAllByParentId(String parentId, Sort sort);

}
