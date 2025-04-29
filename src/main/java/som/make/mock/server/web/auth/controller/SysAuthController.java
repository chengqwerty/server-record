package som.make.mock.server.web.auth.controller;

import org.springframework.web.bind.annotation.*;
import som.make.mock.server.common.ResultBean;
import som.make.mock.server.common.express.ExpressException;
import som.make.mock.server.core.security.SecurityContext;
import som.make.mock.server.core.security.SecurityContextHolder;
import som.make.mock.server.web.auth.entity.UserLoginDto;
import som.make.mock.server.web.auth.entity.TokenModel;
import som.make.mock.server.web.auth.entity.UserLogoutDto;
import som.make.mock.server.web.auth.service.SysAuthService;

@RequestMapping("auth")
@RestController
public class SysAuthController {

    private final SysAuthService sysAuthService;

    public SysAuthController(SysAuthService sysAuthService) {
        this.sysAuthService = sysAuthService;
    }

    @PostMapping("login")
    public ResultBean<TokenModel> userLogin(@RequestBody UserLoginDto userLoginDto) throws ExpressException {
        return new ResultBean<>(sysAuthService.login(userLoginDto));
    }

    @PostMapping("logout")
    public ResultBean<Integer> userLogout() throws ExpressException {
        return new ResultBean<>(sysAuthService.logout(SecurityContextHolder.getAuthentication()));
    }

}
