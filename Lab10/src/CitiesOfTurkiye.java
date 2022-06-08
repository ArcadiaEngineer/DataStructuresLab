
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class Vertex{
    public String value;
    public boolean wasVisited;
    
    public Vertex(String val){
        value = val;
        wasVisited = false;
    }
}

public class CitiesOfTurkiye {
    public Vertex[] vertexes;
    public int[][] edges;
    public int MAX_SIZE;
    public int nElems;
    
    public CitiesOfTurkiye(int max){
        MAX_SIZE = max;
        vertexes = new Vertex[max];
        edges = new int[max][max];
        nElems = 0;
        
        for(int i=0; i<max; i++){
            for(int j=0; j<max; j++){
                edges [i][j] = 0;
            }
        }
    }
    
    public void addVertex(String val){
        vertexes[nElems++] = new Vertex(val);
    }
    
    public int findIndex(String val){
        for(int i=0; i<nElems; i++){
            if(vertexes[i].value.equals(val)){
                return i;
            }
        }
        return -1;
    }
    // A , B 
    public void addEdge(String v1, String v2)
    {
        int ind1 = findIndex(v1);
        int ind2 = findIndex(v2);
        
        if(ind1 != -1 && ind2 != -1){
            edges[ind1][ind2] = 1;
            edges[ind2][ind1] = 1;
        }
    }
    
    //                              0  -> A
    public int getAdjacentVertex(int i){
        for(int j = 0; j<nElems; j++){
            if(edges[i][j] == 1 && vertexes[j].wasVisited == false){
                return j;
            }
        }
        return -1;
    }
    //                  A
    public void dfs(String val){
        int ind = findIndex(val);               // 0
        Stack<Integer> myStack = new Stack<Integer>();
        
        myStack.push(ind);
        vertexes[ind].wasVisited = true;
        System.out.print(vertexes[ind].value);
        
        while(!myStack.isEmpty()){
            int n = getAdjacentVertex(myStack.peek()); // 1
            if(n == -1){
                myStack.pop();
            }
            else{
                vertexes[n].wasVisited = true;
                System.out.print(vertexes[n].value);
                myStack.push(n);
            }
        }
        
        for(int i=0; i<nElems; i++){
            vertexes[i].wasVisited = false;
        }
    }
    
    
    public void displayGraph(){
        for(int i=0; i<nElems; i++){
            System.out.print(vertexes[i].value + ": ");
            for(int j=0; j<nElems; j++){
                if(edges[i][j] == 1){
                    System.out.print(vertexes[j].value + " ");
                }
            }
            System.out.println("");
        }
    }
    
    public void bfs(String city) // breadth-first search
    {                          
    	Queue<String> q1 = new LinkedList<>();
        Queue<String> q2 = new LinkedList<>();
        
        int index = findIndex(city);
        if(index != -1)
        {
            q1.add(city);
            vertexes[index].wasVisited = true;
            
            int level = 0;
            
            while(!q1.isEmpty())
            {
                System.out.println("---------------------------------");
                System.out.println("Neighbourhood Level " + (level++));
                System.out.println("---------------------------------");
                
                while(!q1.isEmpty())
                {
                    String cityPeek = q1.peek();
                    System.out.println(cityPeek);
                    q2.add(cityPeek);
                    q1.remove();
                }
                while(!q2.isEmpty())
                {
                    String currAdj = q2.peek();
                    int currIndex = findIndex(currAdj);
                    for (int i = 0; i < 81; i++) {
                        if(edges[currIndex][i] == 1 && !vertexes[i].wasVisited)
                        {
                            q1.add(vertexes[i].value);
                            vertexes[i].wasVisited = true;
                        }
                    }
                    q2.remove();
                }
            }
        }
    }       
}

class Test{
    public static void main(String[] args){
        CitiesOfTurkiye myGraph = new CitiesOfTurkiye(81);
        
        try {
            File myObj = new File("cities.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                
                myGraph.addVertex(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        
        try {
            File myObj = new File("graph.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String temp[] = data.split(" ");
                for(int j=0; j<temp.length; j++){
                    myGraph.edges[i][j] = Integer.parseInt(temp[j]);                   
                }
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        
        Scanner s = new Scanner(System.in);
        System.out.println("Please select a city to see the neighbourhood level: ");
        String city = s.nextLine();
        myGraph.bfs(city);
        System.out.println("");
    }
}

