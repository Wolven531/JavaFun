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
 * This class is used to obtain input from the user
 *
 * @author Anthony Williams
 */
public class Prompter {

    /**
     * This method is used to obtain a string value from the user
     *
     * @param prompt The string message to display to user (to prompt for input)
     * @param errorMsg The string message to display to user when response is invalid
     * @return A PrompterStringResult containing user response information
     */
    public static PrompterStringResult PromptUserForString(String prompt, String errorMsg) {
        String userEntry = "";
        int attempts = 0;

        // NOTE: prepare to obtain user input
        try (Scanner userInputScanner = new Scanner(System.in)) {
            // NOTE: keep trying as long as long as value is empty
            while (userEntry.length() < 1) {
                attempts++;

                // NOTE: validation failed at least once, display error
                if (attempts > 1) {
                    System.out.println(errorMsg);
                }

                // NOTE: display prompt and obtain (wait for) next input
                System.out.println(prompt);
                userEntry = userInputScanner.nextLine();
            }
        }

        return new PrompterStringResult(userEntry, attempts);
    }
}
