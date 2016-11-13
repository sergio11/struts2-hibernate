/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guice;

import com.google.inject.Binder;
import com.google.inject.Module;
import javax.naming.Context;
import javax.naming.InitialContext;
import static com.google.inject.jndi.JndiIntegration.fromJndi;
import facade.BookFacadeLocal;
import facade.GroupFacadeLocal;
import facade.UserFacadeLocal;

/**
 *
 * @author sergio
 */
public class EJBModule implements Module{
    
    private final static String JNDI_ENV = "java:comp/env/";

    @Override
    public void configure(Binder binder) {
        // Bind Context to the default InitialContext.
        binder.bind(Context.class).to(InitialContext.class);
        // Bind the remote interface to the JNDI name using the JNDI Provider
        binder.bind(BookFacadeLocal.class)
            .toProvider(fromJndi(BookFacadeLocal.class, JNDI_ENV + "booksEJB"));
        binder.bind(GroupFacadeLocal.class)
            .toProvider(fromJndi(GroupFacadeLocal.class, JNDI_ENV + "groupEJB"));
        binder.bind(UserFacadeLocal.class)
            .toProvider(fromJndi(UserFacadeLocal.class, JNDI_ENV + "userEJB"));
        
        
    }
    
}
