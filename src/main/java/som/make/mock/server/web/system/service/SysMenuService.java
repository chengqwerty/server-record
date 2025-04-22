package som.make.mock.server.web.system.service;

import org.springframework.data.domain.Sort;
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
        Sort sort = Sort.by(Sort.Order.asc("menuSort"));
        return sysMenuDao.findAllByParentIdAndDelFlagFalse(parentId, sort);
    }

    /**
     * 获取用户菜单
     * 目前比较简单，直接获取所有菜单
     */
    public List<SysMenu> getUserMenus() {
        return sysMenuDao.findAllByDelFlagFalse();
    }

    @Transactional(rollbackFor = {Error.class, RuntimeException.class, ExpressException.class})
    public String addMenu(SysMenu sysMenu) throws ExpressException {
        if (sysMenu.getParentId().equals("0")) {
            sysMenu.setMenuLevel(1);
        } else {
            Optional<SysMenu> optional = sysMenuDao.findById(sysMenu.getParentId());
            if (optional.isEmpty()) {
                throw new ExpressException(ExpressCode.COMMON_ERROR.getCode(), "parent菜单不存在！");
            } else {
                sysMenu.setMenuLevel(optional.get().getMenuLevel() + 1);
            }
        }
        sysMenu.setCreationInfo(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
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
        oldRecord.setMenuType(newRecord.getMenuType());
        oldRecord.setMenuName(newRecord.getMenuName());
        oldRecord.setMenuIcon(newRecord.getMenuIcon());
        oldRecord.setMenuDescription(newRecord.getMenuDescription());
        oldRecord.setMenuSort(newRecord.getMenuSort());
        oldRecord.setMenuVisible(newRecord.getMenuVisible());
        oldRecord.setMenuLink(newRecord.getMenuLink());
        oldRecord.setUpdateInfo(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        sysMenuDao.save(oldRecord);
        return 1;
    }

    @Transactional(rollbackFor = {Error.class, RuntimeException.class, ExpressException.class})
    public Integer deleteMenu(SysMenu newRecord) throws ExpressException {
        Optional<SysMenu> oldOptional = sysMenuDao.findById(newRecord.getMenuId());
        if (oldOptional.isEmpty()) {
            throw new ExpressException(ExpressCode.UPDATE_NOT_EXIST);
        }
        // 修改人，修改时间待完善
        sysMenuDao.logicalDeleteById(oldOptional.get());
        return 1;
    }

}
