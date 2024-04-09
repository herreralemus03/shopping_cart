package com.hlstudios.orders.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Formatter {

    public static String formatDate(
            String outputPattern,
            String date
    ){
        if(date == null || date.isBlank() || date.trim().isEmpty()) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(outputPattern)
                .withZone(ZoneId.systemDefault());

        Instant instant = Instant.parse(date);
        return formatter.format(instant);
    }
}
