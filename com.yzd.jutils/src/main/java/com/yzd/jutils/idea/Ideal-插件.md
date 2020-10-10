## 显示 Problems 窗口的方法-特别有用byArvin-2020-10-10
- [ntellij IDEA 显示 Problems 窗口的方法](https://blog.csdn.net/huang__2/article/details/83474076)
- [IntelliJ IDEA调出problem窗口](https://blog.csdn.net/u011712163/article/details/79880767)
> ps:帮助发现问题

### Ideal-常用插件-特别推荐byArvin
- 时序图生成工具：SequenceDiagram 推荐安装
- Batch Scripts Support
- CMD Support
- CodeGlance 推荐安装
- codehelper.generator
- FindBugs-IDEA
- Free Mybatis plugin
- GenerateAllSetter
- Lombok Plugin
- SonarLint
- VisualVM Launcher
- Vue.js
- protobuf support
- Key promoter X
- BashSupport（shell）
- Grep Console (日志)
- Maven Dependency Helper（解决Maven的依赖关系-Dependency Analyzer），错误
- Maven Helper（解决Maven的依赖关系-Dependency Analyzer），正确
    ```
    mvn dependency:tree
    mvn dependency:tree -Dverbose -Dincludes=commons-io:commons-io
    ```
- InnerBuilder (Idea 类builder模式插件安装方法)
     ```
     https://soberchina.iteye.com/blog/2330167
     ---
     public static Builder newBuilder() {
             return new Builder();
         }
     ```
- Builder Generator (PS:builder模式插件,推荐使用byArvin)
    ```
    Alt+Insert 
    https://plugins.jetbrains.com/plugin/6585-builder-generator/
    ```
- String Manipulation
    - [String Manipulation](https://blog.csdn.net/j3T9Z7H/article/details/78684055)
- Markdown Navigator
- Rainglow Color Schemes (PS:颜色主题插件)
- RestfulToolkit (ps:一套 RESTful 服务开发辅助工具集)
- MyBatis Log Plugin (PS:这款插件是直接将Mybatis执行的sql脚本显示出来，无需处理)
    ```
    https://plugins.jetbrains.com/plugin/10065-mybatis-log-plugin
    把 mybatis 输出的sql日志还原成完整的sql语句。
    将日志输出的sql语句中的问号 ? 替换成真正的参数值。 
    通过 "Tools -> MyBatis Log Plugin" 菜单或快捷键 "Ctrl+Shift+Alt+O" 启用。 
    点击窗口左边的 "Filter" 按钮，可以过滤不想要输出的sql语句。 
    点击窗口左边的 "Format Sql" 按钮，可以格式化输出的sql语句。 
    选中console的sql日志，右击 "Restore Sql from Selection" 菜单可以还原sql语句。 
    前提条件：输出的sql日志必须包含"Preparing:"和"Parameters:"才能
    ```
- singleton （PS:by WangYi 单例模式）
- [gradianto](https://plugins.jetbrains.com/plugin/12334-gradianto) （PS:主题，保护眼睛）
- Translation-必备的翻译插件
    ```
    使用快捷键 command+ctrl+u(mac) / shift+ctrl+y(win/linux)
    ```
- Codota—代码智能提示

## [安利10个让你爽到爆的IDEA必备插件](https://my.oschina.net/u/4447432/blog/4366920)
## [IntelliJ IDEA 18个常用插件，动图演示，让效率成为习惯](https://blog.csdn.net/lin443514407lin/article/details/86692736)
```
阿里代码规约检测
快捷键提示工具：Key promoter X
代码注解插件： Lombok
代码生成工具：CodeMaker -暂时不推荐使用
单元测试测试生成工具：JUnitGenerator
Mybatis 工具：Free Mybatis plugin
Maven辅助神器：Maven Helper
JSON转领域对象工具：GsonFormat
领域对象转JSON工具：POJO to JSON
时序图生成工具：SequenceDiagram
字符串工具：String Manipulation
代码作色工具：Rainbow Brackets
RESTful 服务开发辅助工具集: RestfulToolkit
日志工具：Grep Console
生成对象set方法：GenerateAllSetter
Redis可视化：Iedis
K8s工具：Kubernetes
中英文翻译工具：Translation
```