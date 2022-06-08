
import java.util.Stack;

class FamilyLinkList {

    class Node {

        public String name;
        public int age;
        public Node next;

        public Node(String name, int age) {
            this.name = name;
            this.age = age;
            this.next = null;
        }

        public String toString() {
            return "Name=" + name + ", Age=" + age;
        }
    }

    public Node head;

    public FamilyLinkList() {
        head = null;
    }

    private boolean isEmpty() {
        return head == null;
    }

    public void insertNode(String name, int age) {

        Node added = new Node(name, age);
        // Inserts a node at the end of the FamilyLinkList
        if (isEmpty()) {
            head = added;
            return;
        }

        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = added;
    }

    public boolean search(String name) {
        // Searches a name into the Family Linked Lists and returns true if found
        // false otherwise

        Node temp = head;

        while (temp != null) {
            if (temp.name.compareTo(name) == 0) {
                return true;
            }
            temp = temp.next;
        }

        return false;
    }

    public boolean deleteNode(String name) {
        // Deletes the node from Family Link Lists
        // Return true if delete is succesful, false otherwise

        if (isEmpty()) {
            return false;
        }
        
        if(head.name.compareTo(name) == 0)
        {
            head = head.next;
            return true;
        }

        Node temp = head;

        while (temp.next != null) {
            if (temp.next.name.compareTo(name) == 0) {
                break;
            }
            temp = temp.next;
        }
        if(temp.next != null)
        {
            temp.next = temp.next.next;
            return true;
        }
        else{
            return false;
        }
    }

    // Prints the FamilyLinkList
    void printLinkList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }
}

public class MyBST {

    // Inner Class -> Only reachable by this class
    class Node {

        String familyName;
        FamilyLinkList memberList;
        Node left, right;

        public Node(String familyName) {
            this.familyName = familyName;
            left = right = null;
            memberList = new FamilyLinkList();
        }
    }

    // Root of BST
    Node root;

    // Constructor
    public MyBST() {
        root = null;
    }

    void insert(String fName, String name, int age) {
        // This method mainly inserts the family members to the Tree
        // 1-) If a family is not in the tree first you need to insert that family
        // Then you can insert the member to the memberList of that Node
        if (root == null) {
            root = new Node(fName);
            root.memberList.insertNode(name, age);
            return;
        }

        FamilyLinkList temp = searchFamily(fName);
        if (temp != null) {
            temp.insertNode(name, age);
            return;
        }

        Node curr = root;
        Node parent = null;
        Node added = new Node(fName);
        added.memberList.insertNode(name, age);

        while (curr != null) {
            parent = curr;

            if (curr.familyName.compareTo(fName) < 0) {
                curr = curr.right;

                if (curr == null) {
                    parent.right = added;
                    break;
                }
            } else {
                curr = curr.left;

                if (curr == null) {
                    parent.left = added;
                    break;
                }
            }
        }

        // 2-) If a family is already in the Tree
        // Then you just need to add the member to the memberList of that Node
    }

    // This method mainly calls InorderRec()
    void inorder() {
        inorderRec(root);
    }

