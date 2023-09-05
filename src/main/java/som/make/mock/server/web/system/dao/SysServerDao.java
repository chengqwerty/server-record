package som.make.mock.server.web.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import som.make.mock.server.web.system.pojo.SysServer;

@Repository
public interface SysServerDao extends JpaRepository<SysServer, Long> {
}
