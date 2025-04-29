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
import som.make.mock.server.web.auth.entity.UserInfo;
import som.make.mock.server.web.auth.entity.UserLoginDto;
import som.make.mock.server.web.auth.entity.UserLogoutDto;
import som.make.mock.server.web.auth.entity.TokenModel;
import som.make.mock.server.web.auth.mapper.UserInfoMapper;
import som.make.mock.server.web.system.repository.SysUserDao;
import som.make.mock.server.web.system.entity.SysPerm;
import som.make.mock.server.web.system.entity.SysRole;
import som.make.mock.server.web.system.entity.SysUser;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysAuthService {

    private final PasswordEncoder passwordEncoder;
    private final TokenCache tokenCache;
    private final SysUserDao sysUserDao;
    private final UserInfoMapper userInfoMapper;

    public SysAuthService(PasswordEncoder passwordEncoder, TokenCache tokenCache, SysUserDao sysUserDao, UserInfoMapper userInfoMapper) {
        this.passwordEncoder = passwordEncoder;
        this.tokenCache = tokenCache;
        this.sysUserDao = sysUserDao;
        this.userInfoMapper = userInfoMapper;
    }

    public TokenModel login(UserLoginDto userLoginDto) throws ExpressException {
        Optional<SysUser> optionalSysUser = sysUserDao.findByLoginName(userLoginDto.getLoginName());
        if (optionalSysUser.isPresent()) {
            SysUser user = optionalSysUser.get();
            if (passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
                String token = Base64.encodeBase64String(UuidUtils.simpleUuid().getBytes(StandardCharsets.UTF_8));
                String refreshToken = passwordEncoder.getNoPrefixEncoder(token);
                Set<SysRole> sysRoleSet = new HashSet<>();
                Set<String> authoritySet = new HashSet<>();
                user.getSysRoleList().forEach(sysRole -> {
                    sysRoleSet.add(sysRole);
                    authoritySet.addAll(sysRole.getSysPermList().stream().map(SysPerm::getPermCode).collect(Collectors.toSet()));
                });
                Authentication<SysUser, SysRole> authentication = new Authentication<>();
                authentication.setToken(token);
                authentication.setPrincipal(user);
                authentication.setRoles(sysRoleSet);
                authentication.setAuthorities(authoritySet);
                TokenDetails tokenDetails = new TokenDetails(token, refreshToken, 0, authentication);
                tokenCache.save(token, tokenDetails);
                tokenCache.save(refreshToken, tokenDetails);

                UserInfo userInfo = userInfoMapper.userToUserInfo(user);
                // 返回前端的信息
                Map<String, Object> authMap = new HashMap<>();
                Set<String> roles = sysRoleSet.stream().map(SysRole::getRoleCode).collect(Collectors.toSet());
                return new TokenModel(token, refreshToken, user.getLoginName(), userInfo, roles, authoritySet);
            } else {
                throw new ExpressException(ExpressCode.USER_LOGIN_ERROR);
            }
        } else {
            throw new ExpressException(ExpressCode.USER_NOT_EXISTS);
        }
    }

    public Integer logout(Authentication<SysUser, SysRole> authentication) throws ExpressException {
        TokenDetails tokenDetails = tokenCache.get(authentication.getToken());
        tokenCache.remove(tokenDetails.getToken());
        tokenCache.remove(tokenDetails.getRefreshToken());
        return 1;
    }

}
