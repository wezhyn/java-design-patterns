---
layout: pattern
title: Singleton
folder: singleton
permalink: /patterns/singleton/
categories: Creational
tags:
 - Java
 - Gang Of Four
 - Difficulty-Beginner
---

## Intent
Ensure a class only has one instance, and provide a global point of
access to it.


## Explanation
Real world example

> There can only be one ivory tower where the wizards study their magic. The same enchanted ivory tower is always used by the wizards. Ivory tower here is singleton.

In plain words

> Ensures that only one object of a particular class is ever created.

Wikipedia says

> In software engineering, the singleton pattern is a software design pattern that restricts the instantiation of a class to one object. This is useful when exactly one object is needed to coordinate actions across the system.

**Programmatic Example**

Joshua Bloch, Effective Java 2nd Edition p.18

> A single-element enum type is the best way to implement a singleton

```java
public enum EnumIvoryTower {
  INSTANCE;
}
```

> **This implementation is thread safe, however adding any other method and its thread safety is developers responsibility.**

Then in order to use

```java
EnumIvoryTower enumIvoryTower1 = EnumIvoryTower.INSTANCE;
EnumIvoryTower enumIvoryTower2 = EnumIvoryTower.INSTANCE;
assertEquals(enumIvoryTower1, enumIvoryTower2); // true
```

### 解释InitializingOnDemandHolderIdiom

```java
public final class InitializingOnDemandHolderIdiom {


    static {
        System.out.println("init InitializingOnDemandHolderIdiom");
    }
    /**
     * Private constructor.
     */
    private InitializingOnDemandHolderIdiom() {
    }

    /**
     * @return Singleton instance
     */
    public static InitializingOnDemandHolderIdiom getInstance() {
        return HelperHolder.INSTANCE;
    }

    /**
     * Provides the lazy-loaded Singleton instance.
     */
    private static class HelperHolder {
        static {
            System.out.println("init HelperHolder");
        }


        private static final InitializingOnDemandHolderIdiom INSTANCE=
                new InitializingOnDemandHolderIdiom();
    }

    public static void main(String[] args) {
        System.out.println("hhh");
    }
}
```

> The inner class is referenced no earlier (and therefore loaded no earlier by the class loader) than the moment that getInstance() is called.
>
> 意思是将对 *InitializingOnDemandHolderIdiom* 单例的成生延迟到对 *getInstance()* 方法的调用，即 Lazy  initialized singleton

> this solution is thread-safe without requiring special language constructs 
>
> 由于 内部类的初始化由 *Java 虚拟机* 控制，保证了在多线程环境下，只有一个线程会执行类的初始化[ 即：<client>() ]，其他线程都将阻塞等待，直到活动线程初始化完  见 [加载实例](./src/main/java/com/iluwatar/singleton/attempt/LoadClass.java)

## Applicability

Use the Singleton pattern when

* Joshua Bloch, Effective Java 2nd Edition p.18there must be exactly one instance of a class, and it must be accessible to clients from a well-known access point
* when the sole instance should be extensible by subclassing, and clients should be able to use an extended instance without modifying their code

## Typical Use Case

* the logging class
* managing a connection to a database
* file manager

## Real world examples

* [java.lang.Runtime#getRuntime()](http://docs.oracle.com/javase/8/docs/api/java/lang/Runtime.html#getRuntime%28%29)
* [java.awt.Desktop#getDesktop()](http://docs.oracle.com/javase/8/docs/api/java/awt/Desktop.html#getDesktop--)
* [java.lang.System#getSecurityManager()](http://docs.oracle.com/javase/8/docs/api/java/lang/System.html#getSecurityManager--)


## Consequences

* Violates Single Responsibility Principle (SRP) by controlling their own creation and lifecycle.
* Encourages using a global shared instance which prevents an object and resources used by this object from being deallocated.     
* Creates tightly coupled code. The clients of the Singleton become difficult to test.
* Makes it almost impossible to subclass a Singleton.

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](http://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612)
* [Effective Java (2nd Edition)](http://www.amazon.com/Effective-Java-Edition-Joshua-Bloch/dp/0321356683)
