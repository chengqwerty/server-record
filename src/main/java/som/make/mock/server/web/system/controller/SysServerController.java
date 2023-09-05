package som.make.mock.server.web.system.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import som.make.mock.server.web.system.pojo.SysServer;
import som.make.mock.server.common.ResultBean;
import som.make.mock.server.web.system.service.SysServerService;

@RestController
@RequestMapping("sys/server")
public class SysServerController {

    private final SysServerService sysServerService;

    public SysServerController(SysServerService sysServerService) {
        this.sysServerService = sysServerService;
    }

    @PostMapping("create")
    public ResultBean<SysServer> createServer(@RequestBody SysServer server) {
        return new ResultBean<>(sysServerService.createServer(server));
    }

}
