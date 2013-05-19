package com.annimon.scheduler.exceptions;

/**
 * 
 * @author aNNiMON
 */
public class EmptyFieldException extends InvalidInputDataException {

    public EmptyFieldException() {
        super("Пустое поле.");
    }
    
    public EmptyFieldException(String name) {
        super("Пустое поле '" + name + "'.");
    }

}
