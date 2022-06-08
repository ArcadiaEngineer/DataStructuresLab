
class MyDeque<T> {

    MyDoublyLinkedList<T> linkedList;

    public MyDeque() {
        linkedList = new MyDoublyLinkedList<>();
    }

    public void push(T data) {
        linkedList.addFirst(data);
    }

    public T pop() {
        return linkedList.removeFirst();
    }

    public void inject(T data) {
        linkedList.addEnd(data);
    }

    public T eject() {
        return linkedList.removeLast();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }

}

class MyDoublyLinkedList<T> {

    Node<T> head;
    Node<T> tail;

    public MyDoublyLinkedList() {
        head = null;
        tail = null;
    }

    class Node<T> {

        T data;
        Node previous;
        Node next;

        public Node(T data) {
            this.data = data;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(T data) {
        Node added = new Node(data);

        if (isEmpty()) {
            head = tail = added;
            head.previous = null;
            tail.next = null;
            return;
        }

        added.next = head;
        added.previous = null;
        head.previous = added;
        head = added;

    }

    public void addEnd(T data) {
        Node added = new Node(data);

        if (isEmpty()) {
            head = tail = added;
            head.previous = null;
            tail.next = null;
            return;
        }

        tail.next = added;
        added.previous = tail;
        added.next = null;
        tail = added;

    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (head == tail) {
            T data = tail.data;
            head = tail = null;
            return data;
        }

        T data = head.data;
        head.next.previous = null;
        head = head.next;

//        if (head.next != null) {
//            
//        }
        return data;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (head == tail) {
            T data = tail.data;
            head = tail = null;
            return data;
        }
        T data = tail.data;
        tail.previous.next = null;
        tail = tail.previous;

//        if (tail.previous != null) {
//            
//        }
        return data;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return null;
        }
        String result = "";
        Node<T> temp = head;
        while (temp != null) {
            result += temp.data.toString() + "\t";
            temp = temp.next;
        }
        return result;
    }

}

public class MyDequeTester {

    public static void check(Integer x, Integer y) {
        if (x.equals(y)) {
            System.out.println("Success!");
        } else {
            System.out.println("********* Failure: value was " + x);
        }
    }

    public static void main(String[] args) {

        Integer tmp;
        MyDeque<Integer> dq = new MyDeque<Integer>();

        System.out.println("Pushing 100");
        dq.push(100);

        System.out.println("Pushing 150");
        dq.push(150);

        System.out.println("Pushing 200");
        dq.push(200);

        System.out.println("The deque is currently: " + dq);

        System.out.println("Popping... Should be 200");

        tmp = dq.pop();
        check(tmp, 200);

        System.out.println("Ejecting... Should be 100");
        tmp = dq.eject();
        check(tmp, 100);

        System.out.println("Injecting 1000");
        dq.inject(1000);

        System.out.println("Ejecting... should be 1000");
        tmp = dq.eject();
        check(tmp, 1000);

        System.out.println("Ejecting... should be 150");
        tmp = dq.eject();
        check(tmp, 150);

        //Begin stress testing
        for (int i = 0; i < 250000; i++) {
            dq.push(i);
            dq.inject(-i);
        }
        System.out.println("Stress testing complete. If that didn't take too "
                + "long, your class is efficiently coded");
    }

}
