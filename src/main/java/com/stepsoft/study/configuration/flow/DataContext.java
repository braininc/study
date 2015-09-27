package com.stepsoft.study.configuration.flow;

import com.stepsoft.study.data.entity.Sinner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.expression.ExpressionParser;
import org.springframework.integration.jpa.core.JpaExecutor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.HIBERNATE_DIALECT_PROPERTY_KEY;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.HIBERNATE_SHOW_SQL_PROPERTY_KEY;
import static org.springframework.integration.jpa.support.PersistMode.DELETE;

/**
 * @author Eugene Stepanenkov
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class DataContext {

    @Value("${db.connection.driverClassName}")
    private String driverClassName;

    @Value("${db.connection.url}")
    private String url;

    @Value("${db.connection.username}")
    private String username;

    @Value("${db.connection.password}")
    private String password;

    @Value("${db.hibernate.dialect}")
    private String dialect;

    @Value("${db.hibernate.showSql}")
    private String showSql;

    @Autowired
    private EntityManagerFactory factory;

    @Autowired
    private ExpressionParser expressionParser;

    @Bean
    public Properties vendorProperties() {

        Properties properties = new Properties();
        properties.setProperty(HIBERNATE_DIALECT_PROPERTY_KEY, dialect);
        properties.setProperty(HIBERNATE_SHOW_SQL_PROPERTY_KEY, showSql);

        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("com.stepsoft.study.data.entity");
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setJpaProperties(vendorProperties());

        return entityManagerFactory;
    }

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factory);

        return transactionManager;
    }

    @Bean
    public JpaExecutor importAddOrUpdateJpaExecutor() {

        JpaExecutor executor = new JpaExecutor(factory);
        executor.setEntityClass(Sinner.class);

        return executor;
    }

    @Bean
    public JpaExecutor importFetchJpaExecutor() {

        JpaExecutor executor = new JpaExecutor(factory);
        executor.setEntityClass(Sinner.class);
        executor.setIdExpression(expressionParser.parseExpression("payload"));
        executor.setExpectSingleResult(true);

        return executor;
    }

    @Bean
    public JpaExecutor importDeleteJpaExecutor() {

        JpaExecutor executor = new JpaExecutor(factory);
        executor.setEntityClass(Sinner.class);
        executor.setPersistMode(DELETE);

        return executor;
    }
}
