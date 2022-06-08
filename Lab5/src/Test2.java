class Node {

    public int data;
    public Node next;

    public Node(int dd) {
        data = dd;
    }

    public void displayLink() // display ourself
    {
        System.out.print(data + " ");
    }
}

class MyQueue {

    private Node front;
    private Node rear;
    private int size;

    public MyQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (front == null);
    }

    public void enqueue(int dd) {
        Node added = new Node(dd);
        size++;
        if(isEmpty())
        {
            front = rear = added;
            return;
        }
        
        rear.next = added;
        rear = added;
        
    }

    public int peek() {
       
        if(isEmpty())
            return -999;
        
        return front.data;
        
    }

    public int dequeue() {
       
        if(isEmpty())
            return -999;
        size--;
        int data;
        if(front == rear)
        {
            data = front.data;
            front = rear = null;
            return data;
        }
        
        data = front.data;
        front = front.next;
        
        return data;
    }

    public int getSize(){
        return size;
    }
    
    public void clear(){
        front = rear = null;
    }
    
    public int sum()
    {
        if(isEmpty())
            return 0;
        int sum = 0;
        Node temp = front;
        
        while(temp != rear)
        {
            sum += temp.data;
            temp = temp.next;
        }
        
        return sum + rear.data;
    }
}

public class Test2 {
    
    public static int equalizeQueues(MyQueue q1, MyQueue q2, MyQueue q3){
       
        MyQueue temp = findMax(q1, q2, q3);
        if(temp == null)
        {
            return q1.sum();
        }
        
        while(temp != null)
        {
            temp.dequeue();
            if(q1.sum() == q2.sum() && q1.sum() == q3.sum() && q1.sum() != 0)
            {
                return q1.sum();
            }
            if(q1.sum() == q2.sum() && q1.sum() == q3.sum() && q1.sum() == 0)
            {
                return 0;
            }
            temp = findMax(q1, q2, q3);
        }
        
        return 0;
    }
    
    public static MyQueue findMax(MyQueue q1, MyQueue q2, MyQueue q3)
    {
        if(q1.sum() >= q2.sum() && q1.sum() >= q3.sum())
            return q1;
        if(q2.sum() >= q1.sum() && q2.sum() >= q3.sum())
            return q2;
        if(q3.sum() >= q2.sum() && q3.sum() >= q1.sum())
            return q3;
        return null;
    }
    
    public static void main(String[] args){
        MyQueue q1 = new MyQueue();
        MyQueue q2 = new MyQueue();
        MyQueue q3 = new MyQueue();

        q1.enqueue(3);
        q1.enqueue(2);
        q1.enqueue(1);
        q1.enqueue(1);
        q1.enqueue(1);
       
        q2.enqueue(4);
        q2.enqueue(3);
        q2.enqueue(2);
        
        q3.enqueue(1);
        q3.enqueue(2);
        q3.enqueue(3);
        q3.enqueue(1);
        
        System.out.print("Test Case #1: ");
        System.out.println(equalizeQueues(q1, q2, q3));
        
        q1.clear();
        q2.clear();
        q3.clear();
        
        q1.enqueue(3);
        q1.enqueue(2);
        q1.enqueue(1);
        q1.enqueue(1);
        q1.enqueue(1);
       
        q2.enqueue(4);
        q2.enqueue(3);
        q2.enqueue(2);
        
        q3.enqueue(1);
        q3.enqueue(1);
        q3.enqueue(4);
        q3.enqueue(1);
        
        System.out.print("Test Case #2: ");
        System.out.println(equalizeQueues(q1, q2, q3));         
    }
}

