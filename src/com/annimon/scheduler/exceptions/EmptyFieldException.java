package com.annimon.scheduler.exceptions;

/**
 * Ошибка пустого поля.
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
