import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Decode_DNA {

    public static void main(String[] args)
            throws FileNotFoundException, UnsupportedEncodingException {

        //keyboard input
        Scanner userIn = new Scanner(System.in);

        //ask for filename to decode
        System.out.print("Please enter filepath of encoded message: ");
        Scanner fileIn = new Scanner(new File(userIn.nextLine()));

        //read input into a String and error check
        String s = fileIn.next();
        fileIn.close();
        assert s.length()
                % 8 == 0 : "Text must consist of complete binary octets";

        //ask for a filename to write to
        System.out.print(
                "Please enter a filepath to write decoded message to (include file extensions): ");
        PrintWriter out = new PrintWriter(userIn.nextLine(), "UTF-8");
        userIn.close();

        //declare second string variable to translate to
        String bin = "";

        /*
         * Translate nucleotides into binary, such that: [A and T = 0], [C and G
         * = 1]
         */
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A' || s.charAt(i) == 'a' || s.charAt(i) == 'T'
                    || s.charAt(i) == 't') {
                bin += "0";
            } else if (s.charAt(i) == 'C' || s.charAt(i) == 'c'
                    || s.charAt(i) == 'G' || s.charAt(i) == 'g') {
                bin += "1";
            } else {
                assert false : "Text must only contain letters {A,T,C,G}";
            }
        }

        /*
         * Translate binary octets into characters, taking 8 binary characters
         * from the string at a time.
         */

        //while there are octets to translate
        while (bin.length() > 0) {

            //fetch octet
            String fragment = bin.substring(0, 8);
            bin = bin.substring(8);

            //translate to char from binary
            char c = (char) Integer.parseInt(fragment, 2);

            //print to output
            out.print(c);
        }

        //State when complete
        System.out.println("Decoding complete.");
        out.close();
    }

}
