package com.justdoit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
        // spring will place this bCryptPasswordEncoder object in what;s the Application Context (registry of beans and other things)
        // then, anywhere we @Autowire, spring can inject no problem
    }

    // TODO: Isadora to get sqlSessionFactory bean here
}
