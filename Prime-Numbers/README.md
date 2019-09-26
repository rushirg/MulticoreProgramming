## Find Prime Numbers between 1 to 10^8 Using 8 threads

### Summary
The program computes all the prime numbers between 1 to 10^8 by spawning 8 threads. 
we know that prime numbers do not occur uniformly hence it will not be efficient to equally divide the work among the other threads. Because there may come a case where one thread will finish early and the other thread still needs to process their work.
Also, we know that there are many prime numbers between a certain range and there are comparably less prime numbers in the higher range.
I have created a method calculatePrime() which performs the operation of calculation of prime numbers. I have used the [Sieve of Eratosthenes](https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes) algorithm to find the number of primes in a certain range. In the algorithm, we took the first prime number 2 and we set the flag to one to all its multiple. We then pick up the next unset element and set the flag to all its multiple. In the end, we will end up with only the primes number of that range.

I have created two [AtomicInteger](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html) variables named as varI and varJ for the two for loops in the calculatePrime() method. The varI is for outer loop which runs initialized with two and runs till the square root of 10^8. This square root approach will eliminate the rest half of the element to process as we know its multiple are present in the first half of the totalNumbersToSearch. The calculatePrime method is synchronized which ensures that only one thread will perform the operation at a time.  

### Installation
Make sure Java is installed on your system. 
if Java is not installed then use the following command lines for <b>Ubuntu</b>
1. ```apt update && apt upgrade -y```
2. ```apt install default-jre```
3. ```apt install default-jdk```

### Compile
Java should be installed on the system.
Compile:
```javac CalculatePrimeNumbers.java```

### Execute
Run:
```java CalculatePrimeNumbers```

### Performance Evaluation
I have compared the performance of similar implementation with a single thread and with eight threads. Following is the comparison of the execution time for a single thread and eight threads.

| Single Thread  | Eight Threads |
| ------------- | ------------- |
| 7.283 sec  | 4.565 sec  |

### System Configuration
Memory : 11.6 GiB
Processor : Intel® Core™ i7-7500U CPU @ 2.70GHz × 4 
OS type : 64-Bit
OS : Ubuntu 18.04.3 LTS
