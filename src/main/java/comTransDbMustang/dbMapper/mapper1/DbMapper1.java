package comTransDbMustang.dbMapper.mapper1;

import comTransDbMustang.dbModel.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DbMapper1 {
    public int insertInfo(User user);
}
