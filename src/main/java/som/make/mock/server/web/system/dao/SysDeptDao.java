package som.make.mock.server.web.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import som.make.mock.server.web.system.entity.SysDept;

import java.util.List;
import java.util.Optional;

public interface SysDeptDao extends JpaRepository<SysDept, String> {

    Optional<SysDept> findByDeptCode(String deptCode);

    List<SysDept> findAllByParentId(String parentId);
}
