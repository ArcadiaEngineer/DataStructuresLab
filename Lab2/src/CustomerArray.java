
public class CustomerArray {

    public Customer[] cArr;
    public int counter;
    public int maxSize;

    public CustomerArray(int maxSize) {
        cArr = new Customer[maxSize];
        counter = 0;
        this.maxSize = maxSize;
    }

    public void sortedInsert(Customer c) {

        if (counter + 1 <= maxSize) {

            if (counter == 0) {
                cArr[counter++] = c;
            } else {
                int i;
                for (i = 0; i < counter; i++) {
                    if (cArr[i].getName().compareTo(c.getName()) > 0) {
                        break;
                    }
                }
                int j;
                for (j = counter; j > i; j--) {
                    cArr[j] = cArr[j - 1];
                }
                cArr[j] = c;
                counter++;
            }

        }
    }
    // Display top 10 and last 10 items of the array

    public void displayTopAndLast10() {
        for (int i = 0; i < 10; i++) {
            cArr[i].displayCustomer();
        }
        System.out.println(" .");
        System.out.println(" .");
        System.out.println(" .");
        for (int i = counter - 10; i < counter; i++) {
            cArr[i].displayCustomer();
        }
        //System.out.println("-------------------------------------------------");
    }

    public int linearSearch(String name) {
        int cost = 0;
        for (int i = 0; i < counter; i++) {
            if (cArr[i].getName().equals(name)) {
                return cost;
            }
            cost++;
        }
        return cost;
    }
    // Returns the number of iteration (loop count)

    public int binarySearch(String name) {
        int cost = 0;
        int low = 0, mid, high = counter - 1;
        while (low <= high) {
            cost++;
            mid = (low + high) / 2;
            if (cArr[mid].getName().equals(name)) {
                return cost;
            } else if (cArr[mid].getName().compareTo(name) > 0) {
                high = mid - 1;
            } else if (cArr[mid].getName().compareTo(name) < 0) {
                low = mid + 1;
            }
        }
        return cost;
    }
}
