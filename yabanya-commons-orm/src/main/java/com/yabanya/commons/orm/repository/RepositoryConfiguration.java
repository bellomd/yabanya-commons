package com.yabanya.commons.orm.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = {RepositoryConfiguration.ENTITY_BASE_PACKAGE})
@EnableJpaRepositories(
        repositoryBaseClass = RepositoryImpl.class,
        basePackages = {RepositoryConfiguration.REPOSITORY_BASE_PACKAGE})
@EnableTransactionManagement
public class RepositoryConfiguration {

    static final String ENTITY_BASE_PACKAGE = "com.yabanya";
    static final String REPOSITORY_BASE_PACKAGE = "com.yabanya";
}
