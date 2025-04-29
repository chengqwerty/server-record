package som.make.mock.server.web.system.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import som.make.mock.server.common.express.ExpressCode;
import som.make.mock.server.common.express.ExpressException;
import som.make.mock.server.core.security.Authentication;
import som.make.mock.server.core.security.SecurityContextHolder;
import som.make.mock.server.web.system.repository.SysMenuRepository;
import som.make.mock.server.web.system.entity.SysMenu;
import som.make.mock.server.web.system.entity.SysRole;
import som.make.mock.server.web.system.entity.SysUser;

import java.util.List;
import java.util.Optional;

@Service
public class SysMenuService {

    private final SysMenuRepository sysMenuRepository;

    public SysMenuService(SysMenuRepository sysMenuRepository) {
        this.sysMenuRepository = sysMenuRepository;
    }

    public List<SysMenu> getListByParent(String parentId) {
        Sort sort = Sort.by(Sort.Order.asc("menuSort"));
        return sysMenuRepository.findAllByParentId(parentId, sort);
    }

    /**
     * 获取用户菜单
     * 目前比较简单，直接获取所有菜单
     */
    public List<SysMenu> getUserMenus() {
        return sysMenuRepository.findAll();
    }

    @Transactional(rollbackFor = {Error.class, RuntimeException.class, ExpressException.class})
    public String addMenu(SysMenu sysMenu, Authentication<SysUser, SysRole> authentication) throws ExpressException {
        if (sysMenu.getParentId().equals("0")) {
            sysMenu.setMenuLevel(1);
        } else {
            Optional<SysMenu> optional = sysMenuRepository.findById(sysMenu.getParentId());
            if (optional.isEmpty()) {
                throw new ExpressException(ExpressCode.COMMON_ERROR.getCode(), "parent菜单不存在！");
            } else {
                sysMenu.setMenuLevel(optional.get().getMenuLevel() + 1);
            }
        }
        sysMenu.setCreationInfo(authentication.getPrincipal());
        sysMenuRepository.save(sysMenu);
        return sysMenu.getMenuId();
    }

    @Transactional(rollbackFor = {Error.class, RuntimeException.class, ExpressException.class})
    public Integer updateMenu(SysMenu newRecord, Authentication<SysUser, SysRole> authentication) throws ExpressException {
        Optional<SysMenu> oldOptional = sysMenuRepository.findById(newRecord.getMenuId());
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
        oldRecord.setUpdateInfo(authentication.getPrincipal());
        sysMenuRepository.save(oldRecord);
        return 1;
    }

    @Transactional(rollbackFor = {Error.class, RuntimeException.class, ExpressException.class})
    public Integer deleteMenu(SysMenu newRecord, Authentication<SysUser, SysRole> authentication) throws ExpressException {
        Optional<SysMenu> oldOptional = sysMenuRepository.findById(newRecord.getMenuId());
        if (oldOptional.isEmpty()) {
            throw new ExpressException(ExpressCode.DELETE_NOT_EXIST);
        } else {
            SysMenu sysMenu = oldOptional.get();
            sysMenu.setUpdateInfo(authentication.getPrincipal());
            sysMenu.setDeleteFlag(1);
            sysMenuRepository.save(sysMenu);
        }
        return 1;
    }

}
