[TOC]









# Spring 中的单例 bean 的线程安全

单例 bean 存在线程问题，主要是因为当多个线程操作同一个对象的时候，对这个对象的非静态成员变量的写操作会存在线程安全问题。

常见的有两种解决办法：

1. 在Bean对象中尽量避免定义可变的成员变量（不太现实）。
2. 在类中定义一个ThreadLocal成员变量，将需要的可变成员变量保存在 ThreadLocal 中（推荐的一种方式）。



# Spring 中的 bean 的作用域



# Spring AOP 和 AspectJ AOP 有什么区别？

**Spring AOP 属于运行时增强，而 AspectJ 是编译时增强。** Spring AOP 基于代理(Proxying)，而 AspectJ 基于字节码操作(Bytecode Manipulation)。

Spring AOP 已经集成了 AspectJ  ，AspectJ  应该算的上是 Java 生态系统中最完整的 AOP 框架了。AspectJ  相比于 Spring AOP 功能更加强大，但是 Spring AOP 相对来说更简单，

如果我们的切面比较少，那么两者性能差异不大。但是，当切面太多的话，最好选择 AspectJ ，它比Spring AOP 快很多。

# Spring 的生命周期及其扩展

## 生命周期

Spring中的bean在创建过程中大概分为以下几个步骤:

> **实例化->填充属性->执行Aware接口->初始化->可用状态->销毁**

1. 实例化就是调用类的构造器进行对象创建的过程，比如：new Object();就实例化了一个Obejct对象；
2. 填充属性是指注入bean的依赖或者给属性赋值；
3. Aware接口是Spring中的**“觉醒”**接口，是Spring容器通过回调向bean注入相关对象的接口；
4. 初始化是指完成bean的创建和依赖注入后进行的一个回调，可以利用这个回调进行一些自定义的工作,实现初始化的方式有三种，分别是实现InitializingBean接口、使用@PostConstruct注解和xml中通过init-method属性指定初始化方法;
5. 可用状态是指bean已经准备就绪、可以被应用程序使用了，此时bean会一直存在于Spring容器中；
6. 销毁是指这个bean从Spring容器中消除，这个操作往往伴随着Spring容器的销毁。实现销毁方法的方式有3中，分别为实现DisposableBean接口、使用@PreDestroy注解和xml中通过destroy-method属性指定。

https://blog.csdn.net/weixin_39610724/article/details/111017647?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-0.pc_relevant_paycolumn_v2&spm=1001.2101.3001.4242.1&utm_relevant_index=3

