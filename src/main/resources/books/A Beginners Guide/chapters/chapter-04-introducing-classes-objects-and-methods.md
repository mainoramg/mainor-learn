# Chapter 4: Introducing Classes, Objects, and Methods

## Table of Contents
* [Class Fundamentals](#class-fundamentals)
  * [Defining a Class](#defining-a-class)
* [How Objects Are Created](#how-objects-are-created)
* [Reference Variables and Assignment](#reference-variables-and-assignment)
* [Returning from a Method](#returning-from-a-method)
* [Constructors](#constructors)
* [The new Operator Revisited](#the-new-operator-revisited)
* [Garbage Collection](#garbage-collection)
* [The finalize() Method](#the-finalize-method)
* [The this Keyword](#the-this-keyword)

## Class Fundamentals

A class is a template that defines the form of an object.

### Defining a Class

Here is a complete program that uses the example **Vehicle** class:
```java
/* A program that uses the Vehicle class.
   Call this file VehicleDemo.java
*/
class Vehicle {
    int passengers; // number of passengers
    int fuelcap;    // fuel capacity in gallons
    int mpg;        // fuel consumption in miles per gallon
}

// This class declares an object of type Vehicle.
public class VehicleDemo {
    public static void main(String args[]){
        Vehicle minivan = new Vehicle();
        int range;
        
        // assign values to fields in minivan
        minivan.passengers = 7; // Notice the use of the dot operator to access a member
        minivan.fuelcap = 16;
        minivan.mpg = 21;

        // compute the range assuming a full tank of gas
        range = minivan.fuelcap * minivan.mpg;
        System.out.println("Minivan can carry " + minivan.passengers + " with a range of " + range);
    }
}
```

You should call the file that contains this program **VehicleDemo.java** because the **main()** method is in the class called **VehicleDemo**, not the class called **Vehicle**. When you compile this program, you will find that two **.class** files have been created, one for **Vehicle** and one for **VehicleDemo**. The Java compiler automatically puts each class into its own **.class** file. It is not necessary for both the **Vehicle** and the **VehicleDemo** class to be in the same source file. You could put each class in its own file, called **Vehicle.java** and **VehicleDemo.java**, respectively.

To run this program, you must execute **VehicleDemo.class**. The following output is displayed:
```text
Minivan can carry 7 with a range of 336
```

## How Objects Are Created

The **new** operator dynamically allocates (that is, allocates at run time) memory for an object and returns a reference to it. This reference is, more or less, the address in memory of the object allocated by **new**. This reference is then stored in a variable. Thus, in Java, all class objects must be dynamically allocated. The two steps combined in the preceding statement can be rewritten like this to show each step individually:
```java
Vehicle minivan; // declare reference to object
minivan = new Vehicle(); // allocate a Vehicle object
```

The first line declares **minivan** as a reference to an object of type **Vehicle**. Thus, **minivan** is a variable that can refer to an object, but it is not an object itself. At this point, **minivan** does not refer to an object. The next line creates a new **Vehicle** object and assigns a reference to it to **minivan**. Now, **minivan** is linked with an object.

## Reference Variables and Assignment

In an assignment operation, object reference variables act differently than do variables of a primitive type, such as **int**. When you assign one primitive-type variable to another, the situation is straightforward. The variable on the left receives a *copy* of the *value* of the variable on the right. When you assign one object reference variable to another, the situation is a bit more complicated because you are changing the object that the reference variable refers to. The effect of this difference can cause some counterintuitive results. For example, consider the following fragment:
```java
Vehicle car1 = new Vehicle();
Vehicle car2 = car1;
```

At first glance, it is easy to think that **car1** and **car2** refer to different objects, but this is not the case. Instead, **car1** and **car2** will both refer to the same object. The assignment of **car1** to **car2** simply makes **car2** refer to the same object as does **car1**. Thus, the object can be acted upon by either **car1** or **car2**. For example, after the assignment:
```java
car1.mpg = 26;
```

executes, both of these **println()** statements:
```java
System.out.println(car1.mpg);
System.out.println(car2.mpg);
```

display the same value: 26

Although **car1** and **car2** both refer to the same object, they are not linked in any other way. For example, a subsequent assignment to **car2** simply changes the object to which **car2** refers. For example:
```java
Vehicle car1 = new Vehicle();
Vehicle car2 = car1;
Vehicle car3 = new Vehicle();

car2 = car3; // now car2 and car3 refer to the same object.
```

After this sequence executes, **car2** refers to the same object as **car3**. The object referred to by **car1** is unchanged.

## Returning from a Method

In a **void** method, you can use the immediate termination of a method by using this form of **return**:
```text
return ;
```

When this statement executes, program control returns to the caller, skipping any remaining code in the method. For example, consider this method:
```java
void MyMeth() {
    for (int i = 0; i < 10; i++) {
        if (i == 5) return; // stop at 5
        System.out.println();
    }
}
```

Here, the **for** loop will only run from 0 to 5, because once **i** equals 5, the method returns. It is permissible to have multiple **return** statements in a method, especially when there are two or more routes out of it. For example:
```java
void MyMeth() {
    // ...
    if (done) return;
    // ...
    if (error) return;
    // ...
}
```

## Constructors

A *constructor* initializes an object when it is created. It has the same name as its class and is syntactically similar to a method. However, constructors have no explicit return type. Typically, you will use a constructor to give initial values to the instance variables defined by the class, or to perform any other startup procedures required to create a fully formed object.

All classes have constructors, whether you define one or not, because Java automatically provides a default constructor that initializes all member variables to their default values, which are zero, **null**, and **false**, for numeric types, reference types, and **boolean**s, respectively. However, once you define your own constructor, the default constructor is no longer used.

Here is a simple example that uses a constructor:
```java
// A simple constructor
class MyClass {
    int x;
    
    MyClass() { // This is the constructor for MyClass
        x = 10;
    }
}
``` 

## The new Operator Revisited

Now that you know more about classes and their constructors, let's take a closer look at the **new** operator. In the context of an assignment, the **new** operator has this general form:
```text
class-var = new class-name(arg-list);
```

Here, *class-var* is a variable of the class type being created. The *class-name* is the name of the class that is being instantiated. The class name followed by a parenthesized argument list (which can be empty) specifies the constructor for the class. If a class does not define its own constructor, **new** will use the default constructor supplied by Java. Thus, **new** can be used to create an object of any class type. The **new** operator returns a reference to the newly created object, which (in this case) is assigned to *class-var*.

Since memory is finite, it is possible that **new** will not be able to allocate memory for an object because insufficient memory exists. If this happens, a run-time exception will occur.

## Garbage Collection

As you have seen, objects are dynamically allocated from a pool of free memory by using the **new** operator. As explained, memory is not infinite, and the free memory can be exhausted. Thus, it is possible for **new** to fail because there is insufficient free memory to create the desired object. For this reason, a key component of any dynamic allocation scheme is the recovery of free memory from unused objects, making that memory available for subsequent reallocation. In some programming languages, the release of previously allocated memory is handled manually. However, Java uses a different, more trouble-free approach: *garbage collection*.

Java's garbage collection system reclaims objects automatically-occurring transparently, behind the scenes, without any programmer intervention. It works like this: When no references to an object exist, that object is assumed to be no longer needed, and the memory occupied by the object is released. This recycled memory can then be used for a subsequent allocation.

Garbage collection occurs only sporadically during the execution of your program. It will not occur simply because one or more objects exist that are no longer used. For efficiency, the garbage collector will usually run only when two conditions are met: there are objects to recycle, and there is a need to recycle them. Remember, garbage collection takes time, so the Java run-time system does it only when it is appropriate. Thus, you can't know precisely when garbage collection will take place.

## The finalize() Method

It is possible to define a method that will be called just before an object's final destruction by the garbage collector. This method is called **finalize()**, and it can be used to ensure that an object terminates cleanly. For example, you might use **finalize()** to make sure that an open file owned by that object is closed.

To add a finalizer to a class, you simply define the **finalize()** method. The Java run-time system calls that method whenever it is about to recycle an object of that class. Inside the **finalize()** method, you will specify those actions that must be performed before an object is destroyed.

The **finalize()** method has this general form:
```java
protected void finalize() {
    // finalization code here
} 
``` 

Here, the keyword **protected** is a specifier that limits access to **finalize()**. 

It is important to understand that **finalize()** is called just before garbage collection. It is not called when an object goes out of scope, for example. This means that you cannot know when-or even if-**finalize()** will be executed. For example, if your program ends before garbage collection occurs, **finalize()** will not execute. Therefore, it should be used as a "backup" procedure to ensure the proper handling of some resource, or for special-use applications, not as the means that your program uses in its normal operation. In short, **finalize()** is a specialized method that is seldom needed by most programs.

## The this Keyword

The **this** has some important uses: the Java syntax permits the name of a parameter or a local variable to be the same as the name of an instance variable. When this happens, the local name hides the instance variable. You can gain access to the hidden instance variable by referring to it through **this**. For example, the following is a syntactically valid way to write the **Pwr()** constructor.
```java
Pwr(double b, int e) {
    this.b = b; // This refers to the b instance variable, not the parameter.
    this.e = e;

    val = 1;
    if (e == 0) return;
    for ( ; e > 0; e--) val = val * b;
}
```

[Chapter 5: More Data Types and Operators](chapter-05-more-data-types-and-operators.md#chapter-5-more-data-types-and-operators)

[Back to Table of Contents](../README.md#table-of-contents)