package com.valchkou.demo.repo

/**
 * Created by evalchkou on 11/15/2017.
 */
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration

abstract class BaseConfig extends AbstractCassandraConfiguration {
    protected String contactPoints
    protected String keyspaceName
    protected String compression

    @Override
    protected String getKeyspaceName() {
        return keyspaceName
    }

    void setKeyspaceName(String keyspaceName) {
        this.keyspaceName = keyspaceName
    }
}