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

## Chapter 3: Program Control Statements

### The switch Statement

Frequently, the expression controlling a **switch** is simply a variable rather than a larger expression. The *expression* controlling the **switch** must be of the following types:
* **byte**
* **short**
* **int**
* **char**
* enumeration
* **String** (Beginning with JDK 7)

Some rules for **case**:
* Each value specified in the **case** statements must be a unique constant expression (such as a literal value).
* Duplicate **case** values are not allowed. 
* The type of each value must be compatible with the type of *expression*. 

Some rules for **default**:
* The **default** statement sequence is executed if no **case** constant matches the expression.
* The **default** is optional; if it is not present, no action takes place if all matches fail.

When a match is found, the statements associated with that **case** are executed until the **break** is encountered or, in the case of **default** or the last **case**, until the end of the **switch** is reached.

Some rules for **break**:
* Technically, the **break** statement is optional, although most applications of the **switch** will use it.
* When encountered within the statement sequence of a **case**, the **break** statement causes program flow to exit from the entire **switch** statement and resume at the next statement outside the **switch**.
* If a **break** statement does not end the statement sequence associated with a **case**, then all the statements *at and following* the matching **case** will be executed until a **break** (or the end of the **switch**) is encountered.

For example, study the following program carefully:
```java
// Demonstrate the switch without break statements
public class NoBreak {
    public static void main(String args[]) {
        int i;
        
        for (i=0; i<=5; i++) {
            switch (i) {
                case 0:
                    System.out.println("i is less than one");
                case 1:
                    System.out.println("i is less than two");
                case 2:
                    System.out.println("i is less than three");
                case 3:
                    System.out.println("i is less than four");
                case 4:
                    System.out.println("i is less than five");
            }
            System.out.println();
        }
    }
}
```

This program displays the following output:
```text
i is less than one
i is less than two
i is less than three
i is less than four
i is less than five

i is less than two
i is less than three
i is less than four
i is less than five

i is less than three
i is less than four
i is less than five

i is less than four
i is less than five

i is less than five
```

As this program illustrates, execution will continue into the next **case** if no **break** statement is present.

You can have empty **case**s, as shown in this example:
```java
switch (i) {
    case 1:
    case 2:
    case 3:
        System.out.println("i is 1, 2 or 3");
        break;
    case 4:
        System.out.println("i is 4");
        break;
}
```

In this fragment, if **i** has the value 1, 2, or 3, the first **println()** statement executes. If it is 4, the second **println()** statement executes. The "stacking" of **case**s, as shown in this example, is common when several **case**s share common code.

#### Nested switch Statements

It is possible to have a **switch** as part of the statement sequence of an outer **switch**. This is called a nested **switch**. Even if the **case** constants of the inner and outer **switch** contain common values, no conflicts will arise. For example, the following code fragment is perfectly acceptable:
```java
switch (ch1) {
    case 'A':
        System.out.println("This A is part of outer switch.");
        switch (ch2) {
            case 'A':
                System.out.println("This A is part of inner switch");
                break;
            case 'B': 
                // ...
        } // end of inner switch
        break;
    case 'B':
        // ...
}
```

### The for Loop

The **for** loop can proceed in a positive or negative fashion, and it can change the loop control variable by any amount. For example, the following program prints the numbers 100 to -95, in decrements of 5:
```java
for (int x = 100; x > -100; x -= 5)
    System.out.println(x);
```

An important point about **for** loops is that the conditional expression is always tested at the top of the loop. This means that the code inside the loop may not be executed at all if the condition is false to begin with. Here is an example:
```java
for (int count = 10; count < 5; count++)
    x += count; // this statement will not execute
```

#### Some Variations on the for Loop

The **for** is one of the most versatile statements in the Java language because it allows a wide range of variations. For example, multiple loop control variables can be used. Consider the following program:
```java
int i, j;

for (i = 0, j = 10; i < j; i++, j--) // Notice the two loop control variables.
    System.out.println("i and j: " + i + " " + j);
```

