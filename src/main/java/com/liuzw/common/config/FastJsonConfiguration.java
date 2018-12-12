package com.liuzw.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.springfox.SwaggerJsonSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

/**
 * @Author: liuzw     Email :a1774214410@163.com
 * @Date: 2018/10/20 23:31
 */
//@Configuration
public class FastJsonConfiguration extends WebMvcConfigurationSupport {

    /**
     * 修改自定义消息转换器
     * @param converters  消息转换器列表
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        /**调用父类的配置*/
        super.configureMessageConverters(converters);
        /**创建fastjson消息转换器*/
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        /**创建配置类*/
        FastJsonConfig fastJsonConfig  = new FastJsonConfig();
        /**添加swagger视图*/
        fastJsonConfig.getSerializeConfig().put(Json.class, SwaggerJsonSerializer.instance);
        /**修改配置返回内容的过滤*/
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteDateUseDateFormat);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        /**将fastjson添加到视图消息转换器列表内*/
        converters.add(fastJsonHttpMessageConverter);
    }


}
