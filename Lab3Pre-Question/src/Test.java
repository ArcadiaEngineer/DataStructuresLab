/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Asus
 */
public class Test {
    public static void main(String[] args) {
        LinkList list = new LinkList();
        list.insertToHead("bir");
        list.insertToTail("iki");
        list.insertToTail("uc");
        list.insertToTail("dort");
        list.insertToTail("bes");
        list.insertToTail("altı");
        list.insertToHead("sıfır");
        list.insertToHead("eksi bir");
        
        list.print();
        System.out.println("\n-----");
        list.reversedPrint();
    }
}
