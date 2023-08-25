package som.make.mock.server.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import som.make.mock.server.web.bean.pojo.SysArea;

import java.util.List;
import java.util.Optional;

@Repository
public interface SysAreaDao extends JpaRepository<SysArea, Long> {

    Optional<SysArea> findByAreaCode(String areaCode);

    List<SysArea> findAllByAreaParentCode(String areaParentCode);

}