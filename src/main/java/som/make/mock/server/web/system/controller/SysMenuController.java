package som.make.mock.server.web.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import som.make.mock.server.common.ResultBean;
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

}
