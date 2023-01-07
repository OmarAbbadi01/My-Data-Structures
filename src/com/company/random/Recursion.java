package com.company.random;

import java.util.Queue;
import java.util.Stack;

public class Recursion {

    public static int fib(int n) {
        if (n == 0 || n == 1)
            return n;
        return fib(n - 1) + fib(n - 2);
    }

    public static double myPow(double x, int n) {
        if (n < 0)
            return 1.0 / myPow(x, n * -1);
        if (n == 0)
            return 1;
        return x * myPow(x, n - 1);
    }

    public static void reverseArray(int[] arr, int i) {
        if (i < arr.length / 2) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
            reverseArray(arr, ++i);
        }
    }

    // recursion output1:
    public static void fun1(int n) {
        if (n <= 0) {
            System.out.println("end");
            return;
        }
        if (n % 2 == 0) {
            fun1(n - 1);
            System.out.println(n + " even");
        } else {
            System.out.println(n + " odd");
            fun1(n - 3);
        }
    }

    public static int numOfMethods(int s) {
        if (s == 1 || s == 2)
            return s;
        else
            return numOfMethods(s - 1) + numOfMethods(s - 2);
    }

    // output when n = 10
    public static void fun(int n) {
        if (n == 0)
            return;
        fun(n / 2);
        System.out.println(n % 2);
    }

    // Q3
    public static Integer bottomOfStack(Stack<Integer> stk) {
        if (stk.size() == 1)
            return stk.pop();
        Integer temp = stk.pop();
        Integer toReturn = bottomOfStack(stk);
        stk.push(temp);
        return toReturn;
    }

    // Q4
    public static void reverseQueue(Queue<Integer> q) {
        if (q.isEmpty())
            return;
        else {
            Integer temp = q.poll();
            reverseQueue(q);
            q.add(temp);
        }
    }

}
