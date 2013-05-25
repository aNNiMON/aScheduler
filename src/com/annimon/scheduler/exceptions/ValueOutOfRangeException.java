package com.annimon.scheduler.exceptions;

/**
 * Ошибка выхода за пределы допустимых значений.
 * @author aNNiMON
 */
public class ValueOutOfRangeException extends InvalidInputDataException {

    public ValueOutOfRangeException() {
        super("Значение превышает допустимое.");
    }

    public ValueOutOfRangeException(Object value, String comment) {
        super("Значение " + value.toString() + " превышает допустимое " + comment + ".");
    }
}