The condition controlling the loop can be any valid Boolean expression. It does not need to involve the loop control variable.
```java
int i;

System.out.println("Press S to stop");

for (i = 0; (char) System.in.read() != 'S'; i++)
    System.out.println("Pass #" + i);
```

#### Missing Pieces

In java, it is possible for any or all of the initialization, condition, or iteration portions of the **for** loop to be blank.

The iteration expression of the **for** is empty:
```java
for (int i = 0; i < 10; ) { // The iteration expression is missing
    System.out.println("Pass #" + i);
    i++; // increment loop control var
}
```

The initialization portion is also moved out of the **for**:
```java
int i = 0; // the initialization expression is moved out of the loop
for ( ; i < 10; ) {
    System.out.println("Pass #" + i);
    i++; // increment loop control var
}
```

##### The Infinite Loop

You can create an *infinite loop* (a loop that never terminates) using the **for** by leaving the conditional expression empty. Hint: To halt a loop of this type you use the **break** statement.
```java
for (;;) // intentionally infinite loop
{
    // ...
}
```

#### Loops with No Body

In Java, the body associated with a **for** loop (or any other loop) can be empty. This is because a *null statement* is syntactically valid. Body-less loops are often useful. For example, the following program uses one to sum the numbers 1 through 5:
```java
int i;
int sum = 0;

// sum the numbers through 5
for (i = 1; i <= 5; sum += i++) ; // No body in this loop
System.out.println("Sum is " + sum); 
```

The output from the program is shown here: `Sum is 15`
Notice that the summation process is handled entirely within the for statement, and no body is needed. Pay special attention to the iteration expression: `sum += i++`, it is the same as this sequence of statements:
```java
sum = sum + i;
i++;
```

### The do-while Loop

The last of Java's loops is the **do-while**. Unlike the **for** and the **while** loops, in which the condition is tested at the top of the loop, the **do-while** loop checks its condition at the bottom of the loop. This means that a **do-while** loop will always execute at least once. The general form of the **do-while** loop is:
```text
do {
    statements;
} while(condition);
```

Although the braces are not necessary when only one statement is present, they are often used to improve readability of the **do-while** construct, thus preventing confusion with the **while**. The **do-while** loop executes as long as the conditional expression is true.

**Question: Given the flexibility inherent in all of Java's loops, what criteria should I use when selecting a loop? That is, how do I choose the right loop for a specific job?**

Answer:
* Use a **for** loop when performing a known number of iterations.
* Use the **do-while** when you need a loop that will always perform at least one iteration.
* The **while** is best used when the loop will repeat an unknown number of times.

### Use break to Exit a Loop

The **break** statement can be used with any of Java's loops, including intentionally infinite loops. For example, the following program simply reads input until the user types the letter q:
```java
char ch;

for ( ; ; ) {
    ch = (char) System.in.read(); // get a char
    if (ch == 'q') break; // This "infinite" loop is terminated by the break
}
System.out.println("You pressed q!"); 
```

When used inside a set of nested loops, the **break** statement will break out of only the innermost loop. For example:
```java
for (int i=0; i<3; i++) {
    System.out.println("Outer loop count: " + i); 
    System.out.print("    Inner loop count: ");
    
    int t = 0;
    while (t < 100) {
        if (t == 10) break; // terminate loop if t is 10
        System.out.print(t + " ");
        t++;
    }
    System.out.println();
}
System.out.println("Loops complete.");
```

This program generates the following output: 
```text
Outer loop count: 0
    Inner loop count: 0 1 2 3 4 5 6 7 8 9
Outer loop count: 1
    Inner loop count: 0 1 2 3 4 5 6 7 8 9
Outer loop count: 2
    Inner loop count: 0 1 2 3 4 5 6 7 8 9
Loops complete.
```

