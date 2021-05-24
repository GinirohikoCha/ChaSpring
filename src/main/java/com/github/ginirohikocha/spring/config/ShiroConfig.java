package com.github.ginirohikocha.spring.config;

import com.github.ginirohikocha.spring.shiro.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GinirohikoCha
 * @since 2021/5/20
 */
@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        return new UserRealm();
    }

    @Bean
    public SessionsSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> shiroFilterChainDefinitionMap = new HashMap<>();
        shiroFilterChainDefinitionMap.put("/auth/logout", "logout");
        shiroFilterChainDefinitionMap.put("/auth/**", "anon");
        shiroFilterChainDefinitionMap.put("/test/**", "anon");
        shiroFilterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setLoginUrl("/auth/login");
        shiroFilterFactoryBean.setSuccessUrl("/test/hello");
        shiroFilterFactoryBean.setUnauthorizedUrl("/auth/register");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
