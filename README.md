# Nazgul

Nazgul是一个基于Dropwizard的深度定制的集成微服务应用开发框架。


====================
## 项目说明

![Nazgul](./nazgul.jpg)
![Nazgul](./icon.png)
> 

====================
## 设计目的
在其基础上根据实际应用开发的业务需要，调试并集成了大量的最佳实践。
对于架构师：可以帮助其将架构设计快速转化为代码，大部分架构设计模式都可以找到一一对应的实现方式。
对于开发者：可以帮助关注业务代码，快速开发一套可用的应用系统。
简化了业界的各种软件工程管理模型，包含CICD和自动化运维的支持，大大降低了QA、版本管理、运维阶段的成本。
大量的实际应用开发检验系统可靠性

====================
## 运行说明

开发模式运行语句：java -jar ./target/NazgulExample.jar docker /config/config.devel.yml --debug --offline
部署模式运行语句：java -jar ./target/NazgulExample.jar docker /config/config.devel.yml

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
  1. 支持Freemarker模板，性能和灵活性优于JSP
  1. 支持开发时使用docker-env配置代替环境变量配置
  1. 支持Consul服务注册
  1. 支持服务内置健康检查
  1. 自带常用Maven插件，用于加速开发
  1. 自带常用的Maven Repository配置
  1. 内含一个样板工程展示各种用途：NazgulExample
  1. 内含一个工程管理工具：Nazgul-Servant
  1. 兼容Horoshu  https://github.com/DreamInSun/Horoshu
  1. 支持JPA
  1. 支持QueryDSL
  1. 支持RSQL查询参数  https://github.com/jirutka/rsql-parser
  1. 支持定时任务，Jobs
  1. 支持SpringContext注入
  1. 配套开箱工具Nazgul-servant
  1. 支持Shiro权限控制
  1. 支持JWT权限支持
  1. 支持Http Basic权限
  1. 可自定义错误页
  1. 支持多数据源设置
  1. 使用了Mngr作为业务逻辑承载单元，可以将核心业务逻辑通过不同接口形式开发
  1. 单实体同时支持Hibernate和MyBatis的ORM方式
  
### 计划特性：
  1. 集成Dubbo等服务代理调用框架
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
   在Applicaton的run方法下注册资源，（1.0.4版后自动扫描resource包）

6. 在OneRing上添加相关的配置
    根据程序内置的配置，写在 /src/main/resources/config/default.yml
    测试时使用的本地配置，有运行参数传入，覆盖（Override）默认配置
    运行配置则由Docker服务参数提供，向OneRing下载，覆盖前两项配置

7. 开发阶段运行
   Applicaton右键点击直接Run，需配置运行参数 docker --debug /config.yml
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
    http://localhost:8080/{短工程名}/{资源路径}
    文档路径：
    http://localhost:8080/{短工程名}/swagger
    静态页路径：
    http://localhost:8080/{短工程名}/web
    监控路径：
    http://localhost:8081/
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

#### Debug模式运行
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

#### REST自动API和强大的Entity标注式开发
* 自动生成MyBatis的Mapper
* 自动生成CURDL的API
* 配合Console套件自动生成实体管理UI

#### WebSocket支持
* 支持WebSocket Client
* 支持WebSocket Server


====================

## 更新历史

#### 0.2.1
* 增加了OneRing服务器可以配置端口的设定，注意配置拉取端口默认是5678，一般读不到配置都是端口不通。

#### 0.2.2
* 修正了OneRing配置文件路径问题，兼容Windows 10和网络延迟问题。

#### 0.2.3
* 优化了OneRing远程读取配置时的设定，预防了OneRing在高负荷下Timeout问题

#### 0.2.4
* 处理了特殊情况下OneRing连接不上时出现的内存溢出错误

#### 0.2.5
* 增加Resource的initialize方法，可以在Application基础组件初始化完毕后再次对Resouece需要依赖基础组件的部分进行初始化

#### 0.2.6
* 增加了定時任務組件
* 增加了WebSocket組件
* 升级Dropwizard到1.2.0

#### 0.2.7
* 增加了JPA支持
* 新增了DbResource，DbMngr基础类
* 优化了数据库类对SqlSessionFactory的获取
* MyBatisMngr类改为MybatisMngr
* 新增了QuerySQL的支持
* 新增了EntityManager
* 新增了JPA支持
* 优化了依赖包
* 新增了Shiro支持
* 新增了default.yml配置项

#### 0.2.8
* 支持RSQL https://github.com/jirutka/rsql-parser

#### 0.3.0 
* 完成了泛类型的实体REST API，实现CURDL中的List。
* 可以使用数据库逆向生产JPA对象，直接提供API
* 或者定义JPA对象，生成数据库脚本以及提供REST API

#### 0.3.1
* 完成了GenericEntityResource作为基础组件，提供实体的CURDL能力.

#### 0.3.2 
* GenericEntityResource增加了Entity列表和JsonSchema
* 修改了基础支持，ServiceName支持'-'分割和'.'等价，用于ServiceName不支持dot的场景。例如Consul（与DNS混淆）
* 升级了Nazgul-servant版本0.3.0
* 升级了NazgulExample版本1.4.0

#### 0.3.3
* 增加了自定义日志
* 固化了内置SuperAdmin配置
* 固化了内置SvcConfig配置

