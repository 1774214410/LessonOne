package com.liuzw.common.config.configClass;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 使用注解读取配置文件内容
 * Email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/5 11:01
 */
@Configuration
@PropertySource(value = {"classpath:properties/reapy.properties"})
public class AppConfig {

    @Value("${app.username}")
    private String username;

    @Value("${app.url}")
    private String url;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
