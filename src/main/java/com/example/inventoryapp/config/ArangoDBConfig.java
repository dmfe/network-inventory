package com.example.inventoryapp.config;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.AbstractArangoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableArangoRepositories(basePackages = {"com.example.inventoryapp.repositories"})
public class ArangoDBConfig extends AbstractArangoConfiguration {

    @Value("${arango.db-host}")
    private String host;

    @Value("${arango.db-name}")
    private String dbName;

    @Value("${arango.db-port}")
    private int port;

    @Value("${arango.user-name}")
    private String user;

    @Value("${arango.password}")
    private String password;

    @Override
    protected ArangoDB.Builder arango() {
        return new ArangoDB.Builder()
                .host(host, port)
                .user(user)
                .password(password);
    }

    @Override
    protected String database() {
        return dbName;
    }
}
