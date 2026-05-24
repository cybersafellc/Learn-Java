package org.eats.errors;

public class ApiExceptions extends RuntimeException{
    private int status;
    public ApiExceptions(int status, String message){
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
