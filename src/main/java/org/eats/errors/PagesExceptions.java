package org.eats.errors;

public class PagesExceptions extends RuntimeException{
    private int status;
    public PagesExceptions(int status, String message){
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
