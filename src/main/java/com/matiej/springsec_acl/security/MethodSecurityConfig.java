package com.matiej.springsec_acl.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.acls.domain.*;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCaching
@RequiredArgsConstructor
public class MethodSecurityConfig {
    private final DataSource dataSource;

    @Autowired
    org.springframework.cache.CacheManager cacheManager;

    @Bean
    public JdbcMutableAclService aclService() {
        JdbcMutableAclService service = new JdbcMutableAclService(dataSource, lookupStrategy(), aclCache());
        service.setClassIdentityQuery("SELECT @@IDENTITY");
        service.setSidIdentityQuery("SELECT @@IDENTITY");
        return service;
    }

    @Bean
    public AclCache aclCache() {
        return new SpringCacheBasedAclCache(cacheManager.getCache("aclCache"),
                permissionGrantingStrategy(), aclAuthorizationStrategy());
    }

    @Bean
    public LookupStrategy lookupStrategy() {
        return new BasicLookupStrategy(dataSource, aclCache(), aclAuthorizationStrategy(), permissionGrantingStrategy());
    }

    @Bean
    public AclAuthorizationStrategy aclAuthorizationStrategy() {
        return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ADMIN"));
    }

    @Bean
    public PermissionGrantingStrategy permissionGrantingStrategy() {
        return new DefaultPermissionGrantingStrategy(new ConsoleAuditLogger());
    }
}