Here are two other points to remember about **break**:
* First, more than one **break** statement may appear in a loop. However, be careful. Too many **break** statements have the tendency to destructure your code.
* Second, the **break** that terminates a **switch** statement affects only that **switch** statement and not any enclosing loops.


### Use break as a Form of goto

In addition to its uses with the **switch** statement and loops, the **break** statement can be employed by itself to provide a "civilized" form of the goto statement. For example, the goto can be helpful when exiting from a deeply nested set of loops. To handle such situations, Java defines an expanded form of the **break** statement. By using this form of **break**, you can, for example, break out of one or more blocks of code. These blocks need not be part of a loop or a **switch**. They can be any block. Further, you can specify precisely where execution will resume, because this form of **break** works with a label. As you will see, **break** gives you the benefits of a goto without its problems.

The general form of the labeled **break** statement is shown here:
```text
break label;
```

Typically, *label* is the name of a label that identifies a block of code. When this form of **break** executes, control is transferred out of the named block of code. The labeled block of code must enclose the **break** statement, but it does not need to be the immediately enclosing block. This means that you can use a labeled **break** statement to exit from a set of nested blocks. But you cannot use **break** to transfer control to a block of code that does not enclose the **break** statement.

To name a block, put a label at the start of it. The block being labeled can be a stand-alone block, or a statement that has a block as its target. A *label* is any valid Java identifier followed by a colon. Once you have labeled a block, you can then use this label as the target of a **break** statement. Doing so causes execution to resume at the *end* of the labeled block. For example, the following program shows three nested blocks:
```java
for (int i = 1; i < 4; i++) {
    one: {
        two: {
            three: {
                System.out.println("\ni is " + i);
                if (i == 1) break one; // Break to a label
                if (i == 2) break two;
                if (i == 3) break three;
                
                // this is never reached
                System.out.println("won't print");
            }
            System.out.println("After block three.");
        }
        System.out.println("After block two.");
    }
    System.out.println("After block one.");
}
System.out.println("After for.");
```

The output from the program is shown here:
```text
i is 1
After block one.

i is 2
After block two.
After block one.

i is 3
After block three.
After block two.
After block one.
After for.
```

Here is another example. This time, **break** is being used to jump outside of a series of nested **for** loops. When the **break** statement in the inner loop is executed, program control jumps to the end of the block defined by the outer **for** loop, which is labeled by **done**. This causes the remainder of all three loops to be bypassed.
```java
done: for (int i = 0; i < 10; i++) {
    for (int j = 0; j < 10; j++) {
        for (int k = 0; k < 10; k++) {
            System.out.println(k + " ");
            if (k == 5) break done; // jump to done
        }
        System.out.println("After k loop"); // won't execute
    }
    System.out.println("After j loop"); // won't execute
}
System.out.println("After i loop");
```

The output from the program is shown here:
```text
0 
1 
2 
3 
4 
5 
After i loop
```

Precisely where you put a label is very important, especially when working with loops. For example, consider the following program:
```java
int x = 0, y = 0;

// here, put label before for statement.
stop1: for (x = 0; x < 5; x++) {
    for (y = 0; y < 5; y++) {
        if (y == 2) break stop1;
        System.out.println("x and y: " + x + " " + y);
    }
}

System.out.println();

// now, put a label immediately before {
for (x = 0; x < 5; x++)
stop2: {
    for (y = 0; y < 5; y++) {
        if (y == 2) break stop2;
        System.out.println("x and y: " + x + " " + y);
    }
}
```

The output from the program is shown here:
```text
x and y: 0 0
x and y: 0 1

x and y: 0 0
x and y: 0 1
x and y: 1 0
x and y: 1 1
x and y: 2 0
x and y: 2 1
x and y: 3 0
x and y: 3 1
x and y: 4 0
x and y: 4 1
```

