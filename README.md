# Java文档生成工具

## 一. 前言

> 作为一名程序员，我们最讨厌两件事：
>
> 1. 别人不写注释
> 2. 自己写注释
>
> 而作为一名接口开发者，我们同样讨厌两件事：
>
> 1. 别人不写接口文档，文档不及时更新
> 2. 需要自己写接口文档，还需要及时更新

相信无论是前端还是后端开发，都或多或少地被接口文档折磨过。前端经常抱怨后端给的接口文档与实际情况不一致。后端又觉得编写及维护接口文档会耗费不少精力，经常来不及更新

而随着 Springboot、Springcloud 等微服务的流行，每个项目都有成百上千个接口调用，这时候再要求人工编写接口文档并且保证文档的实时更新几乎是一件不可能完成的事，所以这时候我们迫切需要一个工具，一个能帮我们自动化生成接口文档以及自动更新文档的工具

## 二. 工具介绍

主要介绍 5 种 java 文档生成工具：

- [Swagger](#Swagger)
- [ShowDoc](#ShowDoc)
- [smart-doc](#smart-doc)
- [knife4j](#knife4j)
- [JApiDocs](#JApiDocs)

1. **<span id="Swagger">Swagger</span>**

   第一步：pom 文件中引入 Swagger 依赖

    ```xml
    <!--引入Swagger3依赖-->
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-boot-starter</artifactId>
        <version>3.0.0</version>
    </dependency>
    ```

   第二步：Application 上面加入 @EnableOpenApi 注解

    ```java
    @EnableOpenApi
    @SpringBootApplication
    public class DocApplication {
        public static void main(String[] args) {
            SpringApplication.run(DocApplication.class, args);
        }
    }
    ```

   第三步：新建 Swagger 配置类

    ```java
    @Configuration
    public class Swagger3Config {
        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.OAS_30)
                    .select()
          .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                    .build();
        }
    }
    ```

   第四步：编写 API 接口

    ```java
    Swagger注解的使用说明
    
    @Api：用在请求的类上，表示对类的说明
        tags="说明该类的作用，可以在UI界面上看到的注解"
        value="该参数没什么意义，在UI界面上也看到，所以不需要配置"
    
    @ApiOperation：用在请求的方法上，说明方法的用途、作用
        value="说明方法的用途、作用"
        notes="方法的备注说明"
    
    @ApiImplicitParams：用在请求的方法上，表示一组参数说明
        @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
            name：参数名
            value：参数的汉字说明、解释
            required：参数是否必须传
            paramType：参数放在哪个地方
                · header --> 请求参数的获取：@RequestHeader
                · query --> 请求参数的获取：@RequestParam
                · path（用于restful接口）--> 请求参数的获取：@PathVariable
                · div（不常用）
                · form（不常用）    
            dataType：参数类型，默认String，其它值dataType="Integer"       
            defaultValue：参数的默认值
    
    @ApiResponses：用在请求的方法上，表示一组响应
        @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
            code：数字，例如400
            message：信息，例如"请求参数没填好"
            response：抛出异常的类
    
    @ApiModel：用于响应类上，表示一个返回响应数据的信息
                （这种一般用在post创建的时候，使用@RequestBody这样的场景，
                请求参数无法使用@ApiImplicitParam注解进行描述的时候）
                
    @ApiModelProperty：用在属性上，描述响应类的属性
    ```

   第五步：启动服务，访问：http://localhost:8080/swagger-ui/#/ 查看生成 API 接口文档

2. **<span id="ShowDoc">ShowDoc</span>**

   ShowDoc 自动生成 API 文档：https://www.showdoc.com.cn/page/741656402509783

   第一步：下载 https://www.showdoc.cc/script/showdoc_api.sh 脚本，到项目的根目录

   第二步：在项目的设置页，获得 ShowDoc 的开放 API 的 api_key 和 api_token 秘钥对

   第三步：修改 showdoc_api.sh 脚本，设置刚获得的 api_key 和 api_token 秘钥对

   第四步：编写 API 接口，添加 ShowDoc 所需的注释

   第五步：执行 showdoc_api.sh 脚本，` sh showdoc_api.sh src/main/java`，扫描 Java代码注释，生成 API 接口文档

   第六步：访问 ShowDoc 对应的项目，查看生成 API 接口文档

3. **<span id="smart-doc">smart-doc</span>**

   ![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0d84316b701544aabb330f2637a2f49f~tplv-k3u1fbpfcp-watermark.gif)

   第一步：pom 文件中引入 smart-doc 插件

    ```xml
    <plugin>
        <groupId>com.github.shalousun</groupId>
        <artifactId>smart-doc-maven-plugin</artifactId>
        <version>[最新版本]</version>
        <configuration>
            <!--指定生成文档的使用的配置文件,配置文件放在自己的项目中-->
            <configFile>./src/main/resources/smart-doc.json</configFile>
            <!--指定项目名称-->
            <projectName>测试</projectName>
            <!--smart-doc实现自动分析依赖树加载第三方依赖的源码，如果一些框架依赖库加载不到导致报错，这时请使用excludes排除掉-->
            <excludes>
                <!--格式为：groupId:artifactId;参考如下-->
                <exclude>com.alibaba:fastjson</exclude>
            </excludes>
            <!--自1.0.8版本开始，插件提供includes支持,配置了includes后插件会按照用户配置加载而不是自动加载，因此使用时需要注意-->
            <!--smart-doc能自动分析依赖树加载所有依赖源码，原则上会影响文档构建效率，因此你可以使用includes来让插件加载你配置的组件-->
            <includes>
                <!--格式为：groupId:artifactId;参考如下-->
                <include>com.alibaba:fastjson</include>
            </includes>
        </configuration>
        <executions>
            <execution>
                <!--如果不需要在执行编译时启动smart-doc，则将phase注释掉-->
                <phase>compile</phase>
                <goals>
                    <!--smart-doc提供了html、openapi、markdown等goal，可按需配置-->
                    <goal>html</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
    ```

   第二步：新建 smart-doc 配置文件 smart-doc.json

    ```json
    // 最小配置单元
    {
       "outPath": "D://md2" //指定文档的输出路径
    }
    
    // 详细配置说明
    {
      "serverUrl": "http://127.0.0.1", //服务器地址,非必须。导出postman建议设置成http://{{server}}方便直接在postman直接设置环境变量
      "pathPrefix": "", //设置path前缀,非必须。如配置Servlet ContextPath 。@since 2.2.3
      "isStrict": false, //是否开启严格模式
      "allInOne": true,  //是否将文档合并到一个文件中，一般推荐为true
      "outPath": "D://md2", //指定文档的输出路径
      "coverOld": true,  //是否覆盖旧的文件，主要用于mardown文件覆盖
      "createDebugPage": true,//@since 2.0.0 smart-doc支持创建可以测试的html页面，仅在AllInOne模式中起作用。
      "packageFilters": "",//controller包过滤，多个包用英文逗号隔开，2.2.2开始需要采用正则：com.test.controller.*
      "md5EncryptedHtmlName": false,//只有每个controller生成一个html文件是才使用
      "style":"xt256", //基于highlight.js的代码高设置,可选值很多可查看码云wiki，喜欢配色统一简洁的同学可以不设置
      "projectName": "smart-doc",//配置自己的项目名称
      "skipTransientField": true,//目前未实现
      "sortByTitle":false,//接口标题排序，默认为false,@since 1.8.7版本开始
      "showAuthor":true,//是否显示接口作者名称，默认是true,不想显示可关闭
      "requestFieldToUnderline":true,//自动将驼峰入参字段在文档中转为下划线格式,//@since 1.8.7版本开始
      "responseFieldToUnderline":true,//自动将驼峰入参字段在文档中转为下划线格式,//@since 1.8.7版本开始
      "inlineEnum":true,//设置为true会将枚举详情展示到参数表中，默认关闭，//@since 1.8.8版本开始
      "recursionLimit":7,//设置允许递归执行的次数用于避免一些对象解析卡主，默认是7，正常为3次以内，//@since 1.8.8版本开始
      "allInOneDocFileName":"index.html",//自定义设置输出文档名称, @since 1.9.0
      "requestExample":"true",//是否将请求示例展示在文档中，默认true，@since 1.9.0
      "responseExample":"true",//是否将响应示例展示在文档中，默认为true，@since 1.9.0
      "urlSuffix":".do",//支持SpringMVC旧项目的url后缀,@since 2.1.0
      "displayActualType":false,//配置true会在注释栏自动显示泛型的真实类型短类名，@since 1.9.6
      "appKey": "20201216788835306945118208",// torna平台对接appKey,, @since 2.0.9
      "appToken": "c16931fa6590483fb7a4e85340fcbfef", //torna平台appToken,@since 2.0.9
      "secret": "W.ZyGMOB9Q0UqujVxnfi@.I#V&tUUYZR",//torna平台secret，@since 2.0.9
      "openUrl": "http://localhost:7700/api",//torna平台地址，填写自己的私有化部署地址@since 2.0.9
      "debugEnvName":"测试环境", //torna环境名称
      "debugEnvUrl":"http://127.0.0.1",//推送torna配置接口服务地址
      "tornaDebug":false,//启用会推送日志
      "ignoreRequestParams":[ //忽略请求参数对象，把不想生成文档的参数对象屏蔽掉，@since 1.9.2
         "org.springframework.ui.ModelMap"
       ],
      "dataDictionaries": [{ //配置数据字典，没有需求可以不设置
          "title": "http状态码字典", //数据字典的名称
          "enumClassName": "com.power.common.enums.HttpCodeEnum", //数据字典枚举类名称
          "codeField": "code",//数据字典字典码对应的字段名称
          "descField": "message"//数据字典对象的描述信息字典
      }],
      "errorCodeDictionaries": [{ //错误码列表，没有需求可以不设置
        "title": "title",
        "enumClassName": "com.power.common.enums.HttpCodeEnum", //错误码枚举类
        "codeField": "code",//错误码的code码字段名称
        "descField": "message"//错误码的描述信息对应的字段名
      }],
      "revisionLogs": [{ //文档变更记录，非必须
          "version": "1.0", //文档版本号
          "revisionTime": "2020-12-31 10:30", //文档修订时间
          "status": "update", //变更操作状态，一般为：创建、更新等
          "author": "author", //文档变更作者
          "remarks": "desc" //变更描述
        }
      ],
      "customResponseFields": [{ //自定义添加字段和注释，一般用户处理第三方jar包库，非必须
          "name": "code",//覆盖响应码字段
          "desc": "响应代码",//覆盖响应码的字段注释
          "ownerClassName": "org.springframework.data.domain.Pageable", //指定你要添加注释的类名
          "ignore":true, //设置true会被自动忽略掉不会出现在文档中
          "value": "00000"//设置响应码的值
      }],
      "customRequestFields": [{ //自定义请求体的注释，@since 2.1.3，非必须
           "name":"code", //属性名
           "desc":"状态码", //描述
           "ownerClassName":"com.xxx.constant.entity.Result", //属性对应的类全路径
           "value":"200", //默认值或者mock值
           "required":true, //是否必填
           "ignore":false //是否忽略
      }],
      "requestHeaders": [{ //设置请求头，没有需求可以不设置
          "name": "token",//请求头名称
          "type": "string",//请求头类型
          "desc": "desc",//请求头描述信息
          "value":"token请求头的值",//不设置默认null
          "required": false,//是否必须
          "since": "-",//什么版本添加的改请求头
          "pathPatterns": "/app/test/**",//请看https://gitee.com/smart-doc-team/smart-doc/wikis/请求头高级配置?sort_id=4178978
          "excludePathPatterns":"/app/page/**"//请看https://gitee.com/smart-doc-team/smart-doc/wikis/请求头高级配置?sort_id=4178978
      },{
          "name": "appkey",//请求头
          "type": "string",//请求头类型
          "desc": "desc",//请求头描述信息
          "value":"appkey请求头的值",//不设置默认null
          "required": false,//是否必须
          "pathPatterns": "/test/add,/testConstants/1.0",//正则表达式过滤请求头,url匹配上才会添加该请求头，多个正则用分号隔开
          "since": "-"//什么版本添加的改请求头
      }],
      "requestParams": [ //设置公共参数，没有需求可以不设置
        {
          "name": "configPathParam",//请求名称
          "type": "string",//请求类型
          "desc": "desc",//请求描述信息
          "paramIn": "path", // 参数所在位置 header-请求头, path-路径参数, query-参数
          "value":"testPath",//不设置默认null
          "required": false,//是否必须
          "since": "2.2.3",//什么版本添加的该请求
          "pathPatterns": "/app/test/**",//请看https://gitee.com/smart-doc-team/smart-doc/wikis/请求高级配置?sort_id=4178978
          "excludePathPatterns":"/app/page/**"//请看https://gitee.com/smart-doc-team/smart-doc/wikis/请求高级配置?sort_id=4178978
      }]
      "rpcApiDependencies":[{ // 项目开放的dubbo api接口模块依赖，配置后输出到文档方便使用者集成
            "artifactId":"SpringBoot2-Dubbo-Api",
            "groupId":"com.demo",
            "version":"1.0.0"
       }],
      "rpcConsumerConfig": "src/main/resources/consumer-example.conf",//文档中添加dubbo consumer集成配置，用于方便集成方可以快速集成
      "apiObjectReplacements": [{ // 自smart-doc 1.8.5开始你可以使用自定义类覆盖其他类做文档渲染，非必须
          "className": "org.springframework.data.domain.Pageable",
          "replacementClassName": "com.power.doc.model.PageRequestDto" //自定义的PageRequestDto替换Pageable做文档渲染
      }],
      "apiConstants": [{//从1.8.9开始配置自己的常量类，smart-doc在解析到常量时自动替换为具体的值，非必须
            "constantsClassName": "com.power.doc.constants.RequestParamConstant"
      }],
      "responseBodyAdvice":{ //自smart-doc 1.9.8起，非必须项，ResponseBodyAdvice统一返回设置(不要随便配置根据项目的技术来配置)，可用ignoreResponseBodyAdvice tag来忽略
          "className":"com.power.common.model.CommonResult" //通用响应体
      },
      "requestBodyAdvice":{ ////自smart-doc 2.1.4 起，支持设置RequestBodyAdvice统一请求包装类，非必须
             "className":"com.power.common.model.CommonResult"
      }
    }
    ```

   第三步：编写 API 接口

   零注解、零学习成本、只需要写标准java注释即可生成文档

    ```java
    /**
     * 用户登录接口
     *
     * @param id       用户id
     * @param userName 用户姓名
     * @param password 用户密码
     * @return {@link User}
     */
    @PostMapping("/login")
    public User login(String id, @RequestParam(required = true) String userName, @RequestParam(required = true) String password) {
        return User.getByUserNameAndPassword(userName, password);
    }
    ```

   第四步：运行 maven 插件，生成 API 接口文档

   第五步：启动服务，访问：http://localhost:8080/smart-doc/api.html 查看生成 API 接口文档

4. **<span id="knife4j">knife4j</span>**

   第一步：pom 文件中引入 knife4j 依赖

    ```xml
    <!--引入knife4j依赖-->
    <dependency>
        <groupId>com.github.xiaoymin</groupId>
        <artifactId>knife4j-spring-boot-starter</artifactId>
        <version>2.0.7</version>
    </dependency>
    ```

   第二步：新建 knife4j 配置类

    ```java
    @Configuration
    @EnableSwagger2WebMvc
    public class Knife4jConfig {
        @Bean(value = "defaultApi2")
        public Docket defaultApi2() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    //这里指定Controller扫描包路径
                    .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                    .paths(PathSelectors.any())
                    .build();
        }
    }
    ```

   第三步：启动服务，访问：http://localhost:8080/doc.html 查看生成 API 接口文档

   第四步：搭建 YApi 平台，并设置自动同步 Swagger 提供的 API 接口信息

5. **<span id="JApiDocs">JApiDocs</span>**

   第一步：pom 文件中引入 JApiDocs 依赖

    ```xml
    <!--引入JApiDocs依赖-->
    <dependency>
        <groupId>io.github.yedaxia</groupId>
        <artifactId>japidocs</artifactId>
        <version>1.4.4</version>
    </dependency>
    ```

   第二步：新建 JApiDocs 配置类

    ```java
    public class JApiDocsConfig {
        public static void main(String[] args) {
            DocsConfig config = new DocsConfig();
            // root project path
            config.setProjectPath("/Users/qinyixuan/IdeaProjects/NewChannel/doc");
            // project name
            config.setProjectName("用户管理");
            // api version
            config.setApiVersion("V1.0");
            // api docs target path
            config.setDocsPath("/Users/qinyixuan/IdeaProjects/NewChannel/doc/src/main/resources/static/japidocs");
            // auto generate
            config.setAutoGenerate(Boolean.TRUE);
            // execute to generate
            Docs.buildHtmlDocs(config);
        }
    }
    ```

   第三步：编写 API 接口

   第四步：执行 main 方法，生成 API 接口文档

   第五步：启动服务，访问：http://localhost:8080/japidocs/V1.0/index.html 查看生成 API 接口文档

## 三. 参考

- [SpringBoot 如何生成接口文档，老鸟们都这么玩的！](https://juejin.cn/post/7007323375366307870)
- [芋道 Spring Boot API 接口文档 Swagger 入门](https://www.iocoder.cn/Spring-Boot/Swagger/)
- [自动生成API文档](https://www.showdoc.com.cn/page/741656402509783)
- [smart-doc: smart-doc是一款同时支持java restful api和apache dubbo rpc接口文档生成的工具。完全基于注释生成文档，做到零侵入。](https://gitee.com/smart-doc-team/smart-doc)
- [SpringBoot 生成接口文档，我用smart-doc，一款比Swagger更nice的工具！](https://juejin.cn/post/7013152753048354823)
- [芋道 Spring Boot API 接口文档 JApiDocs 入门 | 芋道源码 —— 纯源码解析博客](https://www.iocoder.cn/Spring-Boot/JApiDocs/)
- [JApiDocs Documentation](https://japidocs.agilestudio.cn/#/?id=getting-started)

