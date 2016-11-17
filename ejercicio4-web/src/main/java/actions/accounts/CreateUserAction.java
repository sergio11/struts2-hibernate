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
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;

/**
 *
 * @author sergio
 */
public class CreateUserAction extends ActionSupport implements ServletContextAware {
    
    @Inject
    private UserFacadeLocal userFacade;
    private ServletContext servletContext;
    private File upload;
    private String uploadFileName;
    private String uploadContentType;
    private User user = new User();
    private Boolean userNameExists;

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
    
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
                String folder = (String) servletContext.getAttribute("images_profile_folder");
                String destPath = servletContext.getRealPath("") + File.separator +  folder;
                File destFile = new File(destPath, uploadFileName);
                FileUtils.copyFile(upload, destFile);
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

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
