# cyan.svc.NazgulExample
CYAN 缴费系统 辅助工具

====================
### 功能特性

====================
### 部署脚本


====================
### IDE配置步骤

#### 修改工程信息
1. 打开Nazgul-servant辅助工具 ( java -jar ./Nazgul-servant-0.3.2.jar )
2. 点击载入配置
3. 修改工程名和版本号
4. 点击更新配置

#### 打开工程修改包名
1. 使用IDEA打开包名
2. 找到Application.java的上级包
3. 修改包名和工程名完全一致（全小写模式）

#### 加载Maven依赖
1. 打开IDEA菜单：File->Setting->搜索Maven->User Settings File，使用本工程下的./doc/ide_maven_settings.xml覆盖系统配置
2. 同一配置页：勾选always update snapshots
3. 打开IDEA菜单：View->Tool Windows->Maven Project
4. 在Maven Project面板，点击Reimport Maven，完成后点击Lifecycle->compile
5. Build Success说明编译成功

#### 离线运行程序
1. 右键点击Application运行程序
2. 点击右上角运行配置菜单，选择“Edit Configuration”
3. 修改运行配置为XXXX develop
4. “Program Arguments”栏添加docker --offline --debug /config/config.devel.yml 


====================
### 部署脚本


====================
### 运行命令

#### 正式模式：
 java -jar ./target/${ProjectName}.jar docker /config/config.yml

#### 开发模式：
 java -jar ./target/${ProjectName}.jar docker ../../config.devel.yml --debug
 
====================
## 历史版本
 
###1.3.3
* 配合Nazgul-Core 0.3.3 新增功能的演示

### 1.3.4
* 配合Nazgul-Core 0.3.4 新增功能的演示

### 1.3.5
* 配合Nazgul-Core 0.3.5 新增功能的演示

### 1.3.6
* 配合Nazgul-Core 0.3.6 新增功能的演示

### 1.5.1
* 配合Nazgul-Core 0.5.1 新增功能的演示
* 制作了一个基于WebSocketComponent的聊天室Demo：http://localhost:8080/nazgulexample/web/ws/demo/index.html

### 1.5.2
* 配合Nazgul-Core 0.5.2 新增功能的演示

### 1.5.3
* 配合Nazgul-Core 0.5.3 新增功能的演示
* 增加了BeanValidator功能演示

### 1.5.4
* 配合Nazgul-Core 0.5.4 新增功能的演示
* 精简了基础文件

### 1.5.5
* 配合Nazgul-Core 0.5.5 新增功能的演示 