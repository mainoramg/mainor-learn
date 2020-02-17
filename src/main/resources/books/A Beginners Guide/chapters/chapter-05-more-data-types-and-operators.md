# Chapter 5: More Data Types and Operators

## Table of Contents
* [Arrays](#arrays)
  * [One-Dimensional Arrays](#one-dimensional-arrays)
    * [Bubble Sort Example](#bubble-sort-example)
* [Multidimensional Arrays](#multidimensional-arrays)
  * [Two-Dimensional Arrays](#two-dimensional-arrays)
  * [Irregular Arrays](#irregular-arrays)
  * [Arrays of Three or more Dimensions](#arrays-of-three-or-more-dimensions)
  * [Initializing Multidimensional Arrays](#initializing-multidimensional-arrays)
* [Alternative Array Declaration Syntax](#alternative-array-declaration-syntax)
* [Assigning Array References](#assigning-array-references)
* [Using the length Member](#using-the-length-member)
* [The For-Each Style for Loop](#the-for-each-style-for-loop)
  * [Iterating Over Multidimensional Arrays](#iterating-over-multidimensional-arrays)
* [Strings](#strings)
  * [Constructing Strings](#constructing-strings)
  * [Operating on Strings](#operating-on-strings)
  * [Strings Are Immutable](#strings-are-immutable)
* [Using Command-Line Arguments](#using-command-line-arguments)
* [The Bitwise Operators](#the-bitwise-operators)
* [The ? Operator (Ternary Operator)](#the--operator-ternary-operator)

## Arrays

Although arrays in Java can be used just like arrays in other programming languages, they have one special attribute: they are implemented as objects. This fact is one reason that a discussion of arrays was deferred until objects had been introduced. By implementing arrays as objects, several important advantages are gained, not the least of which is that unused arrays can be garbage collected.

### One-Dimensional Arrays

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

#### Bubble Sort Example

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

## Multidimensional Arrays

In Java, a multidimensional array is an array of arrays.

### Two-Dimensional Arrays

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

### Arrays of Three or more Dimensions

Java allows arrays with more than two dimensions. Here is the general form of a multidimensional array declaration:
```text
type name[][]...[] = new type[size1][size2]...[sizeN];
```

For example, the following declaration creates a 4 x 10 x 3 three-dimensional integer array:
```java
int multidim[][][] = new int[4][10][3];
```

### Initializing Multidimensional Arrays

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

## Alternative Array Declaration Syntax

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

## Assigning Array References

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

## Using the length Member

Keep in mind that the value of **length** has nothing to do with the number of elements that are actually in use. It contains the number of elements that the array is capable of holding.

To obtain the length of any individual array part of a two-dimensional array, you will use an expression such as this:
```java
table[0].length
```

## The For-Each Style for Loop

The second form of the **for** implements a "for-each" style loop. A for-each loop cycles through a collection of objects, such as an array, in strictly sequential fashion, from start to finish. Originally, Java did not offer a for-each style loop. However, with the release of JDK 5, the **for** loop was enhanced to provide this option. The for-each style of **for** is also referred to as the *enhanced* **for** loop.

The general form of the for-each style **for** is shown here:
```text
for (type itr-var : collection) statement-block
```

Here, *type* specifies the type, and *itr-var* specifies the name of an *iteration variable* that will receive the elements from a collection, one at a time, from beginning to end. The collection being cycled through is specified by *collection*. With each iteration of the loop, the next element in the collection is retrieved and stored in *itr-var*. The loop repeats until all elements in the collection have been obtained. Thus, when iterating over an array of size *N*, the enhanced **for** obtains the elements in the array in index order, from 0 to *N*-1.

Because the iteration variable receives values from the collection, *type* must be the same as (or compatible with) the elements stored in the collection. Thus, when iterating over arrays, *type* must be compatible with the element type of the array.

**Question: Aside from arrays, what other types of collections can the for-each style** for **loop cycle through?**
**Answer**: One of the most important uses of the for-each style **for** is to cycle through the contents of a collection defined by the Collections Framework. The Collections Framework is a set of classes that implement various data structures, such as lists, vectors, sets, and maps.

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

### Iterating Over Multidimensional Arrays

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

## Strings

In many other programming languages, a string is an array of characters. This is not the case in Java. In Java, strings are objects.

### Constructing Strings

Different ways you can construct a **String**:
* You can construct a **String** just like you construct any other type of object: `String str = new String("Hello")`
* You can also construct a **String** from another **String**. For example: `String str = new String("Hello")` and then `String str2 = new String(str)`
* Another easy way to create a **String** is shown here: `String str = "Java strings are powerful."`

### Operating on Strings

The **String** class contains several methods that operate on strings. Here are the general forms for a few:

Method Name | Method Operation
----------- | ---------------
`boolean equals(str)` | Returns true if the invoking string contains the same character sequence as *str*.
`int length()` | Obtains the length of a string.
`char charAt(index)` | Obtains the character at the index specified by *index*.
`int compareTo(str)` | Returns less than zero if the invoking string is less than *str*, greater than zero if the invoking string is greater than *str*, and zero if the strings are equal.
`int indexOf(str)` | Searches the invoking string for the substring specified by *str*. Returns the index of the first match or -1 on failure.
`int lastIndexOf(str)` | Searches the invoking string for the substring specified by *str*. Returns the index of the last match or -1 on failure.

Here is a program that demonstrate these methods:
```java
String str1 = "When it comes to Web programming, Java is #1.";
String str2 = new String(str1);
String str3 = "Java strings are powerful.";
int result, idx;
char ch;

System.out.println("Length of str1: " + str1.length());

// display str1, one char at a time.
for (int i = 0; i < str1.length(); i++)
    System.out.print(str1.charAt(i));
System.out.println();

if (str1.equals(str2))
    System.out.println("str1 equals str2");
else
    System.out.println("str1 does not equals str2");

if (str1.equals(str3))
    System.out.println("str1 equals str3");
else
    System.out.println("str1 does not equals str3");

result = str1.compareTo(str3);
if (result == 0)
    System.out.println("str1 and str3 are equal");
else if (result < 0)
    System.out.println("str1 is less than str3");
else
    System.out.println("str1 is greater than str3");

// assign a new string to str2
str2 = "One Two Three One";

idx = str2.indexOf("One");
System.out.println("Index of first occurrence of One: " + idx);
idx = str2.lastIndexOf("One");
System.out.println("Index of last occurrence of One: " + idx);
```

This program generates the following output:
```text
Length of str1: 45
When it comes to Web programming, Java is #1.
str1 equals str2
str1 does not equals str3
str1 is greater than str3
Index of first occurrence of One: 0
Index of last occurrence of One: 14
```

You can *concatenate* (join together) two strings using the + operator. For example:
```java
String str1 = "One";
String str2 = "Two";
String str3 = "Three";
String str4 = str1 + str2 + str3; // initializes str4 with the string: "OneTwoThree"
```

**Question: Why does** String **define the** equals() **method? Cant't I just use** == **?**
**Answer**: The **equals()** method compares the character sequences of two **String** objects for equality. Applying the **==** to two **String** references simply determines whether the two references refer to the same object.

### Strings Are Immutable

The content of a **String** object are immutable. That is, once created, the character sequence that makes up the string cannot be altered. It must be made clear, however, that **String** reference variables may, of course, change the object to which they refer. It is just that the content of a specific **String** object cannot be changed after it is created.

**Question: You say that once created,** String **objects are immutable. I understand that, from a practical point of view, this is not a serious restriction, but what if I want to create a string that can be changed?**
**Answer**: You're in luck. Java offers a class called **StringBuffer**, which creates string objects that can be changed. For example, in addition to the **charAt()** method, which obtains the character at a specific location, **StringBuffer** defines **setCharAt()**, which sets a character within the string. Java also supplies **StringBuilder**, which is related to **StringBuffer**, and also supports strings that can be changed. However, for most purposes you will want to use **String**, not **StringBuffer** or **StringBuilder**.

To fully understand why immutable strings are not a hindrance, we will use another of String's methods: **substring()**. The **substring()** method returns a new string that contains a specified portion of the invoking string. Because a new **String** object is manufactured that contains the substring, the original string is unaltered, and the rule of immutability remains intact. The form of **substring()** that we will be using is shown here:
```text
String substring(int startIndex, int endIndex)
```

Here, *startIndex* specifies the beginning index, and *endIndex* specifies the stopping point. Here is a program that demonstrates **substring()** ) and the principle of immutable strings:
```java
String orgstr = "Java makes the Web move.";

// construct a substring
String substr = orgstr.substring(5, 18); // This creates a new string that contains the desired substring

System.out.println("orgstr: " + orgstr); // orgstr: Java makes the Web move.
System.out.println("substr: " + substr); // substr: makes the Web
```

As you can see, the original string **orgstr** is unchanged, and **substr** contains the substring.

## Using Command-Line Arguments

A command-line argument is the information that directly follows the program's name on the command line when it is executed. To access the command--line arguments inside a Java program is quite easy-they are stored as strings in the **String** array passed to **main()**. For example, the following program displays all of the command-line arguments that it is called with:
```java
class CLDemo {
    public static void main(String args[]) {
        System.out.println("These are " + args.length + " command-line arguments.");
        System.out.println("They are: ");
        for (int i = 0; i < args.length; i++)
            System.out.println("arg[" + i + "]: " + args[i]);
    }
}
```

If the program above is executed like this: `java CLDemo one two three` you will see the following output:
```text
These are 3 command-line arguments.
They are: 
arg[0]: one
arg[1]: two
arg[2]: three
```

## The Bitwise Operators

Pending.

## The ? Operator (Ternary Operator)

The **?** operator is often used to replace **if-else** statements of this general form:
```text
if (condition)
    var = expression1;
else
    var = expression2;
```

The **?** is called a *ternary operator* because it requires three operands. It takes the general form:
```text
Exp1 ? exp2 : Exp3;
```

where *Exp1* is a **boolean** expression, and *Exp2* and *Exp3* are expressions of any type other than **void**. The type of *Exp2* and *Exp3* must be the same (or compatible), though.

The value of a **?** expression is determined like this: *Exp1* is evaluated. If it is true, then *Exp2* is evaluated and becomes the value of the entire **?** expression. If *Exp1* is false, then *Exp3* is evaluated and its value becomes the value of the expression.

Consider this **if-else** example:
```java
if (x < 0) y = 10;
else y = 20;
```

This is how this sequence can be rewritten using the **?** operator:
```java
y = (x < 0) ? 10 : 20;
```

[Chapter 6: A Closer Look at Methods and Classes](chapter-06-a-closer-look-at-methods-and-classes.md#chapter-6-a-closer-look-at-methods-and-classes)

[Back to Table of Contents](../README.md#table-of-contents)