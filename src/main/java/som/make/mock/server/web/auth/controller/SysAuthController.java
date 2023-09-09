package som.make.mock.server.web.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import som.make.mock.server.common.ResultBean;
import som.make.mock.server.common.express.ExpressException;
import som.make.mock.server.web.auth.entity.SysUserLoginDto;
import som.make.mock.server.web.auth.service.SysAuthService;

@RequestMapping("auth")
@RestController
public class SysAuthController {

    private final SysAuthService sysAuthService;

    public SysAuthController(SysAuthService sysAuthService) {
        this.sysAuthService = sysAuthService;
    }

    @PostMapping("userLogin")
    public ResultBean<String> userLogin(SysUserLoginDto sysUserLoginDto) throws ExpressException {
        return new ResultBean<>(sysAuthService.login(sysUserLoginDto));
    }

}
