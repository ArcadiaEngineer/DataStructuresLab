
public class Customer {

    private String name;
    private int age;
    private double balance;
//--------------------------------------------------------------

    public Customer(String name_, int age_, double balance_) {
// constructor
        name = name_;
        age = age_;
        balance = balance_;
    }
//--------------------------------------------------------------

    public void displayCustomer() {
        System.out.print(" Customer name: " + name);
        System.out.print(", Age: " + age);
        System.out.println(", Balance: " + balance);
    }
//--------------------------------------------------------------

    public String getName() // get name of customer
    {
        return name;
    }
}
