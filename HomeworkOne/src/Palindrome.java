
import java.util.LinkedList;
import java.io.*;

class MyStack {

    LinkedList<Character> characters;

    public MyStack() {
        characters = new LinkedList<>();
    }

    public boolean isEmpty() {
        return characters.isEmpty();
    }

    public void push(Character c) {
        characters.addLast(c);
    }

    public Character pop() {
        if (isEmpty()) {
            return null;
        }
        return characters.removeLast();
    }
    public int size()
    {
        return characters.size();
    }

}

public class Palindrome {

    public static void main(String[] args) throws FileNotFoundException, IOException {


        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader("C:\\Users\\Asus\\Documents\\NetBeansProjects\\HomeworkOne\\src\\palindrome.txt"));
        String line;
        while((line = lineNumberReader.readLine()) != null)
        {
            StringBuilder cleanLine = new StringBuilder();
            int len = line.length();
            
            for (int i = 0; i < len; i++) {
                if(Character.isAlphabetic(line.charAt(i)))
                    cleanLine.append(Character.toLowerCase(line.charAt(i)));
            }
            
            boolean result = findP(cleanLine.toString());
            if(result)
                System.out.println(line);
        }

    }

    public static boolean findP(String s) {
        
        int len = s.length() / 2;
        MyStack stack = new MyStack();

        for (int i = 0; i < len; i++) {
            stack.push(s.charAt(i));
        }
        
        int size = stack.size();
        len = s.length();
        
        for(int i = len % 2 == 1 ? size + 1 : size; i < len ; i++)
        {
            if(stack.pop() != s.charAt(i))
                return false;
        }
        
        return stack.isEmpty();
    }

}
