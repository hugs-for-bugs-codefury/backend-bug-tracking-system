package org.example.util;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

public abstract class Log {
    public static Logger logger;
    static {
        try {
            logger = Logger.getLogger(Log.class.getName());
            FileHandler fileHandler = new FileHandler("log.txt");
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


