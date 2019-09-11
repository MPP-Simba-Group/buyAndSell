package com.mpp.buyAndSell.core.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingBase {
    private Logger logger;

    public Logger getLogger() {
        if(logger == null){
            logger = LogManager.getLogger(getClass().getSimpleName());
        }
        return logger;
    }
}
