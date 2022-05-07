# Spring的事件机制

[TOC]

# 观察者模式

- Subject：被观察的对象。它提供一系列方法来增加和删除观察者对象，同时它定义了通知方法notify()。目标类可以是接口，也可以是抽象类或具体类。
- ConcreteSubject：具体的被观察对象。Subject的具体实现类，在这里实现通知事件。
- Observer：观察者。这里是抽象的观察者，观察者有一个或者多个。
- ConcreteObserver：具体的观察者。在这里维护观察对象的具体操作。

观察者模式的核心就是：事件中心持有所有的订阅者，每当事件发生时循环通知所有的订阅者

# Java中的事件机制

Java中提供了基本的事件处理基类：

1. EventObject：所有事件状态对象都将从其派生的根类；
2. EventListener：所有事件侦听器接口必须扩展的标记接口；

# Spring 中的事件机制



在 Spring 容器中通过 `ApplicationEvent` 类和 `ApplicationListener` 接口来处理事件。

