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

    @Override
    public void configure(Binder binder) {
        // Bind Context to the default InitialContext.
        binder.bind(Context.class).to(InitialContext.class);
        // Bind the remote interface to the JNDI name using the JNDI Provider
        binder.bind(BookFacadeLocal.class)
            .toProvider(fromJndi(BookFacadeLocal.class, "java:global/ejercicio4-ear-1.0-SNAPSHOT/ejercicio4-ejb-1.0-SNAPSHOT/BookFacade!facade.BookFacadeLocal"));
        binder.bind(GroupFacadeLocal.class)
            .toProvider(fromJndi(GroupFacadeLocal.class, "java:global/ejercicio4-ear-1.0-SNAPSHOT/ejercicio4-ejb-1.0-SNAPSHOT/GroupFacade!facade.GroupFacadeLocal"));
        binder.bind(UserFacadeLocal.class)
            .toProvider(fromJndi(UserFacadeLocal.class, "java:global/ejercicio4-ear-1.0-SNAPSHOT/ejercicio4-ejb-1.0-SNAPSHOT/UserFacade!facade.UserFacadeLocal"));
        
        
    }
    
}
