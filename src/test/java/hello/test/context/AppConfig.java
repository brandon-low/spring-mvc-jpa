package hello.test.context;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("hello.test.context")

//@EnableJpaRepositories(basePackages = {
 //       "net.petrikainulainen.springdata.jpa.todo"
//})
//@EnableTransactionManagement
public class AppConfig {
	
	/*
	 * spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=none
spring.jpa.hibernate.show-sql=true
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=admin


spring.datasource.initialization-mode=always
spring.datasource.initialize=true
spring.datasource.schema=classpath:/schema.sql
spring.datasource.continue-on-error=true

@Bean
public DataSource dataSource() {
    try {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);

        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setPoolName("springHikariCP");

        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

        return new HikariDataSource(hikariConfig);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

Server server = null;
            try {
                server = Server.createTcpServer("-tcpAllowOthers").start();
                Class.forName("org.h2.Driver");
                Connection conn = DriverManager.
                    getConnection("jdbc:h2:tcp://localhost/~/stackoverflow", "sa", "");
                System.out.println("Connection Established: "
                        + conn.getMetaData().getDatabaseProductName() + "/" + conn.getCatalog());
             
            } catch (Exception e) {
                e.printStackTrace();
            }
            @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
    
    @Bean
public DataSource dataSource() throws SQLException {
    DatabaseUtil.createSchemaIfNeeded("postgresql", databaseAddress, dbName, dbUser, dbPassword, dbSchemaName);
    HikariConfig config = new HikariConfig();
    if (ssl && Files.exists(Paths.get(certFile))) {
        config.addDataSourceProperty("ssl", "true");
        config.addDataSourceProperty("sslfactory", "org.postgresql.ssl.SingleCertValidatingFactory");
        config.addDataSourceProperty("sslfactoryarg", "file://" + certFile);
    }
    if (nodeConfig.isNodeIdSpecified()) {
        config.addDataSourceProperty("ApplicationName", nodeConfig.getId());
    }
    config.setDriverClassName("io.opentracing.contrib.jdbc.TracingDriver");
    config.setJdbcUrl(String.format("jdbc:tracing:postgresql://%s/%s?currentSchema=%s", databaseAddress, dbName, dbSchemaName));
    config.setUsername(dbUser);
    config.setPassword(dbPassword);
    config.setMaximumPoolSize(poolSize);
    config.setMinimumIdle(minimumIdle);
    config.setConnectionTimeout(SECONDS.toMillis(connectionTimeout));
    config.setIdleTimeout(MINUTES.toMillis(idleTimeout));
    return new HikariDataSource(config);
}
	 */
	
	// create bean etc
	
	
	/*
	@Bean(destroyMethod = "close")
    DataSource dataSource(Environment env) {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
        dataSourceConfig.setUsername(env.getRequiredProperty("db.username"));
        dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));
 
        return new HikariDataSource(dataSourceConfig);
    }
    
    
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, 
                                                                Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("net.petrikainulainen.springdata.jpa.todo");
 
        Properties jpaProperties = new Properties();
     
        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
 
        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto", 
                env.getRequiredProperty("hibernate.hbm2ddl.auto")
        );
 
        //Configures the naming strategy that is used when Hibernate creates
        //new database objects and schema elements
        jpaProperties.put("hibernate.ejb.naming_strategy", 
                env.getRequiredProperty("hibernate.ejb.naming_strategy")
        );
         //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        jpaProperties.put("hibernate.show_sql", 
                env.getRequiredProperty("hibernate.show_sql")
        );
 
        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        jpaProperties.put("hibernate.format_sql", 
                env.getRequiredProperty("hibernate.format_sql")
        );
 
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
 
        return entityManagerFactoryBean;
    }
    
    
     @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
     
    */
}