#### 0.3.4
* 升级了Dropwizard-swagger到1.3.0-1
* 修改了异常处理机制
* 迁移了EntityOutput和NazgulException路径，需要修改相关引用
* 优化了Manager机制
* 增加了Mybatis在Insert之后获取Insert的PrimaryID的示例
* 增加了MyBatis的多数据库连接模式，配置需增加databaseMap相关，DbResource,DbMngr可以使用getSqlSession(name)模式

#### 0.3.5
* 添加了多@Auth注入支持
* 增加了NazException异常的捕获，应用级开发不需要再进行捕获返回
* 增加了Jwt配置项，需要更新default.yml文件
* 修正了EntityManager的连接未关闭
 
#### 0.3.6
* 增加了自定义错误页，存储路径/resource/web/error,需要从NazgulExample 1.3.5复制
* 增加了Component的自定义开关，需要在default.yml中增加nazComponents配置项
* 优化了启动顺序，在Bootstrap运行之前，完成了配置加载

#### 0.3.7
* 优化了依赖包重复问题
* 优化了收发日志，增加了异常处理部分

#### 0.3.8
* 增加了NazAssert工具
* 增加了Mngr的注解注入

### 0.3.9
* 3.x系列最终版

#### 0.4.0
* 升级MySQL驱动支持8.0，注意连接参数不再支持&zeroDateTimeBehavior=convertToNull

#### 0.4.1
* 增加了数据库连接的nazgul解析模式，注意升级default.yml的project参数

#### 0.4.2
* 植入了Warhound-client模块
* 增加了Nazgul-sdk模块
* 增加了Nazgul-test模块

#### 0.4.3
* 调整了POM结构，各个子模块同步板块号
* 优化了主要POM文件
* 增加了Nazgul-common模块，各个模块间共享功能移植到该模块
* 升级了Dropwizard:1.2.9

##### 0.4.4
* 实现了NazAuth授权机制，重写了Dropwizard的授权片段，支持同时使用多类型授权
* 可配置授权请求头，路由到不同的授权域
* 实现了INazAuthAdapter接口，用于应用快速自定义扩展授权机制。

##### 0.5.0
* 优化了工程结构
* 增加了BaseNazAuthAdpt的非标解析支持

##### 0.5.1
* 升级了dropwizard-websockets:1.3.2,添加了WebSocketComponent组件
* 制作了一个基于WebSocketComponent的聊天室Demo：http://localhost:8080/nazgulexample/web/ws/demo/index.html

##### 0.5.2
* 增加了自定义Task注册管理
* 增加了Runtime配置
* 增加了内置的SvcMngr
* 增加了SvcMngr初始化Sdk时的依赖检测
* 增加了内置的AdminResource
* 升级dropwizard-swagger：1.3.7-1
* 升级了guava:26.0
* TODO javax.mail.mail:1.4.7 替换为 javax.mail-api：1.6.2

##### 0.5.3
* 加入了OA型实体以及相关的逻辑
* 升级了MyBatis:3.4.8
* 增加了MyBatis-PageHelper的分页功能支持
* 更换了dropwizard-mybatis插件为xyz.xyan.dropwizard-mybatis:2.0.0
* 移除了Flyway组件，准备升级dropwizard:1.3版本内置的LiquidBase代替,需移除Flyway相关配置
* 增加了RestAPI的validator支持
* 增加了输入时JSON错误格式校验和捕获报错
* 增加了Mybatis和Hibernate的PersistenceException捕获
* 扩展了分页函数
* 增加了NazValidate的校验注释
* 增加了NazValidator的校验器，支持单一对象和List模式
* 增加了JerseyViolationException的捕获和处理
* 增加了SvcRestApi的框架Entity访问机制
* 换用了xyz.xyan.orm.rsql-jpa:2.1.0 增加了java.sql.Timestamp, java.sql.Date, java.sql.Time 时间格式的比较和处理 支持unix时间戳和“yyyy-mm-dd hh:mm:ss”,“yyyy-mm-dd_hh:mm:ss”格式
* 增加了RESTAPI的update的字段保护,支持 @Column 注解和 @NazProperty 注解
* 增加了Core的Task注册
* 增加了Entity管理的Cnt函数


##### 0.5.4
* 增加了Websocket默认管理类
* 增加了GenericEntityResource的Order排序
* 增加了NazEntity的定义
* 增加了NazProperty的定义
* 增加了GenericEntityResource的模糊查询
* 增加了Nazgul-file组件
* 增加了NazServer相关管理，可以用于协服务管理
* 增加了BaseResource对于逗号分隔型List参数的处理
* 增加了独立嵌入Server单元的管理
* 增加了FileMngr的配置与实现
* 检查了全部的泛型应用，添加@SuppressWarnings("unchecked")

### 0.5.5
* NazAssert的错误消息改为触发时才生成，传参使用lambda,仅需添加()->
* 增加了WebApplicationExceptionMapper
* 增加了GenericEntityResource的参考列表查询
* 升级了xyz.xyan.dropwizard-mybatis:2.1.0,匹配Mybatis版本
* 升级了com.github.pagehelper.PageHelper：:5.1.8,匹配Mybatis版本
* 增加了SyncUtil工具
* 扩展了@NazEntity的定义项
* 扩展了@NazProperty的定义项
* MultiDataSourceComponent增加了PageHelper支持
* EntityOutput增加了Page输出的支持
* 增加了PageUtil工具类
* 升级了Nazgul-servant:0.3.4

### 0.5.6
* 

====================

##### 0.6.0 （计划）
* 变更根路径cyan为xyz.xyan，可以在公网发布