package ua.nure.sharov.Airlines.web.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * I18n listener
 * @author Max
 *
 */
public class I18nListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
    }
	
    public void contextInitialized(ServletContextEvent event) {
    	ServletContext context = event.getServletContext();
    	String localesFileName = context.getInitParameter("locales");
    	
    	String localesFileRealPath = context.getRealPath(localesFileName);
    	
    	Properties locales = new Properties();
    	try {
			locales.load(new FileInputStream(localesFileRealPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	context.setAttribute("locales", locales);
    	locales.list(System.out);
    }
	
}
