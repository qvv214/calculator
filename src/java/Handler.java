import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Handler extends Calculate {
    private String reversePolishNotation = "";

    public String getReversePolishNotation() {
        return this.reversePolishNotation;
    }

    public void setReversePolishNotation (String inputString) {
        Set<Character> listOperations = Set.of('+','-','/','*');
        StringBuilder outputString = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        boolean flag = true;

        for (char element: inputString.toCharArray()) {
            if (Character.isDigit(element)) {
                if (flag) {
                    outputString.append(element);
                } else if (!flag && outputString.length() != 0) {
                    outputString.append(' ').append(element);
                    flag = true;
                } else if (!flag && outputString.length() == 0) {
                    outputString.append(element);
                    flag = true;
                }

            } else if (listOperations.contains(element)) {
                if (stack.isEmpty()) {
                    stack.add(element);
                } else if (element == '+' || element == '-') {
                    while (!stack.isEmpty()) {
                        outputString.append(' ').append(stack.pop());
                    }
                    stack.add(element);
                } else if (element == '*' || element == '/') {
                    if (stack.contains('*') || stack.contains('/')) {
                        while (!stack.isEmpty() && (stack.contains('*') || stack.contains('/'))) {
                            outputString.append(' ').append(stack.pop());
                        }
                    }
                    stack.add(element);
                }
                flag = false;
                
            } else {
                throw new IllegalArgumentException("Incorrect char: " + Character.toString(element));
            }
        }

        while (!stack.isEmpty()) {
            outputString.append(' ').append(stack.pop());
        }

        this.reversePolishNotation = outputString.toString();
    }

    public Integer calculations(String reversePolishNotation) {
        Stack<Integer> stack = new Stack<Integer>();
        String[] array = reversePolishNotation.split(" ");
        List<Integer> resultXY = new ArrayList<>();

        for (String element: array) {
            switch (element) {
                case "+": {
                    resultXY.addAll(getXY(stack, element));
                    stack.push(resultXY.get(1) + resultXY.get(0));
                    resultXY.clear();
                    break;
                }
                case "-": {
                    resultXY.addAll(getXY(stack, element));
                    stack.push(resultXY.get(1) - resultXY.get(0));
                    resultXY.clear();
                    break;
                }
                case "*": {
                    resultXY.addAll(getXY(stack, element));
                    stack.push(resultXY.get(1) * resultXY.get(0));
                    resultXY.clear();
                    break;
                }
                case "/": {
                    resultXY.addAll(getXY(stack, element));
                    stack.push(resultXY.get(1) / resultXY.get(0));
                    resultXY.clear();
                    break;
                }
                default: {
                    try {
                        Integer value = Integer.parseInt(element);
                        stack.add(value);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return stack.pop();
    }

    private List<Integer> getXY(Stack<Integer> stack, String operation) {
        List<Integer> result = new ArrayList<>();

        if (stack.size() > 1) {
            result.add(stack.pop());
            result.add(stack.pop());
        } else if (operation.contains("+") || operation.contains("-")) {
            result.add(stack.pop());
            result.add(0);
        } else throw new ArithmeticException("Incorrect operation");

        return result;
    }
}