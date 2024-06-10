package com.example.myraytracing;

public class InputAcceptance extends Throwable {
    public InputAcceptance() {
        super();
    }

    // Constructor that accepts a message
    public InputAcceptance(String message) {
        super(message);
    }

    // Constructor that accepts a message and a cause
    public InputAcceptance(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor that accepts a cause
    public InputAcceptance(Throwable cause) {
        super(cause);
    }
}
