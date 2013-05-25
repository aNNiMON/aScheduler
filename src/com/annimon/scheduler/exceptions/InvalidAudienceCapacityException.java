package com.annimon.scheduler.exceptions;

/**
 * Ошибка неверной вместимости аудитории.
 * @author aNNiMON
 */
public class InvalidAudienceCapacityException extends InvalidInputDataException {

    public InvalidAudienceCapacityException() {
        super("Неверная вместимость аудитории.");
    }
    

}
