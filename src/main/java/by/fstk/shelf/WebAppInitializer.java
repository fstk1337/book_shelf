package by.fstk.shelf;

import by.fstk.shelf.app.config.AppContextConfig;
import by.fstk.shelf.web.config.WebContextConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {
    Logger logger = LogManager.getLogger(WebApplicationInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) {
        logger.info("loading app config");
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(AppContextConfig.class);
        servletContext.addListener(new ContextLoaderListener(appContext));

        logger.info("loading web config");
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(WebContextConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        logger.info("dispatcher ready");
    }
}
