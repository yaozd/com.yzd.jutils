## *grpc参考*
- [微服务框架 gRPC-Nebula](https://www.oschina.net/p/grpc-nebula)
- []()
- []()

## GRPC
- gRpc-HelloWorld
    - [https://github.com/yaozd/grpc-springboot-lin](https://github.com/yaozd/grpc-springboot-lin)
- gRpc-grpc-proxy
    - [https://github.com/yaozd/grpc-proxy](https://github.com/yaozd/grpc-proxy) -推荐参考byArvin PS:根据个人习惯做了一些调整，更加方便测试
    - [https://github.com/codahale/grpc-proxy](https://github.com/codahale/grpc-proxy)

## [protobuf-maven-plugin插件编译proto文件生成客户端和服务端代码](https://blog.csdn.net/jiangshuanshuan/article/details/100581269)
```
protobuf:compile //编译消息对象
protobuf:compile-custom //依赖消息对象,生成接口服务
------------------------------------------------
mvn protobuf:compile
mvn protobuf:compile-custom
------------------------------------------------
<!--在执行mvn compile的时候会执行以下操作-->
mvn compile
------------------------------------------------
<plugin>
    <groupId>org.xolstice.maven.plugins</groupId>
    <artifactId>protobuf-maven-plugin</artifactId>
    <version>${protobuf.plugin.version}</version>
    <extensions>true</extensions>
    <configuration>
        <protocArtifact>com.google.protobuf:protoc:${protoc.version}:exe:${os.detected.classifier}</protocArtifact>
        <pluginId>grpc-java</pluginId>
        <pluginArtifact>io.grpc:protoc-gen-grpc-java:${grpc.version}:exe:${os.detected.classifier}</pluginArtifact>
        <!--默认值-->
        <protoSourceRoot>${project.basedir}/src/main/proto</protoSourceRoot>
        <!--默认值-->
        <!--<outputDirectory>${project.build.directory}/generated-sources/protobuf/java</outputDirectory>-->
        <outputDirectory>${project.basedir}/src/main/java</outputDirectory>
        <!--设置是否在生成java文件之前清空outputDirectory的文件，默认值为true，设置为false时也会覆盖同名文件-->
        <clearOutputDirectory>false</clearOutputDirectory>
        <!--更多配置信息可以查看https://www.xolstice.org/protobuf-maven-plugin/compile-mojo.html-->
    </configuration>
    <executions>
        <execution>
            <!--在执行mvn compile的时候会执行以下操作-->
            <phase>compile</phase>
            <goals>
                <!--生成OuterClass类-->
                <goal>compile</goal>
                <!--生成Grpc类-->
                <goal>compile-custom</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
