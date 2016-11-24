/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import org.apache.struts2.util.ServletContextAware;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import java.net.URI;
import java.util.Calendar;

/**
 *
 * @author sergio
 */
public abstract class FileProcessAction extends ActionSupport implements ServletContextAware{
    
    protected ServletContext servletContext;
    protected File upload;
    protected String uploadFileName;
    protected String uploadContentType;
    
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
    
    private String getBaseUrl() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String scheme = request.getScheme() + "://";
        String serverName = request.getServerName();
        String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
        String contextPath = request.getContextPath();
        return scheme + serverName + serverPort + contextPath;
     }
    
    protected URI uploadFile(String folderTargetKey) throws IOException{
        //String folder = (String) servletContext.getAttribute(folderTargetKey);
        String folder = "excerpt";
        String extension = "";
        if (uploadFileName != null && !uploadFileName.equals("")) {
            extension = uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length());
        }

        long longName = Calendar.getInstance().getTimeInMillis(); //date in miliseconds
        String newFileName = longName + extension;
        String destPath = servletContext.getRealPath("") + File.separator +  folder;
        Logger.getLogger(FileProcessAction.class.getName()).log(Level.INFO, "DEST PATH: " + destPath);
        File destFile = new File(destPath, newFileName);
        Logger.getLogger(FileProcessAction.class.getName()).log(Level.INFO, "DEST File: " + destFile);
        FileUtils.copyFile(upload, destFile);
        return URI.create(getBaseUrl() + '/' + folder + '/' + newFileName);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
