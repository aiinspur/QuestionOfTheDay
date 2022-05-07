[TOC]



## **1. Overview**

In this quick article, we'll be looking at the *[CopyOnWriteArrayList](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/CopyOnWriteArrayList.html)* from the *java.util.concurrent* package.

This is a very useful construct in the multi-threaded programs – when we want to iterate over a list in a thread-safe way without an explicit synchronization.

这在多线程程序中是一个非常有用的结构——当我们想要以线程安全的方式迭代列表而不需要显式同步时。

## **2. CopyOnWriteArrayList API**

The design of the *CopyOnWriteArrayList* uses an interesting technique to make it thread-safe without a need for synchronization. When we are using any of the modify methods – such as *add()* or *remove() –* the whole content of the *CopyOnWriteArrayList* is copied into the new internal copy.

CopyOnWriteArrayList 的设计使用了一种有趣的技术来使其线程安全，无需同步。 当我们使用任何修改方法时——例如 add() 或 remove()——CopyOnWriteArrayList 的全部内容被复制到新的内部副本中。

Due to this simple fact, **we can iterate over the list in a safe way, even when concurrent modification is happening**.

由于这个简单的事实，我们可以以安全的方式迭代列表，即使发生并发修改。

When we're calling the *iterator()* method on the *CopyOnWriteArrayList,* we get back an *Iterator* backed up by the immutable snapshot of the content of the *CopyOnWriteArrayList*.

当我们在 CopyOnWriteArrayList 上调用 iterator() 方法时，我们会返回一个由 CopyOnWriteArrayList 内容的不可变快照备份的迭代器。

Its content is an exact copy of data that is inside an *ArrayList* from the time when the *Iterator* was created. Even if in the meantime some other thread adds or removes an element from the list, that modification is making a fresh copy of the data that will be used in any further data lookup from that list.

它的内容是创建迭代器时 ArrayList 中数据的精确副本。 即使同时其他线程从列表中添加或删除元素，该修改也会制作数据的新副本，该副本将用于从该列表中进行任何进一步的数据查找。

The characteristics of this data structure make it particularly useful in cases when we are iterating over it more often than we are modifying it. If adding elements is a common operation in our scenario, then *CopyOnWriteArrayList* won't be a good choice – because the additional copies will definitely lead to sub-par performance.

这种数据结构的特性使得它在我们迭代它比我们修改它更频繁的情况下特别有用。 如果在我们的场景中添加元素是一种常见的操作，那么 CopyOnWriteArrayList 将不是一个好的选择——因为额外的副本肯定会导致性能低于标准。

## **3. Iterating Over CopyOnWriteArrayList While Inserting**

Let's say that we're creating an instance of the *CopyOnWriteArrayList* that stores integers:

假设我们正在创建一个存储整数的 CopyOnWriteArrayList 实例：

```java
CopyOnWriteArrayList<Integer> numbers 
  = new CopyOnWriteArrayList<>(new Integer[]{1, 3, 5, 8});
```

Next, we want to iterate over that array, so we are creating an *Iterator* instance:

接下来，我们要迭代该数组，因此我们正在创建一个 Iterator 实例：

```java
Iterator<Integer> iterator = numbers.iterator();
```

After the *Iterator* is created, we are adding a new element to the *numbers* list:

创建迭代器后，我们向numbers添加一个新元素：

```java
numbers.add(10);
```

Keep in mind that, when we create an iterator for the *CopyOnWriteArrayList,* we get an immutable snapshot of the data in the list at the time *iterator()* was called.

请记住，当我们为 CopyOnWriteArrayList 创建迭代器时，我们会在调用 iterator() 时获得列表中数据的不可变快照。

Because of that, while iterating over it, we won't see the number *10* in the iteration:

因此，在迭代它时，我们不会在迭代中看到数字 10：

```java
List<Integer> result = new LinkedList<>();
iterator.forEachRemaining(result::add);
 
assertThat(result).containsOnly(1, 3, 5, 8);
```

Subsequent iteration using newly created *Iterator* will also return the number 10 that was added:

使用新创建的迭代器的后续迭代也将返回添加的数字 10：

```java
Iterator<Integer> iterator2 = numbers.iterator();
List<Integer> result2 = new LinkedList<>();
iterator2.forEachRemaining(result2::add);

assertThat(result2).containsOnly(1, 3, 5, 8, 10);
```

## **4. Removing While Iterating Is Not Allowed**

不允许在迭代时删除

The *CopyOnWriteArrayList* was created to allow for the possibility of safe iterating over elements even when the underlying list gets modified.

创建 CopyOnWriteArrayList 是为了允许安全地迭代元素，即使基础列表被修改

Because of the copying mechanism, the *remove()* operation on the returned *Iterator* is not permitted – resulting with *UnsupportedOperationException:*

由于复制机制，不允许对返回的 Iterator 执行 remove() 操作 - 导致 UnsupportedOperationException：

```java
@Test(expected = UnsupportedOperationException.class)
public void whenIterateOverItAndTryToRemoveElement_thenShouldThrowException() {
    
    CopyOnWriteArrayList<Integer> numbers
      = new CopyOnWriteArrayList<>(new Integer[]{1, 3, 5, 8});

    Iterator<Integer> iterator = numbers.iterator();
    while (iterator.hasNext()) {
        iterator.remove();
    }
}
```

## **5. Conclusion**

In this quick tutorial, we had a look at the *CopyOnWriteArrayList* implementation from the *java.util.concurrent* package.

在这个快速教程中，我们查看了 java.util.concurrent 包中的 CopyOnWriteArrayList 实现

We saw the interesting semantics of this list and how it can be iterated in a thread-safe way, while other threads can continue inserting or removing elements from it.

我们看到了这个列表的有趣语义以及如何以线程安全的方式对其进行迭代，而其他线程可以继续从中插入或删除元素。

The implementation of all these examples and code snippets can be found in the [GitHub project](https://github.com/eugenp/tutorials/tree/master/core-java-modules/core-java-concurrency-collections) – this is a Maven project, so it should be easy to import and run as it is.

所有这些示例和代码片段的实现都可以在 GitHub 项目中找到——这是一个 Maven 项目，因此应该很容易导入并按原样运行。

原文出处: [Guide to CopyOnWriteArrayList](https://www.baeldung.com/java-copy-on-write-arraylist)