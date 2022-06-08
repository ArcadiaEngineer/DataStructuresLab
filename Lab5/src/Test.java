
class Link {

    public char dData;
    public Link next;

    public Link(char dd) {
        dData = dd;
    }

    public void displayLink() // display ourself
    {
        System.out.print(dData + " ");
    }
}

////////////////////////////////////////////////////////////////
class MyStack {

    Link head;

    public MyStack() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void push(char data) {
        Link added = new Link(data);
        if (isEmpty()) {
            head = added;
            return;
        }
        Link temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = added;
        return;
    }

    public char pop() {
        if (isEmpty()) {
            return '$';
        }

        char data;

        if (head.next == null) {
            data = head.dData;
            head = null;
            return data;
        }

        Link temp = head;

        while (temp.next.next != null) {
            temp = temp.next;
        }

        data = temp.next.dData;
        temp.next = null;
        return data;
    }

    public char peek() {
        if (isEmpty()) {
            return '$';
        }

        char data;

        if (head.next == null) {
            data = head.dData;
            return data;
        }

        Link temp = head;

        while (temp.next.next != null) {
            temp = temp.next;
        }

        data = temp.next.dData;
        return data;
    }
}
////////////////////////////////////////////////////////////////

public class Test {

    public static void checkValidity(String command) {

        MyStack stack = new MyStack();
        int n = command.length();

        for (int i = 0; i < n; i++) {
            char c = command.charAt(i);

            if (isOpenChar(c)) {
                stack.push(c);
                continue;
            }

            switch (c) {
                case '}':
                    if (stack.isEmpty()) {
                        System.out.println("{ is missing!");
                        return;
                    } else if (stack.pop() == '{') {
                        continue;
                    } else {
                        System.out.println("Paranthesis are not matching");
                        return;
                    }

                case ']':
                    if (stack.isEmpty()) {
                        System.out.println("[ is missing!");
                        return;
                    } else if (stack.pop() == '[') {
                        continue;
                    } else {
                        System.out.println("Paranthesis are not matching");

                        return;
                    }

                case ')':
                    if (stack.isEmpty()) {
                        System.out.println("( is missing!");
                        return;
                    } else if (stack.pop() == '(') {
                        continue;
                    } else {
                        System.out.println("Paranthesis are not matching");

                        return;
                    }
            }
        }

        if (!stack.isEmpty()) {
            System.out.println(findMissingChar(stack.pop()) + " is missing!");
        } else {
            System.out.println("Accepted");
        }

    }

    public static char findMissingChar(char c) {
        switch (c) {
            case '{':
                return '}';
            case '(':
                return ')';
            case '[':
                return ']';
        }
        return '$';
    }

    public static boolean isOpenChar(char c) {
        switch (c) {
            case '{':
                return true;
            case '(':
                return true;
            case '[':
                return true;
        }
        return false;
    }

    public static void main(String[] args) {

        String com1 = "System.out.println((3+5) + 2);";
        String com2 = "int x = (3+5) * 2);";
        String com3 = "calculateSum(((a+2) * 2), (3*b);";

        String com4 = "int res = (arr[0]+arr[1]) * arr[2];";
        String com5 = "int res = (arr[0]+arr[1)] * arr[2];";
        String com6 = "int res = (arr[0]+arr[1]) * arr[2]];";

        String com7 = "public static void main(String[] args) {"
                + "int res = (arr[0]+arr[1]) * arr[2];"
                + "}}";
        String com8 = "public static void main(String[] args) {"
                + "int[] arr = {1,2,3,4};";
        String com9 = "public static void main(String[] args) {"
                + "int[] arr = {1,2,3,4};"
                + "}";
        String com10 = "{{}[(){()()[]}],([],[{}])}";

        System.out.println("----------------------------------------------");
        System.out.println("Only () CHECK -> 1 POINT");
        System.out.println("----------------------------------------------");
        System.out.println(com1);
        checkValidity(com1);
        System.out.println("----------------------------------------------");
        System.out.println(com2);
        checkValidity(com2);
        System.out.println("----------------------------------------------");
        System.out.println(com3);
        checkValidity(com3);
        System.out.println("----------------------------------------------");
        System.out.println("(),[] CHECK -> 1 POINT");
        System.out.println("----------------------------------------------");
        System.out.println(com4);
        checkValidity(com4);
        System.out.println("----------------------------------------------");
        System.out.println(com5);
        checkValidity(com5);
        System.out.println("----------------------------------------------");
        System.out.println(com6);
        checkValidity(com6);
        System.out.println("----------------------------------------------");
        System.out.println("(),[],{} CHECK -> 1 POINT");
        System.out.println("----------------------------------------------");
        System.out.println(com7);
        checkValidity(com7);
        System.out.println("----------------------------------------------");
        System.out.println(com8);
        checkValidity(com8);
        System.out.println("----------------------------------------------");
        System.out.println(com9);
        checkValidity(com9);
        System.out.println("----------------------------------------------");
        System.out.println(com10);
        checkValidity(com10);
        System.out.println("----------------------------------------------");

    }  // end main()
}  // end class LinkStackApp
////////////////////////////////////////////////////////////////
