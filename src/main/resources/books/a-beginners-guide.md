# A beginners Guide

## Chapter 1: Java Fundamentals

### Java Keywords
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
### Identifiers
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

## Chapter 2: Introducing Data Types and Operators

### Primitive Types
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

#### Integers
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

#### Floating-Point
The floating-point types can represent numbers that have fractional components. There are two kinds of floating-point types: **float** and **double**.

Type | Width in Bits | Represent
---- | ------------- | -----
float | 32 | Single-precision numbers
double | 64 | Double-precision numbers

Of the two, **double** is the most commonly used because all of the math functions in Java's class library use **double** values. For example, the **sqrt()** method (which is defined by the **Math** class) returns a **double** value that is the square root of its **double** argument.

#### Characters
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

### Literals

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

#### Hexadecimal, Octal and Binary Literals

Literal | Example | Note
------- | ------- | ----
Hexadecimal | 0xFF | A hexadecimal literal must begin with **0x** or **0X** (a zero followed by an x or X)
Octal | 018 | An octal literal begins with a zero
Binary | 0b1100 | A binary literal must begin with **0b** or **0B** (a zero followed by an b or B). Important: only beginning with JDK 7

#### Character Escape Sequences

These sequences are used in place of the characters that they represent.

Escape Sequence | Description
--------------- | -----------
\\' | Single quote
\\" | Double quote
\\\ | Backslash
\r | Carriage return
\n | New line
\f | Form feed
\t | Horizontal tab
\b | Backspace
\ddd | Octal constant (where *ddd* is an octal constant)
\uxxxx | Hexadecimal constant (where *xxxx* is a hexadecimal constant)

For example, this assigns **ch** the tab character:
```java
char ch = '\t';
```

#### String Literals

A *string* is a set of characters enclosed by double quotes. In addition to normal characters, a string literal can also contain one or more of the escape sequences. For example:
```java
System.out.println("First line\nSecond line");
System.out.println("A\tB\tC");
```

### The scope and Lifetime of Variables

There is one quirk to Java's scope rules that may surprise you: although blocks can be nested, no variable declared within an inner scope can have the same name as a variable declared by an enclosing scope. For example the following program, which tries to declare two separate variables with the same name, will not compile:
```java
public class NestVar {
    public static void main(String args[]) {
        int count;
        for (count = 0; count < 10; count++) {
            System.out.println("This is count: "+count);
            int count; //illegal !!!!!!!!!!!!!!!!!!!!!!!!!!!
            for (count = 0; count < 2; count++)
                System.out.println("This program is in error!");
        }
    }
}
```

### Operators

#### Arithmetic Operators

Operator | Meaning
-------- | -------
`+` | Addition (also unary plus)
`-` | Subtraction (also unary minus)
`*` | Multiplication
`/` | Division
`%` | Modulus
`++` | Increment
`--` | Decrement

Special situations:
* The operators +, -, *, and / can be used on objects of type **char**.
* When / is applied to an integer, any reminder will be truncated. Example: 10/3 = 3
* You can obtain the remainder of a division by suing the modulus operator %. Example: 10 % 3 = 1
* The % can be applied to both integer and floating-point types. Thus, 10.0 % 3.0 is also 1.

##### Increment and Decrement

The increment operator adds 1 to its operand, and the decrement operator subtracts 1. Therefore,

`x++;` is the same as `x = x + 1;`

`x--;` is the same as `x = x - 1;`

Both the increment and decrement operators can either precede (prefix) or follow (postfix) the operand. For example,

`x = x + 1;` 

can be written as `++x; // prefix form` 

or as `x++; // postfix form`

When an increment or decrement operator precedes its operand, Java will perform the corresponding operation prior to obtaining the operand's value for use by the rest of the expression. If the operator follows its operand, Java will obtain the operand's value before incrementing or decrementing it. Consider the following:
```java
x = 10;
y = ++x;
// x is set to 11
// y is set to 11

x = 10;
y = x++;
// x is set to 11
// y is set to 10
```

