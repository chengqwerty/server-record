package som.make.mock.server.web.system.service;

import org.springframework.stereotype.Service;
import som.make.mock.server.web.system.dao.SysDictDao;
import som.make.mock.server.web.system.dao.SysDictDetailDao;

@Service
public class SysDictService {

    private final SysDictDao sysDictDao;
    private final SysDictDetailDao sysDictDetailDao;


    public SysDictService(SysDictDao sysDictDao, SysDictDetailDao sysDictDetailDao) {
        this.sysDictDao = sysDictDao;
        this.sysDictDetailDao = sysDictDetailDao;
    }

    public Integer addDict() {
        return 0;
    }

    public Integer addDictDetail() {
        return 0;
    }


}
