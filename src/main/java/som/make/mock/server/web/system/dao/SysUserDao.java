package som.make.mock.server.web.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import som.make.mock.server.web.system.entity.SysArea;
import som.make.mock.server.web.system.entity.SysUser;

import java.util.Optional;

@Repository
public interface SysUserDao extends JpaRepository<SysUser, String> {

    Optional<SysUser> findByLoginName(String loginName);

}
