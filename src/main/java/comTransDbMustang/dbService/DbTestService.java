package comTransDbMustang.dbService;

import comTransDbMustang.dbMapper.mapper1.DbMapper1;
import comTransDbMustang.dbMapper.mapper2.DbMapper2;
import comTransDbMustang.dbModel.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DbTestService {
    @Autowired
    private DbMapper1 dbMapper1;
    @Autowired
    private DbMapper2 dbMapper2;

    /**
     * 测试不同数据库之间插入一条数据操作是否都满足全局事务
     * 每个库插入一条数据 成功返回值会返回int值
     */
    @Transactional
    public Integer doTest2DBInsert(User user) {
        Integer count1 = dbMapper1.insertInfo(user);
        Integer count2 = dbMapper2.insertInfo1(user);
        log.info("{}",count1+count2);
        return count1+count2;
    }
}
