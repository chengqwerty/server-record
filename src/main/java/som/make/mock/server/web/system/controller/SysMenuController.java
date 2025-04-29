package som.make.mock.server.web.system.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import som.make.mock.server.common.ResultBean;
import som.make.mock.server.common.express.ExpressException;
import som.make.mock.server.core.security.SecurityContextHolder;
import som.make.mock.server.web.system.entity.SysMenu;
import som.make.mock.server.web.system.service.SysMenuService;

import java.util.List;

@RestController
@RequestMapping("sys/menu")
public class SysMenuController {

    private final SysMenuService sysMenuService;

    public SysMenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    /**
     * 根据parentId查询菜单
     */
    @GetMapping("getListByParent")
    public ResultBean<List<SysMenu>> getListByParent(@RequestParam("parentId") String parentId) {
        return new ResultBean<>(sysMenuService.getListByParent(parentId));
    }

    /**
     * 根据parentId查询菜单
     */
    @GetMapping("getUserMenus")
    public ResultBean<List<SysMenu>> getUserMenus() {
        return new ResultBean<>(sysMenuService.getUserMenus());
    }

    /**
     * 添加菜单
     */
    @PostMapping("add")
    public ResultBean<String> addMenu(@Validated @RequestBody SysMenu sysMenu) throws ExpressException {
        return new ResultBean<>(sysMenuService.addMenu(sysMenu, SecurityContextHolder.getAuthentication()));
    }

    /**
     * 修改菜单
     */
    @PostMapping("update")
    public ResultBean<Integer> updateMenu(@RequestBody SysMenu sysMenu) throws ExpressException {
        return new ResultBean<>(sysMenuService.updateMenu(sysMenu, SecurityContextHolder.getAuthentication()));
    }

    /**
     * 删除菜单
     */
    @PostMapping("delete")
    public ResultBean<Integer> deleteMenu(@RequestBody SysMenu sysMenu) throws ExpressException {
        return new ResultBean<>(sysMenuService.deleteMenu(sysMenu, SecurityContextHolder.getAuthentication()));
    }

}
