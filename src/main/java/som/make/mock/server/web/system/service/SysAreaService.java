package som.make.mock.server.web.system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import som.make.mock.server.common.Constants;
import som.make.mock.server.core.security.SecurityContextHolder;
import som.make.mock.server.web.system.entity.SysArea;
import som.make.mock.server.web.system.repository.SysAreaDao;
import som.make.mock.server.common.express.ExpressCode;
import som.make.mock.server.common.express.ExpressException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SysAreaService {

    private final SysAreaDao sysAreaDao;

    public SysAreaService(SysAreaDao sysAreaDao) {
        this.sysAreaDao = sysAreaDao;
    }

    @Transactional(rollbackFor = {Error.class, RuntimeException.class, ExpressException.class})
    public SysArea addArea(SysArea sysArea) throws ExpressException {
        if (sysAreaDao.findByAreaCode(sysArea.getAreaCode()).isPresent()) {
            throw new ExpressException(ExpressCode.REPEAT.getCode(), "该区域编码已经被使用！");
        }
        Optional<SysArea> optionalParent = sysAreaDao.findById(sysArea.getParentId());
        optionalParent.ifPresentOrElse(area -> sysArea.setExpandCode(area.getExpandCode() + "-" + sysArea.getAreaCode()),
                () -> {
                    sysArea.setExpandCode(sysArea.getAreaCode());
                });
        LocalDateTime now = LocalDateTime.now();
        sysArea.setCreateTime(now);
        sysArea.setCreteUser(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getLoginName());
        sysArea.setUpdateTime(now);
        return sysAreaDao.save(sysArea);
    }

    public List<SysArea> getAreas(String parentId) {
        return sysAreaDao.findAllByParentId(parentId);
    }

    @Transactional(rollbackFor = {Error.class, RuntimeException.class, ExpressException.class})
    public SysArea updateArea(SysArea newRecord) throws ExpressException {
        Optional<SysArea> optionalOld = sysAreaDao.findById(newRecord.getAreaId());
        if (optionalOld.isEmpty()) {
            throw new ExpressException(ExpressCode.UPDATE_NOT_EXIST);
        }
        SysArea oldRecord = optionalOld.get();
        oldRecord.setAreaName(newRecord.getAreaName());
        LocalDateTime now = LocalDateTime.now();
        oldRecord.setUpdateTime(now);
        return sysAreaDao.save(oldRecord);
    }

    @Transactional(rollbackFor = {Error.class, RuntimeException.class, ExpressException.class})
    public SysArea deleteArea(SysArea deleteRecord) throws ExpressException {
        Optional<SysArea> optionalOld = sysAreaDao.findById(deleteRecord.getAreaId());
        if (optionalOld.isEmpty()) {
            throw new ExpressException(ExpressCode.DELETE_NOT_EXIST);
        }
        SysArea oldRecord = optionalOld.get();
        oldRecord.setDeleteFlag(Constants.DELETE_ONE);
        LocalDateTime now = LocalDateTime.now();
        oldRecord.setUpdateTime(now);
        oldRecord.setUpdateUser(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getLoginName());
        return sysAreaDao.save(oldRecord);
    }

}
