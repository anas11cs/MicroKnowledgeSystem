package com.tkxel.microknowledgesystem.configuration;

import com.tkxel.microknowledgesystem.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtSecurityConfiguration {
    @Bean
    public FilterRegistrationBean jwtCustomFilter() {
        FilterRegistrationBean filter= new FilterRegistrationBean();
        filter.setFilter(new JwtFilter());
        // provide endpoints which needs to be restricted.
        // All Endpoints would be restricted if unspecified
        filter.addUrlPatterns("/createuser");
        return filter;
    }
}
