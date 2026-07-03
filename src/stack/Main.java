package stack;

public class Main {
    public static String reverse (String s) {
        Stack2<Object> stack = new Stack2<>();
        StringBuilder reversedString = new StringBuilder();
        for (char c : s.toCharArray()) {
            stack.push(c);
        }
        while (!stack.isEmpty()) {
            reversedString.append(stack.pop());
        }
        return reversedString.toString();
    }

    public static boolean isBalancedParentheses (String s) {
        int balance = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') balance++;
            else if (c == ')') balance--;
        }
        return balance == 0;
    }

    public static Stack2<Integer> sortStack (Stack2<Integer> stack) {
        Stack2<Integer> sortedStack = new Stack2<Integer>();
        sortedStack.push(stack.pop());
        while (!stack.isEmpty()) {
            int temp = stack.pop();
            if (temp > sortedStack.peek()) {
                while (!sortedStack.isEmpty() && sortedStack.peek() < temp) {
                    stack.push(sortedStack.pop());
                }
            }
            sortedStack.push(temp);
        }
        return sortedStack;
    }

    static void main (String[] args) {

    }
}
