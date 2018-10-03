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
package javafuntest;

import javafun.models.PrompterStringResult;
import java.io.IOException;
import java.io.PrintWriter;
import javafun.ConsoleScanner;
import javafun.Prompter;
import javafun.models.PrompterIntResult;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 * Mockito version from: https://mvnrepository.com/artifact/org.mockito/mockito-all/2.0.2-beta
 *
 * @author Anthony Williams
 */
public class TestPrompter {

    @Mock
    PrintWriter mockPrintWriter;

    @Mock
    ConsoleScanner mockScanner;

    public TestPrompter() {
        mockPrintWriter = mock(PrintWriter.class);
        mockScanner = mock(ConsoleScanner.class);
    }

    @Test
    public void PromptUserForString_WhenProvidedValidValueOnFirstAttempt_ShouldReturnPrompterStringResult() throws IOException {
        // Arrange
        PrompterStringResult actual;
        when(mockScanner.nextLine())
            .thenReturn("a");

        // Act
        actual = Prompter.PromptUserForString(mockScanner, mockPrintWriter, "prompt for val", "err");

        // Assert
        verify(mockScanner, times(1)).nextLine();
        verifyNoMoreInteractions(mockScanner);

        verify(mockPrintWriter, times(1)).println("prompt for val");
        verify(mockPrintWriter, never()).println("err");
        verifyNoMoreInteractions(mockPrintWriter);

        assertEquals("a", actual.getValue());
        assertEquals(1, actual.getAttempts());
    }

    @Test
    public void PromptUserForString_WhenProvidedNoValueThenValidValue_ShouldReturnPrompterStringResult() {
        // Arrange
        PrompterStringResult actual;
        when(mockScanner.nextLine())
            .thenReturn("")
            .thenReturn("a");

        // Act
        actual = Prompter.PromptUserForString(mockScanner, mockPrintWriter, "prompt for val", "err");

        // Assert
        verify(mockScanner, times(2)).nextLine();
        verifyNoMoreInteractions(mockScanner);

        InOrder inOrder = Mockito.inOrder(mockPrintWriter);
        inOrder.verify(mockPrintWriter).println("prompt for val");
        inOrder.verify(mockPrintWriter).println("err");
        inOrder.verify(mockPrintWriter).println("prompt for val");
        verifyNoMoreInteractions(mockPrintWriter);

        assertEquals("a", actual.getValue());
        assertEquals(2, actual.getAttempts());
    }

    @Test
    public void PromptUserForInt_WhenProvidedValidValueOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        when(mockScanner.nextLine())
            .thenReturn("3");

        // Act
        actual = Prompter.PromptUserForInt(mockScanner, mockPrintWriter, "prompt for val", "err");

        // Assert
        verify(mockScanner, times(1)).nextLine();
        verifyNoMoreInteractions(mockScanner);

        verify(mockPrintWriter, times(1)).println("prompt for val");
        verify(mockPrintWriter, never()).println("err");
        verifyNoMoreInteractions(mockPrintWriter);

