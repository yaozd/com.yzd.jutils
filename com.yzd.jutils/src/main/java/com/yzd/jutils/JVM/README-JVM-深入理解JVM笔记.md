##  深入理解JVM笔记
- [深入理解JVM笔记](https://github.com/Macky-He/jvm_notes_code/edit/master/doc.md)
* 目录
 ## 一、类加载机制
### 1.1 概述
    虚拟机把描述类的数据从Class文件加载到内存， 并对数据进行校验、 转换解析和初始化， 最终形成可以被虚拟机直接使用的Java类型， 这就是虚拟机的类加载机制。
### 1.2 什么是类的加载
    类的加载指的是将类的.class文件中的二进制数据读入到内存中，将其放在运行时数据区的方法区内，然后在堆区创建一个java.lang.Class对象，用来封装类在方法区内的数据结构。类的加载的最终产品是位于堆区中的Class对象，Class对象封装了类在方法区内的数据结构，并且向Java程序员提供了访问方法区内的数据结构的接口。
![](src/main/resources/img/000868A8FD824E20AA9798183850F52B.png)
### 1.4 加载.class文件的方式
* 从本地系统中直接加载
* 从网络上下载.class文件
* 从zip、jar等归档文件中加载.class文件
* 从专有数据库中提取.class文件
* 将java源文件动态编译为.class文件
### 1.5 类的声明周期
![](src/main/resources/img/0B00728F411343F1B3A730758921D2DB.png)
其中类加载的过程包括了加载、验证、准备、解析、初始化五个阶段。在这五个阶段中，加载、验证、准备和初始化这四个阶段发生的顺序是确定的，而解析阶段则不一定，它在某些情况下可以在初始化阶段之后开始，这是为了支持Java语言的运行时绑定（也成为动态绑定或晚期绑定）。另外注意这里的几个阶段是按顺序开始，而不是按顺序进行或完成，因为这些阶段通常都是互相交叉地混合进行的，通常在一个阶段执行的过程中调用或激活另一个阶段。
### 1.6 类的加载、连接和初始化
 - 加载:查找并加载类的二进制数据

&nbsp;&nbsp;&nbsp;&nbsp;加载时类加载过程的第一个阶段，在加载阶段，虚拟机需要完成以下三件事情：

    1、通过一个类的全限定名来获取其定义的二进制字节流。
    2、将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构。
    3、在Java堆中生成一个代表这个类的java.lang.Class对象，作为对方法区中这些数据的访问入口。
 - 连接：
    * 验证：确保被加载的类的正确性
    ```文件格式验证：验证字节流是否符合Class文件格式的规范；例如：是否以0xCAFEBABE开头、主次版本号是否在当前虚拟机的处理范围之内、常量池中的常量是否有不被支持的类型。
    元数据验证：对字节码描述的信息进行语义分析（注意：对比javac编译阶段的语义分析），以保证其描述的信息符合Java语言规范的要求；例如：这个类是否有父类，除了java.lang.Object之外。
    字节码验证：通过数据流和控制流分析，确定程序语义是合法的、符合逻辑的。
    符号引用验证：确保解析动作能正确执行。
   ```
    * 准备：为类的静态变量分配内存，并将其初始化为默认值
    ```
    1、这时候进行内存分配的仅包括类变量（static），而不包括实例变量，实例变量会在对象实例化时随着对象一块分配在Java堆中。
    2、这里所设置的初始值通常情况下是数据类型默认的零值（如0、0L、null、false等），而不是被在Java代码中被显式地赋予的值。
   ```
    * 解析：把类常量池中的符号引用转换为直接引用
    ```
   解析阶段是虚拟机将常量池内的符号引用替换为直接引用的过程，解析动作主要针对类或接口、字段、类方法、接口方法、方法类型、方法句柄和调用点限定符7类符号引用进行。符号引用就是一组符号来描述目标，可以是任何字面量。
   直接引用就是直接指向目标的指针、相对偏移量或一个间接定位到目标的句柄。
   ```
  - 初始化：为类的静态变量赋予正确的初始值
  ```
    初始化，为类的静态变量赋予正确的初始值，JVM负责对类进行初始化，主要对类变量进行初始化。在Java中对类变量进行初始值设定有两种方式：
    ①声明类变量是指定初始值
    ②使用静态代码块为类变量指定初始值
    JVM初始化步骤
    1、假如这个类还没有被加载和连接，则程序先加载并连接该类
    2、假如该类的直接父类还没有被初始化，则先初始化其直接父类
    3、假如类中有初始化语句，则系统依次执行这些初始化语句
```
- 类的初始化时机：所有的Java虚拟机实现必须在每个类或接口被Java程序**“首次主动使用”**时才会对类进行初始化
 - Java程序对于类的使用方式分为两种
     * -主动使用
     * -被动使用
 - 类的主动使用有七种方式
    * -创建类的实例（new一个对象）
    * -访问某个类或接口的静态变量，或者对静态变量赋值(对应字节码get_static、put_static)
    * -调用类的静态方法(对应字节码invoke_static)
    * -反射(如Class.forName("com.macky.Test"))
    * -初始化一个类的子类
    * -Java虚拟机启动时被标明为启动类的类(main方法、Java Test)
    * -JDK1.7开始提供的动态语言支持：Java.lang.invoke.MethodHandle实例的解析结果REF_getStatic、REF_putStatic、REF_invokeStatic句柄对应的类没有初始化，则初始化
 ### 1.7 虚拟机结束生命周期的情况
 * 程序正常执行结束
 * 程序执行了System.exit()方法
 * 程序在执行过程中遇见异常或者错误而终止
 * 操作系统错误导致Java虚拟机退出
 ### 1.8 类加载器
 ![](src/main/resources/img/DD21342F1D4C4FDEA9F53D6A7B026FBE.jpeg)
 
 **注意：这里父类加载器并不是通过继承关系来实现的，而是采用组合实现的。**
 * 启动类加载器：Bootstrap ClassLoader，负责加载存放在JDK\jre\lib(JDK代表JDK的安装目录，下同)下，或被-Xbootclasspath参数指定的路径中的，并且能被虚拟机识别的类库（如rt.jar
 ，所有的java.*开头的类均被Bootstrap ClassLoader加载）。启动类加载器是无法被Java程序直接引用的。
 * 扩展类加载器：Extension ClassLoader，该加载器由sun.misc.Launcher$ExtClassLoader实现，它负责加载DK\jre\lib\ext目录中，或者由java.ext.dirs
 系统变量指定的路径中的所有类库（如javax.*开头的类），开发者可以直接使用扩展类加载器。
 * 应用程序类加载器：Application ClassLoader，该类加载器由sun.misc.Launcher$AppClassLoader来实现，它负责加载用户类路径（ClassPath
 ）所指定的类，开发者可以直接使用该类加载器，如果应用程序中没有自定义过自己的类加载器，一般情况下这个就是程序中默认的类加载器。
### 1.9 类加载机制特性
 * 全盘负责，当一个类加载器负责加载某个Class时，该Class所依赖的和引用的其他Class也将由该类加载器负责载入，除非显示使用另外一个类加载器来载入
 * 父类委托，先让父类加载器试图加载该类，只有在父类加载器无法加载该类时才尝试从自己的类路径中加载该类
 * 缓存机制，缓存机制将会保证所有加载过的Class都会被缓存，当程序中需要使用某个Class时，类加载器先从缓存区寻找该Class，只有缓存区不存在，系统才会读取该类对应的二进制数据，并将其转换成Class
 对象，存入缓存区。这就是为什么修改了Class后，必须重启JVM，程序的修改才会生效
### 1.10 双亲委派模型
* 双亲委派模型的工作流程是：如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加载这个类，而是把请求委托给父加载器去完成，依次向上，因此，所有的类加载请求最终都应该被传递到顶层的启动类加载器中，只有当父加载器在它的搜索范围中没有找到所需的类时，即无法完成该加载，子加载器才会尝试自己去加载该类。
* 双亲委派机制:
   * 1、当AppClassLoader加载一个class时，它首先不会自己去尝试加载这个类，而是把类加载请求委派给父类加载器ExtClassLoader去完成。
   * 2、当ExtClassLoader加载一个class时，它首先也不会自己去尝试加载这个类，而是把类加载请求委派给BootStrapClassLoader去完成。
   * 3、如果BootStrapClassLoader加载失败（例如在$JAVA_HOME/jre/lib里未查找到该class），会使用ExtClassLoader来尝试加载；
   * 4、若ExtClassLoader也加载失败，则会使用AppClassLoader来加载，如果AppClassLoader也加载失败，则会报出异常ClassNotFoundException。
* 双亲委派模型意义：

    - 系统类防止内存中出现多份同样的字节码
    - 保证Java程序安全稳定运行
## 四、垃圾收集器
### 4.1 概览图
![概览图](src/main/resources/img/40093687372_3b03521a7c.jpg)
### 4.3 CMS垃圾收集器
#### 4.3.1 CMS收集器基本概念
&nbsp;&nbsp;&nbsp;&nbsp;CMS（Concurrent Mark Sweep）收集器，以获取最短回收停顿时间为目标，多数应用于互联网网站或者B/S系统的服务器端
* OopMap：存储着对象的引用关系
#### 4.3.2 CMS收集器过程
* 步骤：
    * 初始标记(initial mark)**STW**:只是标记GC Roots直接关联的对象
    * 并发标记(concurrent mark)：GC Roots Tracing过程
    * 重新标记(remark)：重新标记阶段是为了修正并发标记期间用户线程继续运行产生的变动那部分对象
    * 并发清除(concurrent sweep)
 ![](src/main/resources/img/20170502180239327.png)
 * 优点：并发收集、低停顿
 * 缺点：
    1. CMS收集器对CPU资源敏感
    2. CMS无法处理浮动垃圾（Float Garbage），可能出现Concurrent Mode Failure失败导致出现full Gc
    3. 基于标记清除算法，会产生内存碎片问题
 * CMS收集器步骤：
    * 1.Initial Mark:标记那些直接被GC Root引用或者被年轻代对象直接引用的所有对象
    * 2.Concurrent Mark：这个阶段遍历老年代，然后标记所有存活的对象，会根据初始标记阶段的GC Roots遍历查找。
    * 3.Concurrent Preclean：a)处理新生代新发现的引用，比如在并发阶段，在Eden区中分配了一个A对象，A对象引用了一个老年代对象B（这个B之前没有被标
                            记），在这个阶段就会标记对象B为活跃对象。
                            b)在并发标记阶段，如果老年代中有对象内部引用发生变化，会把所在的Card标记为Dirty（其实这里并非使用CardTable，而是一个
                            类似的数据结构，叫ModUnionTalble），通过扫描这些Table，重新标记那些在并发标记阶段引用被更新的对象（晋升到老年代的对象/
                            原本就在老年代的对象）
    * 4.Concurrent Abortable Preclean:a)处理 From 和 To 区的对象，标记可达的老年代对象
                                      b)和上一个阶段一样，扫描处理Dirty Card中的对象
                                      该阶段发生的前提是，新生代Eden区的内存使用量大于参数CMSScheduleRemarkEdenSizeThreshold 默认是2M，如果新生代的对象
                                      太少，就没有必要执行该阶段，直接执行重新标记阶段。
    * 5.Final Remark:在之前的过程中还会存在一些未标记的对象主要包括：
                     
                     	1、老年代的新对象被GC Roots引用
                     	2、老年代的未标记对象被新生代对象引用
                     	3、老年代已标记的对象增加新引用指向老年代其它对象
                     	4、新生代对象指向老年代引用被删除
                     也许还有其它情况..
                     
                     上述对象中可能有一些已经在Precleaning阶段和AbortablePreclean阶段被处理过，但总存在没来得及处理的，所以还有进行如下的处理：
                     
                     1、遍历新生代对象，重新标记
                     2、根据GC Roots，重新标记
                     3、遍历老年代的Dirty Card，重新标记，这里的Dirty Card大部分已经在clean阶段处理过
    * 6.Concurrent Sweep:清除垃圾对象，回收内存空间
    * 7.Concurrent ReSet：重置CMS内部结构，为下次GC做准备   
