package org.mark.reactor;

public class ServerContext {
	private String message;
	private StringBuilder response = new StringBuilder();
	
	public void addMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void addResponse(String response) {
		this.response.append(response);
	}
	
	public String getResponse() {
		return this.response.toString();
	}
}
