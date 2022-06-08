
class Node {

    public String data;
    public Node next;

    public Node(String nodeData) {
        this.data = nodeData;
        this.next = null;
    }
}

class LinkList {

    public Node head; // First item of LinkList
    public Node tail; // Last item of LinkList

    public LinkList() {
        this.head = null;
        this.tail = null;
    }
// Insert a node at the beginning of LinkList

    public void insertToHead(String nodeData) {
        
        if(head == null){
            head = new Node(nodeData);
            tail = head;
            return;
        }
        
        Node added =  new Node(nodeData);
        added.next = head;
        head = added;
    }
// Insert a node at the end of LinkList

    public void insertToTail(String nodeData) {
        
        if(head == null){
            head = new Node(nodeData);
            tail = head;
            return;
        }
        
        Node added = new Node(nodeData);
        tail.next = added;
        tail = added;
        
    }
// Remove a node from beginning of LinkList returns the removed node.

    public Node removeFromHead() {
        if(head == null){
            return null;
        }
        Node removed = new Node(head.data);
        head = head.next;
        return removed;
    }
// Remove a node from end of LinkList returns the removed node.

    public Node removeFromTail() {
        if(head == null)
            return null;
        Node temp = head;
        while(temp.next != null && temp.next!=tail)
            temp = temp.next;
        if(temp!=null){
            temp.next = null;
            return tail;
        }
        return null;
    }
// Complete the printLinkedList function in the following format below:
// A -> B -> C -> null

    public void print() {
        if(head == null)
            return;
        Node temp = head;
        while(temp != null)
        {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.print("null");
    }
// Prints the list in a reversed order
// Hint: Recursion can be used

    public void reversedPrint() {
        if(head == null)
            return;
        Node temp = head;
        printR(temp);
    }
    
    private void printR(Node temp){
        if(temp == tail){
            System.out.print(temp.data + " ");
        }else{
            printR(temp.next);
            System.out.print(temp.data + " -> ");
        }
    }
}