| 参数名 | 含义 | 
| :------ | :------ |
|-XX:+UseConcMarkSweepGC|使用CMS垃圾收集器|
|-XX:+UseCMSCompactAtFullCollection|在CMS收集器需要进行full Gc时进行内存碎片整理，内存整理过程是STW的（默认开启）|
### 4.4 G1垃圾收集器
![HotSpot_JVM架构图](src/main/resources/img/HotSpot_JVM_Architecture.PNG)
Oracle官方文档：https://www.oracle.com/technetwork/tutorials/tutorials-1876574.html
#### 4.4.1 G1收集器基本概念
* **响应能力**：响应能力是指应用程序或系统对请求的数据做出响应的速度。
    * 桌面用户界面对事件的响应速度
    * 网站返回一个页面的速度
    * 数据库返回一个查询的速度
* **吞吐量**：吞吐量专注于最大程度地提高应用程序在特定时间段内的工作量。
    * 在给定时间内完成的事务数
    * 批处理程序在一小时内可以完成的工作数
    * 一小时内可以完成的数据库查询数
* 分区（Region）：堆被划分为一组大小相等的堆区域，堆区域间（region）不连续，每个区域都有一个连续的虚拟内存范围。
    * region被赋予和老的垃圾收集器同样的角色：Eden、Survivor、Old
    * 一个Region的大小可以通过参数-XX:G1HeapRegionSize设定，取值范围从1M到32M，且是2的指数。
