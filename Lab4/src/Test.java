
class Link {

    public int data;
    public Link next;
    public Link previous;

    public Link(int d) {
        data = d;
    }

    public void displayLink() {
        System.out.print(data + " ");
    }

}

////////////////////////////////////////////////////////////////
class DoublyLinkedList {

    private Link head;
    private Link tail;

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }


    public void insertLast(int dd) {
        
        Link added = new Link(dd);
        if(isEmpty())
        {
             head = tail = added;
             added.next = null;
             added.previous = null;
             return;
        }
        
        tail.next = added;
        added.previous = tail;
        added.next = null;
        tail = added;
    }


    public Link deleteFirst() {
        
        if(isEmpty())
        {
            return null;
        }
        
        if(head == tail)
        {
            Link temp = head;
            head = tail = null;
            return temp;
        }
        
        Link temp = head;
        
        head = head.next;
        head.previous = null;
        return temp;
    }


    public void displayBackward() {
        
        if(isEmpty())
            return;
        
        Link temp = tail;
        
        while(temp != null)
        {
            temp.displayLink();
            temp = temp.previous;
        }
        System.out.println("");
        
    }
    
    public void sortedInsert(int dd) {
        
        Link added = new Link(dd);
        
        if(isEmpty())
        {
             head = tail = added;
             added.next = null;
             added.previous = null;
             return;
        }
        
        Link temp = head;
        
        while(temp != null && temp.data < added.data)
        {
            temp = temp.next;
        }
        
        if(temp == null)
        {
            insertLast(dd);
            return;
        }
        
        if(temp == head)
        {
            added.next = head;
            head.previous = added;
            head = added;
            return;
        }
        
        temp.previous.next = added;
        added.previous = temp.previous;
        temp.previous = added;
        added.next = temp;
        
    }
    
    public void displayForward() {
        System.out.print("List (head-->tail): ");
        Link current = head;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }
    
    public void deleteDuplicateValues() {
        
        if(isEmpty())
            return;
        
        Link temp = head;
        
        while(temp != null)
        {
            if(temp.next != null)
            {
                if(temp.data == temp.next.data)
                {
                    if(temp.next.next != null)
                    {
                        temp.next.next.previous = temp;
                        temp.next = temp.next.next;
                    }
                    else{
                        temp.next = null;
                    }
                    continue;
                }
            }
            
            temp = temp.next;
        }
        
    }
    
    
}
////////////////////////////////////////////////////////////////
public class Test {

    public static void main(String[] args) {
        DoublyLinkedList theList = new DoublyLinkedList();

        theList.insertLast(22);
        theList.displayBackward();
        theList.insertLast(44);
        theList.displayBackward();
        theList.insertLast(66);
        theList.displayBackward();


        theList.deleteFirst();
        theList.displayBackward();
        theList.deleteFirst();
        theList.displayBackward();
        theList.deleteFirst();
        theList.displayBackward();

        
        theList.sortedInsert(8);
        theList.displayForward();
        theList.sortedInsert(6);
        theList.displayForward();
        theList.sortedInsert(2);
        theList.displayForward();
        theList.sortedInsert(4);
        theList.displayForward();
        theList.sortedInsert(5);
        theList.displayForward();
        theList.sortedInsert(9);
        theList.displayForward();
        theList.sortedInsert(9);
        theList.displayForward();
        theList.sortedInsert(5);
        theList.displayForward();
        theList.sortedInsert(4);
        theList.displayForward();
        theList.sortedInsert(4);
        theList.displayForward();
        theList.sortedInsert(9);
        theList.displayForward();
        
        theList.deleteDuplicateValues();
        theList.displayForward();
      
    }
}