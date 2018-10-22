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
import java.util.Scanner;

/**
 * // NOTE: Inspired by http://cs.lmu.edu/~ray/notes/javanetexamples/#date
 *
 * // TODO: Generate tests like
 * https://netbeans.org/kb/docs/java/junit-intro.html#Exercise_24
 *
 * // TODO: Tutorial
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
	 * @param clientConnection
	 * @throws IOException
	 */
	// private void writeResponseInformation(Socket clientConnection) throws
	// IOException {
	// System.out.println("[App.writeResponseInformation()]");
	// PrintWriter responseWriter = null;

	// try {
	// String responseText = new Date().toString();

	// responseWriter = new PrintWriter(clientConnection.getOutputStream(), true);
	// responseWriter.println(responseText);
	// } finally {
	// if (responseWriter != null) {
	// System.out.println("[App.writeResponseInformation()] closing response
	// writer...");
	// responseWriter.close();
	// }
	// }
	// }
	private void writeResponseInformation(PrintWriter responseWriter) throws IOException {
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
	 * // NOTE: getInetAddress = "/0:0:0:0:0:0:0:1"
	 *
	 * // NOTE: getLocalAddress = "/0:0:0:0:0:0:0:1"
	 *
	 * // NOTE: getLocalPort = "9090"
	 *
	 * // NOTE: getLocalSocketAddress = "/0:0:0:0:0:0:0:1:9090"
	 *
	 * // NOTE: getPort = "56967"
	 *
	 * // NOTE: getRemoteSocketAddress = "/0:0:0:0:0:0:0:1:56967"
	 *
	 * // NOTE: getTrafficClass = "0"
	 *
	 * @param clientConnection
	 * @return
	 * @throws IOException
	 */
	// private String parseRequestInformation(Socket clientConnection) throws
	// IOException {
	// System.out.println("[App.parseRequestInformation()]");
	// String result = "";
	// StringBuilder requestBuilder = new StringBuilder("");
	// Scanner requestReader = null;

	// // try {
	// System.out.println("[App.parseRequestInformation()] Creating reader...");
	// requestReader = new Scanner(clientConnection.getInputStream());

	// System.out.println("[App.parseRequestInformation()] Looping...");
	// boolean shouldKeepReading = true;

	// while (shouldKeepReading) {
	// String nextLine = requestReader.nextLine();
	// shouldKeepReading = !nextLine.equals("");

	// System.out.printf("[App.parseRequestInformation()] Appending next
	// line...%n'%s'%n", nextLine);
	// requestBuilder.append(nextLine);
	// }

	// System.out.println("[App.parseRequestInformation()] Calling toString...");
	// result = requestBuilder.toString();
	// // } finally {
	// // if (requestReader != null) {
	// // System.out.println("[App.parseRequestInformation()] closing
	// // requestReader...");
	// // requestReader.close();
	// // }
	// // }

	// System.out.printf("[App.handleRequest()] Got request %s 💖%n", result);

	// return result;
	// }
	private String parseRequestInformation(Scanner requestReader) throws IOException {
		System.out.println("[App.parseRequestInformation()]");
		StringBuilder requestBuilder = new StringBuilder("");
		boolean shouldKeepReading = true;
		String result = "";

		while (shouldKeepReading) {
			String nextLine = requestReader.nextLine();
			shouldKeepReading = !nextLine.equals("");

			// System.out.printf("\t\t'%s'%n", nextLine);
			requestBuilder.append(String.format("%s%n", nextLine));
		}

		result = requestBuilder.toString();
		System.out.printf("[App.handleRequest()] Got request %n%n%s", result);

		return result;
	}
}
