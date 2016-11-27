/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.upload;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialBlob;
import models.PdfFile;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author sergio
 */
public class UploadPdfFileStrategy implements UploadStrategy<PdfFile> {
    @Override
    public PdfFile upload(File file, String fileName) throws IOException{
        PdfFile pdf = null;
        try {
            byte[] content = IOUtils.toByteArray(file.toURI());
            pdf = new PdfFile(fileName, new SerialBlob(content));
        } catch (SQLException ex) {
            Logger.getLogger(UploadPdfFileStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pdf;
    }
}
