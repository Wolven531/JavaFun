/*
 * The MIT License
 *
 * Copyright 2018 Anthony Williams.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package javafun;

import java.util.Scanner;

/**
 *
 * @author Anthony Williams
 */
public class JavaFun {
    /**
     * Reference Materials:
     *
     * System properties:
     *  https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
     * String Formatting:
     *  https://dzone.com/articles/java-string-format-examples
     * Try with resources:
     *  https://www.baeldung.com/java-try-with-resources
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String currentCodeLocation = "[javafun.JavaFun.main()]";
        String greeting = currentCodeLocation.concat(" Ello! 😊");
        String usernamePrompt = "Input a username and press [Enter]";
        String username;
        // NOTE: the below var is equal to a %n in formatted string
        // String newLineStr = System.getProperty("line.separator");

        System.out.println("Running...");
        System.out.println(greeting);
        System.out.println(usernamePrompt);

//        // NOTE: traditional way
//        Scanner userInput = new Scanner(System.in);
//        username = userInput.next();
//        // NOTE: always close scanners
//        userInput.close();

        // NOTE: new way, try with resources
        try (Scanner userInput = new Scanner(System.in)) {
            username = userInput.next();
        }

        String formattedUsername = String.format("\tFormatted: %s%n", username);

        System.out.printf("Username='%s'%n", username);
        System.out.print(formattedUsername);
    }
}
