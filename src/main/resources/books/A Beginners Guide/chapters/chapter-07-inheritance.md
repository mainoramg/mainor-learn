# Chapter 7: Inheritance

## Table of Contents
* [Inheritance Basics](#inheritance-basics)
* [Member Access and Inheritance](#member-access-and-inheritance)
* [Constructors and Inheritance](#constructors-and-inheritance)
* [Using super to Call Superclasses Constructors](#using-super-to-call-superclasses-constructors)
* [Using super to Access Superclasses Members](#using-super-to-access-superclasses-members)
* [Creating a Multilevel Hierarchy](#creating-a-multilevel-hierarchy)
* [When Are Constructors Executed?](#when-are-constructors-executed?)
* [Superclass References and Subclass Objects](#superclass-references-and-subclass-objects)
* [Method Overriding](#method-overriding)
  * [Overriding Methods Support Polymorphism](#overriding-methods-support-polymorphism)
  * [Why Overridden Methods?](#why-overridden-methods)
* [Using Abstract Classes](#using-abstract-classes)
* [Using final](#using-final)
  * [final Prevents Overriding](#final-prevents-overriding)
  * [final Prevents Inheritance](#final-prevents-inheritance)
  * [Using final with Data Members](#using-final-with-data-members)
* [The Object Class](#the-object-class)

## Inheritance Basics

In the language of Java, a class that is inherited is called a *superclass*. The class that does the inheriting is called a *subclass*. Therefore, a subclass is a specialized version of a superclass. It inherits all of the variables and methods defined by the superclass and adds its own, unique elements.

Inheritance is done by using the **extends** keyword. Thus, the subclass adds to (extends) the superclass. The general form of a **class** declaration that inherits a superclass is shown here:
```text
class subclass-name extends superclass-name {
    // body of class
}
```

You can specify only one superclass for any subclass that you create. Java does not support the inheritance of multiple superclasses into a single subclass. You can, however, create a hierarchy of inheritance in which a subclass becomes a superclass of another subclass. Of course, no class can be a superclass of itself.

## Member Access and Inheritance

Inheriting a class *does not* overrule the **private** access restriction. Thus, even though a subclass includes all of the members of its superclass, it cannot access those members of the superclass that have been declared **private**.

Remember that a class member that has been declared **private** will remain private to its class. It is not accessible by any code outside its class, including subclasses. Java programmers typically use accessor methods to provide access to the private members of a class.

**Question: When should I make an instance variable private?**
**Answer**: There are no hard and fast rules, but here are two general principles. If an instance variable is to be used only by methods defined within its class, then it should be made private. If an instance variable must be within certain bounds, then it should be private and made available only through accessor methods. This way, you can prevent invalid values from being assigned.

## Constructors and Inheritance

In a hierarchy, it is possible for both superclasses and subclasses to have their own constructors. This raises an important question: What constructor is responsible for building an object of the subclass-the one in the superclass, the one in the subclass, or both? The answer is this: The constructor for the superclass constructs the superclass portion of the object, and the constructor for the subclass constructs the subclass part. This makes sense because the superclass has no knowledge of or access to any element in a subclass. Thus, their construction must be separate. The preceding examples have relied upon the default constructors created automatically by Java, so this was not an issue. However, in practice, most classes will have explicit constructors. Here you will see how to handle this situation.

When only the subclass defines a constructor, the process is straightforward: simply construct the subclass object. The superclass portion of the object is constructed automatically using its default constructor. For example, here is a reworked version of **Triangle** that defines a constructor. It also makes **style** private, since it is now set by the constructor.
```java
class TwoDShape {
    private double width;  // there are
    private double height; // now private

    // Accessor methods for width and height
    double getWidth() { return width; }
    void setWidth(double width) { this.width = width; }
    double getHeight() { return height; }
    void setHeight(double height) { this.height = height; }

    void showDim() {
        System.out.println("Width and height are " + width + " and " + height);
    }
}
class Triangle extends TwoDShape {
    private String style;

    // Constructor
    Triangle(String s, double w, double h) {
        setWidth(w); // Initialize TwoDShape portion of object
        setHeight(h);

        style = s;
    }

    double area() {
        return getWidth() * getHeight() / 2;
    }

    void showStyle() {
        System.out.println("Triangle is " + style);
    }
}
class Shapes3 {
    public static void main(String[] args) {
        Triangle t1 = new Triangle("filled", 4.0, 4.0);
        Triangle t2 = new Triangle("outlined", 8.0, 12.0);

        System.out.println("Info for t1: ");
        t1.showStyle();
        t1.showDim();
        System.out.println("Area is " + t1.area());

        System.out.println();

        System.out.println("Info for t2: ");
        t2.showStyle();
        t2.showDim();
        System.out.println("Area is " + t2.area());
    }
}
```

Here, **Triangle**'s constructor initializes the members of **TwoDClass** that it inherits along with its own **style** field.

When both the superclass and the subclass define constructors, the process is a bit more complicated because both the superclass and subclass constructors must be executed. In this case, you must use another of Java's keywords, **super**, which has two general forms. The first calls a superclass constructor. The second is used to access a member of the superclass that has been hidden by a member of a subclass. Here, we will look at its first use.

## Using super to Call Superclasses Constructors

A subclass can call a constructor defined by its superclass by use of the following form of **super**:
```text
super(parameter-list);
```

Here, *parameter-list* specifies any parameters needed by the constructor in the superclass. **super()** must always be the first statement executed inside a subclass constructor. To see how **super()** is used, consider the version of **TwoDShape** in the following program. It defines a constructor that initializes **width** and **height**.
```java
class TwoDShape {
    private double width;
    private double height;

    // Parameterized constructor
    TwoDShape(double w, double h) { // A constructor for TwoDShape
        width = w;
        height = h;
    }

    // Accessor methods for width and height
    double getWidth() { return width; }
    void setWidth(double width) { this.width = width; }
    double getHeight() { return height; }
    void setHeight(double height) { this.height = height; }

    void showDim() {
        System.out.println("Width and height are " + width + " and " + height);
    }
}
class Triangle extends TwoDShape {
    private String style;

    // Constructor
    Triangle(String s, double w, double h) {
        super(w, h); // Use super() to execute the TwoDShape constructor

        style = s;
    }

    double area() {
        return getWidth() * getHeight() / 2;
    }

    void showStyle() {
        System.out.println("Triangle is " + style);
    }
}
class Shapes4 {
    public static void main(String[] args) {
        Triangle t1 = new Triangle("filled", 4.0, 4.0);
        Triangle t2 = new Triangle("outlined", 8.0, 12.0);

        System.out.println("Info for t1: ");
        t1.showStyle();
        t1.showDim();
        System.out.println("Area is " + t1.area());

        System.out.println();

        System.out.println("Info for t2: ");
        t2.showStyle();
        t2.showDim();
        System.out.println("Area is " + t2.area());
    }
}
```

Here, **Triangle()** calls **super()** with the parameters **w** and **h**. This causes the **TwoDShape()** constructor to be called, which initializes **width** and **height** using these values. **Triangle** no longer initializes these values itself. It need only initialize the value unique to it: **style**. This leaves **TwoDShape** free to construct its subobject in any manner that it so chooses. Furthermore, **TwoDShape** can add functionality about which existing subclasses have no knowledge, thus preventing existing code from breaking.

Any form of constructor defined by the superclass can be called by **super()**. The constructor executed will be the one that matches the arguments. For example, here are expanded versions of both **TwoDShape** and **Triangle** that include default constructors and constructors that take one argument:
```java
class TwoDShape {
    private double width;
    private double height;

    // A default constructor
    TwoDShape() {
        width = height = 0.0;
    }

    // Parameterized constructor
    TwoDShape(double w, double h) {
        width = w;
        height = h;
    }

    // Construct object with equal width and height
    TwoDShape(double x) {
        width = height = x;
    }

    // Accessor methods for width and height
    double getWidth() { return width; }
    void setWidth(double width) { this.width = width; }
    double getHeight() { return height; }
    void setHeight(double height) { this.height = height; }

    void showDim() {
        System.out.println("Width and height are " + width + " and " + height);
    }
}
class Triangle extends TwoDShape {
    private String style;

    // A default constructor
    Triangle() {
        super(); // Use super() to call the various forms of the TwoDShape constructor

        style = "none";
    }

    // Constructor
    Triangle(String s, double w, double h) {
        super(w, h); // Use super() to call the various forms of the TwoDShape constructor

        style = s;
    }

    // One argument constructor
    Triangle(double x) {
        super(x); // Use super() to call the various forms of the TwoDShape constructor

        style = "filled";
    }

    double area() {
        return getWidth() * getHeight() / 2;
    }

    void showStyle() {
        System.out.println("Triangle is " + style);
    }
}
class Shapes5 {
    public static void main(String[] args) {
        Triangle t1 = new Triangle();
        Triangle t2 = new Triangle("outlined", 8.0, 12.0);
        Triangle t3 = new Triangle(4.0);

        t1 = t2;

        System.out.println("Info for t1: ");
        t1.showStyle();
        t1.showDim();
        System.out.println("Area is " + t1.area());

        System.out.println();

        System.out.println("Info for t2: ");
        t2.showStyle();
        t2.showDim();
        System.out.println("Area is " + t2.area());

        System.out.println();

        System.out.println("Info for t3: ");
        t3.showStyle();
        t3.showDim();
        System.out.println("Area is " + t3.area());

        System.out.println();
    }
}
```

Here is the output from this version:
```text
Info for t1: 
Triangle is outlined
Width and height are 8.0 and 12.0
Area is 48.0

Info for t2: 
Triangle is outlined
Width and height are 8.0 and 12.0
Area is 48.0

Info for t3: 
Triangle is filled
Width and height are 4.0 and 4.0
Area is 8.0
```

Let's review the key concepts behind **super()**. When a subclass calls **super()**, it is calling the constructor of its immediate superclass. Thus, **super()** always refers to the superclass immediately above the calling class. This is true even in a multilevel hierarchy. Also, **super()** must always be the first statement executed inside a subclass constructor.

## Using super to Access Superclasses Members

There is a second form of **super** that acts somewhat like **this**, except that it always refers to the superclass of the subclass in which it is used. This usage has the following general form:
```text
super.member
```

Here, *member* can be either a method or an instance variable.

This form of **super** is most applicable to situations in which member names of a subclass hide members by the same name in the superclass. Consider this simple class hierarchy:
```java
class A {
    int i;
}
class B extends A {
    int i; // this i hides the i in A

    B(int a, int b) {
        super.i = a; // Here, super.i refers to the i in A
        i = b; // i in B
    }

    void show() {
        System.out.println("i in superclass: " + super.i);
        System.out.println("i in subclass: " + i);
    }
}
class UseSuper {
    public static void main(String[] args) {
        B subOb = new B(1, 2);
        subOb.show();
    }
}
```

This program displays the following:
```text
i in superclass: 1
i in subclass: 2
```

Although the instance variable **i** in **B** hides the **i** in **A**, **super** allows access to the **i** defined in the superclass. **super** can also be used to call methods that are hidden by a subclass.

The key point is that once you have created a superclass that defines the general aspects of an object, that superclass can be inherited to form specialized classes. Each subclass simply adds its own, unique attributes. This is the essence of inheritance.

## Creating a Multilevel Hierarchy

Up to this point, we have been using simple class hierarchies that consist of only a superclass and a subclass. However, you can build hierarchies that contain as many layers of inheritance as you like. As mentioned, it is perfectly acceptable to use a subclass as a superclass of another. For example, given three classes called **A**, **B**, and **C**, **C** can be a subclass of **B**, which is a subclass of **A**. When this type of situation occurs, each subclass inherits all of the traits found in all of its superclasses. In this case, **C** inherits all aspects of **B** and **A**.

One other important point: **super()** always refers to the constructor in the closest superclass. Given the **A**, **B**, and **C** classes example in the paragraph above; the **super()** in **C** calls the constructor in **B**. The **super()** in **B** calls the constructor in **A**. In a class hierarchy, if a superclass constructor requires parameters, then all subclasses must pass those parameters "up the line." This is true whether or not a subclass needs parameters of its own.

## When Are Constructors Executed?

In the foregoing discussion of inheritance and class hierarchies, an important question may have occurred to you: When a subclass object is created, whose constructor is executed first, the one in the subclass or the one defined by the superclass? For example, given a subclass called **B** and a superclass called **A**, is **A**'s constructor executed before **B**'s, or vice versa? The answer is that in a class hierarchy, constructors complete their execution in order of derivation, from superclass to subclass. Further, since **super()** must be the first statement executed in a subclass' constructor, this order is the same whether or not **super()** ) is used. If **super()** is not used, then the default (parameterless) constructor of each superclass will be executed. The following program illustrates when constructors are executed:
```java
class A {
    A() {
        System.out.println("Constructing A.");
    }
}
class B extends A {
    B() {
        System.out.println("Constructing B");
    }
}
class C extends B {
    C() {
        System.out.println("Constructing C.");
    }
}
class OrderOfConstruction {
    public static void main(String[] args) {
        C c = new C();
    }
}
```

The output from this program is shown here:
```text
Constructing A.
Constructing B
Constructing C.
```

As you can see, the constructors are executed in order of derivation.

If you think about it, it makes sense that constructors are executed in order of derivation. Because a superclass has no knowledge of any subclass, any initialization it needs to perform is separate from and possibly prerequisite to any initialization performed by the subclass. Therefore, it must complete its execution first.

## Superclass References and Subclass Objects

As you know, Java is a strongly typed language. Aside from the standard conversions and automatic promotions that apply to its primitive types, type compatibility is strictly enforced. Therefore, a reference variable for one class type cannot normally refer to an object of another class type.

There is, however, an important exception to Java's strict type enforcement. A reference variable of a superclass can be assigned a reference to an object of any subclass derived from that superclass. In other words, a superclass reference can refer to a subclass object. Here is an example:
```java
class X {
    int a;
    X(int i) { a = i; }
}
class Y extends X {
    int b;
    Y(int i, int j) {
        super(j);
        b = i;
    }
}
class SupSubRef {
    public static void main(String[] args) {
        X x = new X(10);
        X x2;
        Y y = new Y(5, 6);

        x2 = x; // OK, both of same type
        System.out.println("x2.a: " + x2.a);
        
        x2 = y; // OK because Y is a subclass of X; thus x2 can refer to y
        System.out.println("x2.a: " + x2.a);
        
        // X references know only about X members
        x2.a = 19; // OK
//      x2.b = 27; // Error, X doesn't have a b member
    }
}
```

Here, **Y** is now derived from **X**; thus, it is permissible for **x2** to be assigned a reference to a **Y** object.

It is important to understand that it is the type of the reference variable-not the type of the object that it refers to-that determines what members can be accessed. That is, when a reference to a subclass object is assigned to a superclass reference variable, you will have access only to those parts of the object defined by the superclass. This is why **x2** can't access **b** even when it refers to a **Y** object. If you think about it, this makes sense, because the superclass has no knowledge of what a subclass adds to it. This is why the last line of code in the program is commented out.

An important place where subclass references are assigned to superclass variables is when constructors are called in a class hierarchy. As you know, it is common for a class to define a constructor that takes an object of the class as a parameter. This allows the class to construct a copy of an object. Subclasses of such a class can take advantage of this feature. For example, consider the following versions of **TwoDShape** and **Triangle**. Both add constructors that take an object as a parameter.
```java
class TwoDShape {
    private double width;
    private double height;

    // A default constructor
    TwoDShape() {
        width = height = 0.0;
    }

    // Parameterized constructor
    TwoDShape(double w, double h) {
        width = w;
        height = h;
    }

    // Construct object with equal width and height
    TwoDShape(double x) {
        width = height = x;
    }

    // Construct an object from an object
    TwoDShape(TwoDShape ob) { // Construct object from an object
        width = ob.width;
        height = ob.height;
    }

    // Accessor methods for width and height
    double getWidth() { return width; }
    void setWidth(double width) { this.width = width; }
    double getHeight() { return height; }
    void setHeight(double height) { this.height = height; }

    void showDim() {
        System.out.println("Width and height are " + width + " and " + height);
    }
}
class Triangle extends TwoDShape {
    private String style;

    // A default constructor
    Triangle() {
        super(); // call superclass constructor
        style = "none";
    }

    // Constructor for Triangle
    Triangle(String s, double w, double h) {
        super(w, h); // call superclass constructor
        style = s;
    }

    // One argument constructor
    Triangle(double x) {
        super(x); // call superclass constructor
        style = "filled";
    }

    // Construct an object from an object
    Triangle(Triangle ob) {
        super(ob); // Pass a Triangle reference to TwoDShape's constructor
        style = ob.style;
    }

    double area() {
        return getWidth() * getHeight() / 2;
    }

    void showStyle() {
        System.out.println("Triangle is " + style);
    }
}
class Shapes7 {
    public static void main(String[] args) {
        Triangle t1 = new Triangle("outlined", 8.0, 12.0);
        Triangle t2 = new Triangle(t1); // make a copy of t1

        System.out.println("Info for t1: ");
        t1.showStyle();
        t1.showDim();
        System.out.println("Area is " + t1.area());

        System.out.println();

        System.out.println("Info for t2: ");
        t2.showStyle();
        t2.showDim();
        System.out.println("Area is " + t2.area());
    }
}
```

In this program, **t2** is constructed from **t1** and is, thus, identical. The output is shown here:
```text
Info for t1: 
Triangle is outlined
Width and height are 8.0 and 12.0
Area is 48.0

Info for t2: 
Triangle is outlined
Width and height are 8.0 and 12.0
Area is 48.0
```

Pay special attention to this **Triangle** constructor:
```java
// Construct an object from an object
Triangle(Triangle ob) {
    super(ob); // Pass a Triangle reference to TwoDShape's constructor
    style = ob.style;
}
```

It receives an object of type **Triangle** and it passes that object (through **super**) to this **TwoDShape** constructor:
```java
// Construct an object from an object
TwoDShape(TwoDShape ob) { // Construct object from an object
    width = ob.width;
    height = ob.height;
}
```

The key point is that **TwoDShape()** is expecting a **TwoDShape** object. However, **Triangle()** passes it a **Triangle** object. The reason this works is because, as explained, a superclass reference can refer to a subclass object. Thus, it is perfectly acceptable to pass **TwoDShape()** a reference to an object of a class derived from **TwoDShape**. Because the **TwoDShape()** constructor is initializing only those portions of the subclass object that are members of **TwoDShape**, it doesn't matter that the object might also contain other members added by derived classes.

## Method Overriding

In a class hierarchy, when a method in a subclass has the same return type and signature as a method in its superclass, then the method in the subclass is said to *override* the method in the superclass. When an overridden method is called from within a subclass, it will always refer to the version of that method defined by the subclass. The version of the method defined by the superclass will be hidden.

If you want to access the superclass version of an overridden method, you can do so by using **super**.

Method overriding occurs only when the signatures of the two methods are identical. If they are not, then the two methods are simply overloaded. For example, consider this modified version of the preceding example:
```java
class A {
    void show() {
        System.out.println("This is show() method on A");
    }
}
class B extends A {
    // overload show()
    void show(String msg) { // Because signatures differ, this show() simply overloads show() in superclass A
        System.out.println(msg);
    }
}
class Overload {
    public static void main(String[] args) {
        B subOb = new B();
        subOb.show("This is show() method on B"); // // this calls show() in B
        subOb.show(); // this calls show() in A
    }
}
```

The output produced by this program is shown here:
```text
This is show() method on B
This is show() method on A
```

The version of **show()** in **B** takes a string parameter. This makes its signature different from the one in **A**, which takes no parameters. Therefore, no overriding (or name hiding) takes place.

### Overriding Methods Support Polymorphism

While the examples in the preceding section demonstrate the mechanics of method overriding, they do not show its power. Indeed, if there were nothing more to method overriding than a namespace convention, then it would be, at best, an interesting curiosity but of little real value. However, this is not the case. Method overriding forms the basis for one of Java's most powerful concepts: *dynamic method dispatch*. Dynamic method dispatch is the mechanism by which a call to an overridden method is resolved at run time rather than compile time. Dynamic method dispatch is important because this is how Java implements run-time polymorphism.

Let's begin by restating an important principle: a superclass reference variable can refer to a subclass object. Java uses this fact to resolve calls to overridden methods at run time. Here's how. When an overridden method is called through a superclass reference, Java determines which version of that method to execute based upon the type of the object being referred to at the time the call occurs. Thus, this determination is made at run time. When different types of objects are referred to, different versions of an overridden method will be called. In other words, *it is the type of the object being referred to* (not the type of the reference variable) that determines which version of an overridden method will be executed. Therefore, if a superclass contains a method that is overridden by a subclass, then when different types of objects are referred to through a superclass reference variable, different versions of the method are executed.

Here is an example that illustrates dynamic method dispatch:
```java
class Sup {
    void who() {
        System.out.println("who() in Sup");
    }
}
class Sub1 extends Sup {
    void who() {
        System.out.println("who() in Sub1");
    }
}
class Sub2 extends Sup {
    void who() {
        System.out.println("who() in Sub2");
    }
}
class DynDispDemo {
    public static void main(String[] args) {
        Sup superOb = new Sup();
        Sub1 subOb1 = new Sub1();
        Sub2 subOb2 = new Sub2();
        
        Sup supRef;
        
        supRef = superOb;
        supRef.who();

        supRef = subOb1;
        supRef.who();

        supRef = subOb2;
        supRef.who();
    }
}
```

The output from the program is shown here:
```text
who() in Sup
who() in Sub1
who() in Sub2
```

This program creates a superclass called **Sup** and two subclasses of it, called **Sub1** and **Sub2**. **Sup** declares a method called **who()**, and the subclasses override it. Inside the **main()** method, objects of type **Sup**, **Sub1**, and **Sub2** are declared. Also, a reference of type **Sup**, called **supRef**, is declared. The program then assigns a reference to each type of object to **supRef** and uses that reference to call **who()**. As the output shows, the version of **who()** executed is determined by the type of object being referred to at the time of the call, not by the class type of **supRef**.

### Why Overridden Methods?

As stated earlier, overridden methods allow Java to support run-time polymorphism. Polymorphism is essential to object-oriented programming for one reason: it allows a general class to specify methods that will be common to all of its derivatives, while allowing subclasses to define the specific implementation of some or all of those methods. Overridden methods are another way that Java implements the "one interface, multiple methods" aspect of polymorphism. Part of the key to successfully applying polymorphism is understanding that the superclasses and subclasses form a hierarchy that moves from lesser to greater specialization. Used correctly, the superclass provides all elements that a subclass can use directly. It also defines those methods that the derived class must implement on its own. This allows the subclass the flexibility to define its own methods, yet still enforces a consistent interface. Thus, by combining inheritance with overridden methods, a superclass can define the general form of the methods that will be used by all of its subclasses.

## Using Abstract Classes

Sometimes you will want to create a superclass that defines only a generalized form that will be shared by all of its subclasses, leaving it to each subclass to fill in the details. Such a class determines the nature of the methods that the subclasses must implement but does not, itself, provide an implementation of one or more of these methods. One way this situation can occur is when a superclass is unable to create a meaningful implementation for a method.

As you will see as you create your own class libraries, it is not uncommon for a method to have no meaningful definition in the context of its superclass. You can handle this situation in two ways. One way is to simply have it report a warning message. While this approach can be useful in certain situations-such as debugging-it is not usually appropriate. You may have methods which must be overridden by the subclass in order for the subclass to have any meaning. In this case, you want some way to ensure that a subclass does, indeed, override all necessary methods. Java's solution to this problem is the *abstract method*. 

An abstract method is created by specifying the **abstract** type modifier. An abstract method contains no body and is, therefore, not implemented by the superclass. Thus, a subclass must override it-it cannot simply use the version defined in the superclass. To declare an abstract method, use this general form:
```text
abstract type name(parameter-list);
```

As you can see, no method body is present. The **abstract** modifier can be used only on instance methods. It cannot be applied to **static** methods or to constructors.

A class that contains one or more abstract methods must also be declared as abstract by preceding its **class** declaration with the **abstract** modifier. Since an abstract class does not define a complete implementation, there can be no objects of an abstract class. Thus, attempting to create an object of an abstract class by using **new** will result in a compile-time error.

When a subclass inherits an abstract class, it must implement all of the abstract methods in the superclass. If it doesn't, then the subclass must also be specified as **abstract**. Thus, the **abstract** attribute is inherited until such time as a complete implementation is achieved.

Using an abstract class, you can improve the **TwoDShape** class. Since there is no meaningful concept of area for an undefined two-dimensional figure, the following version of the preceding program declares **area()** as **abstract** inside **TwoDShape**, and **TwoDShape** as **abstract**. This, of course, means that all classes derived from **TwoDShape** must override **area()**.
```java
abstract class TwoDShape { // TwoDShape is now abstract
    private double width;
    private double height;
    private String name;

    // A default constructor
    TwoDShape() {
        width = height = 0.0;
        name = "none";
    }

    // Parameterized constructor
    TwoDShape(double w, double h, String n) {
        width = w;
        height = h;
        name = n;
    }

    // Construct object with equal width and height
    TwoDShape(double x, String n) {
        width = height = x;
        name = n;
    }

    // Construct an object from an object
    TwoDShape(TwoDShape ob) {
        width = ob.width;
        height = ob.height;
        name = ob.name;
    }

    // Accessor methods for width and height
    double getWidth() { return width; }
    void setWidth(double width) { this.width = width; }
    double getHeight() { return height; }
    void setHeight(double height) { this.height = height; }
    String getName() { return name; }

    void showDim() {
        System.out.println("Width and height are " + width + " and " + height);
    }

    // Make area() into an abstract method
    abstract double area();
}
class Triangle extends TwoDShape {
    private String style;

    // A default constructor
    Triangle() {
        super();
        style = "none";
    }

    // Constructor for Triangle
    Triangle(String s, double w, double h) {
        super(w, h, "triangle");
        style = s;
    }

    // One argument constructor
    Triangle(double x) {
        super(x, "triangle");
        style = "filled";
    }

    // Construct an object from an object
    Triangle(Triangle ob) {
        super(ob);
        style = ob.style;
    }

    double area() {
        return getWidth() * getHeight() / 2;
    }

    void showStyle() {
        System.out.println("Triangle is " + style);
    }
}
class Rectangle extends TwoDShape {
    //A default Constructor
    Rectangle() {
        super();
    }

    // Constructor for Rectangle
    public Rectangle(double w, double h) {
        super(w, h, "rectangle");
    }

    // Construct a square
    public Rectangle(double x) {
        super(x, "square");
    }

    // Construct an object from an object
    public Rectangle(TwoDShape ob) {
        super(ob);
    }

    boolean isSquare() {
        if (getWidth() == getHeight()) return true;
        return false;
    }

    double area() {
        return getWidth() * getHeight();
    }
}
class AbsShape {
    public static void main(String[] args) {
        TwoDShape shapes[] = new TwoDShape[4];

        shapes[0] = new Triangle("outlined", 8.0, 12.0);
        shapes[1] = new Rectangle(10);
        shapes[2] = new Rectangle(10, 4);
        shapes[3] = new Triangle(7.0);

        for (TwoDShape shape : shapes) {
            System.out.println("object is " + shape.getName());
            System.out.println("Area is " + shape.area());
            System.out.println();
        }
    }
}
```

As the program illustrates, all subclasses of **TwoDShape** *must* override **area()**. To prove this to yourself, try creating a subclass that does not override **area()**. You will receive a compile-time error. Of course, it is still possible to create an object reference of type **TwoDShape**, which the program does. However, it is no longer possible to declare objects of type **TwoDShape**. Because of this, in **main()** the **shapes** array has been shortened to 4, and a **TwoDShape** object is no longer created.

One last point: Notice that **TwoDShape** still includes the **showDim()** and **getName()** methods and that these are not modified by **abstract**. It is perfectly acceptable-indeed, quite common-for an abstract class to contain concrete methods which a subclass is free to use as is. Only those methods declared as **abstract** need be overridden by subclasses.

## Using final

As powerful and useful as method overriding and inheritance are, sometimes you will want to prevent them. For example, you might have a class that encapsulates control of some hardware device. Further, this class might offer the user the ability to initialize the device, making use of private, proprietary information. In this case, you don't want users of your class to be able to override the initialization method. Whatever the reason, in Java it is easy to prevent a method from being overridden or a class from being inherited by using the keyword **final**.

### final Prevents Overriding

To prevent a method from being overridden, specify **final** as a modifier at the start of its declaration. Methods declared as **final** cannot be overridden. The following fragment illustrates **final**:
```java
class A {
    final void meth() {
        System.out.println("This is a final method.");
    }
}
class B extends A {
    void meth() { // ERROR! Can't override
        System.out.println("Illegal!");
    }
}
```

Because **meth()** is declared as **final**, it cannot be overridden in **B**. If you attempt to do so, a compile-time error will result.

### final Prevents Inheritance

You can prevent a class from being inherited by preceding its declaration with **final**. Declaring a class as **final** implicitly declares all of its methods as **final**, too. As you might expect, it is illegal to declare a class as both **abstract** and **final** since an abstract class is incomplete by itself and relies upon its subclasses to provide complete implementations. Here is an example of a **final** class:
```java
final class A {
    // ...
}
class B extends A { // ERROR! Can't subclass A
    // ...
}
```

As the comments imply, it is illegal for **B** to inherit **A** since **A** is declared as **final**.

### Using final with Data Members

In addition to the uses of **final** just shown, **final** can also be applied to member variables to create what amounts to named constants. If you precede a class variable's name with **final**, its value cannot be changed throughout the lifetime of your program. You can, of course, give that variable an initial value.

**final** constants can also be inherited by subclasses and accessed directly inside those subclasses. As a point of style, many Java programmers use uppercase identifiers for **final** constants, but this is not a hard and fast rule. 

**Question: Can** final **member variables be made** static **? Can** final **be used on method parameters and local variables?**
**Answer**: The answer to both is Yes. Making a **final** member variable **static** lets you refer to the constant through its class name rather than through an object. For example: `className.FINAL_CONSTANT_NAME` 
Declaring a parameter **final** prevents it from being changed within the method. Declaring a local variable **final** prevents it from being assigned a value more than once.

## The Object Class

Java defines one special class called **Object** that is an implicit superclass of all other classes. In other words, all other classes are subclasses of **Object**. This means that a reference variable of type **Object** can refer to an object of any other class. Also, since arrays are implemented as classes, a variable of type **Object** can also refer to any array. **Object** defines the following methods, which means that they are available in every object:

Method | Purpose
------ | -------
Object clone() | Creates a new object that is the same as the object being cloned.
boolean equals(Object *object*) | Determines whether one object is equal to another.
void finalize() | Called before an unused object is recycled.
Class<?> getClass() | Obtains the class of an object at run time. 
int hashCode() | Returns the hash code associated with the invoking object. 
void notify() | Resumes execution of a thread waiting on the invoking object. 
void notifyAll() | Resumes execution of all threads waiting on the invoking object. 
String toString() | Returns a string that describes the object. 
void wait() <br/> void wait(long *milliseconds*) <br/> void wait(long *milliseconds*, int *nanoseconds*) | Waits on another thread of execution. 

The methods **getClass()**, **notify()**, **notifyAll()**, and **wait()** are declared as **final**. You can override the others. Notice these two methods: **equals()** and **toString()**. The **equals()** method compares two objects. It returns **true** if the objects are equivalent, and **false** otherwise. The **toString()** method returns a string that contains a description of the object on which it is called. Also, this method is automatically called when an object is output using **println()**. Many classes override this method. Doing so allows them to tailor a description specifically for the types of objects that they create.

One last point: Notice the unusual syntax in the return type for **getClass()**. This relates to Java's *generics* feature. Generics allow the type of data used by a class or method to be specified as a parameter.

[Chapter 8: Packages and Interfaces](chapter-08-packages-and-interfaces.md#chapter-8-packages-and-interfaces)

[Back to Table of Contents](../README.md#table-of-contents)