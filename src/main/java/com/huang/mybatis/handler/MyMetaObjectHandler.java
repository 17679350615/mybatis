package com.huang.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Slf4j //日志
@Component // 一定不要忘记将处理器加入到IOC容器中
public class MyMetaObjectHandler implements MetaObjectHandler {

    //插入时的填充策略
    @Override // 重写
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill....");
        // setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }

    // 更新时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill....");
        // setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}
