package com.github.ginirohikocha.spring.util;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GinirohikoCha
 * @since 2021/5/19
 */
public class MySqlGenerator {

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        GlobalConfig globalConfig = new GlobalConfig()
                .setOutputDir(projectPath + "/src/main/java")
                .setAuthor("GinirohikoCha")
                // 是否覆盖已有文件
                .setFileOverride(false)
                // 是否生成后打开文件夹
                .setOpen(true);

        DataSourceConfig dataSourceConfig = new DataSourceConfig()
                .setUrl("jdbc:mysql://localhost:3306/spring?serverTimezone=UTC&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("用户名")
                .setPassword("密码");

        PackageConfig packageConfig = new PackageConfig()
                // 模块名
                .setModuleName("spring")
                .setParent("com.github.ginirohikocha");

        //将mapper.xml文件输出到resources中
        String templatePath = "/templates/mapper.xml.vm";

        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() { }
        };
        List<FileOutConfig> fileOutConfigs = new ArrayList<>();
        // 自定义配置
        fileOutConfigs.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        injectionConfig.setFileOutConfigList(fileOutConfigs);

        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);

        StrategyConfig strategyConfig = new StrategyConfig()
                // 下划线转驼峰
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setRestControllerStyle(true)
                .setEntityLombokModel(true)
                .setEntitySerialVersionUID(true)
                // 需要导出的表名
                .setInclude(
                        "test"
                );

        AutoGenerator autoGenerator = new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setCfg(injectionConfig)
                .setTemplate(templateConfig)
                .setStrategy(strategyConfig);
        autoGenerator.execute();
    }

}
