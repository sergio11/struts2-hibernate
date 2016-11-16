/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions.accounts;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;
import facade.UserFacadeLocal;
import models.User;

/**
 *
 * @author sergio
 */
public class CreateUserAction extends ActionSupport {
    
    @Inject
    private UserFacadeLocal userFacade;
    private User user = new User();
    private Boolean userNameExists;

    public Boolean getUserNameExists() {
        userNameExists = userFacade.exists(user.getUserName());
        return userNameExists;
    }
   
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String execute() throws Exception {
        userFacade.create(user);
        addActionMessage(getText("success.user.create"));
        return SUCCESS;
    }
}
