# Blog-spring-boot

个人网站
===========================

#### 环境依赖
- Linux
- JRE1.8
- MySQL5.7

#### 调试部署
调试说明：application-dev.properties文件请自行修改，格式如下：
```
#数据库连接信息
spring.datasource.username=<db username>
spring.datasource.password=<db password>
spring.thymeleaf.cache=false

#设置后台管理用户名密码
login.username=<admin username>
login.password=<admin password>
```

#### 目录结构描述
```
├── src/main/java                   // 代码层的结构
│   ├── cn.lancel0t.blog            // 工程启动类
│   ├── cn.lancel0t.blog.config     // 配置信息类
│   ├── cn.lancel0t.blog.domain     // 实体类
│   ├── cn.lancel0t.blog.repository // 数据访问层
│   ├── cn.lancel0t.blog.service    // 数据服务层
│   ├── cn.lancel0t.blog.util       // 工具类
│   ├── cn.lancel0t.blog.vo         // 数据传输类
│   └── cn.lancel0t.blog.web        // 前端控制器
├── src/main/resources              // 资源文件的结构
│   ├── static                      // 静态文件
│   │   ├── app                     // angular主目录
│   │   ├── css                     // css样式
│   │   ├── fonts                   // 字体
│   │   ├── images                  // 图片
│   │   ├── js                      // JavaScript
│   │   └── lib                     // 第三方库
│   ├── templates                   // 模板文件
│   │   ├── admin                   // 后台管理
│   │   └── shared                  // 公共模板
│   └── application.properties      // 配置文件
├── src/test/java                   // 单元测试
└── Readme.md                       // help
```
#### V2.0.0 版本
- 2018/09/02 新版本上线
- 2018/08/25 历史博客迁移
- 2018/08/23 增加功能：置顶、点赞
- 2018/08/17 增加功能：小喇叭
- 2018/08/15 集成 Elasticsearch，增加功能：站内全文检索
- 2018/08/09 增加功能：标签云
- 2018/08/06 增加功能：专题、关于
- 2018/08/01 增加功能：分类和评论排行
- 2018/07/20 增加功能：分类、归档、标签
- 2018/07/15 增加功能：分页和评论
- 2018/07/11 增加功能：博客详情
- 2018/07/05 增加功能：博客列表
- 2018/06/30 增加功能：博客后台管理
- 2018/06/15 集成 Spring Security，增加功能：用户登录
- 2018/06/10 启动重构，使用 Spring Boot 重新开发
