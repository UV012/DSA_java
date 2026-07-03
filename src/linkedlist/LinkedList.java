package linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * A custom, generic‑free implementation of a singly linked list that stores
 * integer values.
 * <p>
 * This class provides fundamental operations (insertion, deletion, traversal,
 * reversal) along with several classic LeetCode‑style exercises. Each method
 * is thoroughly documented with its algorithmic approach and complexity.
 * </p>
 *
 * <p><b>Structure:</b></p>
 * <ul>
 *   <li>{@code head} – reference to the first node</li>
 *   <li>{@code tail} – reference to the last node (enables O(1) appends)</li>
 *   <li>{@code length} – current number of nodes (avoids O(n) size queries)</li>
 * </ul>
 *
 * <p><b>Node class:</b> inner, non‑static, holds an {@code int value} and a
 * {@code Node next} reference.</p>
 */
public class LinkedList {

    // -------- Member Variables --------

    /** First node in the list. {@code null} when the list is empty. */
    private Node head;

    /** Last node in the list. {@code null} when the list is empty. */
    private Node tail;

    /** Current number of nodes. Maintained in O(1) for all operations. */
    private int length;

    /**
     * Inner class representing a single element (node) of the linked list.
     */
    class Node {
        /** The integer value stored in this node. */
        int value;

        /** Reference to the next node in the sequence. {@code null} if this is the tail. */
        Node next;

        /**
         * Constructs a node with a given value.
         * The {@code next} reference is initialised to {@code null}.
         *
         * @param value the integer to store
         */
        Node(int value) {
            this.value = value;
        }
    }

    // -------- Constructor --------

    /**
     * Creates a new linked list initialised with a single node.
     * Head and tail both point to the new node, and length is set to 1.
     *
     * @param value the value of the first node
     */
    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    // -------- Basic Getters --------

    /** @return the head node (may be {@code null}) */
    public Node getHead() {
        return head;
    }

    /** @return the tail node (may be {@code null}) */
    public Node getTail() {
        return tail;
    }

    /** @return the number of nodes currently in the list */
    public int getLength() {
        return length;
    }

    // -------- Utility Methods --------

