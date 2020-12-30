package com.jun.reservation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jun.reservation.security.CustomAuthenticationFilter;
import com.jun.reservation.security.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *   security  configure
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制请求的授权规则
        http.authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .anyRequest().authenticated()
                .and()//customAuthenticationFilter 的filter顺序在UsernamePasswordAuthenticationFilter前面
                .addFilterAt(customAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class) //用重写的Filter替换掉原有的UsernamePasswordAuthenticationFilter
                .formLogin()
                .loginProcessingUrl("/login")
//                .successForwardUrl("/loginSuccess") 前后端分离 不需要跳转到这个页面请求
//                .failureForwardUrl("/fail") 前后端分离 不需要跳转到这个页面请求
//                .successHandler(authenticationSuccessHandler()) 自定义filter已经添加
//                .failureHandler(authenticationFailureHandler()) 自定义filter已经添加
                .and()
                .authorizeRequests()
                .and()
                .csrf()
                .disable()
                .cors().disable()
                .authorizeRequests()
                .and()
                .logout().logoutUrl("/logout").permitAll()
                .logoutSuccessHandler(logoutSuccessHandler())
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())//无认证权限处理handler
        ;

        //开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面
//        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/h")
//        http.formLogin().loginPage("/login").loginProcessingUrl("/").failureForwardUrl("/fail").successForwardUrl("/loginSuccess");
        //1、 /login 来到登陆页
        //2、重定向到/login?error表示登陆失败
        //3、更多详细功能
        //4、默认post形式的 /login 代表处理登陆
        //5、一旦定制loginPage  那么 loginPage的post请求就是登陆

        //开启自动配置的注销功能
//        http.logout().logoutSuccessUrl("/"); //注销成功以后来到首页
        //1、访问/logout 表示用户注销。清空 session
        //2、注销成功会返回 /login?logout 页面
        //3、默认post形式的 /login代表处理登陆


        //开启记住我功能
//        http.rememberMe().rememberMeParameter("remeber");
        //登陆成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登陆
        //点击注销会删除cookiebh ==
    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////      内存存用户登录权限等信息
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())   //在Spring Security 5.0中新增了多种加密方式，页改变了密码的格式
//                .withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1", "VIP2")
//                .and()
//                .withUser("lisi").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP2", "VIP3")
//                .and()
//                .withUser("wangwu").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1", "VIP3");
//
//      使用service 连接查询数据库，获取对应的认证权限信息
        auth.userDetailsService(userDetailsServiceImp).passwordEncoder(new BCryptPasswordEncoder());

    }

    //注册自定义的UsernamePasswordAuthenticationFilter
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
//      注意： 前后端分离要使用这些handler 不使用页面跳转的形式
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(authenticationFailureHandler());
//        默认的请求认证提交接口路径
//        该filter 默认拦截的登录请求接口路径  继承UsernamePasswordAuthenticationFilter 这个filter也是使用这个默认拦截
        filter.setFilterProcessesUrl("/login");

        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    protected AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                Map<String, Object> map = new HashMap<>();
                map.put("code",200);
                map.put("msg", "登录成功！");
                map.put("data", authentication.getPrincipal());
                System.out.println(map);
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                // 对象转json传输给前端
                out.write(new ObjectMapper().writeValueAsString(map));
                out.flush();
                out.close();
            }
        };
    }

    protected AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                Map<String, Object> map = new HashMap<>();
                map.put("code",402);
                map.put("data",null);
                map.put("message", "登录失败！");

                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                // 对象转json传输给前端
                out.write(new ObjectMapper().writeValueAsString(map));
                out.flush();
                out.close();
            }
        };
    }

    protected LogoutSuccessHandler logoutSuccessHandler(){
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                Map<String, Object> map = new HashMap<>();
                map.put("code",200);
                map.put("data",null);
                map.put("message", "注销成功！");
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                // 对象转json传输给前端
                out.write(new ObjectMapper().writeValueAsString(map));
                out.flush();
                out.close();
            }
        };
    }

    protected AuthenticationEntryPoint authenticationEntryPoint(){
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                Map<String, Object> map = new HashMap<>();
                map.put("code",403);
                map.put("data",null);
                map.put("message", "没有访问权限,请先登录！");
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                // 对象转json传输给前端
                out.write(new ObjectMapper().writeValueAsString(map));
                out.flush();
                out.close();
            }
        };
    }

}
