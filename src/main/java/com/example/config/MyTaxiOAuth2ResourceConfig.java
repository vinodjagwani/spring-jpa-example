package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * This class is responsible for Oauth2 resource configuration.
 * Created by vinodjagwani on 7/15/17.
 * <p/>
 */
@Configuration
@EnableResourceServer
public class MyTaxiOAuth2ResourceConfig extends ResourceServerConfigurerAdapter
{

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests().antMatchers("/v1/**")
            .authenticated();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

}
