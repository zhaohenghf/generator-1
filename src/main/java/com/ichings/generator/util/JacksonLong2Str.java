package com.ichings.generator.util;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 避免 JavaScript long 丢失精度
 *
 * @author 宋欢
 */
public final class JacksonLong2Str {
    /**
     * Long.class -> String
     * Long.TYPE -> String
     *
     * @return SimpleModule
     */
    public static SimpleModule newModule() {
        SimpleModule module = new SimpleModule();

        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(Long.TYPE, ToStringSerializer.instance);

        return module;
    }

}
