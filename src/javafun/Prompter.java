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

    private final ConsoleScanner reader;
    private final PrintWriter writer;

    /**
     * Constructs a new instance of the Prompter class
     *
     * @param reader The <code>ConsoleScanner</code> to use whenever reading input
     * @param writer The <code>PrintWriter</code> to use whenever writing output
     */
    public Prompter(ConsoleScanner reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * Prompt the user for a <code>String</code> value
     *
     * @param prompt The <code>String</code> message to display to user (to prompt for input)
     * @param errorMsg The <code>String</code> message to display to user when response is invalid
     * @return A <code>PrompterStringResult</code> containing response information
     */
    public PrompterStringResult PromptUserForString(String prompt, String errorMsg) {
        return PromptUserForString(this.reader, this.writer, prompt, errorMsg);
    }

    /**
     * Prompt the user for an <code>Integer</code> value
     *
     * @param prompt The <code>String</code> message to display to user (to prompt for input)
     * @param errorMsg The <code>String</code> message to display to user when response is invalid
     * @return A <code>PrompterIntResult</code> containing response information
     */
    public PrompterIntResult PromptUserForInt(String prompt, String errorMsg) {
        return PromptUserForInt(this.reader, this.writer, prompt, errorMsg);
    }

    /**
     * Prompt the user for an <code>Integer</code> value within a specified range
     *
     * @param prompt The <code>String</code> message to display to user (to prompt for input)
     * @param errorMsg The <code>String</code> message to display to user when response is invalid
     * @param min The <code>Integer</code> minimum value to accept from the user (inclusive)
     * @param max The <code>Integer</code> maximum value to accept from the user (inclusive)
     * @return A <code>PrompterIntResult</code> containing response information
     */
    public PrompterIntResult PromptUserForIntInRange(String prompt, String errorMsg, int min, int max) {
        return PromptUserForIntInRange(this.reader, this.writer, prompt, errorMsg, min, max);
    }

    /**
     * Prompt the user for a number selection, given a list of options
     *
     * @param prompt The <code>String</code> message to display to user (to prompt for input)
     * @param errorMsg The <code>String</code> message to display to user when response is invalid
     * @param choices The <code>String[]</code> of choices for selection by the user
     * @return A <code>PrompterIntResult</code> containing response information
     */
    public PrompterIntResult PromptUserForChoice(String prompt, String errorMsg, String[] choices) {
        return PromptUserForChoice(this.reader, this.writer, prompt, errorMsg, choices);
    }

    /**
     * This method is used to obtain a string value from the user
     *
     * @param reader A <code>ConsoleScanner</code> to read input from (usually created with System.in)
     * @param writer A <code>PrintWriter</code> to write output to (usually created with System.out)
     * @param prompt The <code>String</code> message to display to user (to prompt for input)
     * @param errorMsg The <code>String</code> message to display to user when response is invalid
     * @return A <code>PrompterStringResult</code> containing user response information
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

    /**
     * This method is used to obtain an integer value from the user
     *
     * @param reader A <code>ConsoleScanner</code> to read input from (usually created with System.in)
     * @param writer A <code>PrintWriter</code> to write output to (usually created with System.out)
     * @param prompt The <code>String</code> message to display to user (to prompt for input)
     * @param errorMsg The <code>String</code> message to display to user when response is invalid
     * @return A <code>PrompterIntResult</code> containing user response information
     */
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

    /**
     * This method is used to obtain an integer value within a specified range from the user
     *
     * @param reader A <code>ConsoleScanner</code> to read input from (usually created with System.in)
     * @param writer A <code>PrintWriter</code> to write output to (usually created with System.out)
     * @param prompt The <code>String</code> message to display to user (to prompt for input)
     * @param errorMsg The <code>String</code> message to display to user when response is invalid
     * @param min The <code>Integer</code> minimum value to accept from the user (inclusive)
     * @param max The <code>Integer</code> maximum value to accept from the user (inclusive)
     * @return A <code>PrompterIntResult</code> containing user response information
     */
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

    public static PrompterIntResult PromptUserForChoice(
        ConsoleScanner reader,
        PrintWriter writer,
        String prompt,
        String errorMsg,
        String[] choices) {
        int numberOfChoices = choices.length - 1;
//        int userChoice = -1;
//        int attempts = 0;

//        // NOTE: keep trying as long as long as value is invalid
//        while (userChoice == -1) {
//            attempts++;
        // NOTE: display prompt and obtain (wait for) next input
//            writer.println(prompt);
//        PrompterIntResult result = PromptUserForIntInRange(reader, writer, prompt, errorMsg, 0, numberOfChoices);
//        }
        return PromptUserForIntInRange(reader, writer, prompt, errorMsg, 0, numberOfChoices);
//        return new PrompterIntResult(result.getValue(), result.getAttempts());
    }
}
