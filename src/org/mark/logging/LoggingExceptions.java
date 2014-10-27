package org.mark.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class LoggingExceptions {
	static class LoggingException extends Exception {
		private static Logger logger = Logger.getLogger("LoggingException");
		
		public LoggingException() {
			StringWriter trace = new StringWriter();
			printStackTrace(new PrintWriter(trace));
			logger.severe(trace.toString());
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			throw new LoggingException();
		} catch (LoggingException e) {
			System.err.println("Caught " + e);
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println();
		
		try {
			throw new LoggingException();
		} catch (LoggingException e) {
			System.err.println("Caught " + e);
		}
	}

}
