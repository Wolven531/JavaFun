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
package com.williams.anthony.models;

/**
 * Model used to represent information regarding a user response (as a choice)
 * to a prompt 
 *   - attempts
 *   - choiceIndex
 *   - choiceText
 *
 * @author Anthony Williams
 */
public class PrompterChoiceResult {

    private final int attempts;
    private final int choiceIndex;
    private final String choiceText;

    /**
     * @param choiceIndex The index of the choice made by the user
     * @param choiceText The string value of the choice made by the user
     * @param attempts The number of attempts (including the final) it took to obtain a value from the user
     */
    public PrompterChoiceResult(
        int choiceIndex,
        String choiceText,
        int attempts) {
        this.choiceIndex = choiceIndex;
        this.choiceText = choiceText;
        this.attempts = attempts;
    }

    /**
     * @return The number of attempts (including the final) it took to obtain a value from the user
     */
    public int getAttempts() {
        return attempts;
    }

    /**
     * @return The string value of the choice made by the user
     */
    public String getChoiceText() {
        return choiceText;
    }

    /**
     * @return The index (zero-based) of the choice made by the user
     */
    public int getChoiceIndex() {
        return choiceIndex;
    }
}
