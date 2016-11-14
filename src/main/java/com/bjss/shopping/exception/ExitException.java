package com.bjss.shopping.exception;

/**
 * Created by angelo on 16/08/2016.
 */

import org.springframework.boot.ExitCodeGenerator;

/**
 * Exception used to force out Spring Boot.
 *
 */
public class ExitException extends RuntimeException implements ExitCodeGenerator {

    private static final long serialVersionUID = 1L;

    public  ExitException(String message){
        super(message);
    }

    @Override
    public int getExitCode() {
        return 10;
    }

}
