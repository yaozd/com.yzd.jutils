## Java -demo0
```
@Slf4j
public class JunitRunner {

    public static void main(String[] args) throws ClassNotFoundException {
        args[0]="com.yzd.demo.test.frontcase.FrequentRequestsCaseUnitTest#frequentRequests4MultiplexingHttpConnectionPoolTest";
        /**参数测试
        //args[0]="com.yzd.demo.test.frontcase.FrequentRequestsCaseUnitTest#frequentRequests4MultiplexingHttpConnectionPoolTest";/**/
        if(args.length==0){
            log.info("缺少参数，eg:com.yzd.demo.test.nettyclientcase.SimulateEstablishingLargeNumberOfConnectionsUnitTest#doWorkWithChannelClose");
            System.exit( 1);
        }
        String[] classAndMethod = args[0].split("#");
        Request request = Request.method(Class.forName(classAndMethod[0]), classAndMethod[1]);
        log.info("METHOD_THREADS:{};METHOD_INVOCATIONS;{};", demoUnitTest.METHOD_THREADS,demoUnitTest.METHOD_INVOCATIONS);
        /**Request request = Request.method(Class.forName("com.yzd.demo.test.nettyclientcase.SimulateEstablishingLargeNumberOfConnectionsUnitTest"), "doWorkWithChannelClose");
        //运行当前单元测试的所有方法
        //Result result = JUnitCore.runClasses(demoUnitTest.class);/**/
        //运行当前单元测试中的某一方法
        Result result = new JUnitCore().run(request);
        //
        for (Failure fail : result.getFailures()) {
            log.info(fail.toString());
        }
        if (result.wasSuccessful()) {
            log.info("All tests finished successfully...");
        }
    }
}
=================================
你可以像这样调用它：
java -jar .\demo-test-1.0-SNAPSHOT.jar com.yzd.demo.test.nettyclientcase.SimulateEstablishingLargeNumberOfConnectionsUnitTest#doWorkWithChannelClose
```

## Java -demo1
```
public class JunitRunner {

    public static void main(String[] args) throws ClassNotFoundException {
        Request request = Request.method(Class.forName("com.yzd.demo.test.nettyclientcase.SimulateEstablishingLargeNumberOfConnectionsUnitTest"),
                "doWorkWithChannelClose");
        //运行当前单元测试的所有方法
        //Result result = JUnitCore.runClasses(demoUnitTest.class);
        //运行当前单元测试中的某一方法
        Result result = new JUnitCore().run(request);
        //
        for (Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests finished successfully...");
        }
    }
}
=================================
你可以像这样调用它：
java -jar .\demo-test-1.0-SNAPSHOT.jar com.yzd.demo.test.nettyclientcase.SimulateEstablishingLargeNumberOfConnectionsUnitTest#doWorkWithChannelClose
```

## Java -demo1
```
public class JunitRunner {

    public static void main(String[] args) throws ClassNotFoundException {
        Request request = Request.method(Class.forName("com.yzd.demo.test.nettyclientcase.SimulateEstablishingLargeNumberOfConnectionsUnitTest"),
                "doWorkWithChannelClose");
        //运行当前单元测试的所有方法
        //Result result = JUnitCore.runClasses(demoUnitTest.class);
        //运行当前单元测试中的某一方法
        Result result = new JUnitCore().run(request);
        //
        for (Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests finished successfully...");
        }
    }
}
=================================
你可以像这样调用它：
java -jar .\demo-test-1.0-SNAPSHOT.jar com.yzd.demo.test.nettyclientcase.SimulateEstablishingLargeNumberOfConnectionsUnitTest#doWorkWithChannelClose
```

## Java -demo2
```
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

public class SingleJUnitTestRunner {
    public static void main(String... args) throws ClassNotFoundException {
        String[] classAndMethod = args[0].split("#");
        Request request = Request.method(Class.forName(classAndMethod[0]),
                classAndMethod[1]);

        Result result = new JUnitCore().run(request);
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}
=================================
你可以像这样调用它：
java -jar .\demo-test-1.0-SNAPSHOT.jar com.yzd.demo.test.nettyclientcase.SimulateEstablishingLargeNumberOfConnectionsUnitTest#doWorkWithChannelClose
```