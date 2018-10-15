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
// import java.util.Scanner;

/**
 * @author Anthony Williams
 */
public final class SimpleServer {

	public void spinUpServer(int port) throws IOException {
		ServerSocket listener = null;
		boolean isRunning = true;
		System.out.printf("[App.spinUpServer()] Starting up... port = %d%n", port);

		try {
			System.out.println("[App.spinUpServer()] Creating server...");
			listener = new ServerSocket(port);
			System.out.println("[App.spinUpServer()] Great Success 🎂");

			while (isRunning) {
				Socket clientConnection = listener.accept();
				handleRequest(clientConnection);
			}
		} finally {
			handleServerShutdown(listener);
		}
	}
	
	public void handleServerShutdown(ServerSocket listener) throws IOException {
		System.out.println("[App.handleServerShutdown()] Shutting down...");
		listener.close();
		System.out.println("[App.handleServerShutdown()] Goodbye! 👍");
	}

	public void handleRequest(Socket clientConnection) throws IOException {
		System.out.printf("[App.handleRequest()] Got request %s %s %s 💖%n",
			clientConnection.getInetAddress(),
			clientConnection.getLocalAddress(),
			clientConnection.getLocalSocketAddress());

		try {
			PrintWriter responseWriter = new PrintWriter(clientConnection.getOutputStream(), true);
			String responseText = new Date().toString();
			responseWriter.println(responseText);
		} finally {
			clientConnection.close();
		}
	}
}