* 收集集合（CSet）：一组可被回收的分区集合。在CSet中存活的数据会在GC过程中被移到另一个可用的分区中，CSet中的分区可以来自eden、survivor、old角色分区；
* 已记忆集合（RSet）：RSet中记录了其他region中的对象引用本region中对象的关系，属于point-into结构（谁引用了我的对象）。RSet
的价值在于使得垃圾收集器不再扫描整个堆找到谁引用了当前分区的对象，只需要扫描RSet即可。
* Snapshot-At-The-Beginning（SATB）:是GC开始时的一个存活对象的快照。通过Root Tracing得到，作用是为了维持并发GC的准确定。
* Humongous区域：如果一个对象占用的空间到了或是超过了region容量的50%以上，就称这个对象为humongous对象。这样设计的目的是为了避免在垃圾收集时频繁的复制造成的内存开销。
* Card Table：每一个Region，又被分成了固定大小的若干张卡(Card)。大小通常介于128byte-512byte。默认情况下，每个卡都没被引用，当一个地址空间被引用时，这个地址空间对应的数组索引的值被标记为`0
`，即被标记为脏引用，此外RSet也将这个数组下标记录下来。一般情况下，这个RSet其实是一个Hash Table，key是别的region的起始地址，Value是一个集合，里面的元素是Card Table的Index
#### 4.4.2 G1的堆分配方式
![](src/main/resources/img/Slide9.PNG)
* G1收集器堆结构
    * heap区域被划分成相等大小的不连续的内存区域（regions），每个regions都有一个分代角色：eden、survivor、old
    * 每个角色的数量没有强制限定，G1会动态的去调整每个角色的regions个数
    * G1的特点是高效的执行回收，优先去执行大量对象可回收的区域（regions）
    * G1使用了GC停顿可预测模型，来满足用户设定的GC停顿时间，根据用户设置的停顿时间，G1会自动的选择哪些region需要清除，一次清除多少个region    
    * G1从多个region中复制存活的对象，然后集中放入到一个region中，同时整理、清除内存（copying收集算法）
