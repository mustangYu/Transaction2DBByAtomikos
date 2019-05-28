package comTransDbMustang.dbConfig.entity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *  @Component，并把这些类纳入进spring容器中管理。
 *  数据库1
 *  @author: mustang.yu
 */
@Component
@Data
public class DBOne {
    @Value("${spring.datasource.firstDb.url}")
    private String url;
    @Value("${spring.datasource.firstDb.username}")
    private String username;
    @Value("${spring.datasource.firstDb.password}")
    private String password;
}
