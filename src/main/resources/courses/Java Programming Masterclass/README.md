# Java Programming Masterclass

## Section 2 - Software Tools Setup

### Configuring IntelliJ IDEA

Check the following inputs on `Preferences > Editor > General > Auto Import`:
- Add unambiguous imports on the fly
- Optimize imports on the fly

Uncheck the following inputs on `Preferences > Editor > General > Code Folding`:
- Imports
- One-line methods
- "Closures" (anonymous classes implementing one method, before Java 8)
- Generic constructor and method parameters

## Section 3 - First Steps

### Primitive Types

#### Overflow and Underflow

Overflow and underflow happen when we assign a value that is out of range of the declared data type of the variable.

If the (absolute) value is too big, we call it overflow, if the value is too small, we call it underflow.

Example:

```java
int myMinIntValue = Integer.MIN_VALUE;
int myMaxIntValue = Integer.MAX_VALUE;
System.out.println("Busted MAX value = " + (myMaxIntValue + 1)); // Overflow, it prints: Busted MAX value = -2147483648
System.out.println("Busted MIN value = " + (myMinIntValue - 1)); // Underflow, it prints: Busted MIN value = 2147483647
```

You can use `BigInteger` or `BigDecimal` to avoid overflow and underflow.

#### BigDecimal

For precise calculations use `BigDecimal` type.

#### Unicode

To represent Unicode `char`, you use `\u` followed by the hexadecimal value. You can do the same using `String`:

```java
char myCopyrightChar = '\u00A9';
String myCopyrightString = " \u00A9 2022";
```


