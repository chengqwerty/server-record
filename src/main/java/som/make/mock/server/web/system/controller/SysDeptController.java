package som.make.mock.server.web.system.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import som.make.mock.server.common.ResultBean;
import som.make.mock.server.common.express.ExpressException;
import som.make.mock.server.common.validation.AddGroup;
import som.make.mock.server.common.validation.DeleteGroup;
import som.make.mock.server.core.security.SecurityContext;
import som.make.mock.server.core.security.SecurityContextHolder;
import som.make.mock.server.web.system.entity.SysDept;
import som.make.mock.server.web.system.service.SysDeptService;

import java.util.List;

@RestController
@RequestMapping("sys/dept")
public class SysDeptController {

    private final SysDeptService sysDeptService;

    public SysDeptController(SysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    /**
     * 新增区域
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResultBean<SysDept> add(@RequestBody @Validated(AddGroup.class) SysDept sysDept) throws ExpressException {
        return new ResultBean<>(sysDeptService.addDept(sysDept, SecurityContextHolder.getAuthentication()));
    }

    /**
     * 根据parentId获取子级区域
     */
    @GetMapping(value = "getListByParent")
    public ResultBean<List<SysDept>> getListByParent(@RequestParam("parentId") String parentId) {
        return new ResultBean<>(sysDeptService.getListByParent(parentId));
    }

    @GetMapping(value = "getAllDeptList")
    public ResultBean<List<SysDept>> getAllDeptList() {
        return new ResultBean<>(sysDeptService.getAllDeptList());
    }

    /**
     * 修改区域，修改项：
     * 名称
     */
    @PostMapping(value = "update")
    public ResultBean<SysDept> update(@RequestBody @Validated(AddGroup.class) SysDept sysDept) throws ExpressException {
        return new ResultBean<>(sysDeptService.updateDept(sysDept, SecurityContextHolder.getAuthentication()));
    }

    /**
     * 删除区域
     */
    @PostMapping(value = "delete")
    public ResultBean<Integer> delete(@RequestBody @Validated(DeleteGroup.class) SysDept sysDept) throws ExpressException {
        return new ResultBean<>(sysDeptService.deleteDept(sysDept, SecurityContextHolder.getAuthentication()));
    }
}
