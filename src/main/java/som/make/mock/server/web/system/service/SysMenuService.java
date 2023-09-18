package som.make.mock.server.web.system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import som.make.mock.server.core.security.SecurityContextHolder;
import som.make.mock.server.web.system.dao.SysMenuDao;
import som.make.mock.server.web.system.entity.SysMenu;

import java.time.LocalDateTime;
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

    @Transactional(rollbackFor = {Error.class, RuntimeException.class})
    public String addMenu(SysMenu sysMenu) {
        LocalDateTime localDateTime = LocalDateTime.now();
        sysMenu.setCreateTime(localDateTime);
        // sysMenu.setCreateUser(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getLoginName());
        sysMenu.setUpdateTime(localDateTime);
        sysMenuDao.save(sysMenu);
        return sysMenu.getMenuId();
    }

}
