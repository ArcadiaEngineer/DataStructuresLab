import java.io.*;

class Customer {
    public int order;
    public static int type1Start = 100;
    public static int type2Start = 400;
    public static int type3Start = 700;
    public String TC;
    public int custId;
    
    public Customer(){
        this.order = type3Start++;
        this.TC = "";
    }
    
    public Customer(String TC){
        this.order = type2Start++;
        this.TC = TC;
        this.custId = 0;
    }
    
    public Customer(int id){
        this.order = type1Start++;
        this.TC = "";
        this.custId = id;
    }
    
    @Override
    public String toString(){
        return "Order: " + order + " TC: " + TC + " ID: " + custId + "\n";
    }
}


////////////////////////////////////////////////////////////////
class Heap {
    private Customer[] heapArray;
    private int maxSize;           // size of array
    private int currentSize;       // number of nodes in array

    public Heap(int max) {
        maxSize = max;
        currentSize = 0;
        heapArray = new Customer[maxSize];
    }
    
    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    public boolean insert(Customer c) {
        if (isFull()) {
            return false;
        }
        heapArray[currentSize] = c;
        percolateUp(currentSize);
        currentSize++;
        return true;
    }
    public void percolateUp(int index) {
        while (index != 0 && heapArray[getParent(index)].order > heapArray[index].order) {
            int parent = getParent(index);
            swap(parent, index);
            index = parent;
        }
    }
    private int getParent(int i)
    {
        return (i-1)/2;
    }
    public void swap(int source, int dest) {
        Customer temp = heapArray[source];
        heapArray[source] = heapArray[dest];
        heapArray[dest] = temp;
    }
    public Customer remove() {
        if(isEmpty())
            return null;
        Customer temp = heapArray[0];
        
        heapArray[0] = heapArray[currentSize - 1];
        percolateDown(0);
        currentSize--;
        
        return temp;
    }
    private void percolateDown(int index)
    {
        while(hasLeftChild(index))
        {
            int smallerIndex = getLeftChild(index);
            if(hasRightChild(index) && heapArray[getLeftChild(index)].order > heapArray[getRightChild(index)].order)
            {
                smallerIndex = getRightChild(index);
            }
            if(heapArray[index].order < heapArray[smallerIndex].order)
                break;
            swap(index, smallerIndex);
            index = smallerIndex;
        }
    }
    private int getLeftChild(int i){
        if(hasLeftChild(i))
            return 2 * i + 1;
        return -1;
    }
    private boolean hasLeftChild(int i)
    {
        return 2 * i + 1 <= currentSize - 1;
    }
    private int getRightChild(int i){
        if(hasRightChild(i))
            return 2 * i + 2;
        return -1;
    }
    private boolean hasRightChild(int i)
    {
        return 2 * i + 2 <= currentSize - 1;
    }

    // Displays the heap
    public void displayHeap() {
        System.out.print("heapArray: ");
        for (int m = 0; m < currentSize; m++) {
            if (heapArray[m] != null) {
                System.out.print(heapArray[m].order + " ");
            } else {
                System.out.print("-- ");
            }
        }
        System.out.println();

        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;
        String dots = "...............................";
        System.out.println(dots + dots);

        while (currentSize > 0) {
            if (column == 0) {
                for (int k = 0; k < nBlanks; k++) {
                    System.out.print(' ');
                }
            }

            System.out.print(heapArray[j].order);

            if (++j == currentSize) {
                break;
            }

            if (++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            } else {
                for (int k = 0; k < nBlanks * 2 - 2; k++) {
                    System.out.print(' ');
                }
            }
        }
        System.out.println("\n" + dots + dots); 
    }
}



public class BankOrder {
    public static void main(String[] args) throws IOException {
        Heap theHeap = new Heap(20);
  
        Customer c1 = new Customer();
        System.out.println(c1);
        theHeap.insert(c1);
        Customer c2 = new Customer();
        System.out.println(c1);
        theHeap.insert(c2);
        Customer c3 = new Customer("1111");
        System.out.println(c3);
        theHeap.insert(c3);
        Customer c4 = new Customer(3259);
        System.out.println(c4);
        theHeap.insert(c4);
        Customer c5 = new Customer();
        System.out.println(c5);
        theHeap.insert(c5);
        Customer c6 = new Customer("2222");
        System.out.println(c6);
        theHeap.insert(c6);
        Customer c7 = new Customer(2594);
        System.out.println(c7);
        theHeap.insert(c7);
        Customer c8 = new Customer("3333");
        System.out.println(c8);
        theHeap.insert(c8);
        Customer c9 = new Customer(1234);
        System.out.println(c9);
        theHeap.insert(c9);
        Customer c10 = new Customer(5985);
        System.out.println(c10);
        theHeap.insert(c10);
                      
        theHeap.displayHeap();
        
        theHeap.remove();
        theHeap.remove();
        
        theHeap.displayHeap();
    }
}