# reactives

QuantLET reactives. A framework for on-demand, real-time computations.

Copyright (C) 2006 Jorge M. Faleiro Jr.

The really-really simple, really-really fast "infinite spreadsheets". State of reactive nodes are backed by a DHT (tapestry) allowing your reactive models to scale literally forever.

The static, explicit, pull-based reactive framework of choice if you are stuck in Java and still partying like it is 2006.

## Installation

```
git clone https://github.com/jfaleiro/reactives.git
cd reactives
gradle check
```

## Use

You can go to one of the nodes in the network and generate a few reactives

```java
R<Integer> a = reactive("a", 1);
R<Integer> b = reactive("b", 2);

R<Integer> c = reactive("c", () -> a.eval + b.eval()); // C = A + B

print(c.eval());
3

a.set(4);
print(c.eval());
6
```

Go to any other node on the network to follow on what is going on, i.e.

```java
R<Integer> c = reactive("c");
c.onReaction((e) -> print("c = " + e.eval());

R<Integer> d = reactive(() -> e.eval() + 1); // unlabeled = C + 1
d.onReaction((e) -> print("d = " + e.eval());

// will yield...
c = 3
d = 4
c = 6
d = 7
```

## For more

Check unit tests for examples of use

```
git clone https://github.com/jfaleiro/reactives.git
cd reactives
gradle test
```
