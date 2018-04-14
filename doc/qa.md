1. dubbo 为什么使用 javassist 而不用 jdk动态代理来生成代理对象？

    
    性能考虑，运行时大量使用反射性能消耗比较大，而 javassist 的性能消耗小很多
    
    
2. jdk 动态代理基于什么，cglib 基于什么？
    
    
    * cglib 基于 asm 的动态字节码技术
    * jdk 动态代理基于 java 本身提供的反射机制，InvocationHandler 的实例对象作为构造参数生成代理对象从而完成切面逻辑                                                          
    
3. asm VS javassist?


    *asm 直接操作 jvm 指令动态生成字节码
    *javassist 原理跟 asm 相同，但是基于 java api，性能稍低，但更易于使用

4. invoker 和 消费者代理对象关系


    代理对象生成之前会根据类信息和 URL 等生成 invoker 对象，invoker 对象负责与外服务的链接，并且提供消费者服务对象创建必要的数据
    顺序：invoker ——> 消费者代理对象

5. 为什么 dubbo 不适合大数据量传输


    因为 provider 端的 IO 线程数量是一定的，一般是核心数 + 1，最大不超过32，如果某个服务进行大数据量传输，可能会造成 IO 线程长时间的读写而无法释放给别的服务使用

6. connections 连接数


    服务端和消费端都可以对其进行设置，但是不推荐服务端不要对此进行控制，因为服务端对该参数进行配置的话，则对所以使用这个服务的客户端的总连接数进行了限制，如果有11个
    consumer，而 connections 设为10，那么会有一个 consumer 因为建立不了连接而无法调用服务。
    因此该参数应该有具体的 consumer 对其进行设置，表示该台机器的 consumer 需要建立几条连接，还是说只用默认的一条连接就可以。数据量不大的情况下
    
7. dubbo 线程模型


    * 两种线程池：IO 线程池；业务处理线程池
    * IO 线程池使用 netty （boss && worker），使用 Executors.newCachedThreadPool 来实现，不过 worker 所代表的 IO 线程池默认初始化为 ：CPU 核心数 + 1，基本
    做到对请求来着不拒
    * 业务处理线程池启动时默认 200，使用 Executors.newFixedThreadPool 来实现，并且任务队列默认是0，也就是说最大并发就是线程池的大小，如果任务数量超过此数目，会
    引起线程耗尽，触发线程池拒绝策略
    
8. dubbo 不太建议用在并发量大且数据量大的场景

    
    * IO 线程池 : 通常在 IO 线程处理层面就会增加压力，如果超过了 worker 的处理能力则会关闭 channel，引起消费端重连
    * 业务处理线程池 : 主要的是业务线程池处理这种请求可能会比较耗时耗 CPU，引发超时重试，同时还可能因为业务线程迟迟不能释放导致线程不够用，引起拒绝执行的问题
    
    
9. 服务暴露过程


    spring 启动之后实例化服务实现类，解析 dubbo 标签 dubbo:service 生成 ServiceConfig 的继承对象 ServiceBean，该对象既继承了 dubbo 的 ServiceConfig 信息，
    又实现了 spring 的多个接口，内部引用真实的服务对象用以对外提供服务
    
10. 服务引用过程


    由解析 dubbo:reference 标签开始，生成继承该类的 ReferenceBean 对象，同时该对象还实现了 spring 的 FactoryBean 等接口，根据接口信息等生成接口的代理对象和
    invoker 对象注入到 spring 中，用来提供服务
