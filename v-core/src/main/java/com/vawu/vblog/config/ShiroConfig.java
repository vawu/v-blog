package com.vawu.vblog.config;

import com.vawu.vblog.shiro.authenticator.DefineModularRealmAuthenticator;
import com.vawu.vblog.shiro.matcher.RetryLimitCredentialsMatcher;
import com.vawu.vblog.shiro.realm.LoginRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {
    /**
     * shiro主配置入口
     *
     * @param securityManager 安全管理器
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean sfb = new ShiroFilterFactoryBean();
        sfb.setSecurityManager(securityManager);
        sfb.setLoginUrl("/login");
        sfb.setUnauthorizedUrl("/unauthorized");
        Map<String, Filter> filters = new HashMap<>();
        sfb.setFilters(filters);
        Map<String, String> filterMap = new LinkedHashMap<>();
        sfb.setFilterChainDefinitionMap(filterMap);
        return sfb;
    }

    /**
     * shiro安全管理器包含了realm,session管理器,缓存管理器
     */
    @Bean(name = "securityManager")
    public SecurityManager getSecurityManager(@Qualifier("userLoginRealm") LoginRealm loginRealm, DefineModularRealmAuthenticator authenticator) {
        DefaultWebSecurityManager dwm = new DefaultWebSecurityManager();
        List<Realm> loginRealms = new ArrayList<>();
        dwm.setAuthenticator(authenticator);
        loginRealm.setName("UserLogin");
        loginRealms.add(loginRealm);
        dwm.setRealms(loginRealms);
        dwm.setCacheManager(getCacheManager());
//        dwm.setSessionManager(defaultWebSessionManager());
        return dwm;
    }

    @Bean(name = "userLoginRealm")
    public LoginRealm getLoginRealm() {
        LoginRealm realm = new LoginRealm();
        realm.setCredentialsMatcher(getRetryLimitCredentialsMatcher());
        return realm;
    }

    /**
     * 匹配器用来加密和限制登陆次数
     */
    private RetryLimitCredentialsMatcher getRetryLimitCredentialsMatcher() {
        RetryLimitCredentialsMatcher rm = new RetryLimitCredentialsMatcher(getCacheManager());
        rm.setHashAlgorithmName("md5");
        rm.setHashIterations(4);
        return rm;

    }

    /**
     * 缓存管理器 ehcache可以换成redis或者memcache
     * 这里被匹配器和安全管理器使用
     */
    @Bean
    public EhCacheManager getCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache.xml");
        return ehCacheManager;
    }

}

