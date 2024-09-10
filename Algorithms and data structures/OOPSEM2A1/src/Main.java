import java.util.Scanner;
public class Main {

    public static void main(String args[]) {
        int n = 26; //length of alphabet
        char[] alphabet = new char[n];
        for (int i = 0; i < n; i++) {
            alphabet[i] = (char) ('a' + i); //populates the alphabet array by using ASCII values that change through a loop
        }
        boolean choice = false; //boolean that represents a correct input (s or e)
        Scanner scanner = new Scanner(System.in); //scanner is created
        while (!choice) { //the program will continue to ask for an input as long as e or s has not been selected
            System.out.println("\nChose to start from the start (type s) or the end (type e)");
            String userInput = scanner.next();
            // user input is stored. equals method is used to compare the user's input and decide if the aphabet is to be used from the start or the end
            if ("s".equalsIgnoreCase(userInput.trim())) {
                choice = true; // boolean is updated and loop ends
                alphabetGameS(alphabet, n, scanner);
                break;
            }
            if ("e".equalsIgnoreCase(userInput.trim())) {
                choice = true; // boolean is updated and loop ends
                alphabetGameE(alphabet, scanner);
                break;
            } else {
                System.out.println("\nInvalid input. Valid inputs are 's' or 'e'"); //loop will continue to run as s or e hasn't been selected
            }
        }
        scanner.close(); //scanner is closed
    }

    public static void alphabetGameS(char[] alphabet, int n, Scanner scanner) {
        System.out.println("\nFirst letter is a");
        long startTime = System.currentTimeMillis(); //timer is started
        for (int k = 0; k < n; k++) {
            String answer = scanner.next();
            if(answer.equalsIgnoreCase(String.valueOf(alphabet[k])) && k < 25) { //checks if answer is correct and that the alphabet array hanst reached the end
                System.out.println("\nCorrect! Next letter is: " + alphabet[k + 1]);
            } else if (!answer.equalsIgnoreCase(String.valueOf(alphabet[k]))){
                System.out.println("\nIncorrect! Try again");
                k -= 1; //this removes that answer as a guess as it wasn't correct
            }
            if(answer.equalsIgnoreCase(String.valueOf(alphabet[n - 1])) && k == n - 1) { //once the last letter has been typed in and the array is on it's last element, the loop ends
                System.out.println("\nSuccess! Game Over");
                long endTime = System.currentTimeMillis(); //end time
                double totalTime = (endTime - startTime) / 1000.0; //total time elapsed, in seconds
                System.out.println("\nTotal time taken: " + totalTime + " seconds.");
                break; //game is finished
            }
        }
    }
// this method works much the same as the other one, except the array is used starting from the last element.
    public static void alphabetGameE(char[] alphabet, Scanner scanner) {
        System.out.println("\nFirst letter is z");
        long startTime = System.currentTimeMillis();
        for (int k = 25; k >= 0; k--) { //loop instead starts at the end of the array, and iterates towards zero, the first element
            String answer = scanner.next();
            if(answer.equalsIgnoreCase(String.valueOf(alphabet[k])) && k > 0) {
                System.out.println("\nCorrect! Next letter is: " + alphabet[k - 1]);
            } else if (!answer.equalsIgnoreCase(String.valueOf(alphabet[k]))){
                System.out.println("\nIncorrect! Try again");
                k += 1; // guess is removed as was incorrect
            }
            if(answer.equalsIgnoreCase(String.valueOf(alphabet[0])) && k == 0) { //once the last letter has been typed in and the array is on it's first element, the loop ends
                System.out.println("\nSuccess! Game Over");
                long endTime = System.currentTimeMillis();
                double totalTime = (endTime - startTime) / 1000.0; //total time elapsed, in seconds
                System.out.println("\nTotal time taken: " + totalTime + " seconds.");
                break;
            }
        }
    }
}
