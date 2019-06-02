---
layout: pattern
title: Bridge
folder: bridge
permalink: /patterns/bridge/
categories: Structural
tags:
 - Java
 - Gang Of Four
 - Difficulty-Intermediate
---

## Also known as
Handle/Body

## Intent
Decouple an abstraction from its implementation so that the two can vary independently.

> 将实现与抽象分离，以便两者可以独立变化

## Explanation

Real world example

> Consider you have a weapon with different enchantments and you are supposed to allow mixing different weapons with different enchantments. What would you do? Create multiple copies of each of the weapons for each of the enchantments or would you just create separate enchantment and set it for the weapon as needed? Bridge pattern allows you to do the second.

In Plain Words

> Bridge pattern is about preferring composition over inheritance. Implementation details are pushed from a hierarchy to another object with a separate hierarchy.

Wikipedia says

> The bridge pattern is a design pattern used in software engineering that is meant to "decouple an abstraction from its implementation so that the two can vary independently"

Wezhyn says

> 桥梁模式将继承关系转化成关联关系，是对 *java 多继承*的一种变种实现，但可扩展性更高

**Programmatic Example**

Translating our weapon example from above. Here we have the `Weapon` hierarchy

```java
public interface Weapon {
  void wield();
  void swing();
  void unwield();
  Enchantment getEnchantment();
}

public class Sword implements Weapon {

  private final Enchantment enchantment;

  public Sword(Enchantment enchantment) {
    this.enchantment = enchantment;
  }

  @Override
  public void wield() {
    LOGGER.info("The sword is wielded.");
    enchantment.onActivate();
  }

  @Override
  public void swing() {
    LOGGER.info("The sword is swinged.");
    enchantment.apply();
  }

  @Override
  public void unwield() {
    LOGGER.info("The sword is unwielded.");
    enchantment.onDeactivate();
  }

  @Override
  public Enchantment getEnchantment() {
    return enchantment;
  }
}

public class Hammer implements Weapon {

  private final Enchantment enchantment;

  public Hammer(Enchantment enchantment) {
    this.enchantment = enchantment;
  }

  @Override
  public void wield() {
    LOGGER.info("The hammer is wielded.");
    enchantment.onActivate();
  }

  @Override
  public void swing() {
    LOGGER.info("The hammer is swinged.");
    enchantment.apply();
  }

  @Override
  public void unwield() {
    LOGGER.info("The hammer is unwielded.");
    enchantment.onDeactivate();
  }

  @Override
  public Enchantment getEnchantment() {
    return enchantment;
  }
}
```

And the separate enchantment hierarchy

```java
public interface Enchantment {
  void onActivate();
  void apply();
  void onDeactivate();
}

public class FlyingEnchantment implements Enchantment {

  @Override
  public void onActivate() {
    LOGGER.info("The item begins to glow faintly.");
  }

  @Override
  public void apply() {
    LOGGER.info("The item flies and strikes the enemies finally returning to owner's hand.");
  }

  @Override
  public void onDeactivate() {
    LOGGER.info("The item's glow fades.");
  }
}

public class SoulEatingEnchantment implements Enchantment {

  @Override
  public void onActivate() {
    LOGGER.info("The item spreads bloodlust.");
  }

  @Override
  public void apply() {
    LOGGER.info("The item eats the soul of enemies.");
  }

  @Override
  public void onDeactivate() {
    LOGGER.info("Bloodlust slowly disappears.");
  }
}
```

And both the hierarchies in action

```java
Sword enchantedSword = new Sword(new SoulEatingEnchantment());
enchantedSword.wield();
enchantedSword.swing();
enchantedSword.unwield();
// The sword is wielded.
// The item spreads bloodlust.
// The sword is swinged.
// The item eats the soul of enemies.
// The sword is unwielded.
// Bloodlust slowly disappears.

Hammer hammer = new Hammer(new FlyingEnchantment());
hammer.wield();
hammer.swing();
hammer.unwield();
// The hammer is wielded.
// The item begins to glow faintly.
// The hammer is swinged.
// The item flies and strikes the enemies finally returning to owner's hand.
// The hammer is unwielded.
// The item's glow fades.

```

### 当不使用桥接模式时

为每一个武器附魔，就需要许许多多的类，例如 Action中的 *Sword 与 SoulEatingEnchantment* ， *Hammer 与 FlyingEnchantment* ,就需要在实现时组合起来，如下

#### HammerFlyingEnchantment

```java
public class HammerFlyingEnchantment implements Weapon, Enchantment {
    private static final Logger LOGGER=LoggerFactory.getLogger(Hammer.class);

    @Override
    public void wield() {
        LOGGER.info("The hammer is wielded.");
        onActivate();
    }

    @Override
    public void swing() {
        LOGGER.info("The hammer is swinged.");
        apply();
    }

    @Override
    public void unwield() {
        LOGGER.info("The hammer is unwielded.");
        onDeactivate();
    }


    @Override
    public void onActivate() {
        LOGGER.info("The item begins to glow faintly.");
    }

    @Override
    public void apply() {
        LOGGER.info("The item flies and strikes the enemies finally returning to owner's hand.");
    }

    @Override
    public void onDeactivate() {
        LOGGER.info("The item's glow fades.");
    }

    @Override
    public Enchantment getEnchantment() {
        return null;
    }
}
```

> 造成了**Hammer**在继承过程中，强制耦合了 *FlyingEnchantment*，在后续扩展Hammer 使用 **SoulEatingEnchantment**时，需要重写一个类，无法更好的扩展

### 优势

桥接模式是为了解决继承的缺点而提出的设计模式，实现类[ *Wapon*子类]可以不受抽象的约束[*Enchantment*]，不用被绑定在一个固定的抽象层次上，将变化的因素包装到最细、最小的逻辑单元中



## Applicability

Use the Bridge pattern when

* you want to avoid a permanent binding between an abstraction and its implementation. This might be the case, for example, when the implementation must be selected or switched at run-time.
* both the abstractions and their implementations should be extensible by subclassing. In this case, the Bridge pattern lets you combine the different abstractions and implementations and extend them independently
* changes in the implementation of an abstraction should have no impact on clients; that is, their code should not have to be recompiled.
* you have a proliferation of classes. Such a class hierarchy indicates the need for splitting an object into two parts. Rumbaugh uses the term "nested generalizations" to refer to such class hierarchies
* you want to share an implementation among multiple objects (perhaps using reference counting), and this fact should be hidden from the client. A simple example is Coplien's String class, in which multiple objects can share the same string representation.

## Tutorial
* [Bridge Pattern Tutorial](https://www.journaldev.com/1491/bridge-design-pattern-java)

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](http://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612)
