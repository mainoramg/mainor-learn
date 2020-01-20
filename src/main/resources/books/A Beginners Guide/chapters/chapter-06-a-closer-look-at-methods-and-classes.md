# Chapter 6: A Closer Look at Methods and Classes

## Table of Contents
* [Controlling Access to Class Members](#controlling-access-to-class-members)
  * [Java's Access Modifiers](#java's-access-modifiers)
* [Pass Objects to Methods](#pass-objects-to-methods)
  * [How Arguments Are Passed](#how-arguments-are-passed)
    * [Call-By-Value](#call-by-value)
    * [Call-By-Reference](#call-by-reference)
* [Method Overloading](#method-overloading)
* [Overloading Constructors](#overloading-constructors)
* [Understanding static](#understanding-static)
  * [Static Blocks](#static-blocks)
* [Introducing Nested and Inner Classes](#introducing-nested-and-inner-classes)
* [Varargs: Variable-Length Arguments](#varargs-variable-length-arguments)
  * [Varargs Basics](#varargs-basics)
  * [Overloading Varargs Methods](#overloading-varargs-methods)
  * [Varargs and Ambiguity](#varargs-and-ambiguity)

## Controlling Access to Class Members

### Java's Access Modifiers

Member access control is achieved through the use of three *access modifiers*: **public**, **private**, and **protected**:
* **public**: member can be accessed by other code in your program. This includes methods defined inside other classes.
* **private**: member can be accessed only by other members of its class. Thus, methods in other classes cannot access a **private** member of another class.
* The default access setting (in which no access modifier is used) is the same as **public** unless your program is broken down into packages.

A *package* is, essentially, a grouping of classes. Packages are both an organizational and an access control feature.

An access modifier precedes the rest of a member's type specification. That is, it must begin a member's declaration statement. Here are some examples:
```java
public String errMsg;
private accountBalance bal;

private boolean isError(byte status) { // ...
```

## Pass Objects to Methods

### How Arguments Are Passed

You need to understand in a general sense the two ways in which an argument can be passed to a subroutine. As you will see, although Java uses call-by-value to pass arguments, the precise effect differs between whether a primitive type or a reference type is passed.

#### Call-By-Value

The first way is *call-by-value*. This approach copies the *value* of an argument into the formal parameter of the subroutine. Therefore, changes made to the parameter of the subroutine have no effect on the argument in the call.

When you pass a primitive type, such as **int* or **double**, to a method, it is passed by value. Thus, a copy of the argument is made, and what occurs to the parameter that receives the argument has no effect outside the method. For example, consider the following program:
```java
class Test {
    // This method causes no change to the arguments used in the call.
    void noChange(int i, int j) {
        i = i + j;
        j = -j;
    }
}

public class CallByValue {
    public static void main(String[] args) {
        Test ob = new Test();

        int a = 15, b = 20;

        System.out.println("a and b before call: " + a + " " + b);
        ob.noChange(a, b);
        System.out.println("a and b after call: " + a + " " + b);
    }
}
```

The output from this program is shown here:
```text
a and b before call: 15 20
a and b after call: 15 20
```

As you can see, the operations that occur inside **noChange()** have no effect on the values of **a** and **b** used in the call.

#### Call-By-Reference

The second way an argument can be passed is *call-by-reference*. In this approach, a reference to an argument (not the value of the argument) is passed to the parameter. Inside the subroutine, this reference is used to access the actual argument specified in the call. This means that changes made to the parameter *will* affect the argument used to call the subroutine.

When you pass an object to a method, the situation changes dramatically, because objects are implicitly passed by reference. Keep in mind that when you create a variable of a class type, you are creating a reference to an object. It is the reference, not the object itself, that is actually passed to the method. As a result, when you pass this reference to a method, the parameter that receives it will refer to the same object as that referred to by the argument. This effectively means that objects are passed to methods by use of call-by-reference. Changes to the object inside the method *do* affect the object used as an argument. For example, consider the following program:
```java
class Test {
    int a, b;

    Test(int i, int j) {
        a = i;
        b = j;
    }

    // Pass an object. Now, ob.a and ob.b in object used in the call will be changed.
    void change(Test ob) {
        ob.a = ob.a + ob.b;
        ob.b = -ob.b;
    }
}

public class PassObRef {
    public static void main(String[] args) {
        Test ob = new Test(15, 20);

        System.out.println("ob.a and ob.b before call: " + ob.a + " " + ob.b);
        ob.change(ob);
        System.out.println("ob.a and ob.b after call: " + ob.a + " " + ob.b);
    }
}
```

The output from this program is shown here:
```text
ob.a and ob.b before call: 15 20
ob.a and ob.b after call: 35 -20
```

As you can see, in this case, the actions inside **change()** have affected the object used as an argument.

**Question: Is there any way that I can pass a primitive type by reference?**
**Answer**: Not directly. However, Java defines a set of classes that *wrap* the primitive types in objects. These are **Double**, **Float**, **Byte**, **Short**, **Integer**, **Long**, and **Character**. In addition to allowing a primitive type to be passed by reference, these wrapper classes define several methods that enable you to manipulate their values. For example, the numeric type wrappers include methods that convert a numeric value from its binary form into its human-readable **String** form, and vice versa.

Remember, when an object reference is passed to a method, the reference itself is passed by use of call-by-value. However, since the value being passed refers to an object, the copy of that value will still refer to the same object referred to by its corresponding argument.

## Method Overloading

In java, two or more methods within the same class can share the same name, as long as their parameter declarations are different. Method overloading is one of the ways that Java implements polymorphism. Method overloading supports polymorphism because it is one way that Java implements the "one interface, multiple methods" paradigm.

You must observe one important restriction: the type and/or number of the parameters of each overloaded method must differ. It is not sufficient for two methods to differ only in their return types. Of course, overloaded methods *may* differ in their return types, too.

Java provides certain automatic type conversions. There conversions also apply to parameters of overloaded methods. For example:
```java
class Overload2 {
    void f(int x) {
        System.out.println("Inside f(int): " + x);
    }

    void f(double x) {
        System.out.println("Inside f(double): " + x);
    }
}
class TypeConv {
    public static void main(String[] args) {
        Overload2 ob = new Overload2();
        int i = 10;
        double d = 10.1;
        byte b = 99;
        short s = 10;
        float f = 11.5F;

        ob.f(i); // calls ob.f(int)
        ob.f(d); // calls ob.f(double)

        ob.f(b); // calls ob.f(int) - type conversion
        ob.f(s); // calls ob.f(int) - type conversion
        ob.f(f); // calls ob.f(double) - type conversion
    }
}
```

The output from this program is shown here:
```text
Inside f(int): 10
Inside f(double): 10.1
Inside f(int): 99
Inside f(int): 10
Inside f(double): 11.5
```

In this example, only two versions of **f()** are defined: one that has an **int** parameter and one that has a **double** parameter. However, it is possible to pass **f()** a **byte**, **short**, or **float** value. In the case of **byte** and **short**, Java automatically converts them to **int**. Thus, **f(int)** is invoked. In the case of **float**, the value is converted to **double** and **f(double)** is called.

It is important to understand, however, that the automatic conversions apply only if there is no direct match between a parameter and an argument. For example, here is the preceding program with the addition of a version of **f()** that specifies a **byte** parameter:
```java
class Overload2 {
    void f(byte x) { // This version specifies a byte parameter
        System.out.println("Inside f(byte): " + x);
    }

    void f(int x) {
        System.out.println("Inside f(int): " + x);
    }

    void f(double x) {
        System.out.println("Inside f(double): " + x);
    }
}
class TypeConv {
    public static void main(String[] args) {
        Overload2 ob = new Overload2();
        int i = 10;
        double d = 10.1;
        byte b = 99;
        short s = 10;
        float f = 11.5F;

        ob.f(i); // calls ob.f(int)
        ob.f(d); // calls ob.f(double)

        ob.f(b); // calls ob.f(byte) - now, no type conversion

        ob.f(s); // calls ob.f(int) - type conversion
        ob.f(f); // calls ob.f(double) - type conversion
    }
}
```

Now when the program is run, the following output is produced:
```text
Inside f(int): 10
Inside f(double): 10.1
Inside f(byte): 99
Inside f(int): 10
Inside f(double): 11.5
```

In this version, since is a version of **f()** that takes a **byte** argument, when **f()** is called with a **byte** argument, **f(byte)** is invoked and the automatic conversion to **int** does not occur.

When you overload a method, each version of that method can perform any activity you desire. There is no rule stating that overloaded methods must relate to one another. However, from a stylistic point of view, method overloading implies a relationship. In practice, you should overload only closely related operations. 

**Question: I've heard the term *signature* used by Java programmers. What is it?**
**Answer**: As it applies to Java, a signature is the name of a method plus its parameter list. Thus, for the purposes of overloading, no two methods within the same class can have the same signature. Notice that a signature does not include the return type, since it is not used by Java for overload resolution.

## Overloading Constructors

Like methods, constructors can also be overloaded. Doing so allows you to construct objects in a variety of ways. One of the most common reasons that constructors are overloaded is to allow one object to initialize another. For example, consider this program that uses the **Summation** class to compute the summation of an integer value:
```java
class Summation {
    int sum;

    // Construct from an int
    Summation(int num) {
        sum  = 0;
        for (int i = 1; i <= num; i++)
            sum += i;
    }

    // Construct from another object
    Summation(Summation ob) {
        sum = ob.sum;
    }
}
class SumDemo {
    public static void main(String[] args) {
        Summation s1 = new Summation(5);
        Summation s2 = new Summation(s1);

        System.out.println("s1.sum: " + s1.sum);
        System.out.println("s2.sum: " + s2.sum);
    }
}
```

The output is shown here:
```text
s1.sum: 15
s2.sum: 15
```

Often, as this example shows, an advantage of providing a constructor that uses one object to initialize another is efficiency. In this case, when **s2** is constructed, it is not necessary to recompute the summation. Of course, even in cases when efficiency is not an issue, it is often useful to provide a constructor that makes a copy of an object.

## Understanding static
There will be times when you will want to define a class member that will be used independently of any object of that class. Normally a class member must be accessed through an object of its class, but it is possible to create a member that can be used by itself, without reference to a specific instance. To create such a member, precede its declaration with the keyword **static**. When a member is declared **static**, it can be accessed before any objects of its class are created, and without reference to any object. You can declare both methods and variables to be **static**. The most common example of a **static** member is **main()**. **main()** is declared as **static** because it must be called by the JVM when your program begins. Outside the class, to use a **static** member, you need only specify the name of its class followed by the dot operator. No object needs to be created. For example, if you want to assign the value 10 to a **static** variable called **count** that is part of the **Timer** class, use this line:
```java
Timer.count = 10;
```

Variables declared as **static** are, essentially, global variables. When an object is declared, no copy of a **static** variable is made. Instead, all instances of the class share the same **static** variable. Here is an example that shows the differences between a **static** variable and an instance variable:
```java
class StaticDemo {
    int x; // a normal instance variable
    static int y; // a static variable. There is one copy of y for all objects to share

    // Return the sum of the instance variable x and the static variable y
    int sum() {
        return x + y;
    }
}
class SDemo {
    public static void main(String[] args) {
        StaticDemo ob1 = new StaticDemo();
        StaticDemo ob2 = new StaticDemo();

        // Each object has its own copy of an instance variable
        ob1.x = 10;
        ob2.x = 20;
        System.out.println("Of course, ob1.x and ob2.x are independent.");
        System.out.println("ob1.x: " + ob1.x + "\nob2.x: " + ob2.x);
        System.out.println();

        // Each object shares one copy of a static variable
        System.out.println("The static variable y is shared.");
        StaticDemo.y = 19;
        System.out.println("Set StaticDemo.y to 19");

        System.out.println("ob1.sum(): " + ob1.sum());
        System.out.println("ob2.sum(): " + ob2.sum());
        System.out.println();

        StaticDemo.y = 100;
        System.out.println("Change StaticDemo.y to 100");

        System.out.println("ob1.sum(): " + ob1.sum());
        System.out.println("ob2.sum(): " + ob2.sum());
        System.out.println();
    }
}
```

The output from this program is shown here:
```text
Of course, ob1.x and ob2.x are independent.
ob1.x: 10
ob2.x: 20

The static variable y is shared.
Set StaticDemo.y to 19
ob1.sum(): 29
ob2.sum(): 39

Change StaticDemo.y to 100
ob1.sum(): 110
ob2.sum(): 120
```

As you can see, the **static** variable **y** is shared by both **ob1** and **ob2**. Changing it affects the entire class, not just an instance.

The difference between a **static** method and a normal method is that the **static** method is called through its class name, without any object of that class being created. You have seen an example of this already: the **sqrt()** method, which is a **static** method within Java's standard **Math** class. Here is an example that creates a **static** method:
```java
class StaticMeth {
    static int val  = 1024; // a static variable

    // a static method
    static int valDiv2() {
        return val/2;
    }
}
class SDemo2 {
    public static void main(String[] args) {
        System.out.println("val is " + StaticMeth.val);
        System.out.println("StaticMeth.valDiv2(): " + StaticMeth.valDiv2());

        StaticMeth.val = 4;
        System.out.println("val is " + StaticMeth.val);
        System.out.println("StaticMeth.valDiv2(): " + StaticMeth.valDiv2());
    }
}
```

The output is shown here:
```text
val is 1024
StaticMeth.valDiv2(): 512
val is 4
StaticMeth.valDiv2(): 2
```

Methods declared as **static** have several restrictions:
* They can directly call only other **static** methods.
* They can directly access only **static** data.
* They do not have a **this** reference.

For example, in the following class, the **static** method **valDivDenom()** is illegal:
```java
class StaticError {
    int denom = 3; // a normal instance variable
    static int val = 1024; // a static variable 
    
    // Error! Can't access a non-static variable from within a static method
    static int valDivDenom() {
        return val/denom; // won't compile!
    }
}
```

Here, **denom** is a normal instance variable that cannot be accessed within a **static** method.

### Static Blocks

Sometimes a class will require some type of initialization before it is ready to create objects. For example, it might need to establish a connection to a remote site. It also might need to initialize certain **static** variables before any of the class' **static** methods are used. To handle these types of situations, Java allows you to declare a **static** block. A static block is executed when the class is first loaded. Thus, it is executed before the class can be used for any other purpose. Here is an example of a **static** block:
```java
class StaticBlock {
    static double rootOf2;
    static double rootOf3;

    static { // This block is executed when the class is loaded
        System.out.println("Inside static block.");
        rootOf2 = Math.sqrt(2.0);
        rootOf3 = Math.sqrt(3.0);
    }

    StaticBlock(String msg) {
        System.out.println(msg);
    }
}
class SDemo3 {
    public static void main(String[] args) {
        StaticBlock ob = new StaticBlock("Inside Constructor");

        System.out.println("Square root of 2 is " + StaticBlock.rootOf2);
        System.out.println("Square root of 3 is " + StaticBlock.rootOf3);
    }
}
```

The output is shown here:
```text
Inside static block.
Inside Constructor
Square root of 2 is 1.4142135623730951
Square root of 3 is 1.7320508075688772
```

As you can see, the **static** block is executed before any objects are constructed.

## Introducing Nested and Inner Classes

In java, you can define a *nested class*. This is a class that is declared within another class. A nested class does not exist independently of its enclosing class. Thus, the scope of a nested class is bounded by its outer class. A nested class that is declared directly within its enclosing class scope is a member of its enclosing class. It is also possible to declare a nested class that is local to a block.

There are two general types of nested classes: those that are preceded by the **static** modifier and those that are not. The only type that we are concerned about in this book is the non-static variety. This type of nested class is also called an *inner class*. It has access to all of the variables and methods of its outer class and may refer to them directly in the same way that other non-**static** members of the outer class do.

Here is an example that uses an inner class:
```java
class Outer {
    int nums[];

    Outer(int n[]) {
        nums = n;
    }

    void getMin() {
        Inner inOb = new Inner();
        System.out.println("Minimum: " + inOb.min());
    }

    // This is an inner class
    class Inner {
        int min() {
            int m = nums[0];

            for (int i = 1; i < nums.length; i++)
                if (nums[i] < m) m = nums[i];

            return m;
        }
    }
}
class NestedClassDemo {
    public static void main(String[] args) {
        int x[] = {3, 2, 1, 5, 6, 9, 7, 8};
        Outer outOb = new Outer(x);
        outOb.getMin();
    }
}
```

The output is shown here:
```text
Minimum: 1
```

As mentioned, it is possible to nest a class within a block scope. Doing so simply creates a localized class that is not known outside its block. This is an example:
```java
class LocalClassDemo {
    public static void main(String[] args) { // this is a scope block defined by main method
        
        // An inner class inside main method
        class ShowBits {
            int numbits;
            
            ShowBits(int n) {
                numbits = n;
            }
            
            // ...
            // more methods if you want
        }
        
        // ...
        // more code if you want
    }
}
```

One last point: You can create an inner class that does not have a name. This is called an *anonymous inner class*. An object of an anonymous inner class is instantiated when the class is declared, using **new**.

**Question: What makes a** static **nested class different from a non-static one?**
**Answer**: A **static** nested class is one that has the **static** modifier applied. Because it is **static**, it can access only other **static** members of the enclosing class directly. It must access other members of its outer class through an object reference.

## Varargs: Variable-Length Arguments

Sometimes you will want to create a method that takes a variable number of arguments, based on its precise usage. In this situation, it would be convenient to pass only the arguments to which the defaults did not apply. To create such a method implies that there must be some way to create a list of arguments that is variable in length, rather than fixed.

In the past, methods that required a variable-length argument list could be handled two ways, neither of which was particularly pleasing. First, if the maximum number of arguments was small and known, then you could create overloaded versions of the method, one for each way the method could be called. Although this works and is suitable for some situations, it applies to only a narrow class of situations. In cases where the maximum number of potential arguments is larger, or unknowable, a second approach was used in which the arguments were put into an array, and then the array was passed to the method.

Beginning with JDK 5, this need was addressed by the inclusion of a feature that simplified the creation of methods that require a variable number of arguments. This feature is called *varargs*, which is short for variable-length arguments. A method that takes a variable number of arguments is called a *variable-arity method*, or simply a *varargs method*. The parameter list for a varargs method is not fixed, but rather variable in length. Thus, a varargs method can take a variable number of arguments.

### Varargs Basics

A variable-length argument is specified by three periods (...). For example, here is how to write a method called **vaTest()** that takes a variable number of arguments:
```java
// vaTest() uses a vararg.
static void vaTest(int ... v) { // Declare a variable-length argument list
    System.out.println("Number of args: " + v.length);
    System.out.println("Contents: ");

    for (int i = 0; i < v.length; i++)
        System.out.println(" arg " + i + ": " + v[i]);

    System.out.println();
}
```
Notice that **v** si declared as shown here: `int ... v`

This syntax tells the compiler that **vaTest()** can be called with zero or more arguments. Furthermore, it causes **v** to be implicitly declared as an array of type **int[]**. Thus, inside **vaTest()**, **v** is accessed using the normal array syntax.

Here is a complete program that demonstrates **vaTest()**:
```java
class VarArgs {
    // vaTest() uses a vararg.
    static void vaTest(int ... v) { // Declare a variable-length argument list
        System.out.println("Number of args: " + v.length);
        System.out.println("Contents: ");

        for (int i = 0; i < v.length; i++)
            System.out.println(" arg " + i + ": " + v[i]);

        System.out.println();
    }

    public static void main(String[] args) {
        // Notice how vaTest() can be called with a variable number of arguments
        vaTest(10);      // 1 arg
        vaTest(1, 2, 3); // 3 args
        vaTest();            // no args
    }
}
```

The output from this program is shown here:
```text
Number of args: 1
Contents: 
 arg 0: 10

Number of args: 3
Contents: 
 arg 0: 1
 arg 1: 2
 arg 2: 3

Number of args: 0
Contents: 
```

There are two important things to notice about this program. First, as explained, inside **vaTest()**, **v** is operated on as an array. This is because **v** is an array. The **...** syntax simply tells the compiler that a variable number of arguments will be used, and that these arguments will be stored in the array referred to by **v**. Second, in **main()**, **vaTest()** is called with different numbers of arguments, including no arguments at all. The arguments are automatically put in an array and passed to **v**. In the case of no arguments, the length of the array is zero.

A method can have "normal" parameters along with a variable-length parameter. However, the variable-length parameter must be the last parameter declared by the method. For example, this method declaration is perfectly acceptable:
```java
int doIt(int a, int b, double c, int ... vals) { 
```

In this case, the first three arguments used in a call to **doIt()** are matched to the first three parameters. Then, any remaining arguments are assumed to belong to ***vals***.

Here is a reworked version of the **vaTest()** method that takes a regular argument and a variable-length argument:
```java
class VarArgs2 {
    // Here, msg is a normal parameter and v is a varargs parameter
    static void vaTest(String msg, int ... v) { // A "normal" and vararg parameter
        System.out.println(msg + v.length);
        System.out.println("Contents: ");

        for (int i = 0; i < v.length; i++)
            System.out.println(" arg " + i + ": " + v[i]);

        System.out.println();
    }

    public static void main(String[] args) {
        vaTest("One vararg: ", 10);
        vaTest("Three varargs: ", 1, 2, 3);
        vaTest("No varargs: ");
    }
}
```

The output from this program is shown here:
```text
One vararg: 1
Contents: 
 arg 0: 10

Three varargs: 3
Contents: 
 arg 0: 1
 arg 1: 2
 arg 2: 3

No varargs: 0
Contents: 
```

Remember, the varargs parameter must be last. For example, the following declaration is incorrect:
```java
int doIt(int a, int b, double c, int ... vals, boolean stopFlag) { // Error!
```

Here, there is an attempt to declare a regular parameter after the varargs parameter, which is illegal. There is one more restriction to be aware of: there must be only one varargs parameter. For example, this declaration is also invalid:
```java
int doIt(int a, int b, double c, int ... vals, double ... morevals) { // Error!
```

The attempt to declare the second varargs parameter is illegal.

### Overloading Varargs Methods

You can overload a method that takes a variable-length argument. For example, the following program overloads **vaTest()** three times:
```java
class VarArgs3 {
    static void vaTest(int ... v) { // First version of vaTest()
        System.out.println("vaTest(int ...): Number of args: " + v.length);
        System.out.println("Contents: ");

        for (int i = 0; i < v.length; i++)
            System.out.println(" arg " + i + ": " + v[i]);

        System.out.println();
    }

    static void vaTest(boolean ... v) { // Second version of vaTest()
        System.out.println("vaTest(boolean ...): Number of args: " + v.length);
        System.out.println("Contents: ");

        for (int i = 0; i < v.length; i++)
            System.out.println(" arg " + i + ": " + v[i]);

        System.out.println();
    }

    static void vaTest(String msg, int ... v) { // Third version of vaTest()
        System.out.println("vaTest(String, int ...): " + msg + v.length);
        System.out.println("Contents: ");

        for (int i = 0; i < v.length; i++)
            System.out.println(" arg " + i + ": " + v[i]);

        System.out.println();
    }

    public static void main(String[] args) {
        vaTest(1, 2, 3);
        vaTest("Testing: ", 10, 20);
        vaTest(true, false, false);
    }
}
```

The output produced by this program is shown here:
```text
vaTest(int ...): Number of args: 3
Contents: 
 arg 0: 1
 arg 1: 2
 arg 2: 3

vaTest(String, int ...): Testing: 2
Contents: 
 arg 0: 10
 arg 1: 20

vaTest(boolean ...): Number of args: 3
Contents: 
 arg 0: true
 arg 1: false
 arg 2: false
```

This program illustrates both ways that a varargs method can be overloaded. First, the types of its vararg parameter can differ. This is the case for **vaTest(int ...)** and **vaTest(boolean ...)**. Remember, the **...** causes the parameter to be treated as an array of the specified type. Therefore, just as you can overload methods by using different types of array parameters, you can overload varargs methods by using different types of varargs. In this case, Java uses the type difference to determine which overloaded method to call.

The second way to overload a varargs method is to add one or more normal parameters. This is what was done with **vaTest(String, int ...)**. In this case, Java uses both the number of arguments and the type of the arguments to determine which method to call.

### Varargs and Ambiguity

Somewhat unexpected errors can result when overloading a method that takes a variable-length argument. These errors involve ambiguity because it is possible to create an ambiguous call to an overloaded varargs method. For example, consider the following program:
```java
// This program contains an error and will not compile!
class VarArgs4 {
    // Use an int vararg parameter
    static void vaTest(int ... v) { // An int vararg
        // ...
    }

    // Use a boolean vararg parameter
    static void vaTest(boolean ... v) { // A boolean vararg
        // ...
    }

    public static void main(String[] args) {
        vaTest(1, 2, 3); // OK
        vaTest(true, false, false); // OK
        vaTest(); // Error: Ambiguous!
    }
}
```

In this program, the overloading of **vaTest()** is perfectly correct. However, this program will not compile because of the following call: `vaTest(); // Error: Ambiguous!`

Because the vararg parameter can be empty, this call could be translated into a call to **vaTest(int ...)** or to **vaTest(boolean ...)**. Both are equally valid. Thus, the call is inherently ambiguous.

Here is another example of ambiguity. The following overloaded versions of **vaTest()** are inherently ambiguous even though one takes a normal parameter:
```java
static void vaTest(int ... v) { // ...
static void vaTest(int n, int ... v) { // ...
``` 

Although the parameter lists of **vaTest()** differ, there is no way for the compiler to resolve the following call: `vaTest(1)`

Does this translate into a call to **vaTest(int ...)**, with one varargs argument, or into a call to **vaTest(int, int ...)** with no varargs arguments? There is no way for the compiler to answer this question. Thus, the situation is ambiguous.

Because of ambiguity errors like those just shown, sometimes you will need to forego overloading and simply use two different method names. Also, in some cases, ambiguity errors expose a conceptual flaw in your code, which you can remedy by more carefully crafting a solution.

[Chapter 7: Inheritance](chapter-07-inheritance.md#chapter-7-inheritance)

[Back to Table of Contents](../README.md#table-of-contents)