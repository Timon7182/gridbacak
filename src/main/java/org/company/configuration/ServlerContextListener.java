package org.company.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServlerContextListener implements ServletContextListener {

    private static final Logger LOG  = LogManager.getLogger();
    private static final Boolean DEBUG_TEMPORARY = false;

    private static final String JAVA_SECURITY_PROPERTY = "java.security.auth.login.config";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if (System.getProperty(JAVA_SECURITY_PROPERTY)==null) {
            String jaasConfigFile = this.getClass().getClassLoader().getResource("jaas.config").getFile();
            System.setProperty(JAVA_SECURITY_PROPERTY, jaasConfigFile);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

}
