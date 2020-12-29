package com.jun.reservation.config;

import com.jun.reservation.security.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        //super.configure(http);
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .and().csrf().disable().cors();

        //开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面
//        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/h")
//                .successForwardUrl("/h");
        http.formLogin().loginPage("/login").loginProcessingUrl("/login").failureForwardUrl("/fail").successForwardUrl("/loginSuccess");
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
        //suer.configure(auth);

        //auth.jdbcAuthentication()...
//
////      不
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())   //在Spring Security 5.0中新增了多种加密方式，页改变了密码的格式
//                .withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1", "VIP2")
//                .and()
//                .withUser("lisi").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP2", "VIP3")
//                .and()
//                .withUser("wangwu").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1", "VIP3");
//

        auth.userDetailsService(userDetailsServiceImp).passwordEncoder(new BCryptPasswordEncoder());

//        authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }
}
