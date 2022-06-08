
import java.io.*;

class Node {

    public String data;
    public Node next;
    
    public Node(String nodeData) {
        this.data = nodeData;
        this.next = null;
    }
    
}

class LinkList {

    public Node head;

    public LinkList() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    void printLinkList() {
        Node current = head;
        if (current == null) {
            System.out.println("None");
            return;
        }
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("");
    }

    void insertToTail(String nodeData) {
        Node newNode = new Node(nodeData);

        if (head == null) {
            head = newNode;
        } else {
            Node cur = head;

            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = newNode;
        }
    }

    public Node delete(String key) {
        Node current = head;
        Node previous = head;

        if (head == null) {
            return null;
        }
        while (!current.data.equals(key)) {
            if (current.next == null) {
                return null;
            } else {
                previous = current;
                current = current.next;
            }
        }

        if (current == head) {
            head = head.next;
        } else // otherwise,
        {
            previous.next = current.next;
        }
        return current;
    }

    public Node search(String key) {
        Node cur = head;

        while (cur != null) {
            if (cur.data.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }
}

class HashTable {

    private LinkList[] hashArray;    // array holds hash table
    private int arraySize;

    public HashTable(int size) {
        arraySize = size;
        hashArray = new LinkList[arraySize];
        for (int i = 0; i < size; i++){
            hashArray[i] = new LinkList();
        }
    }

    public void displayTable() {
        int i = 0;
        System.out.println("-------------------------------------");
        System.out.println("Hash Table: ");
        for (LinkList linkList : hashArray) {
            System.out.print((i++) + "-List: ");
            linkList.printLinkList();
        }
    }

    public int hashFunc(String key) {
        return (key.length() + 3) % arraySize; 
    }

    public void insert(String key) // insert a DataItem
    {
        hashArray[hashFunc(key)].insertToTail(key);
    }  

    public Node delete(String key) // delete a DataItem
    {
        Node deleted = find(key);
        if(deleted != null)
        {
            hashArray[hashFunc(key)].delete(key);
        }
        
        return deleted;
    }  

    public Node find(String key) // find item with key
    {
        return hashArray[hashFunc(key)].search(key);
    }

}  

public class HashTableApp {

    public static void main(String[] args) throws IOException {

        HashTable hTable = new HashTable(5);
        
        hTable.displayTable();
       
        hTable.insert("Ahmet");
        hTable.displayTable();
        hTable.insert("Ali");
        hTable.displayTable();
        hTable.insert("Mehmet");
        hTable.displayTable();
        hTable.insert("Ayse");
        hTable.displayTable();
        hTable.insert("Can");
        hTable.displayTable();
        hTable.insert("Deniz");
        hTable.displayTable();
        hTable.insert("A");
        hTable.displayTable();
        hTable.delete("Ayse");
        hTable.displayTable();
        
        
        if(hTable.find("Ahmet") != null)
           System.out.println("Ahmet is in the Hash ");
        else
           System.out.println("Ahmet is not in the Hash");

        hTable.delete("Ahmet");
        hTable.displayTable();
         
        if(hTable.find("Ahmet") != null)
           System.out.println("Ahmet is in the Hash ");
        else
           System.out.println("Ahmet is not in the Hash");
    }
}  