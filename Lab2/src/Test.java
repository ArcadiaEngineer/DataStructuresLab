
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {

    public static void averageCase(int[] arr, int n) {
        int sum = 0;
        
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        double avrrg = (double)sum/n;
        System.out.println("Average Case: " + avrrg);
    }

    public static void bestAndWorstCase(int[] arr, int n) {
        int best = arr[0];
        int worst = arr[0];
        
        for (int i = 0; i < n; i++) {
            if(arr[i]<best){
                best = arr[i];
            }
            if(arr[i]>worst){
                worst = arr[i];
            }
        }
        System.out.println("Best Case: " + best);
        System.out.println("Worst Case: " + worst);
    }

    public static void main(String[] args) {
        CustomerArray customers = new CustomerArray(10000);

        try {
            File myObj = new File("C:\\Users\\Asus\\Documents\\NetBeansProjects\\LabTwo\\src\\Customer.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] temp = data.split(",");
                Customer p = new Customer(temp[0], Integer.parseInt(temp[1]), Double.parseDouble(temp[2]));
                customers.sortedInsert(p);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int n = customers.counter;
        int[] linear = new int[n];
        int[] binary = new int[n];

        customers.displayTopAndLast10();
        for (int i = 0; i < n; i++) {

            String name = customers.cArr[i].getName();
            linear[i] = customers.linearSearch(name);
            binary[i] = customers.binarySearch(name);
        }
        System.out.println("----------------------------");
        System.out.println("Linear Search");
        System.out.println("----------------------------");
        averageCase(linear, n);
        bestAndWorstCase(linear, n);
        System.out.println("Not Found Cost: " + customers.linearSearch("Not Exist"));
        System.out.println("----------------------------");
        System.out.println("Binary Search");
        System.out.println("----------------------------");
        averageCase(binary, n);
        bestAndWorstCase(binary, n);
        System.out.println("Not Found Cost: " + customers.binarySearch("Not Exist"));
        System.out.println("----------------------------");
    }
}
