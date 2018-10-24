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

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Inspired by http://cs.lmu.edu/~ray/notes/javanetexamples/#date
 *
 * TODO: Generate tests like
 * https://netbeans.org/kb/docs/java/junit-intro.html#Exercise_24
 *
 * TODO: Tutorial
 * https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
 *
 * @author Anthony Williams
 */
public final class SimpleServer {
	private boolean isRunning = false;

	/**
	 *
	 * @param port
	 * @throws IOException
	 */
	public void spinUpServer(int port) throws IOException {
		ServerSocket listener = null;

		try {
			System.out.printf("[App.spinUpServer()] Starting up... port = %d%n", port);
			System.out.println("[App.spinUpServer()] Creating server...");
			listener = new ServerSocket(port);
			isRunning = true;
			System.out.println("[App.spinUpServer()] Great Success 🎂");

			while (isRunning) {
				Socket clientConnection = listener.accept();
				handleRequest(clientConnection);
			}
		} finally {
			handleServerShutdown(listener);
		}
	}

	/**
	 *
	 * @param listener
	 * @throws IOException
	 */
	public void handleServerShutdown(ServerSocket listener) throws IOException {
		System.out.println("[App.handleServerShutdown()] Shutting down...");
		listener.close();
		System.out.println("[App.handleServerShutdown()] Goodbye! 👍");
	}

	/**
	 *
	 * @param clientConnection
	 * @throws IOException
	 */
	public void handleRequest(Socket clientConnection) throws IOException {
		System.out.println("[App.handleRequest()] Got request");

		try {
			// parseRequestInformation(clientConnection);
			// writeResponseInformation(clientConnection);
			parseRequestInformation(new Scanner(clientConnection.getInputStream()));
			writeResponseInformation(new PrintWriter(clientConnection.getOutputStream(), true));
		} finally {
			clientConnection.close();
		}
	}

	/**
	 *
	 * @param responseWriter
	 * @throws IOException
	 */
	private void writeResponseInformation(PrintWriter responseWriter) throws IOException {
		String httpVersion = "HTTP/1.1";
		int statusCode = 200;
		String statusText = "OK";
		String responseHeaderLine = String.format("%s %d %s", httpVersion, statusCode, statusText);

		// NOTE: start the response headers
		responseWriter.println(responseHeaderLine);
		// NOTE: end the response headers
		responseWriter.println();

		try {
			String responseText = new Date().toString();

			responseWriter.println(responseText);
		} finally {
			if (responseWriter != null) {
				System.out.println("[App.writeResponseInformation()] closing response writer...");
				responseWriter.close();
			}
		}
	}

	/**
	 *
	 * @param requestReader
	 * @return
	 * @throws IOException
	 */
	private String parseRequestInformation(Scanner requestReader) throws IOException {
		StringBuilder requestBuilder = new StringBuilder("");
		Dictionary<String, String> requestHeaders = new Hashtable<String, String>();
		boolean shouldKeepReading = true;
		int currentLine = 0;
		String requestMethod = "";
		String requestPath = "";
		String requestVersion = "";
		String result = "";

		while (shouldKeepReading) {
			String nextLine = requestReader.nextLine();
			String[] lineParts = nextLine.split(" ");
			shouldKeepReading = !nextLine.equals("");

			if (currentLine == 0) {
				if (lineParts.length > 0) {
					requestMethod = lineParts[0].toUpperCase();
				}
				if (lineParts.length > 1) {
					requestPath = lineParts[1];
				}
				if (lineParts.length > 2) {
					requestVersion = lineParts[2];
				}
			} else {
				// NOTE: ensure enough parts from line
				if (lineParts.length > 1) {
					// NOTE: ensure properly formatted
					String headerName = lineParts[0].substring(0, lineParts[0].length() - 1);
					String headerValue = lineParts[1];

					requestHeaders.put(headerName, headerValue);
				}
			}
			// System.out.printf("\t\t'%s'%n", nextLine);
			requestBuilder.append(String.format("%s%n", nextLine));
			currentLine++;
		}

		result = requestBuilder.toString();
		System.out.printf("[App.handleRequest()] %s\t%s %s %n", requestVersion, requestMethod, requestPath);

		return result;
	}
}