Keep in mind that you cannot **break** to any label that is not defined for an enclosing block. Since the loop labeled **one** does not enclose the **break** statement, it is not possible to transfer control to that block. For example, the following program is invalid and will not compile:
```java
one: for (int i = 0; i < 3; i++) {
    System.out.print("Pass " + i + ": ");
}

for (int j = 10; j < 100; j++) {
    if (j == 10) break one; // WRONG!!!
    System.out.print(j + " ");
}
```

### Use continue

In **while** and **do-while** loops, a **continue** statement will cause control to go directly to the conditional expression and then continue the looping process. In the case of the **for**, the iteration expression of the loop is evaluated, then the conditional expression is executed, and then the loop continues.

As with the **break** statement, **continue** may specify a label to describe which enclosing loop to continue. Here is an example program that uses **continue** with a label:
```java
outerloop: for (int i = 1; i < 10; i++) {
    System.out.print("\nOuter loop pass " + i + ", Inner loop: ");
    for (int j = 1; j < 10; j++) {
        if (j == 5) continue outerloop; // continue outer loop
        System.out.print(j);
    }
}
```

The output from the program is shown here:
```text
Outer loop pass 1, Inner loop: 1234
Outer loop pass 2, Inner loop: 1234
Outer loop pass 3, Inner loop: 1234
Outer loop pass 4, Inner loop: 1234
Outer loop pass 5, Inner loop: 1234
Outer loop pass 6, Inner loop: 1234
Outer loop pass 7, Inner loop: 1234
Outer loop pass 8, Inner loop: 1234
Outer loop pass 9, Inner loop: 1234
```

## Chapter 4: Introducing Classes, Objects, and Methods

### Class Fundamentals

A class is a template that defines the form of an object.

#### Defining a Class

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

### How Objects Are Created

The **new** operator dynamically allocates (that is, allocates at run time) memory for an object and returns a reference to it. This reference is, more or less, the address in memory of the object allocated by **new**. This reference is then stored in a variable. Thus, in Java, all class objects must be dynamically allocated. The two steps combined in the preceding statement can be rewritten like this to show each step individually:
```java
Vehicle minivan; // declare reference to object
minivan = new Vehicle(); // allocate a Vehicle object
```

The first line declares **minivan** as a reference to an object of type **Vehicle**. Thus, **minivan** is a variable that can refer to an object, but it is not an object itself. At this point, **minivan** does not refer to an object. The next line creates a new **Vehicle** object and assigns a reference to it to **minivan**. Now, **minivan** is linked with an object.

### Reference Variables and Assignment

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

### Returning from a Method

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

### Constructors

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

### The new Operator Revisited

Now that you know more about classes and their constructors, let's take a closer look at the **new** operator. In the context of an assignment, the **new** operator has this general form:
```text
class-var = new class-name(arg-list);
```

Here, *class-var* is a variable of the class type being created. The *class-name* is the name of the class that is being instantiated. The class name followed by a parenthesized argument list (which can be empty) specifies the constructor for the class. If a class does not define its own constructor, **new** will use the default constructor supplied by Java. Thus, **new** can be used to create an object of any class type. The **new** operator returns a reference to the newly created object, which (in this case) is assigned to *class-var*.

Since memory is finite, it is possible that **new** will not be able to allocate memory for an object because insufficient memory exists. If this happens, a run-time exception will occur.

### Garbage Collection

As you have seen, objects are dynamically allocated from a pool of free memory by using the **new** operator. As explained, memory is not infinite, and the free memory can be exhausted. Thus, it is possible for **new** to fail because there is insufficient free memory to create the desired object. For this reason, a key component of any dynamic allocation scheme is the recovery of free memory from unused objects, making that memory available for subsequent reallocation. In some programming languages, the release of previously allocated memory is handled manually. However, Java uses a different, more trouble-free approach: *garbage collection*.

Java's garbage collection system reclaims objects automatically-occurring transparently, behind the scenes, without any programmer intervention. It works like this: When no references to an object exist, that object is assumed to be no longer needed, and the memory occupied by the object is released. This recycled memory can then be used for a subsequent allocation.

