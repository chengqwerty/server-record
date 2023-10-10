package som.make.mock.server.web.auth.service;

import jakarta.annotation.Resource;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import som.make.mock.server.common.express.ExpressCode;
import som.make.mock.server.common.express.ExpressException;
import som.make.mock.server.config.cache.TokenCache;
import som.make.mock.server.config.filter.auth.PasswordEncoder;
import som.make.mock.server.core.security.Authentication;
import som.make.mock.server.extend.UuidUtils;
import som.make.mock.server.web.auth.entity.SysUserLoginDto;
import som.make.mock.server.web.system.dao.SysRolePermDao;
import som.make.mock.server.web.system.dao.SysUserDao;
import som.make.mock.server.web.system.dao.SysUserRoleDao;
import som.make.mock.server.web.system.entity.SysRole;
import som.make.mock.server.web.system.entity.SysRolePerm;
import som.make.mock.server.web.system.entity.SysUser;
import som.make.mock.server.web.system.entity.SysUserRole;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class SysAuthService {

    private final PasswordEncoder passwordEncoder;
    private final TokenCache tokenCache;
    private final SysUserDao sysUserDao;
    private final SysUserRoleDao sysUserRoleDao;
    private final SysRolePermDao sysRolePermDao;

    public SysAuthService(PasswordEncoder passwordEncoder, TokenCache tokenCache, SysUserDao sysUserDao, SysUserRoleDao sysUserRoleDao, SysRolePermDao sysRolePermDao) {
        this.passwordEncoder = passwordEncoder;
        this.tokenCache = tokenCache;
        this.sysUserDao = sysUserDao;
        this.sysUserRoleDao = sysUserRoleDao;
        this.sysRolePermDao = sysRolePermDao;
    }

    public String login(SysUserLoginDto sysUserLoginDto) throws ExpressException {
        Optional<SysUser> optionalSysUser = sysUserDao.findByLoginName(sysUserLoginDto.getLoginName());
        if (optionalSysUser.isPresent()) {
            SysUser sysUser = optionalSysUser.get();
            if (passwordEncoder.matches(sysUserLoginDto.getPassword(), sysUser.getPassword())) {
                String token = Base64.encodeBase64String(UuidUtils.simpleUuid().getBytes(StandardCharsets.UTF_8));
                List<SysUserRole> sysUserRoleList = sysUserRoleDao.findAllByUserId(sysUser.getUserId());
                Set<SysRole> sysRoleSet = new HashSet<>();
                Set<String> roleIdSet = new HashSet<>();
                sysUserRoleList.forEach(sysUserRole -> {
                    sysRoleSet.add(sysUserRole.getSysRole());
                    roleIdSet.add(sysUserRole.getSysRole().getRoleId());
                });
                Set<String> permSet = new HashSet<>();
                List<SysRolePerm> sysRolePermList = sysRolePermDao.findAllByRoleIdIn(roleIdSet);
                sysRolePermList.forEach(sysRolePerm -> sysRolePerm.getSysPerms().forEach(sysPerm -> permSet.add(sysPerm.getPermCode())));
                Authentication<SysUser, SysRole> authentication = new Authentication<>();
                authentication.setToken(token);
                authentication.setPrincipal(sysUser);
                authentication.setRoles(sysRoleSet);
                authentication.setAuthorities(permSet);
                tokenCache.putToken(token, authentication);
                return token;
            } else {
                throw new ExpressException(ExpressCode.USER_LOGIN_ERROR.getName(), ExpressCode.USER_LOGIN_ERROR.getCode());
            }
        } else {
            throw new ExpressException(ExpressCode.USER_NOT_EXISTS.getName(), ExpressCode.USER_NOT_EXISTS.getCode());
        }
    }

}
