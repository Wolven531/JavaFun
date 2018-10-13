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
package com.williams.anthony;

import java.util.Scanner;
import com.williams.anthony.models.PrompterChoiceResult;
import com.williams.anthony.models.PrompterIntResult;
import com.williams.anthony.models.PrompterStringResult;

/**
 * @author Anthony Williams
 */
public final class App {

    private static final String[] CHOICES_SKILL_LEVEL = new String[]{"Untrained", "Trained", "Skilled", "Expert"};
    private static final String ERROR_INVALID_AGE = "Age must be an integer number (e.g.: 1, 15, 50...)";
    private static final String ERROR_INVALID_SKILLLEVEL = "Please select a valid skill level";
    private static final String ERROR_USERNAME_EMPTY = "Username cannot be empty";
    private static final String GREETING = "Ello! 😊";
    private static final String PROMPT_TEXT_AGE = "Input an age (integer) and press [Enter]";
    private static final String PROMPT_TEXT_SKILLLEVEL = "Input a skill level number from the list and press [Enter]";
    private static final String PROMPT_TEXT_USERNAME = "Input a username and press [Enter]";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String currentCodeLocation = "[App.main()]";
        String locationAndGreeting = currentCodeLocation.concat(" ").concat(GREETING);
        // NOTE: Variable below and %n in formatted strings are equal
        // String newLineStr = System.getProperty("line.separator");

        System.out.println(locationAndGreeting);
        Prompter prompter = new Prompter(new Scanner(System.in), System.out);
        PrompterStringResult usernameResult = prompter.PromptUserForString(PROMPT_TEXT_USERNAME, ERROR_USERNAME_EMPTY);
        PrompterIntResult ageResult = prompter.PromptUserForInt(PROMPT_TEXT_AGE, ERROR_INVALID_AGE);
        PrompterChoiceResult skillLevelResult = prompter.PromptUserForChoice(
            PROMPT_TEXT_SKILLLEVEL,
            ERROR_INVALID_SKILLLEVEL,
            CHOICES_SKILL_LEVEL);

        System.out.printf("\tUsername = %s%n", usernameResult.getValue());
        System.out.printf("\tAge = %d%n", ageResult.getValue());
        System.out.printf(
            "\tSkill = %d.) %s%n",
            skillLevelResult.getChoiceIndex() + 1,
            skillLevelResult.getChoiceText());

    }
}
