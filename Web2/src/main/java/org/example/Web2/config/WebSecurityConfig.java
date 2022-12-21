/**
 * Класс, который при старте приложения конфигурирует WebSecurity
 */

package org.example.Web2.config;

import org.example.Web2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()                                //Включаем авторизацию
                    .antMatchers("/", "/registration").permitAll()
                    .anyRequest().authenticated()                   //Для всех остальных требуем авторизацию
                .and()
                    .formLogin()                                    //Включаем formLogin
                    .loginPage("/login")                            //Указываем где находится loginPage
                    .defaultSuccessUrl("/main", true)
                    .permitAll()                                    //Разрешаем всем пользоваться этим
                .and()
                    .rememberMe()
                .and()
                    .logout()                                       //Включаем logout
                    .permitAll();                                   //Разрешаем всем пользоваться этим

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){return new BCryptPasswordEncoder(7);}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}