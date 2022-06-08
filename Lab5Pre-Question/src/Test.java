
import java.util.Stack;

class Link {

    public char data;
    public Link next;

    public Link(char d) // constructor
    {
        data = d;
    }

    public void displayLink() // display this link
    {
        System.out.print(data + " ");
    }
}
////////////////////////////////////////////////////////////////

class MyStack {

    Link head;

    public MyStack() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void push(char num) {

        Link added = new Link(num);

        if (isEmpty()) {
            head = added;
            return;
        }

        Link temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = added;

    }

    public char pop() {

        if (isEmpty()) {
            return '$';
        }

        if (head.next == null) {
            char data = head.data;
            head = null;
            return data;
        }

        Link temp = head;

        while (temp.next.next != null) {
            temp = temp.next;
        }

        char data = temp.next.data;
        temp.next = null;

        return data;

    }

    public char peek() {

        if (isEmpty()) {
            return '$';
        }

        if (head.next == null) {
            char data = head.data;
            return data;
        }

        Link temp = head;

        while (temp.next.next != null) {
            temp = temp.next;
        }

        char data = temp.next.data;

        return data;
    }
}
////////////////////////////////////////////////////////////////
// Queue Node

class Node {

    public String data;
    public Node next;

    public Node(String d) // constructor
    {
        data = d;
    }

    public void displayLink() // display this link
    {
        System.out.print(data + " ");
    }
}
////////////////////////////////////////////////////////////////

class MyQueue {

    Node head;

    public MyQueue() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(String string) {

        Node added = new Node(string);

        if (isEmpty()) {
            head = added;
            return;
        }

        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = added;
    }
// -------------------------------------------------------------

    public String dequeue() {
        if (isEmpty()) {
            return "$";
        }

        if (head.next == null) {
            String data = head.data;
            head = null;
            return data;
        }
        String data = head.data;
        head = head.next;

        return data;

    }

    public String peek() {

        if (isEmpty()) {
            return "$";
        }

        return head.data;
    }

    public int size() {
        int size = 0;
        if (isEmpty()) {
            return size;
        }
        if (head.next == null) {
            return 1;
        }

        Node temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }

        return size;
    }

    public void clear() {

        head = null;
    }
}

class QueueWithTwoStack {
    // implement your queue with using two stack

    MyStack s1, s2;

    QueueWithTwoStack() {
        s1 = new MyStack();
        s2 = new MyStack();
    }

    public boolean isEmpty() {
        return s2.isEmpty();
    }

    public void enqueue(char num) {
        s1.push(num);
    }
// -------------------------------------------------------------

    public char dequeue() {
        shiftStacks();
        return s2.pop();
    }

    public char peek() {
        shiftStacks();
        return s2.pop();
    }

    private void shiftStacks() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
    }
}
////////////////////////////////////////////////////////////////

class StackQueueApp {

    public static void allCombinations(String str) {

        int n = str.length();

        MyQueue queue = new MyQueue();

        for (int i = 1; i <= n; i++) {
            setAllKLength(str.toCharArray(), i);
        }

    }

    static void setAllKLength(char[] set, int k) {
        int n = set.length;
        setAllKLengthRec(set, "", n, k);
    }

    static void setAllKLengthRec(char[] set,
            String prefix,
            int n, int k) {

        if (k == 0) {
            System.out.println(prefix);
            return;
        }

        for (int i = 0; i < n; ++i) {

            String newPrefix = prefix + set[i];

            setAllKLengthRec(set, newPrefix,n, k - 1);
        }
    }

    public static String reverseString(String str) {
        MyStack stack = new MyStack();
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }
        String reversedS = "";
        while (!stack.isEmpty()) {
            reversedS += stack.pop();
        }

        return reversedS;
    }

    private static boolean isOperand(char c) {
        switch (c) {
            case '+':
            case '-':
            case '/':
            case '*':
                return true;
            default:
                return false;
        }
    }

    public static String prefixToInfix(String exp) {

        Stack<String> stackString = new Stack<>();

        int len = exp.length();

        for (int i = len - 1; i >= 0; i--) {

            char c = exp.charAt(i);

            if (isOperand(c)) {

                String operand1 = stackString.pop();
                String operand2 = stackString.pop();

                String temp = "(" + operand1 + c + operand2 + ")";
                stackString.push(temp);
            } else {
                stackString.push(c + "");
            }
        }

        return stackString.pop();
        // Use a stack to convert a prefix expression into Infix
        /*
            Prefix : An expression is called the prefix expression if the
            operator appears in the expression before the operands.
            Simply of the form (operator operand1 operand2).

            Example : *AB -> means that (A*B) in infix
            Example : *+AB-CD -> (Infix : (A+B) * (C-D) )

         */
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push('a');
        stack.push('b');
        stack.push('c');
        stack.push('d');

        System.out.println(stack.peek() + " " + stack.pop() + " " + stack.pop() + " " + stack.pop() + " " + stack.pop() + " " + stack.pop());

        MyQueue queue = new MyQueue();
        queue.enqueue("Ali");
        queue.enqueue("Veli");
        queue.enqueue("Mehmet");

        System.out.println(queue.peek() + " " + queue.dequeue() + " " + queue.size() + " " + queue.dequeue() + " " + queue.dequeue() + " " + queue.dequeue());

        QueueWithTwoStack qs = new QueueWithTwoStack();
        qs.enqueue('a');
        qs.enqueue('b');
        qs.enqueue('c');
        qs.enqueue('d');
        qs.enqueue('e');
        qs.enqueue('f');
        qs.enqueue('g');
        System.out.println(qs.peek() + " " + qs.dequeue() + " " + qs.dequeue() + " " + qs.dequeue() + " " + qs.dequeue() + " " + qs.dequeue());
        qs.enqueue('h');
        System.out.println(qs.peek() + " " + qs.dequeue() + " " + qs.dequeue());

        System.out.println(reverseString("Istanbul"));

        System.out.println(prefixToInfix("*-A/BC-/AKL"));

        allCombinations("abc");
    }

}
///
