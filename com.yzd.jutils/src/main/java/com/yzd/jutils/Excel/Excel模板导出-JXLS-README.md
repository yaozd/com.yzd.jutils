#### [Excel模板导出-JXLS](https://www.cnblogs.com/foxlee1024/category/1090370.html)
- [JXLS 2.4.0系列教程（一）——最简单的模板导出](https://www.cnblogs.com/foxlee1024/p/7616987.html)
- [JXLS 2.4.0系列教程（二）——循环导出一个链表的数据](https://www.cnblogs.com/foxlee1024/p/7617120.html)
- [JXLS 2.4.0系列教程（三）——嵌套循环是怎么做到的](https://www.cnblogs.com/foxlee1024/p/7618264.html)
- [JXLS 2.4.0系列教程（四）——多sheet是怎么做到的](https://www.cnblogs.com/foxlee1024/p/7619845.html)
- [JXLS 2.4.0系列教程（四）——拾遗 如何做页面小计](https://www.cnblogs.com/foxlee1024/p/7619890.html)
- [JXLS 2.4.0系列教程（五）——更进一步的应用和页面边距bug修复](https://www.cnblogs.com/foxlee1024/p/7620054.html)
- [JXLS 2.4.0系列教程（六）番外篇——导出图片（完结）](https://www.cnblogs.com/foxlee1024/p/7620112.html)

#### -百度云代码DEMO-[EXCEL模板导出-JXLS-支出凭单模板-DEMO]
```
EXCEL模板导出-JXLS-支出凭单模板-DEMO-V201810220823
```
#### Jxls-2.4.0-可用-byArvin
```
https://github.com/dazzlingstreak/JXLS
https://github.com/yaozd/JXLS
Jxls-2.4.0
官方信息备注
官网路径：http://jxls.sourceforge.net/
官方Demo：https://bitbucket.org/leonate/jxls-demo
项目案例简要说明
1、模板化配置导出，缩短开发时间，不采用POI方式。
2、支持条件化显示excel中部分栏目。
3、支持自定义函数，供excel中调用，进行格式化输出，以及枚举转换。
4、利用xml配置化，可以指定采用哪个sheet页的模板，指定输出到哪个sheet页。 参照：单元测试 JxlsExportUtilTest/testExportExcel
5、简单的导出，可以不用xml配置，直接用excel批注的方式导出。参照：单元测试 JxlsExportUtilTest/testExportExcelWithoutXml
建议： 1、模板页设置为隐藏，这样导出的excel中，不会有个空的模板sheet页。
2、列表数据绑定，建议使用xml配置，序号采用excel的公式就行。例如：=TEXT(ROW()-ROW(A10),"00")
3、数据展示需要格式化的情况，优化考虑excel是否本身就能实现。
```
#### jxls的xml配置示例
```
Jxls-2.4.0-可用-byArvin
https://github.com/dazzlingstreak/JXLS
https://github.com/yaozd/JXLS

<xls>
    <!--名称是Template的sheet页,A1:D11作为第一个Area,只有在Area区域中的信息块,JXLS才会处理-->
    <area ref="Template!A1:D12">
        <!--如果employer的婚姻情况是已婚,才显示配偶栏目.-->
        <if condition="employer.marriage == 2" ref="Template!A6:D11">
            <area ref="Template!A6:D11">
                <!--雇员信息是列表数据，需要用到each遍历填充数据-->
                <each items="employees" var="employee" ref="Template!A11:D11">
                    <area ref="Template!A11:D11"/>
                </each>
            </area>
            <area ref="Template!A9:D11">
                <each items="employees" var="employee" ref="Template!A11:D11">
                    <area ref="Template!A11:D11"/>
                </each>
            </area>
        </if>
    </area>
</xls>
```