package com.github.thamvannguyen.logging;


import com.github.thamvannguyen.logging.dao.entity.LogDetails;
import com.github.thamvannguyen.logging.dao.entity.LogLevel;
import com.github.thamvannguyen.logging.dao.entity.LogRecord;
import com.github.thamvannguyen.logging.dao.repository.LogDetailsRepository;
import com.github.thamvannguyen.logging.dao.repository.LogRecordRepository;

import java.util.List;

/**
 * Created by huynhduychuong on 10/23/2016.
 */
public abstract class AbstractLogManager implements LogManager {
    private LogRecordRepository logRecordRepository;
    private LogDetailsRepository logDetailsRepository;

    @Override
    public void finishLog() {

        LogCache logCache = LoggingContextHolder.getLogCache();
        LogRecord logRecord = logCache.getLogRecord();
        List<LogDetails> logDetailses = logCache.getLogDetails();
        for (LogDetails details : logDetailses) {
            if (details.getLevel() == LogLevel.ERROR || (details.getLevel() == LogLevel.FATAL)) {
                logRecord.setLevel(LogLevel.ERROR);
                break;
            }
        }

        logRecordRepository.saveAndFlush(logRecord);
        logDetailsRepository.save(logDetailses);
    }
}