* 特点
    * G1收集器是一个面向服务端的垃圾收集器，适用于多核处理器、大内存容量的服务端系统
    * 满足于短时间GC停顿的同时达到一个较高的吞吐量
    * Java 7 Update 9或更高版本以上适用
    * 和CMS垃圾收集器一样都支持和应用程序线程并发操作
    * 不会长时间的停顿去进行空闲空间的整理（CMS只能在fullGC时，用STW整理内存碎片）
    * 需要更多可预测的GC暂停时间
    * 不会对吞吐量造成影响
    * 不需要更大的堆空间（CMS需要预留空间存储浮动垃圾）
* **G1 VS CMS**
    * CMS使用mark-sweep算法，会产生内存碎片；G1使用copying算法，不会造成内存碎片
    * 对比Parallel Scavenge（基于copying）、Parallel Old收集器（基于mark-compact-sweep）、Parallel对整个内存区域进行整理，GC停顿时间较长；G1对特定region
    进行整理，GC停顿时间较短
#### 4.4.3 GC模式
&nbsp;&nbsp;&nbsp;&nbsp;G1提供了两种GC模式：Young GC和Mixed GC，两种都是完全Stop The Word的。
* Young GC：选定所有年轻代里的Region。通过控制年轻代Region的个数，即年轻代内存的大小，来控制Young GC的时间开销。
    * 说明：Young GC主要是对Eden区进行GC，它在Eden空间即将被耗尽时触发。在这种情况下，Eden空间的数据移动到Survivor空间中，如果Survivor空间不够，Eden
    空间的数据会被直接晋升到老年代中。Survivor区中的数据也有部分晋升到老年代空间中。最终Eden空间数据为空，应用线程继续工作。
    * 步骤：
    
        1.阶段一：根扫描（静态和本地对象被扫描）
        
        2.阶段二：更新RSet（处理dirty card队列更新Rset）
        
        3.阶段三：处理RSet（检测从年轻代对象指向老年代的对象）
        
        4.阶段四：对象拷贝（拷贝存活的对象到survivor/old区域）
        
        5.处理引用队列（软引用、弱引用、虚引用处理）
