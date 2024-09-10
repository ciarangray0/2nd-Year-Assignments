import javax.swing.JOptionPane;
public class InflixToPostfix {
    public static void main(String args[]) {
        //initialize the input string and it's length
        int length = 0;
        String input = "";
        //loop will run asking for input until the booleans for correct length and correct characters are set to true
        while (true) {
            boolean corrLength = false; //boolean that is true/false depending if the length is corect
            boolean corrCharacters = false;
            input = JOptionPane.showInputDialog(null, "Enter the infix expression (3-20 characters long, containing only 0-9, *, +, -, /, ^, (, )): "); //text box to recieve the input
            if (input == null) {
                break;
            }
            length = input.length();
            //check length
            if (3 <= length && length <= 20) {
                corrLength = true;
            }
            for (int k = 0; k < length; k++) { //checking if the character is a number/operand/paranthesis
                if ((isNumber(input.charAt(k)) || isOperand(input.charAt(k)) || input.charAt(k) == '(' || input.charAt(k) == ')')) {
                    corrCharacters = true;
                }
            }
            if(corrLength && corrCharacters) {
                break;
            }
            else if(!corrLength) {
                JOptionPane.showMessageDialog(null, "Input must be between 3-20 characters.");
            }
            else if(!corrCharacters) {
                JOptionPane.showMessageDialog(null, "Only accepted characters are 0-9, ^, *, /, +, -, (, ) ");
            }
        }
        char[] inflix = new char[length];
        for (int i = 0; i < length; i++) {
            inflix[i] = input.charAt(i);
        }
        JOptionPane.showMessageDialog(null, "Postfix expression: " + postfix(inflix, length) + "\nResult = " + calculateResult(postfix(inflix, length)));
    }
    public static String postfix(char[] inflix, int length) {
        //initialize output string and arraystack object
        String output = "";
        ArrayStack s = new ArrayStack(length);
        for (char c : inflix) { //first check if character is a numer, and add it to output string
            if (isNumber(c)) {
                output += c;
            }
            else {
                if(isOperand(c)) { //if the character is a following, we will check the following; *note that i am casting the object at the top of the stack as an integer to compare it with the presedence of the curent operator
                    if (s.isEmpty() || operandValue(c) > ((Integer) s.top()).intValue()) { //if stack is empty, or the current operand has a higher presendence than the character at the top of the stack, push the operand's corresponding presedence to the stack
                        s.push(operandValue(c));
                    }
                    else { //if the character doesn't have a higher presedence than the operator at the top of the stack:
                        while (!s.isEmpty() && operandValue(c) <= ((Integer) s.top()).intValue() && ((Integer) s.top()).intValue() > 2) { //pop every operator with higher presedence from the stack to the output string while the stack isn't empty and untiol we encounter '(' [2]
                            output += intToChar(((Integer) s.pop()).intValue()); //here we have to cast the object returned from the array stack to an integer, and then through the intToChar() method for the corresponding operator
                        }
                        s.push(operandValue(c)); //push the current operator's corresponding presedence value to the stack
                    }
                }
                if (operandValue(c) == 2) { // if we encounter a '(', we'll push it to the stack
                    s.push(operandValue(c));
                }
                else if (operandValue(c) == 1) { //if we encounter ')', we'll pop everything from the stack to the output string until we encounter another paranthesis or the stack is empty
                    while(!s.isEmpty() && ((Integer) s.top()).intValue() > 2) {
                        output += intToChar(((Integer) s.pop()).intValue());
                    }
                    s.pop(); //discard the paranthesis
                }
            }
        }
        //pop everything left in the stack to the output string, but if we encounter '(' or ')' , we discard them
        while (!s.isEmpty()) {
            if (((Integer) s.top()).intValue() <= 2) {
                s.pop();
            }
            else {
                output += intToChar(((Integer) s.pop()).intValue());
            }
        }
        return output; //return the output
    }
    public static boolean isNumber (char c) {
        if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean isOperand (char c) {
        if (c == '^' || c == '*' || c == '/' || c == '+' || c == '-') {
            return true;
        }
        else {
            return false;
        }
    }
    //method to get the presedence of each Symbol
    public static int operandValue(char c) {
        if (c == '^') {
            return 7;
        }
        else if (c == '*') {
            return 6;
        }
        else if (c == '/') {
            return 5;
        }
        else if (c == '+') {
            return 4;
        }
        else if (c == '-') {
            return 3;
        }
        else if (c == '(') {
            return 2;
        }
        else if (c == ')') {
            return 1;
        }
        return 0;
    }
    //method to retrieve the corresponding character from each symbol's presedence value
    public static char intToChar(int value) {
        if (value == 7) {
            return '^';
        }
        else if (value == 6) {
            return '*';
        }
        else if (value == 5) {
            return '/';
        }
        else if (value == 4) {
            return '+';
        }
        else if (value == 3) {
            return '-';
        }
        else if (value == 2) {
            return '(';
        }
        else if (value == 1) {
            return ')';
        }
        return ' ';
    }
    private static float calculateResult(String output) {
        float number1, number2, result= 0; //initialize variables
        int aLength = output.length(); //length of output
        ArrayStack stack = new ArrayStack(aLength);
        for (int i = 0; i < aLength; i++) { //loop to check every character in the string
            if (isNumber(output.charAt(i))) { //if the current character is a number:
                float numValue = Character.getNumericValue(output.charAt(i)); //get the numerical value of the character and push it to the stack
                stack.push(numValue);
            }
            else if(isOperand(output.charAt(i))) { //if the current character is an operand, pop the two numbers at the top of the stack
                number2 = (float)stack.pop(); //here i'm casting the objects as floating point numbers to avoid compution errors
                number1 = (float)stack.pop();
                //performing various operations on the two numbers depending on what the current operand is, and pushing the result to the stack
                if(output.charAt(i) == '^') {
                    stack.push((float)Math.pow(number1, number2));;
                }
                else if(output.charAt(i) == '*') {
                    stack.push((float)number1*number2);
                }
                else if(output.charAt(i) == '/') {
                    stack.push((float)number1/number2);
                }
                else if(output.charAt(i) == '+') {
                    stack.push((float)number1+number2);
                }
                else if(output.charAt(i) == '-') {
                    stack.push((float)number1-number2);
                }
            }
        }
        result = (float)stack.pop(); //when every character in the ouput string has been checked, pop the result from the stack and return it
        return result;
    }
}
