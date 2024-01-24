package de.ju.server.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static String getCurrentTimeAndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    private static String ANSI_RESET = "\u001B[0m";
    private static String ANSI_BLUE = "\u001B[34m";
    private static String ANSI_YELLOW = "\u001B[33m";
    private static String ANSI_RED = "\u001B[31m";

    public static void info(String message) {
        System.out.println(getCurrentTimeAndDate() + " " + ANSI_BLUE + "INFO" + ANSI_RESET + " " + message);
    }

    public static void warn(String message) {
        System.out.println(getCurrentTimeAndDate() + " " + ANSI_YELLOW + "WARN" + ANSI_RESET + " " + message);
    }

    public static void error(String message) {
        System.out.println(getCurrentTimeAndDate() + " " + ANSI_RED + "ERROR" + ANSI_RESET + " " + message);
    }
}
