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
 * Reference Materials:
 *
 * System properties:
 *  https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
 * String Formatting:
 *  https://dzone.com/articles/java-string-format-examples
 * Try with resources:
 *  https://www.baeldung.com/java-try-with-resources
 *
 * @author Anthony Williams
 */
public class JavaFun {
    private static final String GREETING = "Ello! 😊";
    private static final String PROMPT_TEXT_USERNAME = "Input a username and press [Enter]";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String currentCodeLocation = "[javafun.JavaFun.main()]";
        String locationAndGreeting = currentCodeLocation.concat(" ").concat(GREETING);
        String username;
        // NOTE: Variable below and %n in formatted strings are equal
        // String newLineStr = System.getProperty("line.separator");

        System.out.println(locationAndGreeting);
        System.out.println(JavaFun.PROMPT_TEXT_USERNAME);

        // Update username with user input (using try with resources)
        try (Scanner userInput = new Scanner(System.in)) {
            username = userInput.next();
        }

        System.out.printf("Username = '%s'%n", username);
    }
}
