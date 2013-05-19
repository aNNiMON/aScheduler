package com.annimon.scheduler.util;

/**
 * Класс обработки исключительных ситуаций.
 * @author aNNiMON
 */
public class ExceptionHandler {

    private static enum HANDLE_MODE {
        NO_LOG, LOG_STACK_TRACE
    };
    
    private static HANDLE_MODE mode = HANDLE_MODE.LOG_STACK_TRACE;

    public static void setNoLogMode() {
        ExceptionHandler.mode = HANDLE_MODE.NO_LOG;
    }
    
    public static void setStackTraceMode() {
        ExceptionHandler.mode = HANDLE_MODE.LOG_STACK_TRACE;
    }
    
    public static void handle(Throwable throwable) {
        if (mode == HANDLE_MODE.LOG_STACK_TRACE) {
            throwable.printStackTrace();
        }
    }
    
    public static void handle(Throwable throwable, String comment) {
        if (mode == HANDLE_MODE.LOG_STACK_TRACE) {
            System.out.println(comment);
            handle(throwable);
        }
    }
    
    public static void handle(String comment) {
        if (mode == HANDLE_MODE.LOG_STACK_TRACE) {
            System.out.println(comment);
            handle(new Throwable(comment));
        }
    }
}
