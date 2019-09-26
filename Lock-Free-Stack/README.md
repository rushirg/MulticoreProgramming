


# Programming Assignment 1 - Problem 2

## Lock-free stack operations

### Summary
The  problem was given a concurrent stack with locks, we have to perform the stack operations such as push and pop without using locks.  This can be achieved by using atomic variables and their atomic operations(like CAS). I have used [AtomicReference](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/atomic/AtomicReference.html)  class in Java to perform the operations.

### Installation
Make sure Java is installed on your system. 
if Java is not installed then use the following command lines for <b>Ubuntu<b>
1. ```apt update && apt upgrade -y```
2. ```apt install default-jre```
3. ```apt install default-jdk```

### Compile
Java should be installed on the system.
Compile:
```javac LinkedlistAsStack.java```

### Execute
Run:
```java LinkedlistAsStack```

