import model.Decrypter;
import model.Encrypter;
import view.UserIO;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        UserIO.out("Welcome to our Decrypter/Encrypter\n" +
                "Follow the menu options to continue");
        while (true) {
            UserIO.out("Press 1. For Encryption/Decryption");
            UserIO.out("Press 2. For Cryptanalysis");
            int selection = UserIO.readInt("Input a whole number between 1 and 2", 1, 2);

            switch (selection) {
                case 1:
                    //do something
                    encrpytOrDecrpyt();
                    break;
                case 2:
                    cryptanalysis();
                    break;
            }
        }
    }

    public static void encrpytOrDecrpyt() {
        UserIO.out("===Decrypter/Encrypter Menu===");
        UserIO.out("Press 1. For Encryption");
        UserIO.out("Press 2. For Decryption");
        int selection = UserIO.readInt("Input a whole number between 1 and 2", 1, 2);
        Path filePath = UserIO.getPath(String.format("Please enter the file you want to %s", selection == 1 ? "Encrpyt" : "Decrypt"));
        UserIO.out(String.format("Please enter the key for %s", selection == 1 ? "Encrpytion" : "Decryption"));
        int key = UserIO.readInt("Input a whole number greater than 0", 0, Integer.MAX_VALUE);
        switch (selection) {
            case 1:
                //do something
                Encrypter encrypter = new Encrypter(filePath, key);
                encrypter.encrypt();
                break;
            case 2:
                Decrypter decrypter = new Decrypter(filePath, key);
                decrypter.decrypt();
                break;
        }
    }

    public static void cryptanalysis() {
        UserIO.out("Welcome to our Cryptanalysis\n" +
                "Follow the menu options to continue");
        UserIO.out("Press 1. For Brute Force");
        UserIO.out("Press 2. For Statistical Analysis Method.");
        int selection = UserIO.readInt("Input a whole number between 1 and 2", 1, 2);
        Path filePath = UserIO.getPath("Please enter the file you want to Decrypt");
        Decrypter decrypter = new Decrypter(filePath, 0);
        switch (selection) {
            case 1:
                //Brute force
                int keyUsed = decrypter.bruteForce();
                switch (keyUsed) {
                    case -1:
                        UserIO.out("Could not find the key used to decrypt");
                        break;
                    default:
                        UserIO.out("The key used to decrypt the file was " + keyUsed);
                }
                break;
            case 2:
                //statistical Analysis
                Path encryptedFile = UserIO.getPath("Please enter a decrypted file that you want to compare to");
                keyUsed = decrypter.statisticalAnalysis(encryptedFile);
                switch (keyUsed) {
                    case -1:
                        UserIO.out("Could not find the key used to decrypt");
                        break;
                    default:
                        UserIO.out("The key used to decrypt the file was " + keyUsed);
                }
                UserIO.out("The key used to decrypt the file was " + keyUsed);
                break;
        }
    }

}