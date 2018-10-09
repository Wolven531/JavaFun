package com.williams.anthony;

//import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.williams.anthony.Prompter;
import com.williams.anthony.models.PrompterIntResult;
import com.williams.anthony.models.PrompterStringResult;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Mock
    PrintStream mockPrintStream;

    public AppTest() {
        mockPrintStream = mock(PrintStream.class);
    }

    @Test
    public void PromptUserForString_WhenProvidedValidValueOnFirstAttempt_ShouldReturnPrompterStringResult() throws IOException {
        // Arrange
        PrompterStringResult actual;
        Scanner scanner = new Scanner("a");

        // Act
        actual = Prompter.PromptUserForString(scanner, mockPrintStream, "prompt for val", "err");

        // Assert
        verify(mockPrintStream, times(1)).println("prompt for val");
        verify(mockPrintStream, never()).println("err");
        verifyNoMoreInteractions(mockPrintStream);

        assertEquals("a", actual.getValue());
        assertEquals(1, actual.getAttempts());
    }

    @Test
    public void PromptUserForString_WhenProvidedNoValueThenValidValue_ShouldReturnPrompterStringResult() {
        // Arrange
        PrompterStringResult actual;
        Scanner scanner = new Scanner(String.format("%na"));

        // Act
        actual = Prompter.PromptUserForString(scanner, mockPrintStream, "prompt for val", "err");

        // Assert
        InOrder inOrder = Mockito.inOrder(mockPrintStream);
        inOrder.verify(mockPrintStream).println("prompt for val");
        inOrder.verify(mockPrintStream).println("err");
        inOrder.verify(mockPrintStream).println("prompt for val");
        verifyNoMoreInteractions(mockPrintStream);

        assertEquals("a", actual.getValue());
        assertEquals(2, actual.getAttempts());
    }

    @Test
    public void PromptUserForInt_WhenProvidedValidValueOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        Scanner scanner = new Scanner(String.format("3"));

        // Act
        actual = Prompter.PromptUserForInt(scanner, mockPrintStream, "prompt for val", "err");

        // Assert
        verify(mockPrintStream, times(1)).println("prompt for val");
        verify(mockPrintStream, never()).println("err");
        verifyNoMoreInteractions(mockPrintStream);

        assertEquals(3, actual.getValue());
        assertEquals(1, actual.getAttempts());
    }

    @Test
    public void PromptUserForInt_WhenProvidedNoValueThenValidValue_ShouldReturnPrompterIntResult() {
        // Arrange
        PrompterIntResult actual;
        Scanner scanner = new Scanner(String.format("%nasdf%n50"));

        // Act
        actual = Prompter.PromptUserForInt(scanner, mockPrintStream, "prompt for val", "err");

        // Assert
        InOrder inOrder = Mockito.inOrder(mockPrintStream);
        inOrder.verify(mockPrintStream).println("prompt for val");
        inOrder.verify(mockPrintStream).println("err");
        inOrder.verify(mockPrintStream).println("prompt for val");
        inOrder.verify(mockPrintStream).println("err");
        inOrder.verify(mockPrintStream).println("prompt for val");
        verifyNoMoreInteractions(mockPrintStream);

        assertEquals(50, actual.getValue());
        assertEquals(3, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenProvidedValidValueOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        Scanner scanner = new Scanner(String.format("3"));

        // Act
        actual = Prompter.PromptUserForIntInRange(scanner, mockPrintStream, "prompt for val", "err", 0, 100);

        // Assert
        verify(mockPrintStream, times(1)).println("prompt for val");
        verify(mockPrintStream, never()).println("err");
        verifyNoMoreInteractions(mockPrintStream);

        assertEquals(3, actual.getValue());
        assertEquals(1, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenProvidedNonNumberOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        Scanner scanner = new Scanner(String.format("a%n3"));

        // Act
        actual = Prompter.PromptUserForIntInRange(scanner, mockPrintStream, "prompt for val", "err", 0, 100);

        // Assert
        InOrder inOrder = Mockito.inOrder(mockPrintStream);
        inOrder.verify(mockPrintStream).println("prompt for val");
        inOrder.verify(mockPrintStream).println("err");
        inOrder.verify(mockPrintStream).println("prompt for val");
        verifyNoMoreInteractions(mockPrintStream);

        assertEquals(3, actual.getValue());
        assertEquals(2, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenProvidedTooLowNumberOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        Scanner scanner = new Scanner(String.format("5%n50"));

        // Act
        actual = Prompter.PromptUserForIntInRange(scanner, mockPrintStream, "prompt for val", "err", 10, 100);

        // Assert
        InOrder inOrder = Mockito.inOrder(mockPrintStream);
        inOrder.verify(mockPrintStream).println("prompt for val");
        inOrder.verify(mockPrintStream).println("err");
        inOrder.verify(mockPrintStream).println("prompt for val");
        verifyNoMoreInteractions(mockPrintStream);

        assertEquals(50, actual.getValue());
        assertEquals(2, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenProvidedTooHighNumberOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        Scanner scanner = new Scanner(String.format("100%n20"));

        // Act
        actual = Prompter.PromptUserForIntInRange(scanner, mockPrintStream, "prompt for val", "err", 10, 50);

        // Assert
        InOrder inOrder = Mockito.inOrder(mockPrintStream);
        inOrder.verify(mockPrintStream).println("prompt for val");
        inOrder.verify(mockPrintStream).println("err");
        inOrder.verify(mockPrintStream).println("prompt for val");
        verifyNoMoreInteractions(mockPrintStream);

        assertEquals(20, actual.getValue());
        assertEquals(2, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenProvidedMaxValueOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        Scanner scanner = new Scanner(String.format("100"));

        // Act
        actual = Prompter.PromptUserForIntInRange(scanner, mockPrintStream, "prompt for val", "err", 0, 100);

        // Assert
        verify(mockPrintStream, times(1)).println("prompt for val");
        verify(mockPrintStream, never()).println("err");
        verifyNoMoreInteractions(mockPrintStream);

        assertEquals(100, actual.getValue());
        assertEquals(1, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenProvidedMinValueOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        Scanner scanner = new Scanner(String.format("0"));

        // Act
        actual = Prompter.PromptUserForIntInRange(scanner, mockPrintStream, "prompt for val", "err", 0, 100);

        // Assert
        verify(mockPrintStream, times(1)).println("prompt for val");
        verify(mockPrintStream, never()).println("err");
        verifyNoMoreInteractions(mockPrintStream);

        assertEquals(0, actual.getValue());
        assertEquals(1, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenProvidedNegativeValueOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        Scanner scanner = new Scanner(String.format("-25"));

        // Act
        actual = Prompter.PromptUserForIntInRange(scanner, mockPrintStream, "prompt for val", "err", -100, 100);

        // Assert
        verify(mockPrintStream, times(1)).println("prompt for val");
        verify(mockPrintStream, never()).println("err");
        verifyNoMoreInteractions(mockPrintStream);

        assertEquals(-25, actual.getValue());
        assertEquals(1, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenProvidedDecimalValueOnFirstAttempt_ShouldReturnPrompterIntResult() throws IOException {
        // Arrange
        PrompterIntResult actual;
        Scanner scanner = new Scanner(String.format("2.5%n25"));

        // Act
        actual = Prompter.PromptUserForIntInRange(scanner, mockPrintStream, "prompt for val", "err", -100, 100);

        // Assert
        InOrder inOrder = Mockito.inOrder(mockPrintStream);
        inOrder.verify(mockPrintStream).println("prompt for val");
        inOrder.verify(mockPrintStream).println("err");
        inOrder.verify(mockPrintStream).println("prompt for val");
        verifyNoMoreInteractions(mockPrintStream);

        assertEquals(25, actual.getValue());
        assertEquals(2, actual.getAttempts());
    }

    @Test
    public void PromptUserForString_WhenInstanceCreatedForConvenience_ShouldCallExplicitMethod() {
        // Arrange
        PrompterStringResult actual;
        Scanner scanner = new Scanner(String.format("a"));
        Prompter fixture = new Prompter(scanner, mockPrintStream);

        // Act
        actual = fixture.PromptUserForString("prompt for val", "err");

        // Assert
        verify(mockPrintStream, times(1)).println("prompt for val");
        verify(mockPrintStream, never()).println("err");
        verifyNoMoreInteractions(mockPrintStream);

        assertEquals("a", actual.getValue());
        assertEquals(1, actual.getAttempts());
    }

    @Test
    public void PromptUserForInt_WhenInstanceCreatedForConvenience_ShouldCallExplicitMethod() {
        // Arrange
        PrompterIntResult actual;
        Scanner scanner = new Scanner(String.format("3"));
        Prompter fixture = new Prompter(scanner, mockPrintStream);

        // Act
        actual = fixture.PromptUserForInt("prompt for val", "err");

        // Assert
        verify(mockPrintStream, times(1)).println("prompt for val");
        verify(mockPrintStream, never()).println("err");
        verifyNoMoreInteractions(mockPrintStream);

        assertEquals(3, actual.getValue());
        assertEquals(1, actual.getAttempts());
    }

    @Test
    public void PromptUserForIntInRange_WhenInstanceCreatedForConvenience_ShouldCallExplicitMethod() {
        // Arrange
        PrompterIntResult actual;
        Scanner scanner = new Scanner(String.format("5"));
        Prompter fixture = new Prompter(scanner, mockPrintStream);

        // Act
        actual = fixture.PromptUserForIntInRange("prompt for val", "err", 1, 10);

        // Assert
        verify(mockPrintStream, times(1)).println("prompt for val");
        verify(mockPrintStream, never()).println("err");
        verifyNoMoreInteractions(mockPrintStream);

        assertEquals(5, actual.getValue());
        assertEquals(1, actual.getAttempts());
    }
}
