package som.make.mock.server.web.system.service;

import org.springframework.stereotype.Service;
import som.make.mock.server.web.system.bean.SysArea;
import som.make.mock.server.web.system.dao.SysAreaDao;
import som.make.mock.server.common.express.ExpressCode;
import som.make.mock.server.common.express.ExpressException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SysAreaService {

    private final SysAreaDao sysAreaDao;

    public SysAreaService(SysAreaDao sysAreaDao) {
        this.sysAreaDao = sysAreaDao;
    }

    public void addArea(SysArea sysArea) throws ExpressException {
        if (sysAreaDao.findByAreaCode(sysArea.getAreaCode()).isPresent()) {
            throw new ExpressException("该区域编码已经被使用！", ExpressCode.REPEAT.getCode());
        }
        LocalDateTime now = LocalDateTime.now();
        sysArea.setCreateTime(now);
        sysArea.setUpdateTime(now);
        sysAreaDao.save(sysArea);
    }

    public List<SysArea> getAreas(String areaParentCode) {
        return sysAreaDao.findAllByAreaParentCode(areaParentCode);
    }

}
