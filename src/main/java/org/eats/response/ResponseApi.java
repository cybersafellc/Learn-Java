package org.eats.response;

public class ResponseApi {
    private int status;
    private String message;
    private Object data;
    private String refrence;
    private boolean error;

    public ResponseApi(int status, String message, Object data, String refrence, boolean error){
        this.status = status;
        this.message = message;
        this.data = data;
        this.refrence = refrence;
        this.error = error;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setRefrence(String refrence) {
        this.refrence = refrence;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public String getRefrence() {
        return refrence;
    }

    public boolean isError() {
        return error;
    }
}
