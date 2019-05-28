package comTransDbMustang.dbConfig.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import comTransDbMustang.dbConfig.entity.DBTwo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;

/**
 * @title:DBTwo配置类
 * @author: mustang.yu
 */

@Configuration
@MapperScan(basePackages = "comTransDbMustang.dbMapper.mapper2",sqlSessionTemplateRef = "twoSqlSessionTemplate")
public class DbTwoConfig {
    /**
     * 设置数据源
     * @author: mustang.yu
     */
    @Bean(name = "mustangDBTwo")
    //@primary---------意思是在众多相同的bean中，优先使用用@Primary注解的bean.
    public DataSource setDataSource (DBTwo dbTwo) throws Exception{
        MysqlXADataSource mysqlXADataSource2 = new MysqlXADataSource();

        mysqlXADataSource2.setUrl(dbTwo.getUrl());
        mysqlXADataSource2.setUser(dbTwo.getUsername());
        mysqlXADataSource2.setPassword(dbTwo.getPassword());
        mysqlXADataSource2.setPinGlobalTxToPhysicalConnection(true);
        //创建atomikos全局事务
        AtomikosDataSourceBean xaDataSource2 = new AtomikosDataSourceBean();
        xaDataSource2.setXaDataSource(mysqlXADataSource2);
        xaDataSource2.setUniqueResourceName("mustangDBTwo");
        return xaDataSource2;
    }

    /**
     * 设置sqlSessionFactory
     * @author: mustang.yu
     */
    @Bean(name = "twoSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("mustangDBTwo")DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/twoMapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    /**
     * 设置sqlSessionTemplate
     * @author: mustang.yu
     */
    @Bean(name = "twoSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate (@Qualifier("twoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