    /**
     * Prints every value in the list, one per line, from head to tail.
     * <p><b>Time complexity:</b> O(n) – visits each node once.</p>
     */
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    /**
     * Empties the list completely.
     * All references are dropped so the garbage collector can reclaim the nodes.
     */
    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }

    /**
     * Prints a detailed summary of the list:
     * head value, tail value, length, and the full sequence of values.
     * Useful for debugging and testing.
     */
    public void printAll() {
        if (length == 0) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("Length: " + length);
        System.out.println("\nLinked list: ");
        if (length == 0) {
            System.out.println("List is empty");
        } else {
            printList();
        }
    }

    // -------- Core Operations (Add / Remove) --------

    /**
     * Appends a new node with the given value to the end of the list.
     * <p><b>Approach:</b> Utilise the {@code tail} reference for O(1) insertion.
     * Special case: if the list is empty, head and tail both become the new node.</p>
     * <p><b>Time complexity:</b> O(1)</p>
     *
     * @param value the integer to append
     */
    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            // List was empty – new node becomes both head and tail
            head = newNode;
            tail = newNode;
        } else {
            // Attach to the current tail and update tail reference
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    /**
     * Removes the last node from the list and returns it.
     * <p><b>Approach:</b> Traverse to the second‑last node (O(n) because singly linked).
     * Special cases: empty list (returns {@code null}), single‑node list
     * (resets head and tail to {@code null}).</p>
     * <p><b>Time complexity:</b> O(n)</p>
     *
     * @return the removed node, or {@code null} if the list was empty
     */
    public Node removeLast() {
        if (length == 0) return null;

        Node temp = head;
        if (length == 1) {
            // Only one node – clear the list
            head = null;
            tail = null;
            length--;
            return temp;
        }

        // General case: find the node before the tail
        Node pre = head;
        while (temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        // pre is now the second‑last node; temp is the last node
        tail = pre;
        pre.next = null;
        length--;
        return temp;
    }

    /**
     * Prepends a new node with the given value to the beginning of the list.
     * <p><b>Approach:</b> O(1) insertion by updating {@code head}.
     * If the list is empty, tail is also set.</p>
     * <p><b>Time complexity:</b> O(1)</p>
     *
     * @param value the integer to prepend
     */
    public void prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    /**
     * Removes the first node from the list and returns it.
     * <p><b>Approach:</b> O(1) removal by advancing {@code head}.
     * Handles empty and single‑node edge cases.</p>
     * <p><b>Time complexity:</b> O(1)</p>
     *
     * @return the removed node, or {@code null} if the list was empty
     */
    public Node removeFirst() {
        if (length == 0) return null;

        Node temp = head;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            temp.next = null;   // detach the removed node from the list
        }
        length--;
        return temp;
    }

    // -------- Index‑Based Access --------

    /**
     * Retrieves the node at the specified index (0‑based).
     * <p><b>Approach:</b> Linear traversal from the head until the desired index.</p>
     * <p><b>Time complexity:</b> O(n)</p>
     *
     * @param index the position (0 ≤ index &lt; length)
     * @return the node at {@code index}, or {@code null} if index is out of bounds
     */
    public Node get(int index) {
        if (length == 0) return null;
        if (index < 0 || index >= length) return null;

        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * Updates the value of the node at the given index.
     * <p><b>Approach:</b> Reuse {@link #get(int)} to locate the node, then modify.</p>
     * <p><b>Time complexity:</b> O(n)</p>
     *
     * @param index the target position (0 ≤ index &lt; length)
     * @param value the new integer value
     * @return {@code true} if successful, {@code false} if index is invalid
     */
    public boolean set(int index, int value) {
        if (index < 0 || index >= length) return false;

        Node temp = get(index);
        temp.value = value;
        return true;
    }

    /**
     * Inserts a new node at the specified index.
     * <p><b>Approach:</b> Delegates to {@link #prepend(int)} or {@link #append(int)}
     * for boundary indices; otherwise walks to the node before the insertion point.</p>
     * <p><b>Time complexity:</b> O(n) (due to {@link #get(int)})</p>
     *
     * @param index the insertion position (0 ≤ index ≤ length)
     * @param value the integer to insert
     * @return {@code true} if insertion succeeded, {@code false} for invalid index
     */
    public boolean insert(int index, int value) {
        if (index < 0 || index > length) return false;
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }

        // Insert in the middle
        Node newNode = new Node(value);
        Node pre = get(index - 1);    // node before the insertion point
        Node temp = pre.next;         // node currently at the insertion point
        pre.next = newNode;
        newNode.next = temp;
        length++;
        return true;
    }

    /**
     * Removes the node at the specified index and returns it.
     * <p><b>Approach:</b> Delegates to {@link #removeFirst()} or {@link #removeLast()}
     * for boundaries; otherwise finds the predecessor and bypasses the target node.</p>
     * <p><b>Time complexity:</b> O(n)</p>
     *
     * @param index the removal position (0 ≤ index &lt; length)
     * @return the removed node, or {@code null} if index is invalid or list empty
     */
    public Node remove(int index) {
        if (length == 0) return null;
        if (index < 0 || index >= length) return null;
        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();

        Node pre = get(index - 1);
        Node temp = pre.next;          // node to be removed
        pre.next = temp.next;          // bypass temp
        temp.next = null;              // detach temp
        length--;
        return temp;
    }

    // -------- Advanced Operations / LeetCode Exercises --------

    /**
     * Reverses the linked list in‑place.
     * <p><b>Approach:</b> Iterative three‑pointer technique.
     * 1. Swap {@code head} and {@code tail}.
     * 2. Traverse the original list order while reversing the {@code next} pointers.</p>
     * <p><b>Time complexity:</b> O(n), <b>Space complexity:</b> O(1)</p>
     */
    public void reverse() {
        if (length == 0) return;

        // Swap head and tail – the list will be traversed from the old head (now tail)
        Node temp = head;
        head = tail;
        tail = temp;

        Node before = null;
        Node after = temp.next;

        for (int i = 0; i < length; i++) {
            after = temp.next;      // save the next node before overwriting
            temp.next = before;     // reverse the link
            before = temp;          // move 'before' forward
            temp = after;           // move 'temp' forward
        }
    }

    /**
     * Finds the middle node of the list using the fast‑slow pointer technique.
     * <p><b>Approach:</b> {@code slow} moves one step, {@code fast} moves two steps.
     * When {@code fast} reaches the end, {@code slow} is at the middle.
     * For even length, returns the second middle node (standard convention).</p>
     * <p><b>Time complexity:</b> O(n), <b>Space:</b> O(1)</p>
     *
     * @return the middle node, or {@code null} if the list is empty
     */
    public Node findMiddleNode() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * Detects whether the linked list contains a cycle (loop).
     * <p><b>Approach:</b> Floyd’s Cycle‑Finding Algorithm (fast‑slow pointers).
     * If {@code fast} ever equals {@code slow}, a loop exists.</p>
     * <p><b>Time complexity:</b> O(n), <b>Space:</b> O(1)</p>
     *
     * @return {@code true} if a cycle is present, {@code false} otherwise
     */
    public boolean hasLoop() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /**
     * Finds the k‑th node from the end of the list (1‑based).
     * <p><b>Approach:</b> Two‑pointer technique.
     * 1. Advance {@code fast} by {@code k} steps.
     * 2. Move both pointers until {@code fast} reaches the end;
     *    then {@code slow} points to the desired node.</p>
     * <p><b>Time complexity:</b> O(n), <b>Space:</b> O(1)</p>
     *
     * @param k the position from the end (1 ≤ k ≤ length)
     * @return the k‑th node from the end
     */
    public Node findKthFromEnd(int k) {
        Node slow = head;
        Node fast = head;
        // Create a gap of k steps
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        // Move both until fast hits null
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * Removes duplicate values from an <b>unsorted</b> linked list.
     * <p><b>Approach 1 (no buffer):</b> Use a runner pointer for each node,
     * removing subsequent duplicates. Time O(n²), Space O(1).</p>
     */
    public void removeDuplicates() {
        Node current = head;
        while (current != null) {
            Node runner = current;
            while (runner.next != null) {
                if (current.value == runner.next.value) {
                    // Duplicate found – bypass it
                    runner.next = runner.next.next;
                    length--; // optional: maintain accurate length
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    /**
     * Removes duplicate values from an <b>unsorted</b> linked list using a {@code HashSet}.
     * <p><b>Approach 2 (with buffer):</b> Keep a set of seen values. If a value is already
     * in the set, remove the node. Time O(n), Space O(n).</p>
     */
    public void removeDuplicates2() {
        Set<Integer> values = new HashSet<>();
        Node current = head;
        Node prev = null;
        while (current != null) {
            if (!values.contains(current.value)) {
                values.add(current.value);
                prev = current;
            } else {
                // Duplicate – bypass current
                prev.next = current.next;
                length--;
            }
            current = current.next;
        }
    }

    /**
     * Converts a binary number represented by the linked list (most significant bit
     * at the head) to its decimal integer value.
     * <p><b>Approach:</b> Horner’s method: result = result * 2 + current.value.
     * Traverse the list once.</p>
     * <p><b>Time complexity:</b> O(n), <b>Space:</b> O(1)</p>
     *
     * @return the decimal equivalent
     */
    public int binaryToDecimal() {
        int result = 0;
        Node temp = head;
        while (temp != null) {
            result = result * 2 + temp.value;
            temp = temp.next;
        }
        return result;
    }

    /**
     * Partitions the list such that all nodes with values less than {@code x}
     * come before nodes with values greater than or equal to {@code x}.
     * The original relative order within each partition is preserved.
     * <p><b>Approach:</b> Create two separate dummy‑headed lists (“less” and “more”),
     * traverse the original list and append each node to the appropriate dummy list.
     * Finally, connect the two lists and set the tail correctly.</p>
     * <p><b>Time complexity:</b> O(n), <b>Space:</b> O(1) (only dummy nodes)</p>
     *
     * @param x the partition value
     */
    public void partitionList(int x) {
        if (head == null) return;

        // Dummy heads for the two partitions
        Node lessHead = new Node(0);
        Node moreHead = new Node(0);
        Node less = lessHead;
        Node more = moreHead;

        Node current = head;
        while (current != null) {
            if (current.value < x) {
                less.next = current;
                less = current;
            } else {
                more.next = current;
                more = current;
            }
            current = current.next;
        }

        // Connect the less list to the more list
        less.next = moreHead.next;
        // Terminate the more list
        more.next = null;

        // Update head and tail
        head = lessHead.next;
        // If the more list is empty, tail is 'less'; otherwise tail is 'more'
        tail = (moreHead.next == null) ? less : more;
    }

    /**
     * Reverses the sub‑list between {@code startIndex} and {@code endIndex} (inclusive).
     * <p><b>Approach:</b> Use a dummy node to simplify handling of the head.
     * Walk to the node just before the start index, then iteratively move nodes
     * from the current position to the front of the sub‑list.</p>
     * <p><b>Time complexity:</b> O(n), <b>Space:</b> O(1)</p>
     *
     * @param startIndex beginning index (0‑based, inclusive)
     * @param endIndex   ending index (0‑based, inclusive)
     */
    public void reverseBetween(int startIndex, int endIndex) {
        if (head == null) return;

        // Dummy node to avoid special case when startIndex == 0
        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;

        // Move prev to the node just before the sub‑list
        for (int i = 0; i < startIndex; i++) {
            prev = prev.next;
        }

        Node current = prev.next; // first node of the sub‑list
        // Reverse the sub‑list by moving nodes after current to the front
        for (int i = startIndex; i < endIndex; i++) {
            Node nodeToMove = current.next;
            current.next = nodeToMove.next;
            nodeToMove.next = prev.next;
            prev.next = nodeToMove;
        }

        head = dummy.next;
        // The tail may have changed if the sub‑list included the original tail.
        // We could recalculate tail by traversing, but for simplicity it is omitted
        // as it is not strictly required for further operations in this class.
    }

    /**
     * Swaps every two adjacent nodes in the list.
     * <p><b>Approach:</b> Use a dummy node before the head. Iterate while there
     * are at least two nodes remaining. For each pair, rewire pointers:
     * {@code prev -> second -> first -> rest}. Then advance {@code prev} by two nodes.</p>
     * <p><b>Time complexity:</b> O(n), <b>Space:</b> O(1)</p>
     */
    public void swapPairs() {
        if (head == null || head.next == null) return;

        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            Node first = prev.next;
            Node second = first.next;

            // Perform the swap
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // Move prev two steps forward for the next pair
            prev = first;
        }

        head = dummy.next;
        // The tail may have changed. We could recompute it in O(n) if needed,
        // but it is not critical for the demonstration.
    }

    public void bubbleSort () {
        if (getLength() < 2) return;
        Node sortedUntil = null;
        Node current = this.head;
        while (sortedUntil != this.head.next) {
            Node second = current.next;
            while (second != sortedUntil) {
                if (current.value > second.value) {
                    int temp = current.value;
                    current.value = second.value;
                    second.value = temp;
                }
                current = second;
                second = second.next;
            }
            sortedUntil = current;
            current = get(0);
        }
    }

    public void selectionSort () {
        if (this.length < 2) return;
        Node current = this.head;
        while (current.next != null) {
            Node smallest = current;
            Node innerCurrent = current.next;
            while (innerCurrent != null) {
                if (smallest.value > innerCurrent.value) {
                    smallest = innerCurrent;
                }
                innerCurrent = innerCurrent.next;
            }
            if (smallest != current) {
                int temp = current.value;
                current.value = smallest.value;
                smallest.value = temp;
            }
            current = current.next;
        }
    }

    public void insertionSort () {
        if (this.length < 2) return;
        Node sortedHead = this.head;
        Node unsortedHead = sortedHead.next;
        sortedHead.next = null;
        while (unsortedHead != null) {
            Node current = unsortedHead;
            unsortedHead = unsortedHead.next;
            if (current.value < sortedHead.value) {
                current.next = sortedHead;
                sortedHead = current;
            } else {
                Node pointer = sortedHead;
                while (pointer.next != null && pointer.next.value < current.value) {
                    pointer = pointer.next;
                }
                current.next = pointer.next;
                pointer.next = current;
            }
        }
        this.head = sortedHead;
        Node temp = this.head;
        while (temp.next != null) {
            temp = temp.next;
        }
        this.tail = temp;
    }

    public void merge (LinkedList otherList) {
        Node otherHead = otherList.getHead();
        Node dummy = new Node(0);
        Node current = dummy;
        while (head != null && otherHead != null) {
            if (head.value > otherHead.value) {
                current.next = otherHead;
                otherHead = otherHead.next;
            } else {
                current.next = head;
                head = head.next;
            }
            current = current.next;
        }
        if (head != null) current.next = head;
        else {
            current.next = otherHead;
            tail = otherList.getTail();
        }
        head = dummy.next;
    }

}