Garbage collection occurs only sporadically during the execution of your program. It will not occur simply because one or more objects exist that are no longer used. For efficiency, the garbage collector will usually run only when two conditions are met: there are objects to recycle, and there is a need to recycle them. Remember, garbage collection takes time, so the Java run-time system does it only when it is appropriate. Thus, you can't know precisely when garbage collection will take place.

### The finalize() Method

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

### The this Keyword

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

## Chapter 5: More Data Types and Operators

### Arrays

Although arrays in Java can be used just like arrays in other programming languages, they have one special attribute: they are implemented as objects. This fact is one reason that a discussion of arrays was deferred until objects had been introduced. By implementing arrays as objects, several important advantages are gained, not the least of which is that unused arrays can be garbage collected.

#### One-Dimensional Arrays

A one-dimensional array is a list of related variables. Such lists are common in programming. For example, you might use a one-dimensional array to store the account numbers of the active users on a network. Another array might be used to store the current batting averages for a baseball team.

To declare a one-dimensional array, you can use this general form:
```text
type array-name[] = new type[size];
```

Here, *type* declares the element type of the array. (The element type is also commonly referred to as the base type.) The element type determines the data type of each element contained in the array. The number of elements that the array will hold is determined by *size*. Since arrays are implemented as objects, the creation of an array is a two-step process. First, you declare an array reference variable. Second, you allocate memory for the array, assigning a reference to that memory to the array variable. Thus, arrays in Java are dynamically allocated using the **new** operator.

Here is an example. The following creates an **int** array of 10 elements and links it to an array reference variable named **sample**:
```java
int sample[] = new int[10];
```

This declaration works just like an object declaration. The **sample** variable holds a reference to the memory allocated by **new**.

Arrays can be initialized when they are created. The general form for initializing a one-dimensional array is shown here:
```text
type array-name[] = { val1. val2, val3, ..., valN };
```

Here, the initial values are specified by *val1* through *valN*. They are assigned in sequence, left to right, in index order. Java automatically allocates an array large enough to hold the initializers that you specify. There is no need to explicitly use the **new** operator. For example:
```java
int nums[] = {99, -10, 100123, -978, 463};
```

##### Bubble Sort Example

Because a one-dimensional array organizes data into an indexable linear list, it is the perfect data structure for sorting. In this project you will learn a simple way to sort an array. As you may know, there are a number of different sorting algorithms. There are the quick sort, the shaker sort, and the shell sort, to name just three. However, the best known, simplest, and easiest to understand is called the Bubble sort. Although the Bubble sort is not very efficient-in fact, its performance is unacceptable for sorting large arrays-it may be used effectively for sorting small arrays.

The Bubble sort gets its name from the way it performs the sorting operation. It uses the repeated comparison and, if necessary, exchange of adjacent elements in the array. In this process, small values move toward one end and large ones toward the other end. The process is conceptually similar to bubbles finding their own level in a tank of water. The Bubble sort operates by making several passes through the array, exchanging out-of-place elements when necessary. The number of passes required to ensure that the array is sorted is equal to one less than the number of elements in the array.

Here is the code that forms the core of the Bubble sort. The array being sorted is called **nums**.
```java
// This is the Bubble sort.
for (a = 1; a < size; a++) {
    for (b = size - 1; b >= a; b--) {
        if (nums[b-1] > nums[b]) { // if out of order
            // exchange elements
            t = nums[b-1];
            nums[b-1] = nums[b];
            nums[b] = t;
        }
    }
}
```

