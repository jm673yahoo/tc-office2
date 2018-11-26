package ca.mydemo.tcoffice;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
//@ComponentScan({"ca.mydemo.tcoffice.dao.mapper"})
@MapperScan("ca.mydemo.tcoffice.dao.mapper")
public class DataSourceConfig //implements TransactionManagementConfigurer
{

    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private Environment env;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {

        DataSource dataSource = null;
        /**
         *      JndiTemplate jndi = new JndiTemplate();
         *      dataSource = jndi.lookup("jdbc/tcdatav3", DataSource.class);
         */
        try {
            if (dataSource == null) {
                JndiTemplate jndi = new JndiTemplate();
                dataSource = jndi.lookup("java:comp/env/jdbc/tcdatav3", DataSource.class);
            }
        } catch (Exception e) {
            logger.error("NamingException for java:comp/env/jdbc/tcdatav3", e);
        }

        logger.info("==============jndi done==========================================" + dataSource == null);

        try {
            if (dataSource == null) {
                BasicDataSource dataSource2 = new BasicDataSource();
                dataSource2.setDriverClassName("org.mariadb.jdbc.Driver");
                dataSource2
                        .setUrl("jdbc:mariadb://testing-appv3.czfrtjykov7r.ca-central-1.rds.amazonaws.com:3306/appv3_db");
                //                                 testing-appv3.czfrtjykov7r.ca-central-1.rds.amazonaws.com:3306/appv3_db
                dataSource2.setUsername("APP_MAIN");
                dataSource2.setPassword("r9t5jFWQ");

                dataSource = dataSource2;
            }
        } catch (Exception e) {
            logger.error("NamingException for db connection", e);

        } finally {

        }
        return dataSource;
    }

    /*
            @Bean
            public SqlSessionFactory sqlSessionFactory() throws Exception{

                SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
                ssf.setMapperLocations(new Resource[]{
                        new ClassPathResource("mybatis/dao/mapper/biz.xml")});

                ssf.setDataSource(dataSource());

                return ssf.getObject();
            }

            @Bean
            public SqlSession sqlSession() throws Exception{
                return new SqlSessionTemplate(this.sqlSessionFactory());
            }

            @Bean
            public MapperScannerConfigurer getMapperScannerConfigurer(){
                MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
                mapperScannerConfigurer.setBasePackage("ca.mydemo.tcoffice.dao.mapper");
                return mapperScannerConfigurer;
            }
    */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("ca.mydemo.tcoffice.domain");
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/dao/mapper/*.xml"));
        return sessionFactory;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

//        @Bean
//        public PlatformTransactionManager txManager() {
//            DataSourceTransactionManager txnManager = new DataSourceTransactionManager(dataSource());
//            txnManager.setEnforceReadOnly(true);
//            txnManager.setDefaultTimeout(36 * 1000);
//            return txnManager;
//        }
//
//       // @Override
//        public PlatformTransactionManager annotationDrivenTransactionManager() {
//            return txManager();
//        }

}
