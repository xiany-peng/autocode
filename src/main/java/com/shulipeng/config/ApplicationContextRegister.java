package com.shulipeng.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author pengxianyang
 * @Description:
 * @date 2018/4/2
 */
@Component
@Configuration
public class ApplicationContextRegister implements ApplicationContextAware{
    private static Logger logger = LoggerFactory.getLogger(ApplicationContextRegister.class);
    private static ApplicationContext APPLICATION_CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("ApplicationContext registed-->" + applicationContext);
        APPLICATION_CONTEXT = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return APPLICATION_CONTEXT;
    }

    /**
     * 获取容器对象
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> type) {
        return APPLICATION_CONTEXT.getBean(type);
    }
}
