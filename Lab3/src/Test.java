
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

    public LinkList() {
        this.head = null;
    }

    void printLinkList() {
        Node current = head;
        if (current == null) {
            System.out.println("The list is empty!");
            return;
        }
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("");
    }

    void insertToHead(String nodeData) {
        Node newNode = new Node(nodeData);
        newNode.next = head;
        head = newNode;
    }

    // Complete the insertToSpecificPosition function below.
    // Indexes are starting from 0.
    // A->B->C->null 
    // Want to add D to position 1   A -> D -> B -> C
    void insertToSpecificPosition(String nodeData, int position) {

        if (head == null) {
            head = new Node(nodeData);
            return;
        }
        int sequence = 0;
        Node curr = head;
        Node prev = null;

        while (curr != null) {
            if (sequence == position) {
                break;
            }
            sequence++;
            prev = curr;
            curr = curr.next;
        }
        if (curr != null) {
            if (prev == null) {
                insertToHead(nodeData);
                return;
            } else {
                Node added = new Node(nodeData);
                prev.next = added;
                added.next = curr;
                return;
            }
        }
        if (sequence < position) {
            System.out.println("Can not add " + nodeData + " to this position!");
            return;
        }
        Node added = new Node(nodeData);
        prev.next = added;
    }

    // Delete if a node has same data with key value
    // Return deleted Node or null if it is not found.
    public Node delete(String key) // delete link with given key
    {
        if (head == null) {
            return null;
        }
        if (head.data.equals(key)) {
            Node tmp = head;
            head = head.next;
            return tmp;
        }
        Node cur = head;
        Node prev = null;

        while (cur != null && !cur.data.equals(key)) {
            prev = cur;
            cur = cur.next;
        }
        if (cur != null) {
            prev.next = cur.next;
            return cur;
        }
        return null;
    }

    // Complete the sortedInsert function below which inserts items alphabetically sorted.
    void sortedInsert(String nodeData) {
        if (head == null) {
            head = new Node(nodeData);
            return;
        }
        Node curr = head;
        Node prev = null;

        while (curr != null && curr.data.compareTo(nodeData) < 0) {
            prev = curr;
            curr = curr.next;
        }
        if (curr != null) {
            if (prev == null) {
                insertToHead(nodeData);
                return;
            } else {
                Node added = new Node(nodeData);
                prev.next = added;
                added.next = curr;
                return;
            }
        } else {
            Node added = new Node(nodeData);
            prev.next = added;
        }
    }

}

public class Test {

    public static void main(String[] args) {
        LinkList llist = new LinkList();

        System.out.println("-------------------------------");
        System.out.println("Sorted Insert Operations");
        System.out.println("-------------------------------");
        llist.printLinkList();
        llist.sortedInsert("D");
        llist.printLinkList();
        llist.sortedInsert("B");
        llist.printLinkList();
        llist.sortedInsert("C");
        llist.printLinkList();
        llist.sortedInsert("A");
        llist.printLinkList();
        llist.sortedInsert("F");
        llist.printLinkList();
        llist.sortedInsert("E");
        llist.printLinkList();

        System.out.println("-------------------------------");
        System.out.println("Insert to a Position Operations");
        System.out.println("-------------------------------");

        llist.insertToSpecificPosition("Z", 6);
        llist.printLinkList();
        llist.insertToSpecificPosition("T", 3);
        llist.printLinkList();
        llist.insertToSpecificPosition("X", 0);
        llist.printLinkList();

        llist.insertToSpecificPosition("G", 10);

        System.out.println("-------------------------------");
        System.out.println("Delete Operations");
        System.out.println("-------------------------------");

        if (llist.delete("Z") != null) {
            llist.printLinkList();
        } else {
            System.out.println("Z is not found");
        }
        if (llist.delete("T") != null) {
            llist.printLinkList();
        } else {
            System.out.println("T is not found");
        }
        if (llist.delete("X") != null) {
            llist.printLinkList();
        } else {
            System.out.println("X is not found");
        }
        if (llist.delete("L") != null) {
            llist.printLinkList();
        } else {
            System.out.println("L is not found");
        }
        System.out.println("-------------------------------");
    }
}
