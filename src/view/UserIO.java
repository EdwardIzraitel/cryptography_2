package view;

import exceptions.NotAFileException;
import exceptions.OutOfBoundsException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public class UserIO {
    private static Scanner myScanner = new Scanner(System.in);

    private UserIO() {
    }

    public static void out(String message) { //String, Object
        System.out.println(message);
    }

    public static int readInt(String errorMessage, int lowerBound, int upperBound) {
        String text = myScanner.nextLine();
        int value = 0;
        while (true) {
            try {
                value = Integer.parseInt(text);
                if (value < lowerBound || value > upperBound) {
                    throw new OutOfBoundsException("");
                }
                return value;
            } catch (NumberFormatException | OutOfBoundsException error) {
                out(errorMessage);
                text = myScanner.nextLine();
            }
        }
    }

    public static String getString() {
        return myScanner.nextLine();

    }

    public static Path getPath(String message) {
        out(message);
        String path = getString();
        while (true) {
            try {
                Path filePath = Path.of(path);
                if (!Files.exists(filePath)) {
                    throw new InvalidPathException(path, "The path that you chose is not a valid path, try again");
                }
                if (Files.isDirectory(filePath)) {
                    throw new NotAFileException("The path you chose is not considered a File, try again");
                }
                return filePath;
            } catch (InvalidPathException | NotAFileException error) {
                out(error.getMessage());
                path = getString();
            }
        }


    }

}
