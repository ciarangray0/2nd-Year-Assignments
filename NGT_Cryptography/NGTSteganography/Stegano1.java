/**
* CT255 - Assignment 4
* Skeleton code for Steganography assignment.
*
* @author Michael Schukat
* @version 1.0
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// binary for ciaran: 01100011 01101001 01100001 01110010 01100001 01101110
//binary for gray: 01100111 01110010 01100001 01111001
public class Stegano1 {
    /**
    * Constructor for objects of class Stegano1
    */
    public Stegano1() {
    }

    public static void main(String[] args) {
        String arg1, arg2, arg3, arg4;
        boolean err = false;
        if (args != null && args.length > 1) { // Check for minimum number of arguments
            arg1 = args[0];
            arg2 = args[1];
            if (arg2.isEmpty()) {
                err = true;
            } else if ((arg1.equals("A")) && (args.length > 3)) {
                // Get other arguments
                arg3 = args[2];
                arg4 = args[3];
                if ((arg3.isEmpty()) || (arg4.isEmpty())) {
                    err = true;
                } else {
                    // Hide bitstring
                    hide(arg2, arg3, arg4);
                }
            } else if (arg1.equals("E")) {
                // Extract bitstring from text
                retrieve(arg2);
            } else {
                err = true;
            }
        } else {
            err = true;
        }
        if (err) {
            System.out.println();
            System.out.println("Use: Stegano1 <A:E><Input File><OutputFile><Bitstring>");
            System.out.println("Example: Stegano1 A inp.txt out.txt 0010101");
            System.out.println("Example: Stegano1 E inp.txt");
        }
    }

    static void hide(String inpFile, String outFile, String binString) {
        BufferedReader reader;
        BufferedWriter writer;
        try {
            reader = new BufferedReader(new FileReader(inpFile));
            writer = new BufferedWriter(new FileWriter(outFile));
            String line = reader.readLine();
            int bitIndex = 0;

            if (binString.length() % 2 != 0) {
                binString += "0";
            }

            while (line != null) {

                // Store amended line in output file
                if (bitIndex < binString.length()) {
                
                    //Problem 1
                    //Get current bit as character as '0' or '1'
                    char bit = binString.charAt(bitIndex);

                    if (bit == '0') {
                        line += " "; // Append one space for '0'
                    } else if (bit == '1') {
                        line += "  "; // Append two spaces for '1'
                    }
                }
            }
            writer.write(line);
            writer.newLine();
            // read next line
            line = reader.readLine();
            bitIndex++;
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void retrieve(String inpFile) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(inpFile));
            String line = reader.readLine();

            String result = "";
            while (line != null) {
                // Your code starts here
                 System.out.println(line);
                // read next line
                // If line ends with two spaces, append '1' to result
                if (line.endsWith("  ")) {
                    result += "1";
                }
                // If line ends with one space, append '0' to result
                else if (line.endsWith(" ")) {
                    result += "0";
                }
                // If line does not end with space, append nothing
                else {
                    result += " ";
                }
                line = reader.readLine();
            }
            System.out.println("Expected Output: \n\n");
            System.out.println("Actual Output: \n" + result);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}