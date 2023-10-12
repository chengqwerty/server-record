package som.make.mock.server.web.system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import som.make.mock.server.web.system.entity.SysArea;
import som.make.mock.server.web.system.dao.SysAreaDao;
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
            throw new ExpressException("该区域编码已经被使用！", ExpressCode.REPEAT.getCode());
        }
        Optional<SysArea> optionalParent = sysAreaDao.findById(sysArea.getParentId());
        optionalParent.ifPresentOrElse(area -> sysArea.setExpandCode(area.getExpandCode() + "-" + sysArea.getAreaCode()),
                () -> {
                    sysArea.setExpandCode(sysArea.getAreaCode());
                });
        LocalDateTime now = LocalDateTime.now();
        sysArea.setCreateTime(now);
        sysArea.setUpdateTime(now);
        return sysAreaDao.save(sysArea);
    }

    public List<SysArea> getAreas(String parentId) {
        return sysAreaDao.findAllByParentId(parentId);
    }

}
