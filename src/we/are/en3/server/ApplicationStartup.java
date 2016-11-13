package we.are.en3.server;

import we.are.en3.server.DataStore;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;

/**
 * Servlet to initialize the DataStore on application startup
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */

//@WebListener
public class ApplicationStartup implements ServletContextListener {

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("initialize servlet");

        InputStream input = event.getServletContext().getResourceAsStream("web/data.csv");

        // for junit
        if(input == null) {
             input = this.getClass().getClassLoader().getResourceAsStream("data.csv");
        }

        // open a file from the web/ folder
        DataStore.getInstance().loadCSVFile(input);

    }

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("destroy servlet");
        // clean up resources
    }
}