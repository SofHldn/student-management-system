package se.iths.exception;

import javax.ws.rs.core.Response;

public class MyException extends RuntimeException{


    public MyException (String errorMessage){
        super (errorMessage);

    }

}
