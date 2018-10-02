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

import javafun.models.PrompterStringResult;
import java.io.PrintWriter;
import javafun.models.PrompterIntResult;

/**
 * This class is used to obtain input from the user
 *
 * @author Anthony Williams
 */
public class Prompter {

    /**
     * This is a convenience method to use standard input and output when prompting
     *
     * @param prompt
     * @param errorMsg
     * @return
     */
    public static PrompterStringResult PromptUserForString(String prompt, String errorMsg) {
        return PromptUserForString(new ConsoleScanner(System.in), new PrintWriter(System.out), prompt, errorMsg);
    }

    /**
     * This method is used to obtain a string value from the user
     *
     * @param reader A <code>ConsoleScanner</code> to read input from (usually created with System.in)
     * @param writer A <code>PrintWriter</code> to write output to (usually created with System.out)
     * @param prompt The string message to display to user (to prompt for input)
     * @param errorMsg The string message to display to user when response is invalid
     * @return A PrompterStringResult containing user response information
     */
    public static PrompterStringResult PromptUserForString(
        ConsoleScanner reader,
        PrintWriter writer,
        String prompt,
        String errorMsg) {
        String userEntry = "";
        int attempts = 0;

        // NOTE: keep trying as long as long as value is empty
        while (userEntry.length() < 1) {
            attempts++;

            // NOTE: validation failed at least once, display error
            if (attempts > 1) {
                writer.println(errorMsg);
            }

            // NOTE: display prompt and obtain (wait for) next input
            writer.println(prompt);
            userEntry = reader.nextLine();
        }

        return new PrompterStringResult(userEntry, attempts);
    }

    public static PrompterIntResult PromptUserForInt(String prompt, String errorMsg) {
        return PromptUserForInt(new ConsoleScanner(System.in), new PrintWriter(System.out), prompt, errorMsg);
    }

    public static PrompterIntResult PromptUserForInt(
        ConsoleScanner reader,
        PrintWriter writer,
        String prompt,
        String errorMsg) {
        int userEnteredInt = Integer.MIN_VALUE;
        int attempts = 0;

        // NOTE: keep trying as long as long as value is invalid
        while (userEnteredInt == Integer.MIN_VALUE) {
            attempts++;

            // NOTE: display prompt and obtain (wait for) next input
            writer.println(prompt);
            String userEnteredLine = reader.nextLine();

            try {
                int potentialInt = Integer.parseInt(userEnteredLine);
                userEnteredInt = potentialInt;
            } catch (NumberFormatException numberFormatEx) {
                // NOTE: validation failed, display error
                writer.println(errorMsg);
            }
        }

        return new PrompterIntResult(userEnteredInt, attempts);
    }

    public static PrompterIntResult PromptUserForIntInRange(String prompt, String errorMsg, int min, int max) {
        return PromptUserForIntInRange(new ConsoleScanner(System.in), new PrintWriter(System.out), prompt, errorMsg, min, max);
    }

    public static PrompterIntResult PromptUserForIntInRange(
        ConsoleScanner reader,
        PrintWriter writer,
        String prompt,
        String errorMsg,
        int min,
        int max) {
        int userEnteredInt = Integer.MIN_VALUE;
        int attempts = 0;

        // NOTE: keep trying as long as long as value is invalid
        while (userEnteredInt == Integer.MIN_VALUE) {
            attempts++;

            // NOTE: display prompt and obtain (wait for) next input
            writer.println(prompt);
            String userEnteredLine = reader.nextLine();

            try {
                int potentialInt = Integer.parseInt(userEnteredLine);

                if (potentialInt >= min && potentialInt <= max) {
                    userEnteredInt = potentialInt;
                } else {
                    // NOTE: validation failed, display error
                    writer.println(errorMsg);
                }
            } catch (NumberFormatException numberFormatEx) {
                // NOTE: validation failed, display error
                writer.println(errorMsg);
            }
        }

        return new PrompterIntResult(userEnteredInt, attempts);
    }

//    public static PrompterIntResult PromptUserForChoice(String prompt, String errorMsg, String[] choices) {
//        return PromptUserForChoice(new ConsoleScanner(System.in), new PrintWriter(System.out), prompt, errorMsg, choices);
//    }
//
//    public static PrompterIntResult PromptUserForChoice(
//            ConsoleScanner reader,
//            PrintWriter writer,
//            String prompt,
//            String errorMsg,
//            String[] choices) {
//        int numberOfChoices = choices.length - 1;
//        int userChoice = -1;
//        int attempts = 0;
//
//        // NOTE: keep trying as long as long as value is invalid
//        while (userChoice == -1) {
//            attempts++;
//
//            // NOTE: display prompt and obtain (wait for) next input
//            writer.println(prompt);
//            String potentialChoice = reader.nextLine();
//
//            try {
//                int potentialIndex = Integer.parseInt(potentialChoice);
//
//                if (potentialIndex >= 0 && potentialIndex <= numberOfChoices) {
//                    userChoice = potentialIndex;
//                } else {
//                    // NOTE: validation failed, display error
//                    writer.println(errorMsg);
//                }
//            } catch (NumberFormatException numberFormatEx) {
//                // NOTE: validation failed, display error
//                writer.println(errorMsg);
//            }
//        }
//
//        return new PrompterIntResult(userChoice, attempts);
//    }
}
