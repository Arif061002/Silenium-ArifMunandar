package com.bishwa.project;

import com.bishwa.project.lis.core.specification.IDriverManager;
import com.bishwa.project.lis.core.webdrivers.ChromeDriverManager;
import com.bishwa.project.scheduler.TaskScheduler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.TimeZone;

/**
 * Author: Bishwa
 * Date: 30/01/2021
 * Time: 19:31
 */
@WebListener
public class RequestListener implements ServletContextListener {
    private final TaskScheduler taskScheduler = new TaskScheduler();

    // initializing class will initialize static values once, thereby initializes chrome driver.
    private final IDriverManager driverManager = new ChromeDriverManager();

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kathmandu"));
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("SERVER STARTED SUCCESSFULLY");
        System.out.println("RUNNING POST DEPLOYMENT HOOKS");

        taskScheduler.initScheduler();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[CONTEXT-DESTROYED] SERVER STOP SIGNAL RECEIVED");

        taskScheduler.stopScheduler();
        driverManager.tearDown();
    }
}
