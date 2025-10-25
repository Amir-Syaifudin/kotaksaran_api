package id.ac.stis.kotaksaran.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * (dari BAB 3.4.e)
 * Konfigurasi JPA Manual Eksplisit.
 * Solusi terakhir untuk masalah "Found 0 JPA repository interfaces".
 * Kita secara eksplisit memberi tahu Spring di mana mencari Entity dan Repository.
 */
@Configuration
@EnableTransactionManagement
// PAKSA SCAN REPOSITORY DI PACKAGE INI
@EnableJpaRepositories(basePackages = "id.ac.stis.kotaksaran.repository")
public class PersistenceJPAConfig {

    @Autowired
    private Environment env;

    /**
     * Bean #1: DataSource (Menggunakan DriverManagerDataSource karena HikariCP sering bermasalah saat manual config)
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    /**
     * Bean #2: EntityManagerFactory (Mengkonfigurasi Hibernate/JPA).
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        // PAKSA SCAN ENTITY DI PACKAGE INI
        em.setPackagesToScan("id.ac.stis.kotaksaran.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        // Mengatur properti Hibernate
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql", "false"));
        em.setJpaProperties(properties);

        return em;
    }

    /**
     * Bean #3: Transaction Manager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
