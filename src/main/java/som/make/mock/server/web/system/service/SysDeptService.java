package som.make.mock.server.web.system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import som.make.mock.server.common.express.ExpressCode;
import som.make.mock.server.common.express.ExpressException;
import som.make.mock.server.core.security.Authentication;
import som.make.mock.server.core.security.SecurityContextHolder;
import som.make.mock.server.web.system.entity.SysRole;
import som.make.mock.server.web.system.entity.SysUser;
import som.make.mock.server.web.system.repository.SysDeptRepository;
import som.make.mock.server.web.system.entity.SysDept;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SysDeptService {

    private final SysDeptRepository sysDeptRepository;

    public SysDeptService(SysDeptRepository sysDeptRepository) {
        this.sysDeptRepository = sysDeptRepository;
    }

    @Transactional(rollbackFor = {Error.class, RuntimeException.class})
    public SysDept addDept(SysDept sysDept, Authentication<SysUser, SysRole> authentication) throws ExpressException {
        if (sysDeptRepository.findByDeptCode(sysDept.getDeptCode()).isPresent()) {
            throw new ExpressException(ExpressCode.REPEAT.getCode(), "该部门编码已经被使用！");
        }
        Optional<SysDept> optionalParent = sysDeptRepository.findById(sysDept.getParentId());
        optionalParent.ifPresentOrElse(parent -> sysDept.setExpandCode(parent.getExpandCode() + "-" + sysDept.getDeptCode()),
                () -> sysDept.setExpandCode(sysDept.getDeptCode()));
        sysDept.setCreationInfo(authentication.getPrincipal());
        return sysDeptRepository.save(sysDept);
    }

    public List<SysDept> getListByParent(String parentId) {
        return sysDeptRepository.findAllByParentId(parentId);
    }

    public List<SysDept> getAllDeptList() {
        return sysDeptRepository.findAll();
    }

    @Transactional(rollbackFor = {Error.class, RuntimeException.class})
    public SysDept updateDept(SysDept newRecord, Authentication<SysUser, SysRole> authentication) throws ExpressException {
        Optional<SysDept> optionalOld = sysDeptRepository.findById(newRecord.getDeptId());
        if (optionalOld.isEmpty()) {
            throw new ExpressException(ExpressCode.UPDATE_NOT_EXIST);
        } else {
            SysDept oldRecord = optionalOld.get();
            oldRecord.setDeptName(newRecord.getDeptName());
            oldRecord.setDeptDescription(newRecord.getDeptDescription());
            oldRecord.setUpdateInfo(authentication.getPrincipal());
            return sysDeptRepository.save(oldRecord);
        }
    }

    @Transactional(rollbackFor = {Error.class, RuntimeException.class})
    public Integer deleteDept(SysDept deleteRecord, Authentication<SysUser, SysRole> authentication) throws ExpressException {
        Optional<SysDept> optionalOld = sysDeptRepository.findById(deleteRecord.getDeptId());
        if (optionalOld.isEmpty()) {
            throw new ExpressException(ExpressCode.DELETE_NOT_EXIST);
        } else {
            SysDept oldRecord = optionalOld.get();
            oldRecord.setUpdateInfo(authentication.getPrincipal());
            oldRecord.setDeleteFlag(1);
            sysDeptRepository.save(oldRecord);
        }
        return 1;
    }

}
