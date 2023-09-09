package som.make.mock.server.web.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import som.make.mock.server.web.system.entity.SysUserRole;

import java.util.List;

@Repository
public interface SysUserRoleDao extends JpaRepository<SysUserRole, String> {

    List<SysUserRole> findAllByUserId(String userId);
}
