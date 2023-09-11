package som.make.mock.server.web.system.service;

import org.springframework.stereotype.Service;
import som.make.mock.server.web.system.dao.SysMenuDao;
import som.make.mock.server.web.system.entity.SysMenu;

import java.util.List;

@Service
public class SysMenuService {

    private final SysMenuDao sysMenuDao;

    public SysMenuService(SysMenuDao sysMenuDao) {
        this.sysMenuDao = sysMenuDao;
    }

    public List<SysMenu> getListByParent(String parentId) {
        return sysMenuDao.findAllByParentId(parentId);
    }
}
