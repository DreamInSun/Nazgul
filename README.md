
====================
## 运行说明

开发模式运行语句：java -jar ./target/NagzulExample.jar docker /config/config.devel.yml --debug --offline
部署模式运行语句：java -jar ./target/NagzulExample.jar docker /config/config.devel.yml

NOTE: Debug mode cannot be used in .jar file.
注意：Debug 模式在Jar模式是失效的

====================
## 项目说明

Nazgul是基于Dropwizard的Cyan高度定制版Java微服务开发框架。
能够使用极少的配置，完成微服务的开发工作。
集代码开发、文档管理、数据库管理、配置管理于一体的快速开发框架。
完全符合标准CICD开发流程管理模式。
配合运维工具链，可以进行大规模微服务治理。

### 主要特性
  1. FatJar模式独立运行，Jettey作为Servlet容器，内存足迹小，节省服务器资源。打包基础应用仅26M.
  1. 直连OneRing服务，支持”应用默认配置+本地运行配置+在线配置“的组合配置模式，支持YAML和JSON格式。
  1. 快速切换开发模式和生产模式，"--debug"参数
  1. 离线运行模式,脱离OneRing独立运行 "--offline"参数
  1. 支持完整JAX-RS，使用Jersey库
  1. 自动生成swagger文档，直接在线测试
  1. 支持轻量级JDBI数据库访问
  1. 支持重量级MyBatis数据库ORM框架
  1. 支持Flyway数据库管理库，部署时自动管理数据库版本
  1. 支持Freemarker模板，远超越JSP
  1. 支持开发时使用docker-env配置代替环境变量配置
  1. 支持Consul服务注册
  1. 支持服务内置健康检查
  1. 自带常用Maven插件，用于加速开发
  1. Docker打包样例 NazgulExample
  1. 兼容Horushu 
      https://github.com/DreamInSun/Horoshu
  1. 支持JPA
  1. 支持QueryDSL
  1. 支持RSQL查询参数
      https://github.com/jirutka/rsql-parser
  1. 支持定时任务，Jobs
  1. 配套开箱工具Nazgul-servant
  1. 支持Shiro权限控制

### 计划特性：
  1. 集成Horoshu，服务代理调用
  1. 制作成Maven-Archetype,直接由IDE创建 
  1. 约定配置自动解析
  1. UC改造后开放权限接口，用注解直接对接资源
  1. Metric集成Kafka，Zabbix
        https://github.com/hengyunabc/zabbix-sender
        https://github.com/hengyunabc/metrics-zabbix

====================
## 项目地址

Coding.net
https://coding.net/u/dreaminsun/p/cyan.svc.Nazgul/git

GitHub
https://github.com/DreamInSun/Nazgul

====================
## 开箱操作:

1. 修改Docker配置
   /src/main/resources/config/docker-env.yml

2. 修改应用配置
   /src/main/resources/config/default.yml

2. 创建应用 
   在{src包路径}下添加继承自NazgulConfiguration的MyConfiguration
   在{src包路径}下添加继承自NazgulApplication的MyApplicaton<MyConfiguration>
   所有运行配置添加到MyConfiguration

3. 编写应用
   在{src包路径}.resource下添加资源
   在{src包路径}.dao下添加jdbi映射
   在{src包路径}.mapper下添加Mybatis映射Interface
   在/src/main/resources/{src包路径}/mapper下添加Mybatis映射XML
  
4. 编写文档
   在{src包路径}.resource下外放的资源添加@Api系列注解

5. 注册资源
   在MyApplicaton的run方法下注册资源，（1.0.4版后自动扫描resource包）

6. 在OneRing上添加相关的配置
    根据程序内置的配置，写在 /src/main/resources/config/default.yml
    测试时使用的本地配置，有运行参数传入，覆盖（Override）默认配置
    运行配置则由Docker服务参数提供，向OneRing下载，覆盖前两项配置

