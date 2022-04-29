import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main{

    public static char[] validChars;
    public static void main(String[] args){

        File file = new File("validCharacters.txt");

        try {
            
            Scanner scanner = new Scanner(file);
            validChars = scanner.nextLine().toCharArray(); //Generates an array of valid charcters that the password may use.
            scanner.close();

            
            Scanner userIn = new Scanner(System.in);
            System.out.print("How many characters in your password? ");
            int charNum = userIn.nextInt();
            System.out.print("How many passwords? ");
            int passwordNum = userIn.nextInt();
            userIn.close();


            String[] passwordList = generator(charNum, passwordNum, new Random());

           
            FileWriter fileWriter = new FileWriter("passwords.txt");

            for(int i=0; i<passwordNum;i++){

                fileWriter.write(passwordList[i]+"\n");

            }

            fileWriter.close();


        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        } catch (IOException e){
            e.printStackTrace();
        }

    }


    public static String[] generator(int charNum, int passwordNum, Random rand){

        //Generates a list of valid passwords.

        String[] passwords = new String[passwordNum];
        
        for(int i=0; i<passwordNum; i++){

            do{

                passwords[i] = "";
                for(int j=0; j<charNum; j++){
                    passwords[i] += (char) (rand.nextInt(94)+33);
                }

            } while(!checker(passwords[i]));

            
        }

        return passwords;

    }

    public static boolean checker(String password){

        //Checks each generated password against the valid characters.

        for(int i=0; i<password.length(); i++){

            boolean result = false;

            for(int j=0; j<validChars.length; j++){

                if(password.charAt(i) == validChars[j]){
                    result = true;
                    break;
                }

            }
            if(!result){
                return false;
            }
        }
        return true;


    }


}