package Console;

import Classes.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static IWoordenProcessor wp = new WoordenProcessor();
    static String inputText = "";

    private static void showIntro() {
        System.out.println("JCF ASSIGNMENT 2");
        System.out.println("Welcome to the JCF (assignment 2) application.");
        System.out.println("Created by Berend and Rick.");
        System.out.println("==============================");
        System.out.println("");

        System.out.println("The functionality of this application requires a base string to work with.");

        showIntro2();
    }

    private static void showIntro2() {
        System.out.println("Please input a string (or leave empty for a default string) and then press ENTER: ");

        // Read input
        inputText = readStringInputFromConsole();
        if (inputText.isEmpty()) {
            // Use the default string instead.
            inputText = "This is an example string!";
        }

        showIntro3();
    }

    private static void showIntro3() {
        System.out.println("This application is capable of showcasing various kinds of functionality.");
        System.out.println("Some functions may be locked, first requiring the execution of another method.");
        System.out.println("0. Change input string. Current string: " + inputText);
        System.out.println("1. Count unique characters.");
        System.out.println("2. Compress a string with Huffman logic.");
        System.out.println("3. Save the encoded string to a file.");
        System.out.println("4. Decode a local file and output its uncompressed data.");
        System.out.println("Please input the digit (1-9) of the action you'd like to execute and then press ENTER: ");

        switch (readIntegerInputFromConsole()) {
            case 0:
                showIntro2();
                break;

            case 1:
                System.out.println("Frequency of unique words:");
                System.out.println(wp.frequentie(inputText));
                System.out.println("==============================");
                showIntro3();
                break;

            case 2:
                System.out.println("The application will now losslessly compress the string: " + inputText);
                System.out.println("This might take a few seconds, depending on how horribly unoptimized our behind-the-scenes logic actually is.");

                // Might as well keep track of how long it actually takes to perform this task.
                long start = System.currentTimeMillis();

                HuffmanTree tree = new HuffmanTree(inputText);

                long end = System.currentTimeMillis();
                break;

            default:
                // Don't be bullshittin' me, mate.
                System.out.println("Yeah... no. That's not something I can work with.");
                showIntro3();
                break;
        }
    }

    public static void main(String[] args) {
        showIntro();
    }

    private static String readStringInputFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static Integer readIntegerInputFromConsole() {
        Scanner scanner = new Scanner(System.in);

        try {
            return scanner.nextInt();
        }
        catch (InputMismatchException ex) {
            return -1;
        }
    }
}
