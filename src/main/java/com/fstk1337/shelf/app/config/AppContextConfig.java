package com.fstk1337.shelf.app.config;

import com.fstk1337.shelf.app.service.IdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "com.fstk1337.shelf.app")
public class AppContextConfig {

    @Bean(initMethod = "initIdProvider", destroyMethod = "destroyIdProvider")
    @Scope("prototype")
    public IdProvider idProvider() {
        return new IdProvider();
    }
}
