/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions.accounts;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;
import facade.GroupFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Group;

/**
 *
 * @author sergio
 */
public class PreCreateUserAction extends ActionSupport{
    
    @Inject
    private GroupFacadeLocal groupFacade;
    
    private List<Group> allGroups = new ArrayList<>();

    public List<Group> getAllGroups() {
        return allGroups;
    }
    
    public String execute() throws Exception {
        Logger.getLogger(PreCreateUserAction.class.getName()).log(Level.INFO, "Obteniendo grupos");
        allGroups = groupFacade.findAll();
        Logger.getLogger(PreCreateUserAction.class.getName()).log(Level.INFO, "Número de grupos: " + allGroups.size());
        return SUCCESS;
    }
}
