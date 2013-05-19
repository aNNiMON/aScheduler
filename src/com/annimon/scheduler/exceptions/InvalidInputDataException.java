package com.annimon.scheduler.exceptions;

/**
 * Ошибка неправильного ввода данных.
 * @author aNNiMON
 */
public class InvalidInputDataException extends Exception {
    
    public InvalidInputDataException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\nПроверьте правильность ввода данных.";
    }

}
