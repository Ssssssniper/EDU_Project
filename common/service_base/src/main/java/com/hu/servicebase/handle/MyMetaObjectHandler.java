package com.hu.servicebase.handle;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**  自动填充实现
 * 插入和更新表时的自动填充触发事件
 *
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    // 插入时触发
    @Override
    public void insertFill(MetaObject metaObject) {
        // 属性名称
        this.setFieldValByName("createTime", new Date(),metaObject);
        this.setFieldValByName("updateTime", new Date(),metaObject);
    }
    // 更新时触发
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}