* Mixed GC：选定所有年轻代里的Region，外加根据global concurrent marking统计得出的收集效益最高的老年代Region。在用户指定的停顿时间内尽可能的选择收益高的老年代Region。
    * 步骤：
    
    1.全局并发标记（global concurrent marking）
    
    2.拷贝存活对象（evacuation）
* Full GC：Mixed GC不是Full GC，它只能回收部分年老代的Region，如果Mixed GC实在无法跟上程序分配内存的速度，Mixed GC就会膨胀为Serial Old GC（Full GC）来收集整个GC
 Heap。所以本质上，G1是不提供Full GC的
#### 4.4.4 global concurrent marking
&nbsp;&nbsp;&nbsp;&nbsp;global concurrent marking的执行过程类似于CMS，但是不同的是：在G1 GC中，它主要是为Mixed GC提供标记服务的，并不是一次GC过程的必须过程。
* global concurrent marking执行步骤：
    1. 初始标记（initial mark，STW）：它标记了从GC Root开始直接可达的对象。（共用Young GC的暂停，复用root scan操作）
    2. 并发标记（concurrent mark）：这个阶段从GC Root开始对heap中的对象进行标记，标记线程和应用线程并发执行，并且收集各个region中的存活对象的信息
    3. 重新标记（remark）：标记在并发标记阶段变化的对象
    4. 清理（cleanup）：清除空region（没有存活对象的），加入到free list中 
* 三色标记算法
    * 黑色：根对象，或者该对象与它的子对象都被扫描过（该根对象被标记了，且它的所有field也被标记完了）
    * 灰色：对象本身被标记了，但是它的子对象还未标记完（它的子对象没被标记或者没被标记完）
    * 白色：未被扫描对象，扫描完成所有对象后，最终为白色的为不可达对象，即垃圾对象。
