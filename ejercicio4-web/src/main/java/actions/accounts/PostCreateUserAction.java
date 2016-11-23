/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions.accounts;

import actions.FileProcessAction;
import com.google.inject.Inject;
import facade.UserFacadeLocal;
import models.User;
import java.io.IOException;

/**
 *
 * @author sergio
 */
public class PostCreateUserAction extends FileProcessAction {
    
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
        try {
            if(upload != null){
                uploadFile("images_profile_folder");
                user.setProfileImage(uploadFileName);
            }
            userFacade.create(user);
            addActionMessage(getText("success.user.create"));
            return SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
    }
}
