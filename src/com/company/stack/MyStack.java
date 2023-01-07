package com.company.stack;

import java.util.*;

public class MyStack<E> {

    private LinkedList<E> list;

    public MyStack() {
        list = new LinkedList<>();
    }

    public void push(E e) {
        list.addLast(e);
    }

    public E pop() {
        return list.removeLast();
    }

    public E peek() {
        return list.getLast();
    }

    public void clear() {
        list = new LinkedList<>();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(E element) {
        return list.contains(element);
    }

    public int size() {
        return list.size();
    }

    public void addToStackByIndex(int index, E value) {
        if (index > list.size() || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        if (list.size() == index) {
            push(value);
        } else {
            E temp = this.pop();
            addToStackByIndex(index, value);
            this.push(temp);
        }
    }

    public E removeFromStackByIndex(int index) {
        if (index >= list.size() || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        if (index == list.size() - 1) {
            return this.pop();
        } else {
            E temp = this.pop();
            E toReturn = removeFromStackByIndex(index);
            this.push(temp);
            return toReturn;
        }
    }

    @Override
    public String toString() {
        return list.toString();
    }

    // Exercises
    public static int[] nextGreaterElement(int[] array) {
        int[] answer = new int[array.length];
        Stack<Integer> stk = new Stack<>();
        int current;
        for (int i = array.length - 1; i >= 0; i--) {
            current = array[i];
            while (!stk.isEmpty() && current >= stk.peek()) {
                stk.pop();
            }
            if (stk.isEmpty())
                answer[i] = -1;
            else
                answer[i] = stk.peek();
            stk.push(current);
        }
        return answer;
    }

    public static int[] nextGreaterElement2(int[] array) {
        int[] answer = new int[array.length];
        for (int i = 0; i < array.length - 1; i++) {
            answer[i] = -1;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] > array[i]) {
                    answer[i] = array[j];
                    break;
                }
            }
        }
        answer[answer.length - 1] = -1;
        return answer;
    }

    public static String removeAdjacent(String s) {
        String answer = "";
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stk.isEmpty() && Math.abs(s.charAt(i) - stk.peek()) == 32) {
                stk.pop();
            } else {
                stk.push(s.charAt(i));
            }
        }
        while (!stk.isEmpty()) {
            answer = stk.pop() + answer;
        }
        return answer;
    }

    public static String infixToPostfix(String s) {
        String answer = "";
        Character[] array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                'V', 'W', 'X', 'Y', 'Z'};
        List<Character> chars = new ArrayList<>(Arrays.asList(array));
        Stack<Character> stk = new Stack<>();
        char temp;
        for (int i = 0; i < s.length(); i++) {
            temp = s.charAt(i);
            // Case1: temp is a normal char (A to Z) we add it to
            // the string answer
            if (chars.contains(temp)) {
                answer += temp;
            }
            // Case2: the stack is empty or the last char in it is a '('
            // we can add whatever operator in this case.
            else if (stk.isEmpty() || temp == '(') {
                stk.push(temp);
            }
            // Case3: temp is a ')'
            // we must remove all operators in the stack to the answer
            // until we reach a '(' then we remove the '('.
            else if (temp == ')') {
                while (stk.peek() != '(')
                    answer += stk.pop();
                // to remove the '('
                stk.pop();
            }
            // Case4: temp is a '^'
            // we can push temp if the last operator in the stack IS NOT a '^'
            else if (temp == '^') {
                if (stk.peek() == '^')
                    answer += stk.pop();
                stk.push(temp);
            }
            // Case5: temp is a '*' or a '/'
            // we can push temp only if the last element in the
            // stack is a '+' or '-' or '(' [lower priority]
            // or if the stack is empty.
            else if (temp == '*' || temp == '/') {
                // this loop requires that the last element in
                // the stack is a lower priority than '*' and '/'
                // or the stack is empty so we can push temp.
                while (!stk.isEmpty() && stk.peek() != '+'
                        && stk.peek() != '-'
                        && stk.peek() != '(') {
                    answer += stk.pop();
                }
                stk.push(temp);
            }
            // Case6: temp is a '+' or a '-'
            // we can push temp only if the stack is empty or above a '('.
            else if (temp == '+' || temp == '-') {
                // this loop requires that the stack is empty
                // or the last operator in it is a '(' so we can push temp.
                while (!stk.isEmpty() && stk.peek() != '(')
                    answer += stk.pop();
                stk.push(temp);
            }
        }
        // after looping over the whole string s
        // we must empty the operators left in the stack
        // to the end of the string answer one by one respectively.
        while (!stk.isEmpty())
            answer += stk.pop();
        // END
        return answer;
    }

    public static boolean isValid(String s) {
        // Case1: there is odd parenthesis (not valid for sure)
        if (s.length() % 2 == 1)
            return false;
        // now I will loop over all characters in s
        // if the bracket is open I will add it to the stack
        // if not it should be the opposite of the last element in the stack.
        Stack<Character> stk = new Stack<>();
        char current, top;
        for (int i = 0; i < s.length(); i++) {
            current = s.charAt(i);
            // case A: current is an opening bracket
            // then add it to the stack
            if (current == '(' || current == '{' || current == '[') {
                stk.push(current);
            }
            // case B: current is a closing bracket
            // then compare it with the pop() of the stack
            // it should be the opposite.
            else {
                // if stack is empty this means that there is no opening brackets in it and now I am at a closing bracket
                // in other words, there is a closing brackets that does not have opening brackets
                // this leads to false.
                if (stk.isEmpty())
                    return false;
                top = stk.pop();
                if ((top == '[' && current != ']')
                        || (top == '(' && current != ')')
                        || (top == '{' && current != '}'))
                    return false;
            }
        }
        // By now the string will be valid if and only if the stack is empty.
        // if the stack is NOT empty this means that there were more opening brackets than closing ones
        // there for s is not valid.
        return stk.isEmpty();
    }


}
