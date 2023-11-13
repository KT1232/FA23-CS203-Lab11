import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        String raw = readFile(inputFilePath);
        String edited = "";
        for(int i=0; i < raw.length(); i++) {
            char e = (char) raw.charAt(i);
            if ((e >= 'a' && e <= 'z')) {
                 e += shift;
                Character.toString(e);
                int m = Integer.valueOf(e);
                if (m > 122) {
                e = 'a';
                e += m%122 -1;
                }
                edited+=e;
        }
            else if ((e >= 'A' && e <= 'Z')) {
                 e += shift;
                Character.toString(e);
                int m = Integer.valueOf(e);
                if (m > 90) {
                e = 'A';
                e += m%90 -1;
                }
                edited+= e;
            }
            else  {
                edited+=e;
            }
        }
       System.out.println(edited);
        
        

       // writeFile(, encryptedFilePath);
        //TODO: Call the read method, encrypt the file contents, and then write to new file
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        String raw = readFile(messageFilePath);
        String edited = "";
        for(int i=0; i < raw.length(); i++) {
            char e = (char) raw.charAt(i);
            if ((e >= 'a' && e <= 'z')) {
                e-=shift;
                Character.toString(e);
                int m = Integer.valueOf(e);
                if (m < 97) {
                e = 'z';
                e -= 97-m -1;
                
                }
                edited+=e;
        }
            else if ((e >= 'A' && e <= 'Z')) {
                 e -= shift;
                Character.toString(e);
                int m = Integer.valueOf(e);
                if (m < 65) {
                e = 'Z';
                e -= 65-m  - 1;
                }
                edited+= e;
            }
            else  {
                edited+=e;
            }
        }
       System.out.println(edited);
        //TODO: Call the read method, decrypt the file contents, and then write to new file
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        String message = "";
        try (Scanner fileScanner = new Scanner(Paths.get(filePath))) {
            while(fileScanner.hasNextLine()) {
                 message += fileScanner.nextLine();
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error:" + e.toString());
        }
         return message;
        }
        //TODO: Read file from filePath


    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        try (PrintWriter output = new PrintWriter("output.txt")) {
            output.println(data);
            output.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        //TODO: Write to filePath
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
