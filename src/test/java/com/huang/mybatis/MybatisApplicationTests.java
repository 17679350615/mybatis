package com.huang.mybatis;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.mybatis.mapper.UserMapper;
import com.huang.mybatis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class MybatisApplicationTests {

    // 继承了BaseMapper，所有的方法来自自己的父类
    // 也可以编写自己的扩展方法
    @Autowired
    private UserMapper userMapper;


    @Test
    void contextLoads() {
        // 查询所有用户
        // 参数是一个Wrapper，条件构造器，这里先不用，设置为null
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    // 测试插入
    @Test
    public void testInsert(){
        User user = new User();

        user.setName("嘿嘿");
        user.setAge(15);
        user.setEmail("123456789");

        int result = userMapper.insert(user); //自动生成id，数据库插入默认的id值是全局的唯一id
        System.out.println(result);
        System.out.println(user);
    }

    // 测试更新
    @Test
    public void testUpdate(){
        User user = new User();

        // 通过条件自动拼接动态sql
        user.setId(1L); // id是Long类型数据
        user.setName("芜湖");

        int result = userMapper.updateById(user);
        System.out.println(result);


    }

    // 测试乐观锁成功
    @Test
    public void testOptimisticLocker(){
        // 1.查询用户信息
        User user = userMapper.selectById(1L);
        // 2.修改用户信息
        user.setName("嘻嘻");
        // 3.执行更新操作
        userMapper.updateById(user);

    }

    // 多线程必须加锁！
    // 多线程下测试乐观锁失败
    @Test
    public void testOptimisticLocker1(){

        // 线程1
        User user = userMapper.selectById(1L);
        user.setName("嘻嘻1");

        // 线程2
        User user1 = userMapper.selectById(1L);
        user1.setName("嘻嘻2");
        userMapper.updateById(user1);

        // 自旋锁来多次尝试提交
        userMapper.updateById(user); // 如果没有乐观锁会修改插队线程的值，有乐观锁的话会避免用户取到过期数据并进行操作，比如此处线程1取到的是未经过线程2修改过后的数据；
    }

    // 批量查询
    @Test
    public void testSelectByBatchId() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    // 条件查询,map封装
    @Test
    public void testSelectByBatchIds(){
        HashMap<String,Object> map = new HashMap<>();

        //自定义查询
        map.put("name", "嘿嘿");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);

    }

    // 测试分页查询
    @Test
    public void testPage(){
        // 参数一：查询的页面
        // 参数二：页面大小
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
    }

    // 测试删除
    @Test
    public void testDeleteById() {
        userMapper.deleteById(1L);
    }

    // 批量删除
    @Test
    public void testDeleteBatchId() {
        userMapper.deleteBatchIds(Arrays.asList(2,3));
    }

    // 通过map删除
    @Test
    public void testDeleteMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","嘿嘿");
        userMapper.deleteByMap(map);
    }




}
