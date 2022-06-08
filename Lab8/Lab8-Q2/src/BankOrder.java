
import java.io.*;

class Customer {

    public int order;
    public String TC;
    public int custId;
    public static int type1Start = 100;
    public static int type2Start = 400;
    public static int type3Start = 700;
    public int arrivalTime;

    public Customer(int time) {
        this.order = type3Start++;
        this.TC = "";
        this.arrivalTime = time;
    }

    public Customer(String TC, int time) {
        this.order = type2Start++;
        this.TC = TC;
        this.custId = 0;
        this.arrivalTime = time;
    }

    public Customer(int id, int time) {
        this.order = type1Start++;
        this.TC = "";
        this.custId = id;
        this.arrivalTime = time;
    }

    @Override
    public String toString() {
        return "Order: " + order + " TC: " + TC + " ID: " + custId + "\n";
    }
}

////////////////////////////////////////////////////////////////
class Heap {

    private int time;
    private int waitingTime = 0;
    private Customer[] heapArray;
    private int maxSize; // size of array
    private int currentSize; // number of nodes in array

    public Heap(int max) {
        time = 0;
        maxSize = max;
        currentSize = 0;
        heapArray = new Customer[maxSize];
    }

    public void increaseTime(int num) {
        time += num;
    }

    public int getTime() {
        return time;
    }

    public int getWaitingTime() {
        return waitingTime;
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

    private int getParent(int i) {
        return (i - 1) / 2;
    }

    public void swap(int source, int dest) {
        Customer temp = heapArray[source];
        heapArray[source] = heapArray[dest];
        heapArray[dest] = temp;
    }

    public Customer remove() {
        if (isEmpty()) {
            return null;
        }
        Customer temp = heapArray[0];

        heapArray[0] = heapArray[currentSize - 1];
        percolateDown(0);
        currentSize--;
        
        waitingTime -= temp.arrivalTime - time;
        
        return temp;
    }

    private void percolateDown(int index) {
        while (hasLeftChild(index)) {
            int smallerIndex = getLeftChild(index);
            if (hasRightChild(index) && heapArray[getLeftChild(index)].order > heapArray[getRightChild(index)].order) {
                smallerIndex = getRightChild(index);
            }
            if (heapArray[index].order < heapArray[smallerIndex].order) {
                break;
            }
            swap(index, smallerIndex);
            index = smallerIndex;
        }
    }

    private int getLeftChild(int i) {
        if (hasLeftChild(i)) {
            return 2 * i + 1;
        }
        return -1;
    }

    private boolean hasLeftChild(int i) {
        return 2 * i + 1 <= currentSize - 1;
    }

    private int getRightChild(int i) {
        if (hasRightChild(i)) {
            return 2 * i + 2;
        }
        return -1;
    }

    private boolean hasRightChild(int i) {
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
        System.out.println("QUESTION 2");

        System.out.println("..............................................................");

        // Bank opened
        Heap bank = new Heap(20);
        // First 3 customer come in time 0
        Customer cus1 = new Customer(bank.getTime());
        Customer cus2 = new Customer("11", bank.getTime());
        Customer cus3 = new Customer(80, bank.getTime());

        bank.insert(cus1);
        bank.insert(cus2);
        bank.insert(cus3);

        // Increase time to 5 min later
        bank.increaseTime(5);

        // Another customer comes in time 5
        Customer cus4 = new Customer(bank.getTime());
        bank.insert(cus4);
        // Increase time to 2 min later
        bank.increaseTime(2);

        bank.displayHeap();
        // A customer leaves the bank at time 7
        bank.remove();

        // Increase time to 6 min later
        bank.increaseTime(6);

        Customer cus5 = new Customer("22", bank.getTime());
        bank.insert(cus5);

        // Increase time to 1 min later
        bank.increaseTime(1);
        Customer cus6 = new Customer(bank.getTime());
        bank.insert(cus6);

        // Increase time to 1 min later
        bank.increaseTime(1);
        Customer cus7 = new Customer(90, bank.getTime());
        bank.insert(cus7);

        bank.displayHeap();

        // Another customer leaves
        bank.remove();

        bank.increaseTime(5);
        bank.remove();

        bank.increaseTime(3);
        bank.remove();

        bank.increaseTime(2);
        bank.remove();

        bank.increaseTime(5);
        bank.remove();

        bank.increaseTime(3);
        bank.remove();

        System.out.println("Waiting time for all customers: "
                + bank.getWaitingTime());

    }
}
