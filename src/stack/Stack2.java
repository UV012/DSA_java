package stack;

import java.util.ArrayList;

public class Stack2<T> {
    private ArrayList<T> myStackList = new ArrayList<>();

    public ArrayList<T> getStackList() { return myStackList; }

    public void printStackList() {
        for(int i = myStackList.size() - 1; i >= 0; i--) {
            System.out.println(myStackList.get(i));
        }
    }

    public boolean isEmpty() {
        return myStackList.isEmpty();
    }

    public T peek() {
        if (myStackList.isEmpty()) return null;
        else return myStackList.getLast();
    }

    public int size() { return myStackList.size(); }

    public void push (T value) { myStackList.add(value); }

    public T pop() {
        if (myStackList.isEmpty()) return null;
        else return myStackList.removeLast();
    }
}
