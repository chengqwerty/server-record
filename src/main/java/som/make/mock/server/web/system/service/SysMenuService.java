package som.make.mock.server.web.system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import som.make.mock.server.common.express.ExpressCode;
import som.make.mock.server.common.express.ExpressException;
import som.make.mock.server.core.security.SecurityContextHolder;
import som.make.mock.server.web.system.dao.SysMenuDao;
import som.make.mock.server.web.system.entity.SysMenu;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Transactional(rollbackFor = {Error.class, RuntimeException.class, ExpressException.class})
    public Integer updateMenu(SysMenu newRecord) throws ExpressException {
        Optional<SysMenu> oldOptional = sysMenuDao.findById(newRecord.getMenuId());
        if (oldOptional.isEmpty()) {
            throw new ExpressException(ExpressCode.UPDATE_NOT_EXIST);
        }
        SysMenu oldRecord = oldOptional.get();
        oldRecord.setMenuCode(newRecord.getMenuCode());
        oldRecord.setMenuName(newRecord.getMenuName());
        oldRecord.setMenuIcon(newRecord.getMenuIcon());
        oldRecord.setMenuDescription(newRecord.getMenuDescription());
        oldRecord.setMenuLink(newRecord.getMenuLink());
        // 修改人，修改时间待完善
        return 1;
    }

}
