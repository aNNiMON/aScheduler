package com.annimon.scheduler.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Класс создания HTML-текста для отчётов из ресурсов, с добавлением параметров.
 * @author aNNiMON
 */
public class HtmlBuilder {

    private int paramIndex;
    private final Object[] params;
    private final StringBuilder text;

    public HtmlBuilder(String resource, Object[] params) {
        this.params = params;
        paramIndex = 0;
        text = new StringBuilder();
        try {
            read("/html/" + resource);
        } catch (IOException ex) {
            ExceptionHandler.handle(ex, "read html");
        }
    }
    
    private void read(String resource) throws IOException {
        InputStream stream = getClass().getResourceAsStream(resource);
        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        
        String line = in.readLine();

        while (line != null) {
            text.append(processLine(line));
            line = in.readLine();
        }
        
        in.close();
    }

    @Override
    public String toString() {
        return text.toString();
    }

    /*
     * ОБработка ключевых слов в HTML-файле.
     */
    private String processLine(String line) {
        String out = line;
        if (line.startsWith("$$$")) {
            // Обычный подставляемый параметр.
            out = params[paramIndex].toString();
            paramIndex++;
        } else if (line.startsWith("$array$")) {
            // Массив с параметрами.
            out = addArray("", "");
        } else if (line.startsWith("$+array+$")) {
            // Массив с параметрами, каждый элемент которого, окружается
            // дополнительным текстом.
            String delimer = line.substring(10);
            int paramDelimer = delimer.indexOf("|||");
            String beforeText = delimer.substring(0, paramDelimer);
            String afterText = delimer.substring(paramDelimer + 3);
            out = addArray(beforeText, afterText);
        }
        return out;
    }

    private String addArray(String beforeText, String afterText) {
        Object[] array = (Object[]) params[paramIndex];
        paramIndex++;
            
        String out = "";
        for (int i = 0; i < array.length; i++) {
            out += beforeText + array[i].toString() + afterText; 
        }
        return out;
    }
    
}