        assertEquals(3, actual.getValue());
        assertEquals(1, actual.getAttempts());
    }

    @Test
    public void PromptUserForInt_WhenProvidedNoValueThenValidValue_ShouldReturnPrompterIntResult() {
        // Arrange
        PrompterIntResult actual;
        when(mockScanner.nextLine())
            .thenReturn("")
            .thenReturn("asdf")
            .thenReturn("50");

        // Act
        actual = Prompter.PromptUserForInt(mockScanner, mockPrintWriter, "prompt for val", "err");

        // Assert
        verify(mockScanner, times(3)).nextLine();
        verifyNoMoreInteractions(mockScanner);

        InOrder inOrder = Mockito.inOrder(mockPrintWriter);
        inOrder.verify(mockPrintWriter).println("prompt for val");
        inOrder.verify(mockPrintWriter).println("err");
        inOrder.verify(mockPrintWriter).println("prompt for val");
        inOrder.verify(mockPrintWriter).println("err");
        inOrder.verify(mockPrintWriter).println("prompt for val");
        verifyNoMoreInteractions(mockPrintWriter);

        assertEquals(50, actual.getValue());
        assertEquals(3, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenProvidedValidValueOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        when(mockScanner.nextLine())
            .thenReturn("3");

        // Act
        actual = Prompter.PromptUserForIntInRange(mockScanner, mockPrintWriter, "prompt for val", "err", 0, 100);

        // Assert
        verify(mockScanner, times(1)).nextLine();
        verifyNoMoreInteractions(mockScanner);

        verify(mockPrintWriter, times(1)).println("prompt for val");
        verify(mockPrintWriter, never()).println("err");
        verifyNoMoreInteractions(mockPrintWriter);

        assertEquals(3, actual.getValue());
        assertEquals(1, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenProvidedNonNumberOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        when(mockScanner.nextLine())
            .thenReturn("a")
            .thenReturn("3");

        // Act
        actual = Prompter.PromptUserForIntInRange(mockScanner, mockPrintWriter, "prompt for val", "err", 0, 100);

        // Assert
        verify(mockScanner, times(2)).nextLine();
        verifyNoMoreInteractions(mockScanner);

        InOrder inOrder = Mockito.inOrder(mockPrintWriter);
        inOrder.verify(mockPrintWriter).println("prompt for val");
        inOrder.verify(mockPrintWriter).println("err");
        inOrder.verify(mockPrintWriter).println("prompt for val");
        verifyNoMoreInteractions(mockPrintWriter);

        assertEquals(3, actual.getValue());
        assertEquals(2, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenProvidedTooLowNumberOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        when(mockScanner.nextLine())
            .thenReturn("5")
            .thenReturn("50");

        // Act
        actual = Prompter.PromptUserForIntInRange(mockScanner, mockPrintWriter, "prompt for val", "err", 10, 100);

        // Assert
        verify(mockScanner, times(2)).nextLine();
        verifyNoMoreInteractions(mockScanner);

        InOrder inOrder = Mockito.inOrder(mockPrintWriter);
        inOrder.verify(mockPrintWriter).println("prompt for val");
        inOrder.verify(mockPrintWriter).println("err");
        inOrder.verify(mockPrintWriter).println("prompt for val");
        verifyNoMoreInteractions(mockPrintWriter);

        assertEquals(50, actual.getValue());
        assertEquals(2, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenProvidedTooHighNumberOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        when(mockScanner.nextLine())
            .thenReturn("100")
            .thenReturn("20");

        // Act
        actual = Prompter.PromptUserForIntInRange(mockScanner, mockPrintWriter, "prompt for val", "err", 10, 50);

        // Assert
        verify(mockScanner, times(2)).nextLine();
        verifyNoMoreInteractions(mockScanner);

        InOrder inOrder = Mockito.inOrder(mockPrintWriter);
        inOrder.verify(mockPrintWriter).println("prompt for val");
        inOrder.verify(mockPrintWriter).println("err");
        inOrder.verify(mockPrintWriter).println("prompt for val");
        verifyNoMoreInteractions(mockPrintWriter);

        assertEquals(20, actual.getValue());
        assertEquals(2, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenProvidedMaxValueOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        when(mockScanner.nextLine())
            .thenReturn("100");

        // Act
        actual = Prompter.PromptUserForIntInRange(mockScanner, mockPrintWriter, "prompt for val", "err", 0, 100);

        // Assert
        verify(mockScanner, times(1)).nextLine();
        verifyNoMoreInteractions(mockScanner);

        verify(mockPrintWriter, times(1)).println("prompt for val");
        verify(mockPrintWriter, never()).println("err");
        verifyNoMoreInteractions(mockPrintWriter);

        assertEquals(100, actual.getValue());
        assertEquals(1, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenProvidedMinValueOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        when(mockScanner.nextLine())
            .thenReturn("0");

        // Act
        actual = Prompter.PromptUserForIntInRange(mockScanner, mockPrintWriter, "prompt for val", "err", 0, 100);

        // Assert
        verify(mockScanner, times(1)).nextLine();
        verifyNoMoreInteractions(mockScanner);

        verify(mockPrintWriter, times(1)).println("prompt for val");
        verify(mockPrintWriter, never()).println("err");
        verifyNoMoreInteractions(mockPrintWriter);

        assertEquals(0, actual.getValue());
        assertEquals(1, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenProvidedNegativeValueOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        when(mockScanner.nextLine())
            .thenReturn("-25");

        // Act
        actual = Prompter.PromptUserForIntInRange(mockScanner, mockPrintWriter, "prompt for val", "err", -100, 100);

        // Assert
        verify(mockScanner, times(1)).nextLine();
        verifyNoMoreInteractions(mockScanner);

        verify(mockPrintWriter, times(1)).println("prompt for val");
        verify(mockPrintWriter, never()).println("err");
        verifyNoMoreInteractions(mockPrintWriter);

        assertEquals(-25, actual.getValue());
        assertEquals(1, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenProvidedDecimalValueOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        when(mockScanner.nextLine())
            .thenReturn("2.5")
            .thenReturn("25");

        // Act
        actual = Prompter.PromptUserForIntInRange(mockScanner, mockPrintWriter, "prompt for val", "err", -100, 100);

        // Assert
        verify(mockScanner, times(2)).nextLine();
        verifyNoMoreInteractions(mockScanner);

        InOrder inOrder = Mockito.inOrder(mockPrintWriter);
        inOrder.verify(mockPrintWriter).println("prompt for val");
        inOrder.verify(mockPrintWriter).println("err");
        inOrder.verify(mockPrintWriter).println("prompt for val");
        verifyNoMoreInteractions(mockPrintWriter);

        assertEquals(25, actual.getValue());
        assertEquals(2, actual.getAttempts());
    }
}
