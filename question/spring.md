[TOC]



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



## 扩展点

在这些过程当中有很多的扩展点，这里我们介绍一些常用的，我们用EP(extension point)来进行标识，如下：

> EP1->实例化->EP2->填充属性->执行Aware接口->EP3->初始化->EP4->可用状态->销毁

从上述过程中我们可以看出实例化前后的扩展点分别为EP1和EP2,初始化前后的扩展点分别为EP3和EP4。







# Spring的事件机制





# Spring Aware接口

# Spring InitializingBean接口



# Spring BeanPostProcessor



# Spring InstantiationAwareBeanPostProcessor



# Spring ApplicationListener接口

ApplicationContext事件机制是观察者设计模式的实现，通过ApplicationEvent类和ApplicationListener接口，可以实现ApplicationContext事件处理。

# Spring DisposableBean 接口