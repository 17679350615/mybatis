package com.huang.mybatis.mapper;

// 使用mybatis-plus只需要在对应的Mapper上面继承基本的类 BaseMapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.mybatis.pojo.User;
import org.springframework.stereotype.Repository;

@Repository // 代表持久层
public interface UserMapper extends BaseMapper<User> {
    // 所有的CRUD操作已经基本完成了
    // 不需要像以前一样配置一堆文件
}