![img](https://img2018.cnblogs.com/blog/1066538/201909/1066538-20190902000437259-1068766043.png)

### 实例化

AbstractAutowireCapableBeanFactory.doCreateBean中会调用createBeanInstance()方法,该阶段主要是从beanDefinitionMap循环读取bean,获取它的属性，然后利用反射(core包下有ReflectionUtil会先强行将构造方法setAccessible(true))读取对象的构造方法(spring会自动判断是否是有参数还是无参数，以及构造方法中的参数是否可用),然后再去创建实例（newInstance）

### 初始化

初始化主要包括两个步骤,一个是属性填充，另一个就是具体的初始化过程

#### **属性赋值** 

PopulateBean()会对bean的依赖属性进行填充，@AutoWired注解注入的属性就发生这个阶段，假如我们的bean有很多依赖的对象，那么spring会依次调用这些依赖的对象进行实例化，注意这里可能会有循环依赖的问题。后面我们会讲到spring是如何解决循环依赖的问题

#### **初始化** Initialization

初始化的过程包括将初始化好的bean放入到spring的缓存中、填充我们预设的属性进一步做后置处理等

### 使用和销毁

在Spring将所有的bean都初始化好之后，我们的业务系统就可以调用了。而销毁主要的操作是销毁bean，主要是伴随着spring容器的关闭，此时会将spring的bean移除容器之中。此后spring的生命周期到这一步彻底结束，不再接受spring的管理和约束。

### 实例化和初始化的区别

实例化是在jvm的堆中创建了这个对象实例，此时它只是一个空的对象，所有的属性为null。

初始化的过程就是将对象依赖的一些属性进行赋值之后，调用某些方法来开启一些默认加载。比如spring中配置的数据库属性Bean，在初始化的时候就会将这些属性填充，比如driver、jdbcurl等。



## 扩展点

在这些过程当中有很多的扩展点，这里我们介绍一些常用的，我们用EP(extension point)来进行标识，如下：

> EP1->实例化->EP2->填充属性->执行Aware接口->EP3->初始化->EP4->可用状态->销毁

从上述过程中我们可以看出实例化前后的扩展点分别为EP1和EP2,初始化前后的扩展点分别为EP3和EP4。

EP1: 是在BeanDefinition加载完成之后,实例化之前.

# 





# spring普通bean初始化过程



![在这里插入图片描述](https://img-blog.csdnimg.cn/20201025114250757.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NjamF2YQ==,size_16,color_FFFFFF,t_70#pic_center)



# BeanFactory接口

BeanFactory是接口，提供了IOC容器最基本的形式，给具体的IOC容器的实现提供了规范。

BeanFactory是个Factory，也就是IOC容器或对象工厂，**所有的Bean都是由BeanFactory(也就是IOC容器)来进行管理的**。spring用它来管理和装配**普通bean**的IOC容器,它有多种实现，比如AnnotationConfigApplicationContext、XmlWebApplicationContext等

在Spring中，BeanFactory是IOC容器的核心接口，它的职责包括：**实例化、定位、配置应用程序中的对象及建立这些对象间的依赖**。BeanFactory只是个接口，并不是IOC容器的具体实现，但是Spring容器给出了很多种实现，如 DefaultListableBeanFactory、XmlBeanFactory、ApplicationContext等，其中XmlBeanFactory就是常用的一个，该实现将以XML方式描述组成应用的对象及对象间的依赖关系。XmlBeanFactory类将持有此XML配置元数据，并用它来构建一个完全可配置的系统或应用。  

BeanFactory和ApplicationContext就是spring框架的两个IOC容器，现在一般使用ApplicationnContext，其不但包含了BeanFactory的作用，同时还进行更多的扩展。原始的BeanFactory无法支持spring的许多插件，**如AOP功能、Web应用等**。ApplicationContext接口,它由BeanFactory接口派生而来，ApplicationContext包含BeanFactory的所有功能，通常建议比BeanFactory优先。

ApplicationContext 以一种更向面向框架的方式工作以及对上下文进行分层和实现继承，ApplicationContext包还提供了以下的功能： 
• MessageSource, 提供国际化的消息访问 
• 资源访问，如URL和文件 
• 事件传播 
• 载入多个（有继承关系）上下文 ，使得每一个上下文都专注于一个特定的层次，比如应用的web层; 

# FactoryBean及自定义实例

Spring 中有两种类型的Bean，一种是普通Bean，另一种是工厂Bean 即 FactoryBean。

FactoryBean跟普通Bean不同，其返回的对象不是指定类的一个实例，而是该FactoryBean的getObject方法所返回的对象。

一般情况下，Spring通过反射机制利用<bean>的class属性指定实现类实例化Bean，在某些情况下，实例化Bean过程比较复杂，如果按照传统的方式，则需要在<bean>中提供大量的配置信息。配置方式的灵活性是受限的，这时采用编码的方式可能会得到一个简单的方案。

Spring为此提供了一个org.springframework.bean.factory.FactoryBean的工厂类接口，用户可以通过实现该接口定制实例化Bean的逻辑。FactoryBean接口对于Spring框架来说占有重要的地位，Spring自身就提供了70多个FactoryBean的实现。它们隐藏了实例化一些复杂Bean的细节，给上层应用带来了便利。从Spring3.0开始，FactoryBean开始支持泛型，即接口声明改为FactoryBean<T>的形式.

应用场景：

FactoryBean 通常是用来创建比较复杂的bean，一般的bean 直接用xml配置即可，但如果一个bean的创建过程中涉及到很多其他的bean 和复杂的逻辑，用xml配置比较困难，这时可以考虑用FactoryBean。

第三方框架要集成进Spring，往往就是通过实现FactoryBean来集成的，比如 MyBatis3 提供 mybatis-spring项目中的 `org.mybatis.spring.SqlSessionFactoryBean`：

https://cloud.tencent.com/developer/article/1497577

https://blog.csdn.net/u014082714/article/details/81166648

# ApplicationContext接口



# Spring Aware接口

# BeanDefintaion接口

BeanDefintaion的作用是集成了我们的配置对象中的各种属性。



# BeanDefinitionRegistryPostProcessor

BeanDefinitionRegistryPostProcessor继承自BeanFactoryPostProcessor，是一种比较特殊的BeanFactoryPostProcessor。
BeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry方法可实现**自定义的bean注册**定义。
通常spring注册bean使用静态方式, 如:xml、@Bean注解或@Component方式实现注册.不能通过程序来选择是否注册。

而实现BeanDefinitionRegistryPostProcessor的类可以获得BeanDefinitionRegistry 对象，**通过它可以动态的注册组件**,是实现动态注册的钩子函数。spring典型的ConfigurationClassPostProcessor拓展BeanDefinitionRegistryPostProcessor 解析@Configuration配置类 .

```javaj a
src/test/java/com/tiger/questionoftheday/BeanDefinitionRegistryPostProcessorTest.java
```

# Spring InitializingBean接口

InitializingBean为bean提供了定义初始化方法的方式。

# SmartInitializingSingleton接口

# BeanPostProcessor 扩展点

BeanPostProcessor 执行时机为bean初始化（**Initialization**）阶段，日常可以拓展该接口对bean初始化进行定制化处理。

BeanPostProcessor 接口定义了你可以提供实现你自己的（或覆盖容器默认的）实 例逻辑，依赖解析逻辑等的回调方法。如果你想在 Spring 容器完成实例化，配置和初始化 bean 之后实现一些自定义逻辑，那么你可以使用一个或多个 BeanPostProcessor 实现类 的插件。

你可以配置多个 BeanPostProcessor 实例，而且你还可以通过设置 order 属性来 控制这些 BeanPostProcessor 执行的顺序。 仅当 BeanPostProcessor 实现了 Ordered 接口你才可以设置设个属性；如果你想编写你自己的 BeanPostProcessor，你 也应该考虑实现 Ordered 接 口 。

BeanPostProcessor 的范围是对于每一个容器来说的。如果你使用了容器继承，那么这才有所关联。如果你在一个容器中定义了 BeanPostProcessor，那么它仅仅 会在那个容器中后处理 bean。换句话说，一个容器中定义的 bean 不会被另外一个容器 中定义的 BeanPostProcessor 来进行后处理，即便是两个容器都是相同继承链上的 一部分。

```java
com.tiger.questionoftheday.TracingBeanPostProcessor
```

# BeanFactoryPostProcessor 扩展点

https://wizardforcel.gitbooks.io/spring-doc-3x/content/24.html

BeanFactoryPostProcessor是spring容器级别的拓展接口。这个扩展点是发生在bean实例化之前，BeanDefinition读取完之后。

1. BeanFactoryPostProcessor是beanFactory的后置处理器接口，通过BeanFactoryPostProcessor，我们可以自定义spring容器中的bean定义，BeanFactoryPostProcessor是在spring容器加载了bean的定义信息之后、bean实例化之前执行；
2. BeanFactoryPostProcessor类型的bean会被spring自动检测，在常规bean实例化之前被spring调用；
3. BeanFactoryPostProcessor的常用场景包括s**pring中占位符的处理、我们自定义的敏感信息的解密处理**，当然不局限与此；

# InstantiationAwareBeanPostProcessor

InstantiationAwareBeanPostProcessor 执行时机为bean实例化（**Instantiation**）阶段，典型用于替换bean默认创建方式，例如aop通过拓展接口生成代理对应，主要用于基础框架层面。如果日常业务中需要拓展该，spring推荐使用适配器类InstantiationAwareBeanPostProcessorAdapter。



# Spring InstantiationAwareBeanPostProcessor



# Spring ApplicationListener接口

ApplicationContext事件机制是观察者设计模式的实现，通过ApplicationEvent类和ApplicationListener接口，可以实现ApplicationContext事件处理。

# Spring DisposableBean 接口



# Spring中的XML schema扩展机制

https://mp.weixin.qq.com/s/d10MlNm5IBVKE0axGf3RYg