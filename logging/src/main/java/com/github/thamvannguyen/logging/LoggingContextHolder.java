package com.github.thamvannguyen.logging;


import com.github.thamvannguyen.logging.dao.entity.LogDetails;
import com.github.thamvannguyen.logging.dao.entity.LogLevel;

/**
 * Created by huynhduychuong on 10/9/2016.
 */
public class LoggingContextHolder {
    private static ThreadLocal<LogCache> logCacheThreadLocal = new InheritableThreadLocal<>();


    public static void setLogCache(LogCache logCache) {
        logCacheThreadLocal.set(logCache);
    }

    public static LogCache getLogCache() {
        return logCacheThreadLocal.get();
    }

    public void addLogDetail(LogDetails logDetails) {
        LogCache logCache = getLogCache();
        logCache.getLogDetails().add(logDetails);
    }

    public void log(LogLevel logLevel, String message) {
        LogCache logCache = getLogCache();
        LogDetails logDetails = new LogDetails();
        logDetails.setLevel(logLevel);
        logDetails.setContent(message);
        logDetails.setLogRecordId(logCache.getLogRecord().getId());
        logCache.getLogDetails().add(logDetails);
    }

    public static void clear() {
        logCacheThreadLocal.remove();
    }

}
