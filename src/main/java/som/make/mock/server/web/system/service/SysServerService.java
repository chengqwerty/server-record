package som.make.mock.server.web.system.service;

import org.springframework.stereotype.Service;
import som.make.mock.server.web.system.entity.SysServer;
import som.make.mock.server.web.system.repository.SysServerDao;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class SysServerService {

    private final SysServerDao sysServerDao;

    public SysServerService(SysServerDao sysServerDao) {
        this.sysServerDao = sysServerDao;
    }

    public SysServer createServer(SysServer server) {
        Timestamp nowTime = Timestamp.valueOf(LocalDateTime.now());
        server.setCreateTime(nowTime);
        server.setUpdateTime(nowTime);
        return sysServerDao.save(server);
    }

}
