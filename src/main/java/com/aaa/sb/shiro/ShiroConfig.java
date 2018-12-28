package com.aaa.sb.shiro;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
        import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
        import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

        import java.util.LinkedHashMap;
        import java.util.Map;

/**
 * className:ShiroConfig
 * discription:
 * author:Dbailing
 * createTime:2018-12-06 14:45
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建 ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /*
          添加Shiro内置过滤器,实现权限相关拦截
            常用过滤器：anon 无需登录 可以访问
                        authc 必须认证才可以访问
                        user 如果使用了rememberMe 的功能可以直接访问
                        perms 该资源必须授于资源权限才可以访问
                       role 该资源必须得到角色权限才可以访问*/
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面;
        // shiroFilterFactoryBean.setUnauthorizedUrl("/");
        //保证顺序用这个LinkedHashMap
        //我认为拦截的是方法
        //filterMap.put("/user/update","authc");
        //设置未授权的页面
        //shiroFilterFactoryBean.setUnauthorizedUrl("/user/unAuthorization");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        Map<String,String> filterMap = new LinkedHashMap<String, String>();
        //不拦截该资源(放行)
        filterMap.put("/user/login","anon");
        filterMap.put("/css/**","anon");
        filterMap.put("/img/**","anon");
        filterMap.put("/js/**","anon");

        //拦截所有请求
        filterMap.put("/*/*","authc");
        //filterMap.put("/user/index","authc");



        //授权过滤器
        //注意：当前授权拦截后，shiro会自动跳转到未授权的页面
        //filterMap.put("/add","perms[user:add]");
        //filterMap.put("/update","perms[user:update]");

        //修改调整跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/user/toLogin");
        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/noAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }



    /**
     * 创建DefaultSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm   userRealm来自下面的方法
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // bean的意思是让方法返回的对象放入spring环境
    @Bean(name = "userRealm") //方法返回的对象放入spring的环境 以便让上面的方法使用
    public UserRealm getRealm(){
        return new UserRealm();
    }

    /**
     * 配置shiroDialect用于thymeleaf 和shiro标签配合使用
     *
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return  new ShiroDialect();
    }
}