Notice that sort relies on two **for** loops. The inner loop checks adjacent elements in the array, looking for out-of-order elements. When an out-of-order element pair is found, the two elements are exchanged. With each pass, the smallest of the remaining elements moves into its proper location. The outer loop causes this process to repeat until the entire array has been sorted. Here is the entire **Bubble** program:
```java
/*
    Bubble.java
    Demonstrate the Bubble sort.
*/
public class Bubble {
    public static void main(String args[]) {
        int nums[] = {99, -10, 100123, 18, -978, 5623, 463, -9, 287, 49};
        int a, b, t;

        // display original array
        System.out.print("Original array is:");
        for (int i = 0; i < nums.length; i++)
            System.out.print(" " + nums[i]);
        System.out.println();
        
        // This is the Bubble sort
        for (a = 1; a < nums.length; a++) {
            for (b = nums.length - 1; b >= a; b--) {
                if (nums[b-1] > nums[b]) { // if out of order
                    // exchange elements
                    t = nums[b-1];
                    nums[b-1] = nums[b];
                    nums[b] = t;
                }
            }
        }

        // display sorted array
        System.out.print("Sorted array is:");
        for (int i = 0; i < nums.length; i++)
            System.out.print(" " + nums[i]);
        System.out.println();
    }
}
```

The output from the program is shown here:
```text
Original array is: 99 -10 100123 18 -978 5623 463 -9 287 49
Sorted array is: -978 -10 -9 18 49 99 287 463 5623 100123
```

Although the Bubble sort is good for small arrays, it is not efficient when used on larger ones. The best general-purpose sorting algorithm is the Quicksort.

### Multidimensional Arrays

In Java, a multidimensional array is an array of arrays.

#### Two-Dimensional Arrays

The simplest form of the multidimensional array is the two-dimensional array. A two-dimensional array is, in essence, a list of one-dimensional arrays. To declare a two-dimensional integer array **table** of size 10, 20 you would write:
```java
int table[][] = new int[10][20];
```

Pay careful attention to the declaration. Unlike some other computer languages, which use commas to separate the array dimensions, Java places each dimension in its own set of brackets. Similarly, to access point 3, 5 of array **table**, you would use **table[3][5]**.

In the next example, a two-dimensional array is loaded with the numbers 1 through 12:
```java
int table[][] = new int[3][4];

for (int t = 0; t < 3; ++t) {
    for (int i = 0; i < 4; ++i) {
        table[t][i] = (t*4)+i+1;
        System.out.print(table[t][i] + " ");
    }
    System.out.println();
}
``` 

In this example, **table[0][0]** will have the value 1, **table[0][1]** the value 2, **table[0][2]** the value 3, and so on. The value of **table[2][3]** will be 12. Conceptually, the array will look like this:

Index | `0` | `1` | `2` | `3`
----- | --- | --- | --- | ---
`0` | 1 | 2 | 3 | 4 
`1` | 5 | 6 | 7 | 8 
`2` | 9 | 10 | 11 | 12 

```text
table[1][2] --> 7
```

### Irregular Arrays

When you allocate memory for a multidimensional array, you need to specify only the memory for the first (leftmost) dimension. You can allocate the remaining dimensions separately. For example, the following code allocates memory for the first dimension of **table** when it is declared. It allocates the second dimension manually:
```java
int table[][] = new int[3][];
table[0] = new int[4];
table[1] = new int[4];
table[2] = new int[4];
```

Although there is no advantage to individually allocating the second dimension arrays in this situation, there may be in others. For example, when you allocate dimensions separately, you do not need to allocate the same number of elements for each index. Since multidimensional arrays are implemented as arrays of arrays, the length of each array is under your control. For example:
```java
int riders[][] = new int[5][];
riders[0] = new int[10];
riders[1] = new int[10];
riders[2] = new int[10];
riders[3] = new int[2]; // only two elements long
riders[4] = new int[2]; // only two elements long
```

#### Arrays of Three or more Dimensions

Java allows arrays with more than two dimensions. Here is the general form of a multidimensional array declaration:
```text
type name[][]...[] = new type[size1][size2]...[sizeN];
```

For example, the following declaration creates a 4 x 10 x 3 three-dimensional integer array:
```java
int multidim[][][] = new int[4][10][3];
```

#### Initializing Multidimensional Arrays

