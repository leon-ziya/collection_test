package com.atguigu.collect.interceptor;


import com.atguigu.collect.interceptor.util.JsonUtil;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.Iterator;
import java.util.List;


/**
 * @author 姜来
 * @ClassName ETLInterceptor.java
 * @createTime 2022年09月19日 08:54:00
 */
public class ETLInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    /**
     * 拦截单个事件
     * @param event
     * 获取每个事件的body，判断是否符合json格式，是就在头部插入type:true,不是插入type:fasle
     * @return
     */
    @Override
    public Event intercept(Event event) {
        // 1. 判断
        if(JsonUtil.isJSONValidate(event.getBody()))
            event.getHeaders().put("type","true");
        else
            event.getHeaders().put("type","false");
        return event;
    }

    /**
     * 拦截批量事件
     * @param events
     * 调用单个事件拦截，根据其头部标签的type值，判断是否符合json格式，不符合的移除掉
     * @return
     */
    @Override
    public List<Event> intercept(List<Event> events) {
        // 1. 要移除数据，就得用迭代器
        Iterator<Event> iterator = events.iterator();
        // 2. 根据迭代1器进行遍历
        while (iterator.hasNext()){
            // 3. 获取Event
            Event nextEvent = iterator.next();
            // 4. 调用单个事件拦截器进行拦截
            intercept(nextEvent);
            // 5. 判断nextEvent的头部中type的值是false的就移除
            if(nextEvent.getHeaders().get("type").equals("false")){
                iterator.remove();
            }
        }
        return events;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{
        @Override
        public Interceptor build() {
            return new ETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }

}
