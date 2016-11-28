/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions.books;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;
import facade.PdfFileFacadeLocal;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import models.PdfFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDStream;
import utils.pdf.PDFTextAnnotator;

/**
 *
 * @author sergio
 */
public class ShowPDFAction extends ActionSupport{
    
    @Inject
    private PdfFileFacadeLocal pdfsFacade;
    private Long idPdf;
    private String highlight;
    private InputStream inputStream;
   
    public Long getIdPdf() {
        return idPdf;
    }

    public void setIdPdf(Long idPdf) {
        this.idPdf = idPdf;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }
    
    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    
    private void highlightContent() throws IOException, Exception{
        ByteArrayOutputStream output = new ByteArrayOutputStream(); 
        PDDocument document = PDDocument.load(inputStream);
        PDFTextAnnotator pdfAnnotator = new PDFTextAnnotator("UTF-8");
        pdfAnnotator.setLineSeparator(" ");
        pdfAnnotator.initialize(document);
        pdfAnnotator.highlight(document, highlight);
        document.save(output);
        document.close();
        inputStream = new ByteArrayInputStream(output.toByteArray());
    }
    
    public String execute() throws Exception {
        PdfFile pdf = pdfsFacade.find(idPdf);
        inputStream = pdf.getContent().getBinaryStream();
        if(highlight != null) highlightContent();
        return SUCCESS;
    }
}
