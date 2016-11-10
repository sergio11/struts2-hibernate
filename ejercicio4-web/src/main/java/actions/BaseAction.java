/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.PrincipalAware;
import org.apache.struts2.interceptor.PrincipalProxy;

/**
 *
 * @author sergio
 */
public class BaseAction extends ActionSupport implements PrincipalAware {
    
    protected PrincipalProxy principal;

    @Override
    public void setPrincipalProxy(PrincipalProxy pp) {
       this.principal = pp;
    }

    public PrincipalProxy getPrincipal() {
        return principal;
    }
}
