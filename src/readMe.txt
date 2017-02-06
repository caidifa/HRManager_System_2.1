--> 项目名:人力资源管理系统项目
-->   作者:蔡宝龙
-->   版本:Version 2.0
----------------------------------------------------------------------------->
--> IDE工具:IntelliJ IDEA 2016
--> 环境搭配:Maven,JDK1.8,win10

--> 版本控制:Git
--> 项目开源地址:
-->     1.GitHub: https://github.com/caidifa/HRManager_System_2.1 (最新v2.1)
-->     2.码云: https://git.oschina.net/caibaolong/HRManageSystem (旧v1.2)
----------------------------------------------------------------------------->
--> 设计模式:MVC

--> 数据库:MySQL
--> 连接池:C3P0(可尝试Druid)

--> 框架支持:SSM → [SpringMVC + Spring + Mybatis(采用了面向接口编程)](可尝试SSH)
--> 后端语言:Java
--> 前端技术:html,JavaScript,ajax,JQuery
----------------------------------------------------------------------------->

开发步骤(简易):

    0.根据项目要求,设计一套完整的数据库表结构(表关联(HRMS).png) → [**重要**]:
        ⑴.使用Excel设计制作,截图保存为表关联(HRMS).png;
        ⑵.在MySQL里建立新建数据库(hr_db);
        ⑶.根据设计,创建好所有的表(创建时的SQL语句会写在对应的每个实体类的注释中(/java/com/cai/domain/));

    1.使用IDEA创建一个新的Maven项目(Web版),并配置项目所需的文件夹结构;

    2.通过编辑pom.xml来配置项目所需的所有架包;

    3.创建并配置相应的XML文件(/resources/) → [**重要**]:

        ⑴.配置 beans.xml(Spring核心)
            ①.管理 dao,service (扫描注解);
            ②.引用 db.properties (数据库连接配置文件);
            ③.获得 dataSource 数据源 (用C3P0数据库连接池);
            ④.声明 sqlSessionFactory (使用 mybatis的架包里的 SqlSessionFactoryBean配置);
            ⑤.配置扫描 Dao接口包，动态实现 Dao接口，注入到 Spring容器中;
            ⑥.配置 transactionManager事务管理 并通过aop(切面编程)切入进去;

        ⑵.配置 springmvc-servlet.xml(前端核心 servlet)
            ①.管理 web下的 controller控制层(扫描注解);
            ②.配置 InternalResourceViewResolver内部资源视图解析器;

        ⑶.配置 web.xml(/webapp/WEB-INF/)
            ①.引入 spring配置文件(beans.xml);
            ②.添加 ContextLoaderListener监听器(监听上下文的加载);
            ③.配置 springmvc前端核心分发器(引入springmvc-servlet.xml);
            ④.处理 post请求中的中文乱码;

    4.之后重复的步骤为(每次做一个模块):

        ⑴.根据模块需求编写实体类 → domain (注释上数据库里建表SQL语句);
        ⑵.根据实体类进行面向接口编程 → 编写*Mapper接口+*Mapper.xml;
        ⑶.编写相应的Dao层,并用单元测试测试(/test/);
        ⑷.根据业务需求编写相应的service层,并用单元测试测试(/test/);
        ⑸.根据客户交互界面的需求编写相应的Controller层;
        ⑹.过程中根据需要编写自己的工具类(java/com/cai/utils),并用单元测试测试(/test/);

    5.为了规范(减少手误和代码量),尽量在当多个类使用类似方法或属性时用继承接口和泛型的思路编写;

    6.后面大多是前端步骤了,以后有时间再补全;
    .
    .
    .
    .
    .
    .
    .
    未完待续......



    7.
    8.
⑴.
⑵.
⑶.
⑷.
⑸.
①.
②.
③.
④.
⑤.
⑥.
⑦.









