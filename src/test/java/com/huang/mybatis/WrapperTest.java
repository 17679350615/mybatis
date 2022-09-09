package com.huang.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huang.mybatis.mapper.UserMapper;
import com.huang.mybatis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    void test() {
        // 查询name不为空、并且邮箱为空且年龄大于等于12的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name")
                        .isNotNull("email")
                                .ge("age",12);
        userMapper.selectList(wrapper).forEach(System.out::println);

    }

    @Test
    void test1(){
        // 查询名字
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","sandy");
        User user = userMapper.selectOne(wrapper); // 查询一个数据，查询多个数据使用list或者map
        System.out.println(user);
    }

    @Test
    void test2(){
        // 查询年龄在20~30之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",20,30); // 区间
        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    //  模糊查询！
    @Test
    void test3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.notLike("name", "e")
                .likeRight("email","t"); // 以‘t’开头的email

        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);

    }

    @Test
    void test4(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id", "select id from user where id<3"); // 条件构造器依然支持sql语句查询

        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);


    }

    @Test
    void test5(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id"); // 降序排序

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}
