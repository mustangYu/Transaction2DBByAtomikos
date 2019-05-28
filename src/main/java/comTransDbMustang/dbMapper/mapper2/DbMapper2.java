package comTransDbMustang.dbMapper.mapper2;

import comTransDbMustang.dbModel.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DbMapper2 {
    public int insertInfo1(User user);
}