#### Relational and Logical Operators

The relational operators ans shown here:

Operator | Meaning
-------- | -------
== | Equal to
!= | Not equal to
&gt; | Greater than
< | Less than
&gt;= | Greater than or equal to
<= | Less than or equal to

The logical operators are shown next:

Operator | Meaning
-------- | -------
& | AND
&#124; | OR
^ | XOR (exclusive OR)
&#124;&#124; | Short-circuit OR
&& | Short-circuit AND
! | NOT

Note: all of the relational operators can be applied to all numeric types and to type **char**.

Truth table for logical operators:

 p  |  q  | p & q | p &#124; q | p ^ q | !p 
--- | --- | ----- | ---------- | ----- | ---
False | False | False | False | False | True
True | False | False | True | True | False
False | True | False | True | True | True
True | True | True | True | False | False

As the table shows, the outcome of an exclusive OR operation is true when exactly one and only one operand is true.

#### Short-Circuit Logical Operators

The short-circuit AND operator is **&&**, and the short-circuit OR operator is **||**. Their normal counterparts are **&** and **|**. The only difference between the normal and short-circuit versions is that the normal operands will always evaluate each operand, but short-circuit versions will evaluate the second operand only when necessary.

#### The Assigment Operator

The assigment operator does have one interesting attribute that you may not be familiar with: it allows you to create a chain of assignments. For example:
```java
int x, y, z;

x = y = z = 100; // set x, y, and z to 100
```

#### Shorthand Assignments (Compound Assignments)

Java provides special *shorthand* assignment operators that simplify the coding of certain assignments statements. This shorthand will work for all the binary operators in Java (that is, those that require two operands). The general form of the shorthand is:

*var op = expression*

Some examples:

`x = x + 10;` can be written as `x += 10;`

`x = x - 100;` is the same as `x -= 100;`

Thus, the arithmetic and logical shorthand assignment operators are the following:

Operator | Operator | Operator | Operator
-------- | -------- | -------- | --------
+= | -= | *= | /=
%= | &= | &#124;= | ^=

Because there operators combine an operation with an assignment, they are formally referred to as *compound assignment* operators.

#### Type Conversion in Assignments

In programming, it is common to assign one type of variable to another. For example, you might want to assign an **int** value to a **float** variable, as shown here:
```java
int i;
float f; 

i = 10;
f = i; // assign an int to a float 
```

When compatible types are mixed in an assignment, the value of the right side is automatically converted to the type of the left side. Thus, in the preceding fragment, the value in **i** is converted into a **float** and then assigned to **f**. However, because of Java's strict type checking, not all types are compatible, and thus, not all type conversions are implicitly allowed. For example, **boolean** and **int** are not compatible. 

When one type of data is assigned to another type of variable, an *automatic type conversion* will take place if:
* The two types are compatible.
* The destination type is larger than the source type.

When these two conditions are met, a *widening conversion* takes place. For example, the **int** type is always large enough to hold all valid **byte** values, and both **int** and **byte** are integer types, so an automatic conversion from **byte** to **int** can be applied.

For widening conversions, the numeric types, including integer and floating-point types, are compatible with each other. For example, the following program is perfectly valid since **long** to **double** is a widening conversion that is automatically performed. 
```java
long L;
double D;

L = 100123285L;
D = L; // Automatic conversion from long to double
```

Although there is an automatic conversion from **long** to **double**, there is no automatic conversion from **double** to **long**, since this is not a widening conversion. Thus, the following version of the preceding program is invalid.
```java
long L;
double D;

D = 100123285.0;
L = D; // Illegal !!! No automatic conversion from double to long
```
Some interesting facts about conversions:
* There are no automatic conversions from the numeric types to **char** or **boolean**.
* **char** and **boolean** are not compatible with each other.
* An integer literal can be assigned to **char**.

#### Casting Incompatible Types

