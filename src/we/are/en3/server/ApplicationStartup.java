package we.are.en3.server;

import we.are.en3.server.DataStore;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Servlet to initialize the DataStore on application startup
 *
 */

@WebListener
public class ApplicationStartup implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("initialize servlet");

        DataStore.getInstance().loadCSVFile("correct path...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("destroy servlet");
        // clean up resources
    }
}