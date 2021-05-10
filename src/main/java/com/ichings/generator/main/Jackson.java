package com.ichings.generator.main;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichings.generator.util.JacksonLong2Str;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Web Json
 *
 * @author 宋欢
 */
@JsonComponent
public class Jackson {
    /**
     * 避免 JavaScript long 丢失精度
     */
    @Bean
    public ObjectMapper longToStr(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper r = builder.createXmlMapper(false).build();

        r.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        r.registerModule(JacksonLong2Str.newModule());

        return r;
    }

}
