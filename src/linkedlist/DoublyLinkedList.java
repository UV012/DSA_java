package linkedlist;

public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int length;

    class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) { this.value = value; }
    }

    public DoublyLinkedList (int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public Node getHead() { return head; }

    public Node getTail() { return tail; }

    public int getLength() { return length; }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void printAll() {
        if (length == 0) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("Length: " + length);
        System.out.println("\nDoubly Linked List: ");
        if (length == 0) {
            System.out.println("Empty List");
        }  else { printList(); }
    }

    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        length++;
    }

    public Node removeLast() {
        if (length == 0) { return null; }
        Node temp = tail;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            tail = temp.prev;
            tail.next = null;
            temp.prev = null;
        }
        length--;
        return temp;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        length++;
    }

    public Node removeFirst() {
        if (length == 0) { return null; }
        Node temp = head;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            head = temp.next;
            temp.next = null;
            head.prev = null;
        }
        length--;
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) { return null; }
        Node temp = head;
        if (index < length/2) {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i = length - 1; i > index; i--) {
                temp = temp.prev;
            }
        }
        return temp;
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if (temp != null) {
            temp.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value) {
        if (index < 0 || index > length) { return false; }
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }
        Node before = get(index - 1);
        Node after = before.next;
        Node newNode = new Node(value);
        before.next = newNode;
        newNode.prev = before;
        newNode.next = after;
        after.prev = newNode;
        length++;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= length) { return null; }
        if (index == 0) { return removeFirst(); }
        if (index == length - 1) { return removeLast(); }
        Node temp = get(index);
        Node before = temp.prev;
        Node after = temp.next;
        before.next = after;
        after.prev = before;
        temp.next = null;
        temp.prev = null;
        length--;
        return temp;
    }

    public boolean isPalindrome() {
        if (length <= 0) { return true; }
        Node temp1 = head;
        Node temp2 = tail;
        for (int i = 0; i < length / 2; i++) {
            if (temp1.value != temp2.value) { return false; }
            temp1 = temp1.next;
            temp2 = temp2.prev;
        }
        return true;
    }

    public void reverse() {
        Node current = head;
        Node temp = null;
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
        tail = head;
        head = temp.prev;
    }

    public void partitionList(int k) {
        Node temp = head;
        Node dummy1 = new Node(0);
        Node runner1 = dummy1;
        Node dummy2 = new Node(0);
        Node runner2 = dummy2;
        while (temp != null) {
            if (temp.value < k) {
                runner1.next = temp;
                temp.prev = runner1;
                runner1 = temp;
            } else {
                runner2.next = temp;
                temp.prev = runner2;
                runner2 = temp;
            }
            temp = temp.next;
        }
        runner1.next = dummy2.next;
        if (dummy2.next != null) {
            dummy2.next.prev = runner1;
        }
        head = dummy1.next;
        tail = runner2;
        tail.next = null;
        if (head != null) { head.prev = null; }
    }

    public void reverseBetween(int startIndex, int endIndex) {
        if (head != null || startIndex == endIndex) return;
        Node previous = new Node(0);
        previous.next = head;
        Node headNode = previous;
        for (int i = 0; i < startIndex; i++) {
            previous = previous.next;
        }
        Node current = previous.next;
        for (int i = startIndex; i < endIndex; i++) {
            Node nodeToMove = current.next;
            current.next = nodeToMove.next;
            if (nodeToMove.next != null) {
                nodeToMove.next.prev = current;
            }
            nodeToMove.next = previous.next;
            previous.next.prev = nodeToMove;
            previous.next = nodeToMove;
            nodeToMove.prev = previous;
        }
        head = headNode.next;
        head.prev = null;
    }

    public void swapPairs() {
        Node previous = new Node(0);
        previous.next = head;
        Node headNode = previous;
        Node current = head;
        while (current != null && current.next != null) {
            Node nodeToMove = current.next;
            current.next = nodeToMove.next;
            nodeToMove.next = previous.next;
            previous.next.prev = nodeToMove;
            previous.next = nodeToMove;
            nodeToMove.prev = previous;
            previous = current;
            current = current.next;
        }
        head = headNode.next;
        head.prev = null;
    }

    public void swapPairs2() {
        Node dummyNode = new Node(0);
        dummyNode.next = head;
        Node previousNode = dummyNode;

        while (head != null && head.next != null) {
            Node firstNode = head;
            Node secondNode = head.next;

            previousNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            secondNode.prev = previousNode;
            firstNode.prev = secondNode;

            if (firstNode.next != null) {
                firstNode.next.prev = firstNode;
            }

            head = firstNode.next;
            previousNode = firstNode;
        }

        head = dummyNode.next;
        if (head != null) head.prev = null;
    }
}
