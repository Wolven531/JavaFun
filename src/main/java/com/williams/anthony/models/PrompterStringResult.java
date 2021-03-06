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
 * Model used to represent information regarding a user response (as a string) to a prompt
 *
 * @author Anthony Williams
 */
public class PrompterStringResult {

    private final int attempts;
    private final String value;

    /**
     * @param value The string value obtained from the user
     * @param attempts The number of attempts (including the final) it took to obtain a value from the user
     */
    public PrompterStringResult(String value, int attempts) {
        this.value = value;
        this.attempts = attempts;
    }

    /**
     * @return The number of attempts (including the final) it took to obtain a value from the user
     */
    public int getAttempts() {
        return attempts;
    }

    /**
     * @return The string value obtained from the user
     */
    public String getValue() {
        return value;
    }
}
