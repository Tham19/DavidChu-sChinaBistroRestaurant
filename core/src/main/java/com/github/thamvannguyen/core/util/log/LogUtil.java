package com.github.thamvannguyen.core.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/**
 * Created by huynhduychuong on Oct 2, 2016.
 */
public class LogUtil {

    // 1: java.lang.Thread
    // 2: com.github.duychuongvn.core.util.LogUtil.getCallingClassName
    // 3: com.github.duychuongvn.core.util.LogUtil.getLogger
    // 4: Thread of calling class
    // IF add or remove any method before calling logger, shut change the value
    // of this constant.
    private static final int CALLING_CLASS_INDEX_IN_THREAD = 4;

    private static String getCallingClassName() {
        StackTraceElement stackTraceElement = Thread.currentThread()
                .getStackTrace()[CALLING_CLASS_INDEX_IN_THREAD];
        return stackTraceElement.getClassName();
    }

    public static Logger getLogger() {
        return LoggerFactory.getLogger(getCallingClassName());

    }

    public static boolean isDebugEnabled() {
        return getLogger().isDebugEnabled();
    }

    public static void info(String message) {
        getLogger().info(message);
    }

    public static void info(String message, Object... args) {
        getLogger().info(message, args);
    }

    public static void info(String message, Throwable throwable) {
        getLogger().info(message, throwable);
    }

    public static void info(Marker marker, String message) {
        getLogger().info(marker, message);
    }

    public static void info(Marker marker, String message, Throwable throwable) {
        getLogger().info(marker, message, throwable);
    }

    public static void error(String message) {
        getLogger().error(message);
    }

    public static void error(String message, Object... args) {
        getLogger().error(message, args);
    }

    public static void error(String message, Throwable throwable) {
        getLogger().error(message, throwable);
    }

    public static void error(Marker marker, String message) {
        getLogger().error(marker, message);
    }

    public static void error(Marker marker, String message, Throwable throwable) {
        getLogger().error(marker, message, throwable);
    }

    public static void debug(String message) {
        getLogger().debug(message);
    }

    public static void debug(String message, Object... args) {
        getLogger().debug(message, args);
    }

    public static void debug(String message, Throwable throwable) {
        getLogger().debug(message, throwable);
    }

    public static void debug(Marker marker, String message) {
        getLogger().debug(marker, message);
    }

    public static void debug(Marker marker, String message, Throwable throwable) {
        getLogger().debug(marker, message, throwable);
    }

    public static void warn(String message, Throwable throwable) {
        getLogger().warn(message, throwable);
    }

    public static void warn(Marker marker, String message) {
        getLogger().warn(marker, message);
    }

    public static void warn(Marker marker, String message, Throwable throwable) {
        getLogger().warn(marker, message, throwable);
    }
}
