package som.make.mock.server.web.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import som.make.mock.server.web.system.entity.SysRolePerm;

import java.util.List;
import java.util.Set;

@Repository
public interface SysRolePermDao extends JpaRepository<SysRolePerm, String> {

    List<SysRolePerm> findAllByRoleIdIn(Set<String> roleIdSet);
}
