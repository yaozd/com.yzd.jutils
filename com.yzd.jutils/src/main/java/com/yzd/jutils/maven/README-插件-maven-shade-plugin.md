## 插件maven-shade-plugin
> maven-shade-plugin	用来打可执行包，包含依赖，以及对依赖进行取舍过滤

- [插件maven-shade-plugin](https://my.oschina.net/u/2377110/blog/1585553)

### [资源转换-Transformer](http://maven.apache.org/plugins/maven-shade-plugin/examples/resource-transformers.html)
- AppendingTransformer
```
用来处理多个jar包中存在重名的配置文件的合并，尤其是spring。
<configuration>
	<transformers>
		<transformer
			implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer">
			<resource>META-INF/spring.handlers</resource>
		</transformer>
		<transformer
			implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer">
			<resource>META-INF/spring.schemas</resource>
		</transformer>
	</transformers>
</configuration>
```
- ServicesResourceTransformer
```
JDK的服务发现机制是基于META-INF/services/目录的，如果同一接口存在多个实现需要合并 ，则可以使用此Transformer。

<configuration>
  <transformers>
    <transformer implementation="org.apache.maven.plugins.shade.resource.ServicesResourceTransformer"/>
  </transformers>
</configuration>
```