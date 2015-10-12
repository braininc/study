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

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.JPA_TRANSACTION_MANAGER;
import static org.hibernate.cfg.AvailableSettings.DIALECT;
import static org.hibernate.cfg.AvailableSettings.FORMAT_SQL;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;
import static org.hibernate.cfg.AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS;
import static org.springframework.integration.jpa.support.PersistMode.DELETE;

/**
 * @author Eugene Stepanenkov
 */
@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class CoreDataContext {

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

    @Value("${db.hibernate.formatSql}")
    private String formatSql;

    @Value("${db.hibernate.useNewIdGeneratorMappings}")
    private String useNewIdGenerator;

    @Autowired
    private ExpressionParser expressionParser;

    @Bean
    public Properties vendorProperties() {

        Properties properties = new Properties();
        properties.setProperty(DIALECT, dialect);
        properties.setProperty(SHOW_SQL, showSql);
        properties.setProperty(FORMAT_SQL, formatSql);
        properties.setProperty(USE_NEW_ID_GENERATOR_MAPPINGS, useNewIdGenerator);

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

    @Autowired
    @Bean(name = JPA_TRANSACTION_MANAGER)
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factory);

        return transactionManager;
    }

    @Bean
    @Autowired
    public JpaExecutor importAddOrUpdateJpaExecutor(EntityManagerFactory factory) {

        JpaExecutor executor = new JpaExecutor(factory);
        executor.setEntityClass(Sinner.class);

        return executor;
    }

    @Bean
    @Autowired
    public JpaExecutor importFetchJpaExecutor(EntityManagerFactory factory) {

        JpaExecutor executor = new JpaExecutor(factory);
        executor.setEntityClass(Sinner.class);
        executor.setIdExpression(expressionParser.parseExpression("payload"));
        executor.setExpectSingleResult(true);

        return executor;
    }

    @Bean
    @Autowired
    public JpaExecutor importDeleteJpaExecutor(EntityManagerFactory factory) {

        JpaExecutor executor = new JpaExecutor(factory);
        executor.setEntityClass(Sinner.class);
        executor.setPersistMode(DELETE);
        executor.setIdExpression(expressionParser.parseExpression("payload"));
        executor.setExpectSingleResult(true);

        return executor;
    }

    @Bean
    @Autowired
    public JpaExecutor exportFetchJpaExecutor(EntityManagerFactory factory) {

        JpaExecutor executor = new JpaExecutor(factory);
        executor.setEntityClass(Sinner.class);
        executor.setExpectSingleResult(false);
        executor.setDeleteAfterPoll(true);

        return executor;
    }
}