A multidimensional array can be initialized by enclosing each dimension's initializer list within its own set of curly braces. For example, the general form of array initialization for a two-dimensional array is shown here:
```text
type-specifier array_name[][] = {
    { val, val, val, ..., val },
    { val, val, val, ..., val },
    { val, val, val, ..., val },
    .
    .
    .
    { val, val, val, ..., val }
};
```

Here, *val* indicates an initialization value. Each inner block designates a row. Within each row, the first value will be stored in the first position of the subarray, the second value in the second position, and so on. Notice that commas separate the initializer blocks and that a semicolon follows the closing }.

For example, the following program initializes an array called **sqrs** with the numbers 1 through 10 and their squares:
```java
int sqrs [][] = {
    { 1, 1 },
    { 2, 4 },
    { 3, 9 },
    { 4, 16 },
    { 5, 25 },
    { 6, 36 },
    { 7, 49 },
    { 8, 64 },
    { 9, 81 },
    { 10, 100 }
};
```

### Alternative Array Declaration Syntax

There is a second form that can be used to declare an array:
```text
type[] var-name;
```

Here, the square brackets follow the type specifier, not the name of the array variable. For example, the following two declarations are equivalent:
```java
int counter[] = new int[3];
int[] counter = new int[3];
```

The following declarations are also equivalent:
```java
char table[][] = new char[3][4];
char[][] table = new char[3][4];
```

This alternative declaration form offers convenience when declaring several arrays at the same time. For example:
```java
int[] nums, nums2, nums3; // create three arrays
``` 

This creates three array variables of type **int**. It is the some as writing:
```java
int nums[], nums2[], nums3[]; // also, create three arrays
```

The alternative declaration form is also useful when specifying an array as a return type for a method. For example:
```java
int[] someMethod() { ... }
```
 
This declares that **someMethod()** returns an array of type **int**.

### Assigning Array References

As with other objects, when you assign one array reference variable to another, you are simply changing what object that variable refers to. You are not causing a copy of the array to be made, nor are you causing the contents of one array to be copied to the other. For example, consider this program:
```java
// Assigning array reference variables
public class AssignARef {
    public static void main(String[] args){
        int nums1[] = new int[10];
        int nums2[] = new int[10];

        for (int i = 0; i < 10; i++)
            nums1[i] = i;

        for (int i = 0; i < 10; i++)
            nums2[i] = -i;

        System.out.print("Here is num1: ");
        for (int i = 0; i < 10; i++)
            System.out.print(nums1[i] + " ");
        System.out.println();

        System.out.print("Here is num2: ");
        for (int i = 0; i < 10; i++)
            System.out.print(nums2[i] + " ");
        System.out.println();

        nums2 = nums1; // Assign an array reference: now nums2 refers to nums1

        System.out.print("Here is num2 after assignment: ");
        for (int i = 0; i < 10; i++)
            System.out.print(nums2[i] + " ");
        System.out.println();

        nums2[3] = 99; // now operate on nums1 array through nums2

        System.out.print("Here is num1 after change through nums2: ");
        for (int i = 0; i < 10; i++)
            System.out.print(nums1[i] + " ");
        System.out.println();
    }
}
```

The output from the program is shown here:
```text
Here is num1: 0 1 2 3 4 5 6 7 8 9 
Here is num2: 0 -1 -2 -3 -4 -5 -6 -7 -8 -9 
Here is num2 after assignment: 0 1 2 3 4 5 6 7 8 9 
Here is num1 after change through nums2: 0 1 2 99 4 5 6 7 8 9 
```

As the output shows, after the assignment of **nums1** to **nums2**, both array reference variables refer to the same object.

### Using the length Member

Keep in mind that the value of **length** has nothing to do with the number of elements that are actually in use. It contains the number of elements that the array is capable of holding.

To obtain the length of any individual array part of a two-dimensional array, you will use an expression such as this:
```java
table[0].length
```

### The For-Each Style for Loop

