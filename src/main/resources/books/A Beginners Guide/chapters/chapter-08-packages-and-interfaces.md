# Chapter 8: Packages and Interfaces

## Table of Contents
* [Packages](#packages)
  * [Defining a Package](#defining-a-package)
  * [Finding Packages and CLASSPATH](#finding-packages-and-classpath)
  * [A short Package Example](#a-short-package-example)
* [Packages and Member Access](#packages-and-member-access)
  * [A Package Access Example](#a-package-access-example)
* [Understanding Protected Members](#understanding-protected-members)
* [Importing Packages](#importing-packages)
* [Java's Class Library Is Contained in Packages](#javas-class-library-is-contained-in-packages)
* [Interfaces](#interfaces)
* [Implementing Interfaces](#implementing-interfaces)
* [Using Interface References](#using-interface-references)
* [Creating a Queue Interface](#creating-a-queue-interface)
* [Variables in Interfaces](#variables-in-interfaces)
* [Interfaces Can Be Extended](#interfaces-can-be-extended)
* [Default Interface Methods](#default-interface-methods)
  * [Default Method Fundamentals](#default-method-fundamentals)
  * [A More Practical Example of a Default Method](#a-more-practical-example-of-a-default-method)
  * [Multiple Inheritance Issues](#multiple-inheritance-issues)
* [Use static Methods in an Interface](#use-static-methods-in-an-interface)
* [Final Thoughts on Packages and Interfaces](#final-thoughts-on-packages-and-interfaces)

## Packages

*Packages* are groups of related classes. Packages help organize your code and provide another layer of encapsulation. A package serves two purposes. First, it provides a mechanism by which related pieces of a program can be organized as a unit. Classes defined within a package must be accessed through their package name. Thus, a package provides a way to name a collection of classes. Second, a package participates in Java's access control mechanism. Classes defined within a package can be made private to that package and not accessible by code outside the package. Thus, the package provides a means by which classes can be encapsulated.

In general, when you name a class, you are allocating a name from the *namespace*. A namespace defines a declarative region. In Java, no two classes can use the same name from the same namespace. Thus, within a given namespace, each class name must be unique. The examples shown in the preceding chapters have all used the default (global) namespace. While this is fine for short sample programs, it becomes a problem as programs grow and the default namespace becomes crowded. In large programs, finding unique names for each class can be difficult. Furthermore, you must avoid name collisions with code created by other programmers working on the same project, and with Java's library. The solution to these problems is the package because it gives you a way to partition the namespace. When a class is defined within a package, the name of that package is attached to each class, thus avoiding name collisions with other classes that have the same name, but are in other packages.

### Defining a Package

All classes in Java belong to some package. When no **package** statement is specified, the default (global) package is used. Furthermore, the default package has no name, which makes the default package transparent. To create a package, put a **package** command at the top of a Java source file. This is the general form of the **package** statement:
```text
package pkg;
```

Here, *pkg* is the name of the package. For example, the following statement creates a package called **mypack**:
```java
package mypack;
```

Java uses the file system to manage packages, with each package stored in its own directory. For example, the **.class** files for any classes you declare to be part of **mypack** must be stored in a directory called **mypack**.

Like the rest of Java, package names are case sensitive. This means that the directory in which a package is stored must be precisely the same as the package name. More than one file can include the same **package** statement.
 
You can create a hierarchy of packages. To do so, simply separate each package name from the one above it by use of a period. The general form of a multileveled package statement is shown here: `package pack1.pack2.pack3...packN;`

Of course, you must create directories that support the package hierarchy that you create. For example: `package alpha.beta.gamma;` must be stored in .../alpha/beta/gamma, where ... specifies the path to the specified directories.

### Finding Packages and CLASSPATH

As just explained, packages are mirrored by directories. This raises an important question: How does the Java run-time system know where to look for packages that you create? The answer has three parts. First, by default, the Java run-time system uses the current working directory as its starting point. Thus, if your package is in a subdirectory of the current directory, it will be found. Second, you can specify a directory path or paths by setting the **CLASSPATH** environmental variable. Third, you can use the **-classpath** option with **java** and **javac** to specify the path to your classes.

For example, assuming the following package specification: `package mypack`. In order for a program to find **mypack**, one of three things must be true:
1. The program can be executed from a directory immediately above **mypack**
2. **CLASSPATH** must be set to include the path to **mypack**
3. The **-classpath** option must specify the path to **mypack** when the program is run via **java**.

The easiest way to try the examples shown in this book is to simply create the package directories below your current development directory, put the **.class** files into the appropriate directories, and then execute the programs from the development directory. This is the approach used by the following examples.

One last point: To avoid problems, it is best to keep all **.java** and **.class** files associated with a package in that package's directory. Also, compile each file from the directory above the package directory.

### A short Package Example

Keeping the preceding discussion in mind, try this short package example. It creates a simple book database that is contained within a package called **bookpack**.
```java
package bookpack; // This file is part of the bookpack package

class Book { // Thus, Book is part of bookpack
    private String title;
    private String author;
    private int pubDate;

    Book(String t, String a, int d) {
        title = t;
        author = a;
        pubDate = d;
    }

    void show() {
        System.out.println(title);
        System.out.println(author);
        System.out.println(pubDate);
        System.out.println();
    }
}
class BookDemo { // BookDemo is also part of bookpack
    public static void main(String[] args) {
        Book books[] = new Book[5];

        books[0] =  new Book("Java: A Beginner's Guide", "Schildt", 2014);
        books[1] =  new Book("Java: The Complete Reference", "Schildt", 2014);
        books[2] =  new Book("The Art of Java", "Schildt and Holmes", 2003);
        books[3] =  new Book("Red Storm Rising", "Clancy", 1986);
        books[4] =  new Book("On the Road", "Kerouac", 1955);

        for (Book book : books) book.show();
    }
}
```

Call this file **BookDemo.java** and put it in a directory called **bookpack**. Next, compile the file. You can do this by specifying `javac bookpack/BookDemo.java` from the directory directly above **bookpack**. Then try executing the class, using the following command line: `java bookpack.BookDemo`

Remember, you will need to be in the directory above **bookpack** when you execute this command. (Or, use one of the other two options described in the preceding section to specify the path to **bookpack**.)

As explained, **BookDemo** and **Book** are now part of the package **bookpack**. This means that **BookDemo** cannot be executed by itself. That is, you cannot use this command line: `java BookDemo`. Instead, **BookDemo** must be qualified with its package name.

## Packages and Member Access

The preceding chapters have introduced the fundamentals of access control, including the **private** and **public** modifiers, but they have not told the entire story. The reason for this is that packages also participate in Java's access control mechanism, and a complete discussion had to wait until packages were covered.

The visibility of an element is determined by its access specification-**private**, **public**, **protected**, or default—and the package in which it resides. Thus, the visibility of an element is determined by its visibility within a class and its visibility within a package. This multilayered approach to access control supports a rich assortment of access privileges. Let's examine each access option individually.

Access | Private Member | Default Member | Protected Member | Public Member
------ | -------------- | -------------- | ---------------- | -------------
**Visible within <br/>same class** | Yes | Yes | Yes | Yes
**Visible within <br/>same package <br/>by subclass** | No | Yes | Yes | Yes
**Visible within <br/>same package <br/>by non-subclass** | No | Yes | Yes | Yes
**Visible within <br/>different package <br/>by subclass** | No | No | Yes | Yes
**Visible within <br/>different package <br/>by non-subclass** | No | No | No | Yes

If a member of a class has no explicit access modifier, then it is visible within its package but not outside its package. Therefore, you will use the default access specification for elements that you want to keep private to a package but public within that package.

Members explicitly declared **public** are visible everywhere, including different classes and different packages. There is no restriction on their use or access. A **private** member is accessible only to the other members of its class. A **private** member is unaffected by its membership in a package. A member specified as **protected** is accessible within its package and to all subclasses, including subclasses in other packages.

The table above applies only to members of classes. A top-level class has only two possible access levels: default and public. When a class is declared as **public**, it is accessible by any other code. If a class has default access, it can be accessed only by other code within its same package. Also, a class that is declared **public** must reside in a file by the same name.

### A Package Access Example

In the **package** example shown earlier, both **Book** and **BookDemo** were in the same package, so there was no problem with **BookDemo** using **Book** because the default access privilege grants all members of the same package access. However, if **Book** were in one package and **BookDemo** were in another, the situation would be different. In this case, access to **Book** would be denied. To make **Book** available to other packages, you must make three changes. First, **Book** needs to be declared **public**. This makes **Book** visible outside of **bookpack**. Second, its constructor must be made **public**, and finally, its **show()** method needs to be **public**. This allows them to be visible outside of **bookpack**, too. Thus, to make **Book** usable by other packages, it must be recoded as shown here:
```java
package bookpack;

public class Book { // Book and its members must be public in order to be used by other packages
    private String title;
    private String author;
    private int pubDate;

    // Now public
    public Book(String t, String a, int d) {
        title = t;
        author = a;
        pubDate = d;
    }

    // Now public
    public void show() {
        System.out.println(title);
        System.out.println(author);
        System.out.println(pubDate);
        System.out.println();
    }
}
```

To use **Book** from another package, either you must use the **import** statement described in the next section, or you must fully qualify its name to include its full package specification. For example, here is a class called **UseBook**, which is contained in the **bookpackext** package. It fully qualifies **Book** in order to use it.
```java
package bookpackext;

class UseBook {
    public static void main(String[] args) {
        bookpack.Book books[] = new bookpack.Book[5]; // Qualify Book with its package name: bookpack

        books[0] =  new bookpack.Book("Java: A Beginner's Guide", "Schildt", 2014);
        books[1] =  new bookpack.Book("Java: The Complete Reference", "Schildt", 2014);
        books[2] =  new bookpack.Book("The Art of Java", "Schildt and Holmes", 2003);
        books[3] =  new bookpack.Book("Red Storm Rising", "Clancy", 1986);
        books[4] =  new bookpack.Book("On the Road", "Kerouac", 1955);

        for (bookpack.Book book : books) book.show();
    }
}
```

Notice how every use of **Book** is preceded with the **bookpack** qualifier. Without this specification, Book would not be found when you tried to compile **UseBook**.

To compile and run the example above, execute these commands from the directory directly above **bookpackext**:
```text
javac bookpack/Book.java
javac bookpackext/UseBook.java
java bookpackext.UseBook
```

## Understanding Protected Members

Newcomers to Java are sometimes confused by the meaning and use of **protected**. As explained, the **protected** modifier creates a member that is accessible within its package and to subclasses in other packages. Thus, a **protected** member is available for all subclasses to use but is still protected from arbitrary access by code outside its package.

To better understand the effects of **protected**, let's work through an example. First, change the **Book** class so that its instance variables are **protected**, as shown here:
```java
package bookpack;

public class Book {
    // these are now protected
    protected String title;
    protected String author;
    protected int pubDate;

    public Book(String t, String a, int d) {
        title = t;
        author = a;
        pubDate = d;
    }

    public void show() {
        System.out.println(title);
        System.out.println(author);
        System.out.println(pubDate);
        System.out.println();
    }
}
```

Next, create a subclass of **Book**, called **ExtBook**, and a class called **ProtectDemo** that uses **ExtBook**. **ExtBook** adds a field that stores the name of the publisher and several accessor methods. Both of these classes will be in their own package called **bookpackext**. They are shown here:
```java
package bookpackext;

class ExtBook extends bookpack.Book {
    private String publisher;

    public ExtBook(String t, String a, int d, String p) {
        super(t, a, d);
        publisher = p;
    }

    public void show() {
        super.show();
        System.out.println(publisher);
        System.out.println();
    }

    public String getPublisher() { return publisher; }
    public void setPublisher(String p) { publisher = p; }

    // These are OK because subclass can access a protected member
    public String getTitle() { return title; }
    public void setTitle(String t) { title = t; }
    public String getAuthor() { return author; }
    public void setAuthor(String a) { author = a; }
    public int getPubDate() { return pubDate; }
    public void setPubDate(int d) { pubDate = d; }
}
class ProtectDemo {
    public static void main(String[] args) {
        ExtBook books[] = new ExtBook[5];

        books[0] =  new ExtBook("Java: A Beginner's Guide", "Schildt", 2014, "McGraw-Hill Professional");
        books[1] =  new ExtBook("Java: The Complete Reference", "Schildt", 2014, "McGraw-Hill Professional");
        books[2] =  new ExtBook("The Art of Java", "Schildt and Holmes", 2003, "McGraw-Hill Professional");
        books[3] =  new ExtBook("Red Storm Rising", "Clancy", 1986, "Putnam");
        books[4] =  new ExtBook("On the Road", "Kerouac", 1955, "Viking");

        for (ExtBook book : books) book.show();

        // Find books by author
        System.out.println("Showing all books by Schildt.");
        for (ExtBook book : books)
            if (book.getAuthor().equals("Schildt"))
                System.out.println(book.getTitle());

//        books[0].title = "test title"; // Access to protected field not allowed by non-subclass
    }
}
```

Look first at the code inside **ExtBook**. Because **ExtBook** extends **Book**, it has access to the **protected** members of **Book**, even though **ExtBook** is in a different package. Thus, it can access **title**, **author**, and **pubDate** directly, as it does in the accessor methods it creates for those variables. However, in **ProtectDemo**, access to these variables is denied because **ProtectDemo** is not a subclass of **Book**. For example, if you remove the comment symbol from the following line, the program will not compile.
```java
//        books[0].title = "test title"; // Access to protected field not allowed by non-subclass
```

**Question: I know that C++ also includes an access specifier called** protected. **Is it similar to Java's?**
**Answer**: Similar, but not the same. In C++, **protected** creates a member that can be accessed by subclasses but is otherwise private. In Java, **protected** creates a member that can be accessed by any code within its package but only by subclasses outside of its package.

## Importing Packages

When you use a class from another package, you can fully qualify the name of the class with the name of its package, as the preceding examples have done. However, it should come as no surprise that a more convenient method exists for using the contents of packages: the **import** statement. Using **import** you can bring one or more members of a package into view. This allows you to use those members directly, without explicit package qualification. Here is the general form of the **import** statement:
```text
import pkg.classname;
```

Here, *pkg* is the name of the package, which can include its full path, and *classname* is the name of the class being imported. If you want to import the entire contents of a package, use an asterisk (&#42;) for the class name. Here are examples of both forms:
```java
import mypack.MyClass;
import mypack.*; 
```

In the first case, the **MyClass** class is imported from **mypack**. In the second, all of the classes in **mypack** are imported. In a Java source file, **import** statements occur immediately following the **package** statement (if it exists) and before any class definitions.

You can use **import** to bring the **bookpack** package into view so that the **Book** class can be used without qualification. To do so, simply add this **import** statement to the top of any file that uses **Book**: `import bookpack.*;`. For example, here is the **UseBook** class recoded to use **import**:
```java
package bookpackext;
import bookpack.*; // Import bookpack

class UseBook {
    public static void main(String[] args) {
        Book books[] = new Book[5]; // Now, you can refer to Book directly, without qualification

        books[0] =  new Book("Java: A Beginner's Guide", "Schildt", 2014);
        books[1] =  new Book("Java: The Complete Reference", "Schildt", 2014);
        books[2] =  new Book("The Art of Java", "Schildt and Holmes", 2003);
        books[3] =  new Book("Red Storm Rising", "Clancy", 1986);
        books[4] =  new Book("On the Road", "Kerouac", 1955);

        for (Book book : books) book.show();
    }
}
```

Notice that you no longer need to qualify **Book** with its package name.

## Java's Class Library Is Contained in Packages

As explained earlier in this book, Java defines a large number of standard classes that are available to all programs. This class library is often referred to as the Java API (Application Programming Interface). The Java API is stored in packages. At the top of the package hierarchy is **java**. Descending from **java** are several subpackages. Here are a few examples:

Subpackage | Description
---------- | -----------
java.lang | Contains a large number of general-purpose classes
java.io | Contains I/O classes
java.net | Contains classes that support networking
java.applet | Contains classes for creating applets
java.awt | Contains classes that support the Abstract Window Toolkit

Since the beginning of this book, you have been using **java.lang**. It contains, among several others, the **System** class, which you have been using when performing output using **println()**. The **java.lang** package is unique because it is imported automatically into every Java program. This is why you did not have to import **java.lang** in the preceding sample programs. However, you must explicitly import the other packages.

## Interfaces

An *interface* defines a set of methods that will be implemented by a class. Thus, an interface gives you a way to specify what a class will do, but not how it will do it.

Pending.

## Implementing Interfaces

Pending.

## Using Interface References

Pending.

## Creating a Queue Interface

Pending.

## Variables in Interfaces

Pending.

## Interfaces Can Be Extended

Pending.

## Default Interface Methods

Pending.

### Default Method Fundamentals

Pending.

### A More Practical Example of a Default Method

Pending.

### Multiple Inheritance Issues

Pending.

## Use static Methods in an Interface

Pending.

## Final Thoughts on Packages and Interfaces

Pending.