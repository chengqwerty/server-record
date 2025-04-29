package som.make.mock.server.web.auth.mapper;

import org.mapstruct.Mapper;
import som.make.mock.server.web.auth.entity.UserInfo;
import som.make.mock.server.web.system.entity.SysUser;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {

    UserInfo userToUserInfo(SysUser user);

}
