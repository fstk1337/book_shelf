package com.fstk1337.shelf.app.service;

import com.fstk1337.shelf.web.dto.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class IdProvider implements InitializingBean, DisposableBean, BeanPostProcessor {

    Logger logger = LogManager.getLogger(IdProvider.class);

    public String provideId(Book book) {
        return this.hashCode() + "_" + book.hashCode();
    }

    private void initIdProvider() {
        //logger.info("provider INIT");
    }

    private void destroyIdProvider() {
        //logger.info("provider DESTROY");
    }

    @Override
    public void afterPropertiesSet() {
        //logger.info("provider afterPropertiesSet invoked");
    }

    @Override
    public void destroy() {
        //logger.info("DisposableBean destroy invoked");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //logger.info("postProcessBeforeInitialization invoked by bean " + beanName);
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //logger.info("postProcessAfterInitialization invoked by bean " + beanName);
        return null;
    }

    @PostConstruct
    public void postConstructIdProvider() {
        //logger.info("PostConstruct annotated method called");
    }

    @PreDestroy
    public void preDestroyIdProvider() {
        //logger.info("PreDestroy annotated method called");
    }
}
