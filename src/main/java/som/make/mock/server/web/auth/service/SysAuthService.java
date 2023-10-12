package som.make.mock.server.web.auth.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import som.make.mock.server.common.express.ExpressCode;
import som.make.mock.server.common.express.ExpressException;
import som.make.mock.server.config.cache.TokenCache;
import som.make.mock.server.config.cache.TokenDetails;
import som.make.mock.server.config.filter.auth.PasswordEncoder;
import som.make.mock.server.core.security.Authentication;
import som.make.mock.server.extend.UuidUtils;
import som.make.mock.server.web.auth.entity.SysUserLoginDto;
import som.make.mock.server.web.auth.entity.TokenModel;
import som.make.mock.server.web.system.dao.SysUserDao;
import som.make.mock.server.web.system.entity.SysPerm;
import som.make.mock.server.web.system.entity.SysRole;
import som.make.mock.server.web.system.entity.SysUser;
import som.make.mock.server.web.system.entity.SysUserRole;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysAuthService {

    private final PasswordEncoder passwordEncoder;
    private final TokenCache tokenCache;
    private final SysUserDao sysUserDao;

    public SysAuthService(PasswordEncoder passwordEncoder, TokenCache tokenCache, SysUserDao sysUserDao) {
        this.passwordEncoder = passwordEncoder;
        this.tokenCache = tokenCache;
        this.sysUserDao = sysUserDao;
    }

    public TokenModel login(SysUserLoginDto sysUserLoginDto) throws ExpressException {
        Optional<SysUser> optionalSysUser = sysUserDao.findByLoginName(sysUserLoginDto.getLoginName());
        if (optionalSysUser.isPresent()) {
            SysUser sysUser = optionalSysUser.get();
            if (passwordEncoder.matches(sysUserLoginDto.getPassword(), sysUser.getPassword())) {
                String token = Base64.encodeBase64String(UuidUtils.simpleUuid().getBytes(StandardCharsets.UTF_8));
                String refreshToken = passwordEncoder.getNoPrefixEncoder(token);
                Set<SysRole> sysRoleSet = new HashSet<>();
                Set<String> permSet = new HashSet<>();
                sysUser.getSysRoleList().forEach(sysRole -> {
                    sysRoleSet.add(sysRole);
                    permSet.addAll(sysRole.getSysPermList().stream().map(SysPerm::getPermCode).collect(Collectors.toSet()));
                });
                Authentication<SysUser, SysRole> authentication = new Authentication<>();
                authentication.setToken(token);
                authentication.setPrincipal(sysUser);
                authentication.setRoles(sysRoleSet);
                authentication.setAuthorities(permSet);
                TokenModel tokenModel = new TokenModel(token, refreshToken);
                TokenDetails tokenDetails = new TokenDetails(token, refreshToken, 0, authentication);
                tokenCache.putToken(token, tokenDetails);
                tokenCache.putToken(refreshToken, tokenDetails);
                return tokenModel;
            } else {
                throw new ExpressException(ExpressCode.USER_LOGIN_ERROR);
            }
        } else {
            throw new ExpressException(ExpressCode.USER_NOT_EXISTS);
        }
    }

}
