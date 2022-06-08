
class Link {

    public int data;
    public Link next;
    public Link previous;
// -------------------------------------------------------------

    public Link(int d) // constructor
    {
        data = d;
    }
// -------------------------------------------------------------

    public void displayLink() // display this link
    {
        System.out.print(data + " ");
    }
// -------------------------------------------------------------
} // end class Link
////////////////////////////////////////////////////////////////

class DoublyLinkedList {

    private Link head;
    private Link tail;
// -------------------------------------------------------------

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }
// -------------------------------------------------------------

    public boolean isEmpty() {
// Write a function which returns:
// -> True: if the list is Empty
// -> False: if the list is NOT Empty
        return head == null ? true : false;
    }
// -------------------------------------------------------------

    public void insertFirst(int num) {
// Write a function:
// -> Inserts the number to the beginning of the list
        if (head == null) {
            head = new Link(num);
            tail = head;
            return;
        }

        Link added = new Link(num);
        added.next = head;
        head.previous = added;
        head = added;

    }
// -------------------------------------------------------------

    public void insertToSpecificPos(int num) {
// Write a function:
// -> Inserts the number to the specific position of list
        if (head == null) {
            insertFirst(num);
            return;
        }
        Link tmp = head;
        int sequence = 0;
        while (tmp != null) {
            if (sequence == num) {
                break;
            }
            sequence++;
            tmp = tmp.next;
        }

        if (tmp != null) {
            if (tmp.previous != null) {
                Link added = new Link(num);

                added.next = tmp;
                tmp.previous.next = added;
                added.previous = tmp.previous;
                tmp.previous = added;

                return;
            }
            insertFirst(num);
            return;
        }
        if (sequence < num) {
            System.out.println("Can not add " + num + " to this position");
            return;
        }
        Link added = new Link(num);
        added.previous = tail;
        tail.next = added;
        tail = added;

        return;
    }

    public void insertAfter(int key, int num) {
// Write a function:
// -> Find the key value inside the list
// -> Then Inserts the number after the key
// -> If the key is not found do not add the num
        if (head == null) {
            return;
        }
        Link tmp = head;

        while (tmp != null && tmp.data != key) {
            tmp = tmp.next;
        }
        if (tmp != null) {
            Link added = new Link(num);
            if (tmp == tail) {
                tail.next = added;
                added.previous = tail;
                tail = added;
                return;
            }
            added.next = tmp.next;
            added.previous = tmp;
            tmp.next = added;
            return;
        }

        System.out.println("Key Could not found");
    }
// -------------------------------------------------------------

    public Link deleteLast() {
// Write a function:
// -> Delete the last link of the list
// Returns null if the list is empty
// Returns the removed link otherwise
        if (isEmpty()) {
            return null;
        }
        if (tail == head) {
            Link tmp = tail;
            head = null;
            tail = head;
            return tmp;
        }
        Link tmp = tail;
        tail.previous.next = null;
        tail = tmp.previous;
        return tmp;
    }
// -------------------------------------------------------------

    public Link deleteKey(int key) {
        if(head == null){
            return null;
        }
        if(head.data == key ){
            Link deleted = head;
            head = head.next;
            return deleted;
        }
        Link tmp = head;
        while (tmp!=null && tmp.data != key) {            
            tmp = tmp.next;
        }
        if(tmp!=null){
            if(tmp == tail)
                return deleteLast();
            Link deleted = tmp;
            tmp.previous.next = tmp.next;
            tmp.next.previous  = tmp.previous;
            return deleted;
        }
        return null;
    }
// -------------------------------------------------------------

    public void displayForward() {
// Write a function that prints the list
// From beginning to the end
        if (isEmpty()) {
            return;
        }
        Link tmp = head;
        while (tmp != null) {
            tmp.displayLink();
            tmp = tmp.next;
        }

    }
}
////////////////////////////////////////////////////////////////

public class DoublyLinkedApp {

    public static void main(String[] args) {
        DoublyLinkedList theList = new DoublyLinkedList();
        theList.insertToSpecificPos(0);
        theList.displayForward();
        System.out.println("");
        theList.insertToSpecificPos(1);
        theList.displayForward();
        System.out.println("");
        theList.insertToSpecificPos(2);
        theList.displayForward();
        System.out.println("");
        theList.insertToSpecificPos(3);
        theList.displayForward();
        System.out.println("");
        theList.insertToSpecificPos(4);
        theList.displayForward();
        System.out.println("");
        theList.insertToSpecificPos(3);
        theList.displayForward();
        System.out.println("");
        theList.deleteLast();
        theList.displayForward();
        System.out.println("");
        theList.insertAfter(2, 5);
        theList.displayForward();
        System.out.println("");
        theList.deleteLast();
        theList.displayForward();
        System.out.println("");
        theList.insertAfter(3, 7);
        theList.displayForward();
        System.out.println("");
        theList.deleteKey(2);
        theList.displayForward();
        System.out.println("");
        theList.deleteKey(0);
        theList.displayForward();

// Test your code
    }
}
////////////////////////////////////////////////////////////////
