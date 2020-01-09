# Chapter 3: Program Control Statements

## Table of Contents
* [The switch Statement](#the-switch-statement)
  * [Nested switch Statements](#nested-switch-statements)
* [The for Loop](#the-for-loop)
  * [Some Variations on the for Loop](#some-variations-on-the-for-loop)
  * [Missing Pieces](#missing-pieces)
    * [The Infinite Loop](#the-infinite-loop)
  * [Loops with No Body](#loops-with-no-body)
* [The do-while Loop](#the-do-while-loop)
* [Use break to Exit a Loop](#use-break-to-exit-a-loop)
* [Use break as a Form of goto](#use-break-as-a-form-of-goto)
* [Use continue](#use-continue)

## The switch Statement

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

### Nested switch Statements

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

## The for Loop

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

### Some Variations on the for Loop

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

### Missing Pieces

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

#### The Infinite Loop

You can create an *infinite loop* (a loop that never terminates) using the **for** by leaving the conditional expression empty. Hint: To halt a loop of this type you use the **break** statement.
```java
for (;;) // intentionally infinite loop
{
    // ...
}
```

### Loops with No Body

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

## The do-while Loop

The last of Java's loops is the **do-while**. Unlike the **for** and the **while** loops, in which the condition is tested at the top of the loop, the **do-while** loop checks its condition at the bottom of the loop. This means that a **do-while** loop will always execute at least once. The general form of the **do-while** loop is:
```text
do {
    statements;
} while(condition);
```

Although the braces are not necessary when only one statement is present, they are often used to improve readability of the **do-while** construct, thus preventing confusion with the **while**. The **do-while** loop executes as long as the conditional expression is true.

**Question: Given the flexibility inherent in all of Java's loops, what criteria should I use when selecting a loop? That is, how do I choose the right loop for a specific job?**
**Answer**:
* Use a **for** loop when performing a known number of iterations.
* Use the **do-while** when you need a loop that will always perform at least one iteration.
* The **while** is best used when the loop will repeat an unknown number of times.

## Use break to Exit a Loop

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


## Use break as a Form of goto

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

## Use continue

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

[Chapter 4: Introducing Classes, Objects, and Methods](chapter-04-introducing-classes-objects-and-methods.md)

[Back to Table of Contents](../README.md)