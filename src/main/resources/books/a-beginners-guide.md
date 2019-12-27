# A beginners Guide
## Java Keywords
These keywords cannot be used as names for a variable, class, or method. The keywords `const` and `goto` are reserved but not used.
```text
abstract    default    goto          package         this
assert      do                       private         throw
            double     if            protected       throws
boolean                implements    public          transient
break       else       import                        try
byte        enum       instanceof    return          
            extends    int                           void
case                   interface     short           volatile
catch       final                    static          
char        finally    long          strictfp        while
class       float                    super           
const       for        native        switch          
continue               new           synchronized    
```
In addition to the keywords, Java reserves the following. These are values used by Java.
```text
true        false      null
```
## Identifiers
An identifier is a name given to a method, a variable, or any other user-defined item.
* Can be from one to several characters long.
* May start with any **letter** of the alphabet, an **underscore**, or a **dollar** sign. Next may be either a **letter**, a **digit**, a **dollar** sign, or an **underscore**.
* Uppercase and lowercase are different: `myvar` and `myVar` are different names.
* Cannot start an identifier with a digit. This is invalid: `2myVar`
* Cannot use any of the Java keywords as identifier names.

Here are some examples of acceptable identifiers:
```text
Test    x       y2        MaxLoad
$up     _top    my_var    sample23
```
## Primitive Types
At the core of Java are eight primitive types of data. The term *primitive* is used here to indicate that these types are not objects in a object-oriented sense, but rather, normal binary values. These primitive types are not objects because of efficiency concerns.

Type | Meaning
---- | -------
boolean | true/false values
byte | 8-bit integer
char | Character
double | Double-precision floating point
float | Single-precision floating point
int | Integer
long | Long integer
short | Short integer

### Integers
Java defines four integer types: **byte**, **short**, **int**, and **long**. All of the integer types are signed positive and negative values. Java does not support unsigned (positive-only) integers.

Type | Width in Bits | Range
---- | ------------- | -----
byte | 8 | -128 to 127
short | 16 | -32,768 to 32,767
int | 32 | -2,147,483,648 to 2,147,483,647
long | 64 | -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807

Use of the integer types:
* **byte**: the smallest integer type. Variables of type **byte** are especially useful when working with binay data.
* **short**: variables of type **short** are appropriate when you don't need the larger range offered by **int**.
* **int**: the most commonly used integer type is **int**. Variables of type **int** are often employed to control loops, to index arrays, and to perform general purpose math.
* **long**: when you need an integer that has a range greater than **int**, use **long**.

### Floating-Point
The floating-point types can represent numbers that have fractional components. There are two kinds of floating-point types: **float** and **double**.

Type | Width in Bits | Represent
---- | ------------- | -----
float | 32 | Single-precision numbers
double | 64 | Double-precision numbers

Of the two, **double** is the most commonly used because all of the math functions in Java's class library use **double** values. For example, the **sqrt()** method (which is defined by the **Math** class) returns a **double** value that is the square root of its **double** argument.

### Characters
Java uses Unicode. Unicode defines a character set than can represent all of the characters found in all human languages. The standard 8-bit ASCII character set is a subset of Unicode and range from 0 to 127. Thus, the ASCII characters are still valid Java characters.

Type | Width in Bits | Range
---- | ------------- | -----
char | 16 | 0 to 65,536

A character variable can ve assigned a value by enclosing the character in single quotes:
```java
char ch = 'X';
```

Since **char** is an unsigned 16-bit type, it is possible to perform various arithmetic manipulations on a **char** variable:
```java
char ch = 'X';
ch++;
System.out.println("ch is now "+ch); //output: ch is now Y
ch = 90;
System.out.println("ch is now "+ch); //output: ch is now Z
```

## Literals

*Literals* refer to fixed values that are represented in their human readable form. Literals are also called *constants*. Java literals can be of any of the primitive data types.

Primitive Type | Literal | Note
-------------- | ------- | ----
boolean | true false | 
char | 'a' '%' 100 | An integer literal can be assigned to **char** as long as the value being assigned can be represented by the target type
byte | -54 12 115 | An integer literal can be assigned to **byte** as long as the value being assigned can be represented by the target type
short | -54 12 1988 | An integer literal can be assigned to **short** as long as the value being assigned can be represented by the target type
int | -54 12 20481024 | By default, integer literals are of type **int**
long | -15L 18l 18L 500 | To specify a **long** literal, append an l or an L. An integer literal can always be assigned to a **long** variable
double | -10.6 0.5 1150.123 | By default, floating-point literals are of type **double**
float | -5.6f -0.6F 10.19F | To specify a **float** literal, append an For f to the constant.

Beginning with JDK 7, you can embed one or more underscores into an integer or floating-point literal. Doing so can make it easier to read values consisting of may digits. When the literal is compiled, the underscores are simply discarded. Here is an example:
```text
123_45_1234
```
This specifies the value 123,451,234. The use of underscores is particularly useful when encoding things like part numbers, customer IDs, and status codes that are commonly thought of as consisting of subgroups of digits.

### Hexadecimal, Octal and Binary Literals

Literal | Example | Note
-------- | ------ | ----
Hexadecimal | 0xFF | A hexadecimal literal must begin with **0x** or **0X** (a zero followed by an x or X)
Octal | 018 | An octal literal begins with a zero
Binary | 0b1100 | A binary literal must begin with **0b** or **0B** (a zero followed by an b or B). Important: only beginning with JDK 7

Enjoy!