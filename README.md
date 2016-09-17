java -jar ./target/Nagzul-0.0.1-SNAPSHOT.jar server D:\WorkStation\Java_WorkSpace\DropWizard\hello-world.yml

java -jar ./target/nazgul-example-0.0.1-SNAPSHOT.jar docker config.yml --debug

NOTE: Debug mode cannot be used in .jar file.

====================
### 项目说明

Nazgul是基于Dropwizard的Orangelife高度定制版Java微服务开发框架。
能够使用极少的配置，完成微服务的开发工作。
集代码开发、文档管理、数据库管理、配置管理于一体的快速开发框架。
完全符合我司开发流程管理特点，量身定制。

#### 主要特性
  1. FatJar模式独立运行，Jettey作为Servlet容器，内存足迹小，节省服务器资源。打包基础应用仅26M.
  1. 直连OneRing服务，支持”应用默认配置+本地运行配置+在线配置“的组合配置模式，支持YAML和JSON格式。
  1. 快速切换开发模式和生产模式，“--debug”参数
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

#### 待完成特性：
  1. 集成Horoshu，服务代理调用
  1. 制作成Maven-Archetype,直接由IDE创建
  1. 制作Docker打包样例
  1. 约定配置自动解析
  1. UC改造后开放权限接口，用注解直接对接资源

====================
### 项目地址

Web地址
https://coding.net/u/orangelife/p/orange.core.Downloads/members/orangelife

HTTP下载地址
https://git.coding.net/orangelife/orange.core.Downloads.git

Git下载地址
git@git.coding.net:orangelife/orange.core.Downloads.git

====================
### 开箱操作:

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
   在MyApplicaton的run方法下注册资源，（改进版将自动扫描resource包）

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
    监控路径：
    http://lcoalhost:8081/
    其他路径，按照自己注册的Task，Assets确认

10. Docker部署
   修改Dockerfile的ENV SERVICE_NAME为分配的应用名

====================
### Debugm模式运行

* 项目设置了docker-env文件用于加速开发调试，
  文件路径：/src/main/resources/config/docker-env.yml
* 当使用启动参数docker --debug启动时，docker-env将替代系统的环境变量。
* 当项目打包为FatJar发布时，--debug模式自动失效。