/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import java.util.logging.Level;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Web application lifecycle listener.
 *
 * @author Chhavi kumar.b
 */
public class ContextListener implements ServletContextListener {

    Logger logger = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        try {
//            //        logger.info("Context Initialized.");
//                    Thread.sleep(100000);
//        } catch (InterruptedException ex) {
//            java.util.logging.Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, ex);
//        }

        System.out.println("came Context Initializing...");
        String configPath = System.getProperty("api.ump.props");
        if (StringUtils.isBlank(configPath)) {
            configPath = "E:\\aqa\\bookmanagement\\config\\bookapi.properties";
        }

//    configPath = "/api/config/bookapi.properties";
        if (configPath != null && configPath.length() > 0) {
            ConfigUtil configUtil = new ConfigUtil();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                java.util.logging.Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, ex);
//            }
            boolean isInit = configUtil.init(configPath);
            System.out.println("file path ******************"+configPath);
            if (isInit) {
                System.setProperty("props.file.path", configPath);
                PropertyConfigurator.configureAndWatch(ConfigUtil.getProperty("api.log4j.path", "/api/config/log4j.properties"), Long.valueOf(ConfigUtil.getProperty("api.ump.log4j.ideal.timeout", "6000")));
                logger = Logger.getLogger(ContextListener.class);
                logger.info("UMP API Configurations loaded.");
                if (logger.isDebugEnabled()) {
                    logger.debug(ConfigUtil.getProperty("api.ump.log4j.path", ""));
                }

            } else {
                System.out.println("Configurations were not loaded.SO we are going to shut down the tomcat.");
                System.exit(0);
            }
        } else {
            logger.info("Configuration path is empty or coming null");
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Application Context is destroyed.");
    }
}