When a cast involves a *narrowing conversion*, information might be lost:
* When casting a **long** into a **short**, information will be lost if the **long**'s value is greater than the range of a **short** because its high-order bits are removed.
* When a floating-point value is cast to an integer type, the fractional component will also be lost due to truncation. For example, if the value 1.23 is assigned to an integer, the resulting value will simply be 1. The 0.23 is lost. 

The following program demonstrates some type conversions that require casts: 
```java
double x, y;
byte b;
int i;
char ch;

x = 10.0;
y = 3.0;

i = (int) (x / y); // Truncation will occur in this conversion. Cast double to int. i = 3

i = 100;
b = (byte) i; // No loss of info here. A byte can hold the value 100. b = 100

i = 257;
b = (byte) i; // Information loss this time. A byte cannot hold the value 257. b = 1

b = 88; // ASCII code for X
ch = (char) b; // Cast between incompatible types. ch = 'X'
```

#### Operator Precedence

The following table shows the order of precedence for all Java operators, from highest to lowest.

**Highest** | &nbsp; | &nbsp; | &nbsp; | &nbsp; | &nbsp; | &nbsp;
----------- | ------ | ------ | ------ | ------ | ------ | ------
[] | () | . |  |  |  | 
++ (postfix) | -- (postfix) |  |  |  |  | 
++ (prefix) | -- (prefix) | ~ | ! | + (unary) | - (unary) | (type-cast)
&#42; | / | % |  |  |  | 
&#43; | - |  |  |  |  | 
&gt;&gt; | &gt;&gt;&gt; | << |  |  |  | 
&gt; | &gt;= | < | <= | instanceof |  | 
== | != |  |  |  |  | 
& |  |  |  |  |  | 
^ |  |  |  |  |  | 
&#124; |  |  |  |  |  | 
&& |  |  |  |  |  | 
&#124;&#124; |  |  |  |  |  | 
?: |  |  |  |  |  | 
-&gt; |  |  |  |  |  | 
= | op= |  |  |  |  | 
**Lowest** |  |  |  |  |  | 


#### Expressions

##### Type Conversion in Expressions

Within an expression, it is possible to mix two or more different types of data as long as they are compatible with each other. For example, you can mix **short** and **long** within an expression because they are both numeric types. When different types of data are mixed within an expression, they are all converted to the same type. This is accomplished through the use of Java's *type promotion rules*:
* First, all **char**, **byte**, and **short** values are promoted to **int**.
* Then, if one operand is a **long**, the whole expression is promoted to **long**.
* If one operand is a **float** operand, the entire expression is promoted to **float**.
* If any of the operands is **double**, the result is **double**.

It is important to understand that type promotions apply only to the values operated upon when an expression is evaluated. For example, if the value of a **byte** variable is promoted to **int** inside an expression, outside the expression, the variable is still a **byte**. Type promotion only affects the evaluation of an expression.

Type promotion can, however, lead to somewhat unexpected results. For example, when an arithmetic operation involves two **byte** values, the following sequence occurs: First, the **byte** operands are promoted to **int**. Then the operation takes place, yielding an **int** result. Thus, the outcome of an operation involving two **byte** values will be an **int**. This is not what you might intuitively expect. Consider the following program:
```java
byte b;
int i;

b = 10;
i = b * b; // No cast needed because result is already elevated to int.

b = 10;
b = (byte) (b * b); // Cast is needed here to assign an int to a byte.
```

Somewhat counterintuitively, no cast is needed when assigning **b &#42; b** to **i**, because **b** is promoted to **int** when the expression is evaluated. However, when you try to assign **b &#42; b** to **b**, you do need a cast-back to **byte**! Keep this in mind if you get unexpected type-incompatibility error messages on expressions that would otherwise seem perfectly OK.

This same sort of situation also occurs when performing operations on **char**s. For example, in the following fragment, the cast back to **char** is needed because of the promotion of **ch1** and **ch2** to **int** within the expression:
```java
char ch1 = 'a', ch2 = 'b';

ch1 = (char) (ch1 + ch2);
```

Without the cast, the result of adding **ch1** to **ch2** would be **int**, which can't be assigned to a **char**. 

Enjoy!