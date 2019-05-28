package comTransDbMustang.dbConfig.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 *  @Component，并把这些类纳入进spring容器中管理。
 *  数据库2
 *  @author: mustang.yu
 */
@Component
@Data
public class DBTwo {
    @Value("${spring.datasource.secondDb.url}")
    private String url;
    @Value("${spring.datasource.secondDb.username}")
    private String username;
    @Value("${spring.datasource.secondDb.password}")
    private String password;
}
