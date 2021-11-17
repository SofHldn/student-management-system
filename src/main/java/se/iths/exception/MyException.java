package se.iths.exception;

import javax.ws.rs.core.Response;

public class MyException extends RuntimeException{
    private Response response;

    public MyException (String errorMessage, Throwable err, Response response){
        super (errorMessage, err);
        if (response == null) {
            this.response = Response.serverError().build();
        } else {
            this.response = response;
        }
    }

}
