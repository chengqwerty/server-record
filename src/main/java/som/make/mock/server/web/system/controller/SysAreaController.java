package som.make.mock.server.web.system.controller;

import org.springframework.web.bind.annotation.*;
import som.make.mock.server.web.system.bean.SysArea;
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
    public ResultBean<String> add(@RequestBody SysArea sysArea) {
        try {
            sysAreaService.addArea(sysArea);
        } catch (ExpressException e) {
            return new ResultBean<>(e.getCode(), e.getMessage());
        }
        return new ResultBean<>();
    }

    /**
     * 根据areaParentCode获取子级区域，方法不递归
     */
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public ResultBean<List<SysArea>> get(@RequestParam("areaParentCode") String areaParentCode) {
        return new ResultBean<>(sysAreaService.getAreas(areaParentCode));
    }

}