    // In Order Traversal of the Family Tree
    // Family names and members need to be printed according to the family tree
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println("---------------------------------");
            System.out.println("Family Name: " + root.familyName);
            System.out.println("Family Members:");
            root.memberList.printLinkList();
            System.out.println("---------------------------------\n");
            inorderRec(root.right);
        }
    }

    FamilyLinkList searchFamily(String fName) {
        // An iterative search function needs to be written

        Node temp = root;
        return searchFamilyRec(temp, fName);
    }

    FamilyLinkList searchFamilyRec(Node root, String fName) {
        if (root == null) {
            return null;
        }
        if (root.familyName.compareTo(fName) == 0) {
            return root.memberList;
        } else if (root.familyName.compareTo(fName) < 0) {
            return searchFamilyRec(root.right, fName);
        } else {
            return searchFamilyRec(root.left, fName);
        }
    }

    void search(String fName, String name) {
        // An iterative search function needs to be written

        Node temp = root;
        searchRec(temp, fName, name);
    }

    private void searchRec(Node root, String fName, String name) {

        if (root == null) {
            System.out.println(fName + " family is not in the BST");
            return;
        }

        if (root.familyName.compareTo(fName) == 0) {
            if (root.memberList.search(name)) {
                System.out.println(name + " " + fName + " is found in the Tree");
            } else {
                System.out.println(fName + " family is found BUT " + name + " not inside the Family");
            }
        } else if (root.familyName.compareTo(fName) < 0) {
            searchRec(root.right, fName, name);
        } else {
            searchRec(root.left, fName, name);
        }

    }

    FamilyLinkList searchBool(String fName, String name) {
        Node temp = root;
        return searchBoolRec(temp, fName, name);
    }

    FamilyLinkList searchBoolRec(Node root, String fName, String name) {
        if (root == null) {
            System.out.println(fName + " family is not in the Tree");
            return null;
        }

        if (root.familyName.compareTo(fName) == 0) {
            if (root.memberList.search(name)) {
                System.out.println(name + " " + fName + " is deleted");
                return root.memberList;
            } else {
                System.out.println(fName + " " + name + " not Found in his/her family!");
                return null;
            }
        } else if (root.familyName.compareTo(fName) < 0) {
            return searchBoolRec(root.right, fName, name);
        } else {
            return searchBoolRec(root.left, fName, name);
        }
    }

    void delete(String fName, String name) {
        // An iterative delete method will be written
        // 1- If the person is exist
        // You just need to remove the family member from the memberlist
        // Family name is not needed to be deleted from the Tree

        FamilyLinkList temp = searchBool(fName, name);
        if (temp != null) {
            temp.deleteNode(name);
        }

        // 2- If person is not exist
        // Just give the correct warning message
    }

    // Is a method to show the tree
    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 35;
        boolean isRowEmpty = false;
        System.out.println(
                "----------------------------------------------------------------------------------------------------");

        System.out.println("FAMILIES TREE");
        System.out.println(
                "----------------------------------------------------------------------------------------------------");
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++) {
                System.out.print(' ');
            }

            while (globalStack.isEmpty() == false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.familyName);
                    localStack.push(temp.left);
                    localStack.push(temp.right);

                    if (temp.left != null
                            || temp.right != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("----");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(' ');
                }
            }  // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty() == false) {
                globalStack.push(localStack.pop());
            }
        }  // end while isRowEmpty is false
        System.out.println(
                "-----------------------------------------------------------------------------------------------------");
    }  // end displayTree()

}

class MyTest {

    public static void main(String[] args) {
        MyBST bst = new MyBST();

        System.out.println("-----------------------------------------------");
        System.out.println("1)INSERT Operations ");
        System.out.println("-----------------------------------------------");

        bst.insert("Malling", "Noel", 30);
        bst.insert("Malling", "Oralia", 25);
        bst.insert("Stagge", "Irvin", 22);
        bst.insert("Fremantle", "Sibyl", 44);
        bst.insert("Billington", "Tracie", 65);
        bst.insert("Stagge", "Patton", 20);
        bst.insert("Tiddeman", "Phaidra", 12);
        bst.insert("Billington", "Derril", 6);
        bst.insert("Cancellieri", "Viviana", 25);
        bst.insert("Stagge", "Josephina", 88);
        bst.insert("Malling", "Cathrine", 68);
        bst.insert("Fremantle", "Veradis", 50);
        bst.insert("McDavitt", "Bryce", 64);
        bst.insert("Tiddeman", "Salome", 42);
        bst.insert("Malling", "Abey", 43);
        bst.insert("Kirkhouse", "Niko", 55);

        bst.displayTree();
        bst.inorder();

        System.out.println("-----------------------------------------------");
        System.out.println("2)SEARCH Operations ");
        System.out.println("-----------------------------------------------");

        bst.search("Malling", "Oralia");
        bst.search("Malling", "Veradis");
        bst.search("Hamilton", "Alfred");
        bst.search("Malling", "Abey");

        System.out.println("-----------------------------------------------");
        System.out.println("3)DELETE Operations ");
        System.out.println("-----------------------------------------------");

        bst.delete("Billington", "Tracie");
        bst.delete("Malling", "Oralia");
        bst.delete("Malling", "Abey");

        bst.delete("Hamilton", "Alfred");
        bst.delete("Malling", "Abey");
        bst.delete("Malling", "Veradis");
        bst.inorder();
    }
}