7. 开发阶段运行
   MyApplicaton右键点击直接Run，需配置运行参数 docker --debug /config.yml
  参数说明： 
  [docker] 为docker方式运行，是定制化方式
  [--debug] 为debug模式运行，使用本地配置代替在线配置
  /config.yml 是指定的本地配置文件，可以不存在，若存在则会覆盖应用默认配置

8. 打包成Jar
   执行Mvn package:package,即可在target文件夹内生成可运行包。
   执行 java -jar MyApplication-1.0-SNAPSHOT.jar  docker /config.yml 即可运行程序
   可附带其他Java运行参数 JAVA_OPT

9. 查看服务
    资源路径:
    http://lcoalhost:8080/{短工程名}/{资源路径}
    文档路径：
    http://lcoalhost:8080/{短工程名}/swagger
    静态页路径：
    http://lcoalhost:8080/{短工程名}/web
    监控路径：
    http://lcoalhost:8081/
    其他路径，按照自己注册的Task，Assets确认

10. Docker部署
   修改Dockerfile的ENV SERVICE_NAME为分配的应用名

====================
### 特性详细说明


#### 配置叠加
* 项目运行配置可划分为：框架配置-基线配置-运行配置-OneRing配置
* 后一项配置可覆盖前一项配置的相同字段
* 配置及支持YML和JSON格式，建议使用YML格式
* OneRing配置为单层KeyValue，通过'.'符号表示层级

#### Debugm模式运行

* 项目设置了docker-env文件用于加速开发调试，
  文件路径：/src/main/resources/config/docker-env.yml
* 当使用启动参数docker --debug启动时，docker-env将替代系统的环境变量。
* 当项目打包为FatJar发布时，--debug模式自动失效。
 
#### 静态页服务
* /src/main/resource/web目录为默认静态页服务目录，可以放置静态页工程用于管理界面和交互界面。
* 实现使用了自带的AssetsBundle，不可再添加AssetsBundle，否则会覆盖现有配置。
* 若需要其他路径的静态页服务，可以添加Resource服务。

#### 数据库脚本管理
* 框架使用flyway进行数据库版本管理，版本脚本存放在/src/main/resource/flyway下，分数据库类型存放。
* 数据库脚本版本按照V{版本数字}_{主要操作}格式编写。
* 运行参数：db init，则在有效连接内初始化数据库，添加版本记录，不会执行数据库脚本。
* 运行参数：db migrate，则对已初始化的数据库进行版本升级，若未初始化会出错。
* docker指令使用“--auto-migrate true”参数将自动运行db migrate 
* 脚本请先在测试数据库执行确保不会中断，造成升级失败。

====================
0.2.1
增加了OneRing服务器可以配置端口的设定，注意配置拉取端口默认是5678，一般读不到配置都是端口不通。

0.2.2
修正了OneRing配置文件路径问题，兼容Windown 10和网络延迟问题。

0.2.3
优化了OneRing远程读取配置时的设定，预防了OneRing在高负荷下Timeout问题

0.2.4
处理了特殊情况下OneRing连接不上时出现的内存溢出错误

0.2.5
增加Resource的initialize方法，可以在Application基础组件初始化完毕后再次对Resouece需要依赖基础组件的部分进行初始化

0.2.6
增加了定時任務組件
增加了Websocket組件
升级Dropwizard到1.2.0

0.2.7
增加了JPA支持
新增了DbResource，DbMngr基础类
优化了数据库类对SqlSessionFactory的获取
MyBatisMngr类改为MybatisMngr
新增了QuerySQL的支持
新增了EntityManeger
新增了JPA支持
优化了依赖包
新增了Shiro支持
新增了default.yml配置项

0.2.8
支持RSQL https://github.com/jirutka/rsql-parser


0.3.0
集成了SpringContext
可以使用Spring-data-jpa


NOTE. 引入的Spring系列组件违背了Nazgul的轻量化选型原则，也造成了依赖包的冲突，在后续的版本中会把该部分组件剥离为独立的工程nazgul-spring