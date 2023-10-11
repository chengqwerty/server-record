package som.make.mock.server.web.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import som.make.mock.server.web.system.entity.SysDictDetail;

public interface SysDictDetailDao extends JpaRepository<SysDictDetail, String> {
}
