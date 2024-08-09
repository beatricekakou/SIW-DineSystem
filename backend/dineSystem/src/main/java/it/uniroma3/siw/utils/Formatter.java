package it.uniroma3.siw.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Formatter {

    private static final DateTimeFormatter SLOT_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    
    public static DateTimeFormatter slotFormatter() {
        return SLOT_FORMATTER;
    }
}
