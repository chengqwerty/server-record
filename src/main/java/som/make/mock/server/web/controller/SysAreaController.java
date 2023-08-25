package som.make.mock.server.web.controller;

import org.springframework.web.bind.annotation.*;
import som.make.mock.server.web.bean.pojo.SysArea;
import som.make.mock.server.web.extend.ExpressException;
import som.make.mock.server.web.extend.ResultBean;
import som.make.mock.server.web.service.SysAreaService;

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
