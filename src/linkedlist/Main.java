package linkedlist;

import stack.Stack;

public class Main {
    static void main(String[] args) {
        LinkedList l1 = new LinkedList(1);
        l1.append(3);
        l1.append(5);
        l1.append(7);
        l1.append(9);
        l1.append(10);
        l1.append(11);


        LinkedList l2 = new LinkedList(2);
        l2.append(4);
        l2.append(6);
        l2.append(8);

        l1.merge(l2);

        l1.printAll();
    }
}
