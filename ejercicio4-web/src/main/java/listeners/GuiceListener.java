/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.inject.struts2.Struts2GuicePluginModule;
import guice.EJBModule;
import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;

/**
 *
 * @author sergio
 */
public class GuiceListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new Struts2GuicePluginModule(),
                new ServletModule() {
                    @Override
                    protected void configureServlets() {
                        // Struts 2 setup
                        bind(StrutsPrepareAndExecuteFilter.class).in(Singleton.class);
                        filter("/*").through(StrutsPrepareAndExecuteFilter.class);

                    }
                },
                new EJBModule()
        );
    }
    
}
