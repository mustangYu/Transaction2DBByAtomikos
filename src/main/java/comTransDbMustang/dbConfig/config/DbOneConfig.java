package comTransDbMustang.dbConfig.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import comTransDbMustang.dbConfig.entity.DBOne;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;

/**
 * @title:DBOne配置类(默认数据源)
 * @author: mustang.yu
 *
 * Atomikos:开源的事务管理器
 * 分布式事务： 分布式事务就是指事务的参与者，支持事务的服务器，资源服务器，以及事务管理器分别位于不同的分布式系统的不同节点之上。分布式事务就是为了保证“不同数据库的数据一致性” 。
 * XA 协议 是可以在数据库commit 之后进行回滚的。
 */

@Configuration
@MapperScan(basePackages = "comTransDbMustang.dbMapper.mapper1",sqlSessionTemplateRef = "oneSqlSessionTemplate")
public class DbOneConfig {
    /**
     * 设置数据源
     * @author: mustang.yu
     */
    @Bean(name = "mustangDBOne")
    @Primary
    //@primary---------意思是在众多相同的bean中，优先使用用@Primary注解的bean.
    public DataSource setDataSource (DBOne dbOne) throws Exception{
        MysqlXADataSource mysqlXADataSource1 = new MysqlXADataSource();

        mysqlXADataSource1.setUrl(dbOne.getUrl());
        mysqlXADataSource1.setUser(dbOne.getUsername());
        mysqlXADataSource1.setPassword(dbOne.getPassword());
        mysqlXADataSource1.setPinGlobalTxToPhysicalConnection(true);
        //创建atomikos全局事务
        AtomikosDataSourceBean xaDataSource1 = new AtomikosDataSourceBean();
        xaDataSource1.setXaDataSource(mysqlXADataSource1);
        xaDataSource1.setUniqueResourceName("mustangDBOne");
        return xaDataSource1;
    }

    /**
     * 设置sqlSessionFactory
     * @author: mustang.yu
     */
    @Bean(name = "oneSqlSessionFactory")
    @Primary
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("mustangDBOne")DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/oneMapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    /**
     * 设置sqlSessionTemplate
     * @author: mustang.yu
     */
    @Bean(name = "oneSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate setSqlSessionTemplate (@Qualifier("oneSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
