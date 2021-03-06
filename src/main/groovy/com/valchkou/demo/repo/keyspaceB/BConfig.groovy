package com.valchkou.demo.repo.keyspaceB

import com.valchkou.demo.repo.BaseConfig
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean
import org.springframework.data.cassandra.core.CassandraAdminOperations
import org.springframework.data.cassandra.core.CassandraAdminTemplate
import org.springframework.data.cassandra.core.CassandraOperations
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories

/**
 * Created by evalchkou on 11/15/2017.
 */

@Configuration
@ConfigurationProperties("spring.data.cassandra.keyspace-b")
@EnableCassandraRepositories(
        basePackages = "com.valchkou.demo.repo.keyspaceB",
        cassandraTemplateRef = "keyspaceBTemplate"
)
class BConfig extends BaseConfig {


    @Override
    @Primary
    @Bean(name = "keyspaceBTemplate")
    CassandraAdminOperations cassandraTemplate() throws Exception {
        return new CassandraAdminTemplate(session().getObject(), cassandraConverter());
    }

    @Bean(name = "keyspaceBOperationsTemplate")
    CassandraOperations cassandraOperationsTemplate() throws Exception {
        return new CassandraTemplate(session().getObject(), cassandraConverter());
    }

    @Override
    @Bean(name = "keyspaceBSession")
    CassandraSessionFactoryBean session() throws Exception {
        CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(cluster().getObject());
        session.setConverter(cassandraConverter());
        session.setKeyspaceName(getKeyspaceName());
        session.setSchemaAction(getSchemaAction());
        session.setStartupScripts(getStartupScripts());
        session.setShutdownScripts(getShutdownScripts());
        return session;
    }

}