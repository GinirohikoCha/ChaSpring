package com.github.ginirohikocha.spring.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author GinirohikoCha
 * @version 0.0.1
 * @date 2021/2/21 22:08
 */
public class MySQLGenerator {

    public static void main(String[] args) {
        GlobalConfig globalConfig = new GlobalConfig()
                .setOutputDir(System.getProperty("user.dir") + "/src/main/java")
                .setAuthor("GinirohikoCha")
                .setFileOverride(true)
                .setOpen(false);

        DataSourceConfig dataSourceConfig = new DataSourceConfig()
                .setUrl("jdbc:mysql://localhost:3306/spring?serverTimezone=UTC&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("root")
                .setPassword("******");

        PackageConfig packageConfig = new PackageConfig()
                .setModuleName("spring")
                .setParent("com.github.ginirohikocha");

        StrategyConfig strategyConfig = new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setRestControllerStyle(true)
                .setEntityLombokModel(true)
                .setEntitySerialVersionUID(true)
                .setInclude(new String[]{
                        // 需要导出的表名
                        // "table1",
                        // "table2",
                        "test"
                });

        AutoGenerator autoGenerator = new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setStrategy(strategyConfig);
        autoGenerator.execute();
    }

}
