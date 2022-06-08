
public class MyHeap {

    private int[] heapArray;
    private int maxSize; // size of array
    private int currentSize; // number of elements in array

    public MyHeap(int max) {
        maxSize = max;
        currentSize = 0;
        heapArray = new int[maxSize];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    public boolean insert(int key) {
        if (isFull()) {
            return false;
        }
        heapArray[currentSize] = key;
        percolateUp(currentSize);
        currentSize++;
        return true;
    }

    public void percolateUp(int index) {
        while (index != 0 && heapArray[getParent(index)] < heapArray[index]) {
            int parent = getParent(index);
            swap(parent, index);
            index = parent;
        }
    }

    private int getParent(int i) {
        return (i - 1) / 2;
    }

    public void swap(int source, int dest) {
        int temp = heapArray[source];
        heapArray[source] = heapArray[dest];
        heapArray[dest] = temp;
    }

    public int remove() {
        if (isEmpty()) {
            return -1;
        }
        int temp = heapArray[0];

        heapArray[0] = heapArray[currentSize - 1];
        percolateDown(0);
        currentSize--;

        return temp;
    }

    private void percolateDown(int index) {
        while (hasLeftChild(index)) {
            int largerIndex = getLeftChild(index);
            if (hasRightChild(index) && heapArray[getLeftChild(index)] < heapArray[getRightChild(index)]) {
                largerIndex = getRightChild(index);
            }
            if (heapArray[index] > heapArray[largerIndex]) {
                break;
            }
            swap(index, largerIndex);
            index = largerIndex;
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

//    public boolean change(int index, int newValue) {
//    }
    public void displayHeap() {
        System.out.print("heapArray: ");
        for (int m = 0; m < currentSize; m++) {
            System.out.print(heapArray[m] + " ");
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
            System.out.print(heapArray[j]);
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

    public static void main(String[] args) {
        MyHeap heap = new MyHeap(10);
        heap.insert(7);
        heap.insert(4);
        heap.insert(5);
        heap.insert(2);
        heap.insert(6);
        heap.insert(10);
        heap.insert(8);
        heap.insert(3);
        heap.insert(1);
        heap.insert(9);
        heap.displayHeap();
        System.out.println("\n*************************************************************************************\n");
        System.out.println("Ordered Array:\n ");
        while(!heap.isEmpty())
        {
            System.out.print(heap.remove() + " ");
        }
    }
}

class MyHeapSort {
    // this is not a heap array, it is an unordered array

    public int[] items = {7, 4, 5, 2, 6, 10, 8, 3, 1, 9};

}
