
# Xue-Yi-An

<!-- PROJECT SHIELDS -->

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]

<!-- PROJECT LOGO -->
<br />

<p align="center">
  <a href="https://github.com/Wr1sw/Xue-Yi-An/">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">"Xue-Yi-An人事管理系统"</h3>
  <p align="center">
    一款完美的人事管理系统！
    <br />
    <a href="https://github.com/Wr1sw/Xue-Yi-An"><strong>探索本项目的文档 »</strong></a>
    <br />
  </p>

</p>

## 友情提示
1. 此项目是CUIT2019级软件工程学院工程实践4项目成果。
2. 这是一款前后端分离的人事管理系统。项目加入常见的企业级应用所涉及到的技术点，例如 Redis、RabbitMQ 等。使用SpringSecurity 实现了多种登录方式:用户名登录、邮箱验证码登录、手机验证码登录。拥有视频在线播放、视频上传等功能。使用WebSocket实现了在线聊天室。
3. 前台项目：https://github.com/wr1sw/xueyanhr
## 目录

- [上手指南](#上手指南)
  - [开发前的配置要求](#开发前的配置要求)
  - [安装步骤](#安装步骤)
- [文件目录说明](#文件目录说明)
- [开发的架构](#开发的架构)
- [部署](#部署)
- [使用到的框架](#使用到的框架)
- [贡献者](#贡献者)
  - [如何参与开源项目](#如何参与开源项目)
- [版本控制](#版本控制)
- [作者](#作者)
- [鸣谢](#鸣谢)

### 上手指南

###### 开发前的配置要求

IDEA + SpringBoot + SpringSecurity + MySQL57 + Redis + RabbitMQ + 

###### **安装步骤**

- IDEA
1. 关于IDEA的安装与使用请参考：[https://github.com/judasn/IntelliJ-IDEA-Tutorial](https://github.com/judasn/IntelliJ-IDEA-Tutorial)
- MySQL
1. 下载并安装mysql5.7版本，下载地址：https://dev.mysql.com/downloads/installer/
2. 设置数据库帐号密码
3. 下载并安装客户端连接工具Navicat,下载地址：http://www.formysql.com/xiazai.html
4. 创建数据库xueyian
5. 导入document/sql下的xueyian_v1.sql文件

- Redis
1. 下载Redis,下载地址：https://github.com/MicrosoftArchive/redis/releases
2. 下载完后解压到指定目录；
3. 在当前地址栏输入cmd后，执行redis的启动命令：
```
redis-server.exe redis.windows.conf
```

- RabbitMQ
1. 安装RabbitMQ，下载地址：https://dl.bintray.com/rabbitmq/all/rabbitmq-server/3.7.14/rabbitmq-server-3.7.14.exe
2. 安装完成后，进入RabbitMQ安装目录下的sbin目录
3. 在地址栏输入cmd并回车启动命令行，然后输入以下命令启动管理功能：
```sh
rabbitmq-plugins enable rabbitmq_management
```
4. 访问地址查看是否安装成功：http://localhost:15672/
5. 输入账号密码并登录：guest guest

- 邮箱设置
1. 详见 https://www.ujcms.com/documentation/351.html

- Github三方登录
1. Github右上角中点击 settings
2. 点击 Developer settings
3. 点击 OAuth Apps 
4. 创建 App， 复制 Client ID、Client secrets、Authorization callback URL

- FastDFS
1. 下载安装参考：https://github.com/happyfish100/fastdfs/wiki

- 腾讯云短信服务
1. 详见 https://cloud.tencent.com/document/product/382/37745


- 克隆命令
```sh
git clone https://github.com/Wr1sw/Xue-Yi-An.git
```


### 文件目录说明
eg:

```
├─.idea
├─mail-server 邮件微服务
│  ├─.mvn
│  │  └─wrapper
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─org
│      │  │      └─cuit
│      │  │          └─mailserver
│      │  │              └─receiver 邮件Receiver
│      │  └─resources
│      │      └─templates 邮件模板
│      │          └─images
│      └─test
│          └─java
│              └─org
│                  └─cuit
│                      └─mailserver
└─xueyian-server Xue-Yi-An人事管理系统后台
    ├─xueyian-api 系统接口
    │  └─src
    │      └─main
    │          ├─java
    │          │  └─org
    │          │      └─cuit
    │          │          ├─exception 异常处理Controller
    │          │          └─xueyian 
    │          │              ├─api 
    │          │              │  ├─announcement 公告Controller包
    │          │              │  ├─chat 聊天室Controller包
    │          │              │  ├─editor 文本编辑器Controller包
    │          │              │  ├─personnel 人事管理Controller包
    │          │              │  ├─salary 薪资管理Controller包
    │          │              │  ├─stastic 统计管理Controller包
    │          │              │  └─system 系统管理Controller包
    │          │              └─config
    │          │                  ├─authentication Spring Security 和 WebSocket
    │          │                  └─validate 多种方式登录Filter
    │          │                      ├─code Account && Password
    │          │                      ├─email Email && Verified Code
    │          │                      ├─github Github OAuth
    │          │                      └─mobile Phone Number && Verified Code
    │          └─resources
    ├─xueyian-common 工具类及通用代码
    │  └─src
    │      └─main
    │          └─java
    │              └─org
    │                  └─cuit
    │                      └─xueyian
    │                          ├─constants 自定义常量
    │                          ├─exception 自定义异常
    │                          └─utils 工具包(FastDFS、Http、Sms、Common)
    ├─xueyian-dao Xue-Yi-An人事管理系统dao层
    │  └─src
    │      └─main
    │          ├─java
    │          │  └─org
    │          │      └─cuit
    │          │          └─xueyian
    │          │              ├─dao
    │          │              └─model
    │          └─resources
    │              └─mapper
    └─xueyian-service Xue-Yi-An人事管理系统service层
        └─src
            └─main
                ├─java
                │  └─org
                │      └─cuit
                │          └─xueyian
                │              └─service
                └─resources


```
### 技术选型

| 技术                 | 说明                | 官网                                           |
| -------------------- | ------------------- | ---------------------------------------------- |
| SpringBoot           | 容器+MVC框架        | https://spring.io/projects/spring-boot         |
| SpringSecurity       | 认证和授权框架      | https://spring.io/projects/spring-security     |
| MyBatis              | ORM框架             | http://www.mybatis.org/mybatis-3/zh/index.html |
| RabbitMQ             | 消息队列            | https://www.rabbitmq.com/                      |
| Redis                | 分布式缓存          | https://redis.io/                              |
| MySQL              | MySQL数据库         | https://www.mysql.com/                        |
| Druid                | 数据库连接池        | https://github.com/alibaba/druid               |
| Hutool               | Java工具类库        | https://github.com/looly/hutool                |

### 鸣谢
- [lenve/vhr](https://github.com/lenve/vhr)

<!-- links -->
[your-project-path]:Wr1sw/Xue-Yi-An
[contributors-shield]: https://img.shields.io/github/contributors/Wr1sw/Xue-Yi-An.svg?style=flat-square
[contributors-url]: https://github.com/Wr1sw/Xue-Yi-An/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/Wr1sw/Xue-Yi-An.svg?style=flat-square
[forks-url]: https://github.com/Wr1sw/Xue-Yi-An/network/members
[stars-shield]: https://img.shields.io/github/stars/Wr1sw/Xue-Yi-An.svg?style=flat-square
[stars-url]: https://github.com/Wr1sw/Xue-Yi-An/stargazers
[issues-shield]: https://img.shields.io/github/issues/Wr1sw/Xue-Yi-An.svg?style=flat-square
[issues-url]: https://img.shields.io/github/issues/Wr1sw/Xue-Yi-An.svg
[license-shield]: https://img.shields.io/github/license/Wr1sw/Xue-Yi-An.svg?style=flat-square
[license-url]: https://github.com/Wr1sw/Xue-Yi-An/blob/master/LICENSE.txt




