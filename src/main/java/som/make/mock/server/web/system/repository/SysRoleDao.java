package som.make.mock.server.web.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import som.make.mock.server.web.system.entity.SysRole;

public interface SysRoleDao extends JpaRepository<SysRole, String> {

}