* 使用SATB（Snapshot-At-The-Beginning），是GC开始时活着的对象的一个快照。

&nbsp;&nbsp;&nbsp;&nbsp;三个步骤：

    1.开始标记时生成一个快照图，标记存活的对象
    2.在并发标记阶段所有被改变的对象入队（在write barrier里把所有旧的的引用所指向的对象都变成非白的）
    3.可能存在浮动垃圾，将在下次被收集
    
* 对象漏标情况？
     * Mutator赋予一个黑对象该白对象的引用。 （利用post—write barrier，记录所有新增的引用关系，然后根据这些引用关系为根重新扫描一遍）
     * Mutator删除了所有从灰对象到该白对象的直接或者间接引用。（利用pre-write barrier，将所有即将被删除的引用关系的旧引用记录下来，最后以旧引用为根重新扫描一遍）
     * 解决办法
        * Region中有两个top-at-mark-start（TAMS）指针，分别为prevTAMS和nextTAMS。在TAMS以上的对象是新分配的，这是一种隐式的标记。对于在GC
        时已经存在的白对象，如果它是活着的，它必然会被另一个对象引用，即条件二中的灰对象。如果灰对象到白对象的直接引用或者间接引用被替换了，或者删除了，白对象就会被漏标，从而导致被回收掉，这是非常严重的错误，所以SATB破坏了第二个条件。也就是说，一个对象的引用被替换时，可以通过write barrier 将旧引用记录下来。
* 为什么要对老年代进行分区？ 为什么要对新生代代进行分区？

答：老年代有的分区垃圾多有的垃圾少，这样在回收时专注于收集垃圾多的分区。新生代使用分区主要是为了便于代大小的调整
* 停顿可预测模型
&nbsp;&nbsp;&nbsp;&nbsp;G1收集器的一大特点是通过一个停顿可预测模型根据用户配置的停顿时间来选择CSet的大小，以尽力达到用户通过-XX:MaxGCPauseMillis
参数所设置的停顿时时间。停顿时间设置太短意味着每次收集的CSet越小，导致垃圾逐步积累增多，最终退化为Serial GC，停顿时间设置太长，会导致每次都会产生长时间的停顿，影响程序的响应时间。
* G1实践
    * 不断调优暂停时间指标
    * 不要设置新生代和老年代的大小
    * 关注Evacuation Failure（堆空间垃圾太多无法完成region之间的拷贝）
#### 4.4.8 常用G1参数
| 参数名 | 含义 | 
| :------ | :------ |
| G1HeapWastePercent | 在global concurrent marking之后,我们可以知道有多少old generation regions中有多少空间可以回收，在每次Young GC之后和再次发生Mixed GC之前都会检查垃圾占比是否达到此参数，达到后才会发生下次Mixed GC | 
| G1MixedGCLiveThresholdPercent | old generation regions中存活对象的占比，只有在此参数之下才会被选入CSet中，默认值为85% | 
| G1MixedGCCountTarget | 一次global concurrent marking后，最多可以执行Mixed GC的次数|
| G1OldCSetRegionThresholdPercent | 一次Mixed GC中能够被选入CSet的最多的old generation region数量 |
|-XX:G1HeapRegionSize=n|设置Region大小，并非最终值|
|-XX:MaxGCPauseMillis	|设置G1收集过程目标时间，默认值200ms，不是硬性条件|
|-XX:G1NewSizePercent	|新生代最小值，默认值5%|
|-XX:G1MaxNewSizePercent	|新生代最大值，默认值60%|
|-XX:ParallelGCThreads	|STW期间，并行GC线程数|
|-XX:ConcGCThreads=n	|并发标记阶段，并行执行的线程数|
|-XX:InitiatingHeapOccupancyPercent	|设置触发标记周期的 Java 堆占用率阈值。默认值是45%。这里的java堆占比指的是non_young_capacity_bytes，包括old+humongous|
