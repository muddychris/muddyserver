package com.muddycottage.muddyserver.web;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity ;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationContextAware {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                // disable cross-site request forgery protection...
                // ... required for websites but not for REST applications
                // TODO change this when we added support for web pages & apps
                .csrf().disable();

        http
                .requiresChannel()
                .anyRequest()
                .requiresSecure();

        http.authorizeRequests()
                .antMatchers("/**")
                .permitAll();
    }

    /*
    @Override
    protected void registerAuthentication(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.inMemoryAuthentication()
                .withUser("user").password("password").roles("ADMIN");
    }
     */
}

