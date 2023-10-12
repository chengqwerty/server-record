package som.make.mock.server.web.system.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import som.make.mock.server.common.validation.AddGroup;
import som.make.mock.server.web.system.entity.SysArea;
import som.make.mock.server.common.express.ExpressException;
import som.make.mock.server.common.ResultBean;
import som.make.mock.server.web.system.service.SysAreaService;

import java.util.List;

@RestController
@RequestMapping("sys/area")
public class SysAreaController {
    private final SysAreaService sysAreaService;

    public SysAreaController(SysAreaService sysAreaService) {
        this.sysAreaService = sysAreaService;
    }

    /**
     * 新增区域
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResultBean<SysArea> add(@RequestBody @Validated(AddGroup.class) SysArea sysArea) throws ExpressException {
        return new ResultBean<>(sysAreaService.addArea(sysArea));
    }

    /**
     * 根据areaParentCode获取子级区域，方法不递归
     */
    @RequestMapping(value = "get")
    public ResultBean<List<SysArea>> get(@RequestParam("parentId") String parentId) {
        return new ResultBean<>(sysAreaService.getAreas(parentId));
    }

}
