
import java.util.Stack;

// BST Node
class Link {

    public int data;
    public String name;
    public double age;
    public Link left;
    public Link right;

    public Link(int d) // constructor
    {
        data = d;
    }

    public void displayLink() // display this link
    {
        System.out.print(data + " ");
    }
}
////////////////////////////////////////////////////////////////

class MyBST {
    // Implement Binary Search Tree class

    public Link root;

    public MyBST() {
        root = null;
    }

    public void insert(int num) {
        Link added = new Link(num);

        if (root == null) {
            root = added;
            return;
        }

        Link current = root;
        Link parent;

        while (true) {
            parent = current;

            if (added.data < current.data) {
                current = current.left;

                if (current == null) {
                    parent.left = added;
                    break;
                }
            } else {
                current = current.right;

                if (current == null) {
                    parent.right = added;
                    break;
                }
            }
        }
    }

    public Link search(int num) { // returns the node of given num
        if (root == null) {
            return null;
        }

        Link temp = root;

        while (temp != null) {
            if (num < temp.data) {
                temp = temp.left;
            } else if (num > temp.data) {
                temp = temp.right;
            } else {
                return temp;
            }
        }

        return temp;
    }

    public void inOrderTraversal() {

        if (root == null) {
            return;
        }

        Link temp = root;
        Stack<Link> stack = new Stack<>();
        boolean done = false;

        while (!done)
        {
            if (temp != null)
            {
                stack.push(temp);
                temp = temp.left;
            } 
            else 
            {
                if (stack.size() == 0)
                {
                    done = true;
                } 
                else {
                    temp = stack.pop();
                    temp.displayLink();
                    temp = temp.right;
                }
            }
        }
        System.out.println("\n************************************");
    }

    public void preOrderTraversal() {

        if (root == null) {
            return;
        }

        Stack<Link> stack = new Stack<>();
        stack.push(root);
        Link temp = root;

        while (stack.size() != 0) {
            
            temp = stack.pop();
            temp.displayLink();
            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }
        }

        System.out.println("\n***********************");
    }

    public void postOrderTraversal() {

        if (root == null) {
            return;
        }

        Stack<Link> stack = new Stack<>();
        Stack<Link> postOrder = new Stack<>();
        stack.push(root);
        Link temp = root;

        while (stack.size() != 0) {
            temp = stack.pop();
            postOrder.add(temp);
            if (temp.left != null) {
                stack.push(temp.left);
            }
            if (temp.right != null) {
                stack.push(temp.right);
            }
        }

        while (!postOrder.isEmpty()) 
        {
            postOrder.pop().displayLink();
        }

        System.out.println("\n***********************");
        // Prints the left child
        // Prints the right child
        // Prints the root
    }

    public int height() {
        
        Link temp = root;
        return height(temp);
        
    }
    public int height(Link root) {
        
        int height = -1;
        
        if(root == null)
            return height;
        
        int left = height(root.left);
        int right = height(root.right);
        
        return left > right ? left + 1 : right + 1;
        
    }
}
////////////////////////////////////////////////////////////////

//class BSTwithArray {
//    // Member variables of this class
//
//    public int root = 0;
//    public int[] items = new int[10];
//
//    public void insert(int num) {
//        // Inserts a node into Binary Search Tree
//    }
//
//    public boolean search(int num) { // returns the true if the item is found
//    }
//
//    public void inOrderTraversal() {
//        // Prints the left child
//        // Prints the root
//        // Prints the right child
//    }
//
//    public void preOrderTraversal() {
//        // Prints the root 
//        // Prints the left child
//        // Prints the right child
//    }
//
//    public void postOrderTraversal() {
//        // Prints the left child
//        // Prints the right child
//        // Prints the root
//    }
//}

public class Test {

    public static void main(String[] args) {
        
        MyBST mbst = new MyBST();
        mbst.insert(28);
        mbst.insert(21);
        mbst.insert(47);
        mbst.insert(13);
        mbst.insert(24);
        mbst.insert(32);
        mbst.insert(55);
        mbst.insert(99);
        
        mbst.preOrderTraversal();
        mbst.inOrderTraversal();
        mbst.postOrderTraversal();
        
        System.out.println(mbst.height());
    }
}
