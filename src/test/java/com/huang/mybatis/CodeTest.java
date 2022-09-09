package com.huang.mybatis;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

// 代码自动生成器
public class CodeTest {
    public static void main(String[] args) {
        // 需要构建一个 代码自动生成器 对象
        AutoGenerator mpg = new AutoGenerator();

        // 配置策略

        //1.全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "src/main/java");
        gc.setAuthor("huang");
        gc.setOpen(false);
        gc.setFileOverride(false);
        gc.setServiceName("%sService");
        gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);

        mpg.setGlobalConfig(gc);

        //2.设置数据源
        DataSourceConfig dsc = new DataSourceConfig();

        dsc.setUrl("jdbc:mysql://localhost:3306/program?useSSL=false&useUnicode=true&CharacterEncoding=utf-8&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("201015");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        //3.包的配置
        PackageConfig pc = new PackageConfig();

        pc.setModuleName("mybatis");
        pc.setParent("com.huang");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");

        mpg.setPackageInfo(pc);


        //4.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("user");//设置要映射的表名(可以映射多个表),只需改这里即可
        strategy.setNaming(NamingStrategy.underline_to_camel); // 设置驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);//是否使用lombok开启注解
        strategy.setLogicDeleteFieldName("deleted");
        // 自动填充配置
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        // 乐观锁配置
        strategy.setVersionFieldName("version");

        strategy.setRestControllerStyle(true);
        strategy.setRestControllerStyle(true); // localhost:8080/hello_id_2
        mpg.setStrategy(strategy);

        mpg.execute();


        mpg.setStrategy(strategy);



    }
}
