package com.guotg.depository;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;


/**
 * 日志打印
 * 集成日志框架logback 配置文件：logback.xml
 */
public class Logger {

    /**
     * 配置文件路径
     */
    private final static String configPath = "./log/config/logback.xml";

    /**
     * logback的log实例
     */
    private static ch.qos.logback.classic.Logger logger;

    static {
        init();
    }

    /**
     * 初始化
     */
    private static void init(){

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        try {
            configurator.doConfigure(configPath);
        } catch (JoranException e) {
        }
        //打印加载配置文件时错误信息
        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
//        StatusPrinter.print(lc);
        logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("DEFAULT");
    }

    public static void error(String message, Throwable throwable){
        logger.error(message, throwable);
    }

    public static void error(Throwable throwable){
        logger.error(throwable.getMessage(), throwable);
    }

    public static void log(String message){
        logger.info(message);
    }

}
