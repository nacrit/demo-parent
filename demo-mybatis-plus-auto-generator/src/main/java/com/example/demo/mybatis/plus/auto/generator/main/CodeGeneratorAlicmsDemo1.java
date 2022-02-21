package com.example.demo.mybatis.plus.auto.generator.main;

import com.alicms.ex.common.core.mybatis.BaseEntity;
import com.alicms.ex.common.core.support.BaseService;
import com.alicms.ex.common.core.support.BaseServiceImpl;
import com.alicms.ex.provider.admin.controller.BaseAdminController;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenghao
 * @description 自定义代码生成器
 * @date 2020/6/10 17:24
 */
// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGeneratorAlicmsDemo1 {
    private static String MODEL_NAME = "demo-mybatis-plus-auto-generator";
    private static String PARENT_PACKAGE_NAME = "com.alicms.ex.provider";  // , "alicms_contract_follow_config_currency"
    private static String URL = "jdbc:mysql://192.168.10.253:3306/alicms?useSSL=false&allowMultiQueries=true";
    private static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
//    private static String USERNAME = "alicms_swap";
    private static String USERNAME = "alicms";
//    private static String PASSWORD = "4Q7pphtILR@3NiCh";
    private static String PASSWORD = "!AnI1LMZA7";

    private static String BASE_MODEL_NAME = "admin";
    private static String BASE_MODEL_PACKAGE_NAME = "account";

    // 配置
    private static String CONTROLLER_PACKAGE_NAME = BASE_MODEL_NAME + ".controllor." + BASE_MODEL_PACKAGE_NAME;
    private static String MAPPER_PACKAGE_NAME = BASE_MODEL_NAME + ".dao." + BASE_MODEL_PACKAGE_NAME;
    private static String SERVICE_PACKAGE_NAME = BASE_MODEL_NAME + ".service." + BASE_MODEL_PACKAGE_NAME;
    private static String MODEL_PACKAGE_NAME = BASE_MODEL_NAME + ".model." + BASE_MODEL_PACKAGE_NAME;
    private static String SERVICEIMPL_PACKAGE_NAME = BASE_MODEL_NAME + ".service." + BASE_MODEL_PACKAGE_NAME + ".impl";

//    private static String CONTROLLER_PACKAGE_NAME = "follow.controllor";
//    private static String MAPPER_PACKAGE_NAME = "follow.dao";
//    private static String SERVICE_PACKAGE_NAME = "follow.service";
//    private static String MODEL_PACKAGE_NAME = "follow.model.entity";
//    private static String SERVICEIMPL_PACKAGE_NAME = "follow.service.impl";
    private static String[] TABLE_NAME_ARR = {
//            "alicms_contract_follow_config",
//            "alicms_contract_follow_config_currency",
//            "alicms_contract_follow_trader",
//            "alicms_contract_follow_trader_verify",
//            "alicms_contract_follow_user",
//            "alicms_contract_follow_user_config",
//            "alicms_contract_activity_contend_config",
//            "alicms_contract_activity_contend_data",
//            "alicms_contract_activity_contend_profit",
//            "alicms_contract_activity_contend_ranking",
            "alicms_account_profit",
    };
    private static Class<?> SUPER_CONTROLLER_CLASS = BaseAdminController.class;
    

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PARENT_PACKAGE_NAME); // 设置父包名
//        pc.setModuleName("provider");
        pc.setController(CONTROLLER_PACKAGE_NAME);
        pc.setMapper(MAPPER_PACKAGE_NAME);
        pc.setService(SERVICE_PACKAGE_NAME);
        pc.setEntity(MODEL_PACKAGE_NAME);
        pc.setServiceImpl(SERVICEIMPL_PACKAGE_NAME);
        mpg.setPackageInfo(pc);

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");    // 当前工程绝对路径
        gc.setOutputDir(projectPath + "/" + MODEL_NAME + "/src/main/java");
        gc.setAuthor("zhenghao");
        gc.setOpen(false);
        gc.setSwagger2(true); // 实体属性 Swagger2 注解
        gc.setEntityName("%sEntity");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        // dsc.setSchemaName("public");
//        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setDriverName(DRIVER_NAME);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing

            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + File.separator + MODEL_NAME + "/src/main/resources/mapper/contract/"
                        + tableInfo.getEntityName().replace("Entity", "") + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
         templateConfig.setEntity("templates/entity2.java");
         templateConfig.setMapper("templates/mapper2.java");
         templateConfig.setService("templates/service2.java");
         templateConfig.setServiceImpl("templates/serviceImpl2.java");
         templateConfig.setController("templates/controller2.java");
//         templateConfig.setXml("templates/mapper.xml");
         templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix("alicms"); // 设置表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);  // 数据库表映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);    // 数据库表字段映射到实体的命名策略
        strategy.setSuperControllerClass(SUPER_CONTROLLER_CLASS);    // 父控制器
        strategy.setSuperServiceClass(BaseService.class);   // 父Service
        strategy.setSuperServiceImplClass(BaseServiceImpl.class);   // 父ServiceImpl
        strategy.setSuperMapperClass("com.alicms.ex.common.core.mybatis.BaseMapper");   // 父Mapper
        strategy.setSuperEntityClass(BaseEntity.class);   // 实体父类
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!"); // 带报名全路径
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id", "create_time", "update_time");
        strategy.setInclude(TABLE_NAME_ARR);
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
