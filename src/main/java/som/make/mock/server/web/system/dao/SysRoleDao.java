package som.make.mock.server.web.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import som.make.mock.server.web.system.entity.SysRole;

import java.util.List;
import java.util.Optional;

public interface SysRoleDao extends JpaRepository<SysRole, String> {

}
