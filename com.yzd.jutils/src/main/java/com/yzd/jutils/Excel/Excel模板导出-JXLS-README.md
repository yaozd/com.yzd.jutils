#### [Excelģ�嵼��-JXLS](https://www.cnblogs.com/foxlee1024/category/1090370.html)
- [JXLS 2.4.0ϵ�н̳̣�һ��������򵥵�ģ�嵼��](https://www.cnblogs.com/foxlee1024/p/7616987.html)
- [JXLS 2.4.0ϵ�н̳̣���������ѭ������һ�����������](https://www.cnblogs.com/foxlee1024/p/7617120.html)
- [JXLS 2.4.0ϵ�н̳̣���������Ƕ��ѭ������ô������](https://www.cnblogs.com/foxlee1024/p/7618264.html)
- [JXLS 2.4.0ϵ�н̳̣��ģ�������sheet����ô������](https://www.cnblogs.com/foxlee1024/p/7619845.html)
- [JXLS 2.4.0ϵ�н̳̣��ģ�����ʰ�� �����ҳ��С��](https://www.cnblogs.com/foxlee1024/p/7619890.html)
- [JXLS 2.4.0ϵ�н̳̣��壩��������һ����Ӧ�ú�ҳ��߾�bug�޸�](https://www.cnblogs.com/foxlee1024/p/7620054.html)
- [JXLS 2.4.0ϵ�н̳̣���������ƪ��������ͼƬ����ᣩ](https://www.cnblogs.com/foxlee1024/p/7620112.html)

#### -�ٶ��ƴ���DEMO-[EXCELģ�嵼��-JXLS-֧��ƾ��ģ��-DEMO]
```
EXCELģ�嵼��-JXLS-֧��ƾ��ģ��-DEMO-V201810220823
```
#### Jxls-2.4.0-����-byArvin
```
https://github.com/dazzlingstreak/JXLS
https://github.com/yaozd/JXLS
Jxls-2.4.0
�ٷ���Ϣ��ע
����·����http://jxls.sourceforge.net/
�ٷ�Demo��https://bitbucket.org/leonate/jxls-demo
��Ŀ������Ҫ˵��
1��ģ�廯���õ��������̿���ʱ�䣬������POI��ʽ��
2��֧����������ʾexcel�в�����Ŀ��
3��֧���Զ��庯������excel�е��ã����и�ʽ��������Լ�ö��ת����
4������xml���û�������ָ�������ĸ�sheetҳ��ģ�壬ָ��������ĸ�sheetҳ�� ���գ���Ԫ���� JxlsExportUtilTest/testExportExcel
5���򵥵ĵ��������Բ���xml���ã�ֱ����excel��ע�ķ�ʽ���������գ���Ԫ���� JxlsExportUtilTest/testExportExcelWithoutXml
���飺 1��ģ��ҳ����Ϊ���أ�����������excel�У������и��յ�ģ��sheetҳ��
2���б����ݰ󶨣�����ʹ��xml���ã���Ų���excel�Ĺ�ʽ���С����磺=TEXT(ROW()-ROW(A10),"00")
3������չʾ��Ҫ��ʽ����������Ż�����excel�Ƿ������ʵ�֡�
```
#### jxls��xml����ʾ��
```
Jxls-2.4.0-����-byArvin
https://github.com/dazzlingstreak/JXLS
https://github.com/yaozd/JXLS

<xls>
    <!--������Template��sheetҳ,A1:D11��Ϊ��һ��Area,ֻ����Area�����е���Ϣ��,JXLS�Żᴦ��-->
    <area ref="Template!A1:D12">
        <!--���employer�Ļ���������ѻ�,����ʾ��ż��Ŀ.-->
        <if condition="employer.marriage == 2" ref="Template!A6:D11">
            <area ref="Template!A6:D11">
                <!--��Ա��Ϣ���б����ݣ���Ҫ�õ�each�����������-->
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