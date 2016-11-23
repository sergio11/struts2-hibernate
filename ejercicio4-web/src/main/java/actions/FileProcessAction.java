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
import org.apache.commons.io.FileUtils;

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
    
    protected String uploadFile(String folderTargetKey) throws IOException{
        String folder = (String) servletContext.getAttribute(folderTargetKey);
        String destPath = servletContext.getRealPath("") + File.separator +  folder;
        Logger.getLogger(FileProcessAction.class.getName()).log(Level.INFO, "DEST PATH: " + destPath);
        File destFile = new File(destPath, uploadFileName);
        Logger.getLogger(FileProcessAction.class.getName()).log(Level.INFO, "DEST File: " + destFile);
        FileUtils.copyFile(upload, destFile);
        return folder + File.separator + uploadFileName;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
