package som.make.mock.server.web.system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import som.make.mock.server.common.Constants;
import som.make.mock.server.common.express.ExpressCode;
import som.make.mock.server.common.express.ExpressException;
import som.make.mock.server.core.security.SecurityContextHolder;
import som.make.mock.server.web.system.dao.SysDeptDao;
import som.make.mock.server.web.system.entity.SysDept;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SysDeptService {
    
    private final SysDeptDao sysDeptDao;

    public SysDeptService(SysDeptDao sysDeptDao) {
        this.sysDeptDao = sysDeptDao;
    }

    @Transactional(rollbackFor = {Error.class, RuntimeException.class, ExpressException.class})
    public SysDept addDept(SysDept sysDept) throws ExpressException {
        if (sysDeptDao.findByDeptCode(sysDept.getDeptCode()).isPresent()) {
            throw new ExpressException(ExpressCode.REPEAT.getCode(), "该部门编码已经被使用！");
        }
        Optional<SysDept> optionalParent = sysDeptDao.findById(sysDept.getParentId());
        optionalParent.ifPresentOrElse(Dept -> sysDept.setExpandCode(Dept.getExpandCode() + "-" + sysDept.getDeptCode()),
                () -> {
                    sysDept.setExpandCode(sysDept.getDeptCode());
                });
        LocalDateTime now = LocalDateTime.now();
        sysDept.setCreateTime(now);
        sysDept.setCreteUser(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getLoginName());
        sysDept.setUpdateTime(now);
        return sysDeptDao.save(sysDept);
    }

    public List<SysDept> getDeptList(String parentId) {
        return sysDeptDao.findAllByParentId(parentId);
    }

    @Transactional(rollbackFor = {Error.class, RuntimeException.class, ExpressException.class})
    public SysDept updateDept(SysDept newRecord) throws ExpressException {
        Optional<SysDept> optionalOld = sysDeptDao.findById(newRecord.getDeptId());
        if (optionalOld.isEmpty()) {
            throw new ExpressException(ExpressCode.UPDATE_NOT_EXIST);
        }
        SysDept oldRecord = optionalOld.get();
        oldRecord.setDeptName(newRecord.getDeptName());
        LocalDateTime now = LocalDateTime.now();
        oldRecord.setUpdateTime(now);
        oldRecord.setUpdateUser(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getLoginName());
        return sysDeptDao.save(oldRecord);
    }

    @Transactional(rollbackFor = {Error.class, RuntimeException.class, ExpressException.class})
    public SysDept deleteDept(SysDept deleteRecord) throws ExpressException {
        Optional<SysDept> optionalOld = sysDeptDao.findById(deleteRecord.getDeptId());
        if (optionalOld.isEmpty()) {
            throw new ExpressException(ExpressCode.DELETE_NOT_EXIST);
        }
        SysDept oldRecord = optionalOld.get();
        sysDeptDao.delete(oldRecord);
        return oldRecord;
    }

}
