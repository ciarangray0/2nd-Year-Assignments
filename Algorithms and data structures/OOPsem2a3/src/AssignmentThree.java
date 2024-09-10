public class AssignmentThree {
    //variables to store the times for each method
    private static long startTime1, endTime1, totalTime1;
    private static long startTime2, endTime2, totalTime2;
    private static long startTime3, endTime3, totalTime3;
    private static long startTime4, endTime4, totalTime4;
   //variables to store the primitive operations for each method
    private static long primOps1 = 0;
    private static long primOps2 = 0;
    private static long primOps3 = 0;
    private static long primOps4 = 0;

    public static void main(String[] args) {
        AssignmentThree a3 = new AssignmentThree();
        //variables to store the number of palidromes for each method
        long decPalidromes1 = 0;
        long binPalidromes1 = 0;
        long bothPalidromes1 = 0;
        long decPalidromes2 = 0;
        long binPalidromes2 = 0;
        long bothPalidromes2 = 0;
        long decPalidromes3 = 0;
        long binPalidromes3 = 0;
        long bothPalidromes3 = 0;
        long decPalidromes4 = 0;
        long binPalidromes4 = 0;
        long bothPalidromes4 = 0;

        startTime1 = System.currentTimeMillis();
        for (int i = 0; i <= 1000000; i++) { //loop that passes through 1,000,000 values
            String decimal = String.valueOf(i); //getting the decimal value of the current value
            String binary = decimalToBinary(decimal); //getting the binary value of the curent value
            if (reverseString(decimal)) {
                decPalidromes1++;
            }
            if (reverseString(binary)) {
                binPalidromes1++;
            }
            if (reverseString(decimal) && (reverseString(binary))) {
                bothPalidromes1++;
            }
            if(i % 50000 == 0) { //checks the number of primitve operations every 50,000 runs
                System.out.println("\nMethod 1 primitive Operations after 50000 checks: " + primOps1);
            }
        }
        endTime1 = System.currentTimeMillis();
        totalTime1 = endTime1 - startTime1; //total time taken for the function
        System.out.println("Method 1 Time : " + totalTime1);
        System.out.println("Method 1 Binary Palidromes: " + binPalidromes1 + " | Decimal Palidromes: " + decPalidromes1 + " | Both: " + bothPalidromes1);
        System.out.println("Primitive Operations: " + primOps1);
        //the following 4 loops work the same as the first, except they all call 1 of each 4 methods
        startTime2 = System.currentTimeMillis();
        for (int i = 0; i <= 1000000; i++) {
            String decimal = String.valueOf(i);
            String binary = decimalToBinary(decimal);
            if (compareElements(decimal)) {
                decPalidromes2++;
            }
            if (compareElements(binary)) {
                binPalidromes2++;
            }
            if (compareElements(decimal) && (compareElements(binary))) {
                bothPalidromes2++;
            }
            if(i % 50000 == 0) {
                System.out.println("\nMethod 2 primitive Operations after 50000 checks: " + primOps2);
            }
        }
        endTime2 = System.currentTimeMillis();
        totalTime2 = endTime2 - startTime2;
        System.out.println("Method 2 Time : " + totalTime2);
        System.out.println("Method 2 Binary Palidromes: " + binPalidromes2 + " | Decimal Palidromes: " + decPalidromes2 + " | Both: " + bothPalidromes2);
        System.out.println("Primitive Operations: " + primOps2);

        startTime3 = System.currentTimeMillis();
        for (int i = 0; i <= 1000000; i++) {
            String decimal = String.valueOf(i);
            String binary = decimalToBinary(decimal);
            if (stackCompToQueue(decimal)) {
                decPalidromes3++;
            }
            if (stackCompToQueue(binary)) {
                binPalidromes3++;
            }
            if (stackCompToQueue(decimal) && (stackCompToQueue(binary))) {
                bothPalidromes3++;
            }
            if(i % 50000 == 0) {
                System.out.println("\nMethod 3 primitive Operations after 50000 checks: " + primOps3);
            }
        }
        endTime3 = System.currentTimeMillis();
        totalTime3 = endTime3 - startTime3;
        System.out.println("Method 3 Time : " + totalTime3);
        System.out.println("Method 3 Binary Palidromes: " + binPalidromes3 + " | Decimal Palidromes: " + decPalidromes3 + " | Both: " + bothPalidromes3);
        System.out.println("Total primitive Operations: " + primOps3);

        startTime4 = System.currentTimeMillis();
        for (int i = 0; i <= 1000000; i++) {
            String decimal = String.valueOf(i);
            String binary = decimalToBinary(decimal);
            if (recursion(decimal)) {
                decPalidromes4++;
            }
            if (recursion(binary)) {
                binPalidromes4++;
            }
            if (recursion(decimal) && (recursion(binary))) {
                bothPalidromes4++;
            }
            if(i % 50000 == 0) {
                System.out.println("\nMethod 4 primitive Operations after 50000 checks: " + primOps4);
            }
        }
        endTime4 = System.currentTimeMillis();
        totalTime4 = endTime4 - startTime4;
        System.out.println("Method 4 Time : " + totalTime4);
        System.out.println("Method 4 Binary Palidromes: " + binPalidromes4 + " | Decimal Palidromes: " + decPalidromes4 + " | Both: " + bothPalidromes4);
        System.out.println("Total primitive Operations: " + primOps4);


    }

    public static boolean reverseString(String input) {
        String reversed = ""; primOps1 += 2; //2 primitive operations for instansiating the reversed string and the int i
        for(int i = input.length() - 1; i >= 0; i--) { //work through the string starting at the end and add each character to the reversed string
            reversed += input.charAt(i); primOps1 += 3; //3 primitive operations for adding a character to the reversed string, checking that i <= 0, and decrementing i
        }
        if(input.equals(reversed)) {
            primOps1 += 2; //2 primitive operations when we compare the strings, and return a value
            return true;
        }
        else {
            primOps1 += 2; //2 primitive operations when we compare the strings, and return a value
            return false;
        }
    }
    public static boolean compareElements(String input) {
        primOps2 += 1; //primitive operation for instansiating i
        for (int i = 0; i <= (input.length() - 1)/2; i++) {
            primOps2 += 2; //primitive operations for checking i<input length and incrementing i
            if(input.charAt(i) != input.charAt((input.length() - 1) - i)) { //if at any points the elements don't match, return false
                primOps2 += 2; //primitive operations for checking the ith element and the inputlength-1-ith element, and returning a value
                return false;
            }
        }
        primOps2 += 2; //primitive operations for checking the ith element and the inputlength-1-ith element, and returning a value
        return true; //if all the elements match
    }
    public static boolean stackCompToQueue(String input) {
        String stacknum = "";
        String queuenum = ""; primOps3 += 2; //2 primitive operations for instansiating the strings that will be used for holding the popped and dequeued strings
        ArrayStack stack = new ArrayStack(input.length());
        ArrayQueue queue = new ArrayQueue(input.length()); primOps3 += 3; //3 primiitive operations for instansiating the stack and queue objects and instansiating i
        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i)); //push each character from the string to the stack
            primOps3 += 2; //for incrementing i and pushing the input string
        }
        primOps3 += 1; //for instansiating k
        for (int k = 0; k < input.length(); k++) {
            queue.enqueue(input.charAt(k)); //enqueue each character from the string from the queue
            primOps3 += 2; //for incrementing k and enqueing the input string
        }
        while (!stack.isEmpty() && !queue.isEmpty()) { //pop and dequque to the two strings
            stacknum += (char) stack.pop();
            queuenum += (char) queue.dequeue();
            primOps3 += 3; //for checking that the queue and stack arent empty, and popping and dequeuing each character
                if (!stacknum.equals(queuenum)) { primOps3 += 2; //for comparing both the strings, and returning a value
                    return false; //if the strings arent equal, not a palidrome
                }
            }
        primOps3 += 2; //for comparing both the strings, and returning a value
        return true;
    }
    public static boolean recursion(String input) {
        primOps4 += 1; //for retrning a value, returns true or false based on if the strings match
        return input.equals(reverse(input)); //if the original and reversed string are equal, return true or false
    }
    public static String reverse(String input) {
        if (input.isEmpty()) {
            primOps4 += 2; //for checking if the input string is empty and returning it
            return input;
        } else {
            String reverse = reverse(input.substring(1));
            primOps4 += 2; //primitive operation for declaring the reversed string as a substring of the input string, excluding the first element , and another primitive operation for recalling the function and appending the first character to the end of the reversed substring
            return reverse + input.charAt(0); //
        }
    }

    public static String decimalToBinary(String decimal) {
        int decimalNum = Integer.parseInt(decimal); //converting the string to a decimal number
        StringBuilder binary = new StringBuilder(); //instansiating a new string builder
        if (decimalNum == 0) { //in the special case the number is ), we dont need to perform any operations on it
            return "0";
        }
        else {
            while(decimalNum > 0) { //loop will run until the decimal number hasnt been reduced to 0
                binary.insert(0, decimalNum % 2); //perform modulo operation on the number and insert the 1 or 0 at the beginning of the string builder, at index 0
                decimalNum = decimalNum / 2; //half the decimal number
            }
        }
        return binary.toString(); //convert the stringbuilder to a string
    }
}