The second form of the **for** implements a "for-each" style loop. A for-each loop cycles through a collection of objects, such as an array, in strictly sequential fashion, from start to finish. Originally, Java did not offer a for-each style loop. However, with the release of JDK 5, the **for** loop was enhanced to provide this option. The for-each style of **for** is also referred to as the *enhanced* **for** loop.

The general form of the for-each style **for** is shown here:
```text
for (type itr-var : collection) statement-block
```

Here, *type* specifies the type, and *itr-var* specifies the name of an *iteration variable* that will receive the elements from a collection, one at a time, from beginning to end. The collection being cycled through is specified by *collection*. With each iteration of the loop, the next element in the collection is retrieved and stored in *itr-var*. The loop repeats until all elements in the collection have been obtained. Thus, when iterating over an array of size *N*, the enhanced **for** obtains the elements in the array in index order, from 0 to *N*-1.

Because the iteration variable receives values from the collection, *type* must be the same as (or compatible with) the elements stored in the collection. Thus, when iterating over arrays, *type* must be compatible with the element type of the array.

**Question: Aside from arrays, what other types of collections can the for-each style **for** loop cycle through?**

Answer: One of the most important uses of the for-each style **for** is to cycle through the contents of a collection defined by the Collections Framework. The Collections Framework is a set of classes that implement various data structures, such as lists, vectors, sets, and maps.

The following fragment uses a for-each style **for** loop to compute the sum of the values in an array:
```java
int nums[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
int sum = 0;

for (int x : nums) sum += x;
```

Although the for-each **for** loop iterates until all elements in an array have been examined, it is possible to terminate the loop early by using a **break** statement. For example, this loop sums only the first five elements of **nums**:
```java
int nums[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
int sum = 0;
// Sum only the first 5 elements
for (int x : nums) {
    sum += x;
    if (x == 5) break; // stop the loop when 5 is obtained
}
```

There is one important point to understand about the for-each style **for** loop. Its iteration variable is "read-only" as it relates to the underlying array. An assignment to the iteration variable has no effect on the underlying array. In other words, you can't change the contents of the array by assigning the iteration variable a new value. For example, consider this program:
```java
// The for-each loop is essentially read-only.
int nums[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

for (int x : nums) {
    System.out.print(x + " ");
    x = x * 10; // no effect on nums
}
System.out.println();

for (int x : nums)
    System.out.print(x + " ");
System.out.println();
```

The first **for** loop increases the value of the iteration variable by a factor of 10. However, this assignment has no effect on the underlying array **nums**, as the second **for** loop illustrates. The output, shown here, proves this point:
```text
1 2 3 4 5 6 7 8 9 10 
1 2 3 4 5 6 7 8 9 10 
```

#### Iterating Over Multidimensional Arrays

The enhanced **for** also works on multidimensional arrays. Remember, however, that in Java, multidimensional arrays consist of *arrays of arrays*. This is important when iterating over a multidimensional array because each iteration obtains the *next array*, not an individual element. Furthermore, the iteration variable in the **for** loop must be compatible with the type of array being obtained. For example, consider the following program, it uses nested **for** loops to obtain the elements of a two-dimensional array in row order, from first to last:
```java
int sum = 0;
int nums[][] = new int [3][5];

// give nums some values
for (int i = 0; i < 3; i++)
    for (int j = 0; j < 5; j++)
        nums[i][j] = (i+1)*(j+1);

// Use for-each for loop to display and sum the values
for (int x[] : nums) { // Notice how x is declared
    for (int y : x) {
        System.out.println("Value is: " + y);
        sum += y;
    }
}
System.out.println("Summation: " + sum);
``` 

The output from this program is shown here:
```text
Value is: 1
Value is: 2
Value is: 3
Value is: 4
Value is: 5
Value is: 2
Value is: 4
Value is: 6
Value is: 8
Value is: 10
Value is: 3
Value is: 6
Value is: 9
Value is: 12
Value is: 15
Summation: 90
```

### Strings

Pending.

Enjoy!