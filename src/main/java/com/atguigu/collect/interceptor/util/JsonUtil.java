package com.atguigu.collect.interceptor.util;

import com.alibaba.fastjson.JSON;

import java.nio.charset.StandardCharsets;

/**
 * @author 姜来
 * @ClassName JsonUtil.java
 * @createTime 2022年09月19日 09:16:00
 */
public class JsonUtil {
    /**
     * 判断参数的字符串是否符合json格式
     * @param body
     * @return
     */
    public static boolean isJSONValidate(byte[] body) {
        // 1. 将参数转换为Stirng类型
        //String 我是错误的 = body.toString();
        String message = new String(body, StandardCharsets.UTF_8);
        // 2. 判断字符串是否符合json格式
        // 根据这行代码是否执行成功判断字符串是否符合json格式
        try {
            JSON.parseObject(message);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
