/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.upload;

import java.io.File;
import java.io.IOException;
import models.PdfFile;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author sergio
 */
public class UploadPdfFileStrategy implements UploadStrategy<PdfFile> {
    @Override
    public PdfFile upload(File file, String fileName) throws IOException {
       byte[] content = IOUtils.toByteArray(file.toURI());
       return new PdfFile(fileName, content);
    }
}
