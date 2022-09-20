package com.atguigu.collect.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 姜来
 * @ClassName TimeStampInterceptor.java
 * @createTime 2022年09月20日 14:37:00
 */
public class TimeStampInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    /**
     *  拦截单个事件
     *  拦截事件，并从body里面获取事件戳，将其插入到头部中
     * @param event
     * @return
     */
    @Override
    public Event intercept(Event event) {
        // 1. 获取body中的数据
        String jsonStr = new String(event.getBody(), StandardCharsets.UTF_8);
        // 2. 根据json字符串创建json对象
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        // 3. 获取时间戳
        String ts = jsonObject.getString("ts");
        // 4. 将时间戳插入到头部中
        event.getHeaders().put("timestamp",ts);
        return event;
    }

    /**
     * 拦截批量事件
     * @param events
     * @return
     */
    @Override
    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
           intercept(event);
        }
        return events;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{
        @Override
        public Interceptor build() {
            return new TimeStampInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
