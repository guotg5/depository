package com.guotg.depository.util;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Type;

/**
 * JSON转换工具类
 */
public class IJSONUtil {

    public static String toJSON(Object o){
        return JSON.toJSONString(o);
    }

    public static <T> T toObject(String str, Type type){
        return JSON.parseObject(str, type);
    }

}
