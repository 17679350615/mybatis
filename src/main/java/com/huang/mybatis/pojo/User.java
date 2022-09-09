package com.huang.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId(type = IdType.ID_WORKER)
    private Long id; // 对应表的主键（雪花算法）
    @Version // 乐观锁Version注解，需要配置组件(config)
    private Integer version;
    private String name;

    private Integer age;
    private String email;

    // 代码级别的自动填充操作，注意数据库级别的自动填充操作在工作中是不允许的
    // 还需要编写自定义的处理器来处理这个注解(handler)
    @TableField(fill = FieldFill.INSERT) // 插入数据时进行自动填充
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE) // 更新数据时进行自动填充
    private Date gmtModified;

    @TableLogic // 逻辑删除
    private Integer deleted;

}
