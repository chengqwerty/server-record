package som.make.mock.server.web.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import som.make.mock.server.web.system.entity.SysDept;

import java.util.List;
import java.util.Optional;

public interface SysDeptRepository extends JpaRepository<SysDept, String> {

    Optional<SysDept> findByDeptCode(String deptCode);

    List<SysDept> findAllByParentId(String parentId);

}
